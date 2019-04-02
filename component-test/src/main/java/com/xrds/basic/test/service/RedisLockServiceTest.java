/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.test.service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.kunpu.frameworks.distributed.lock.RedisLockService;
import com.xrds.basic.test.BaseTest;

/**
 * 
 * @author liukai
 * @version $Id: RedisLockServiceTest.java, v 0.1 2017年6月23日 下午1:45:56 liukai Exp $
 */
public class RedisLockServiceTest extends BaseTest {
  @Autowired
  private RedisLockService redisLockService;

  @Test
  public void lockTest() {
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);
    for (int i = 0; i < 100000; i++) {
      fixedThreadPool.execute(new RedisLockThread("getToken_001", "tokenValue_001"));
    }
    try {
      Thread.sleep(60 * 60 * 1000l);
    } catch (InterruptedException e) {
      LOGGER.error("", e);
    }
  }

  class RedisLockThread implements Runnable {
    private String key;
    private String token;

    public RedisLockThread() {};

    public RedisLockThread(String key, String token) {
      this.key = key;
      this.token = token;
    }

    @Override
    public void run() {

      if (!redisLockService.lock(key)) {
        LOGGER.info("key[{}]拿锁失败", key);
      }

      try {
        // 线程线程暂停
        LOGGER.info("线程[{}],id[{}]业务执行", Thread.currentThread().getName(), Thread.currentThread()
            .getId());
        String tokenString = redisLockService.getKeyValue(token);
        if (StringUtils.isNotBlank(tokenString)) {
          LOGGER.info("缓存中token[{}]", tokenString);
        } else {
          String refreshTokenString = UUID.randomUUID().toString();
          LOGGER.info("设置 token[{}]", refreshTokenString);
          redisLockService.setKeyValue(token, refreshTokenString);
          refreshTokenString = redisLockService.getKeyValue(token);
          LOGGER.info("刷新后第一次获取 token[{}]", refreshTokenString);
        }
        LOGGER.info("线程[{}],id[{}]业务执行结束", Thread.currentThread().getName(), Thread.currentThread()
            .getId());
      } catch (Exception e) {
        LOGGER.error("加锁异常", e);
      } finally {
        redisLockService.unlock(key);
        LOGGER.error("释放锁[{}]", key);
      }

    }
  }


}

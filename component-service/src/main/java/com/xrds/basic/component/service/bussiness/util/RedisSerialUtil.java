/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.bussiness.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import com.kunpu.frameworks.commons.enums.APPCodeEnum;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.xrds.basic.component.common.constants.CommonApplicationConstants.SequenceConstants;
import com.xrds.basic.component.common.constants.CommonApplicationConstants.SequencePrefix;
import com.xrds.basic.component.common.util.StringBuilderUtil;

/**
 * 
 * @author liukai
 * @version $Id: RedisSerialUtil.java, v 0.1 2017年7月7日 下午12:05:04 liukai Exp $
 */
@Component("redisSerialUtil")
public class RedisSerialUtil {

  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_SERVICE.getLogName());

  @Autowired
  private RedisTemplate<Object, Object> redisTemplate;
  private RedisAtomicLong commonRedisAtomicLong;

  @PostConstruct
  private void init() {
    this.getCommonRedisAtomicLong();
  }

  /**
   * 获取通用序列号
   *
   * @return
   */
  public String getCommonRedisAtomicLong() {
    if (commonRedisAtomicLong == null) {
      commonRedisAtomicLong = initRedisAtomicLong(SequencePrefix.COMMON_PREFIX);
    }
    return this.getSerialNoByRedis(commonRedisAtomicLong, SequencePrefix.COMMON_PREFIX,
        SequenceConstants.SEQUENCE_LENGTH_14);
  }

  private RedisAtomicLong initRedisAtomicLong(String prefix) {
    try {
      String prefixString =
          SequencePrefix.REDIS_SERIAL_KEY + APPCodeEnum.NOT_SPECIFIED.sysId + prefix;
      return new RedisAtomicLong(prefixString, redisTemplate.getConnectionFactory());
    } catch (Exception e) {
      // LOGGER.error(BfvpErrorCode.ERROR_801.getMsg(), e);
    }
    return null;
  }

  private String getSerialNoByRedis(RedisAtomicLong redisAtomicLong, String prefix, int length) {
    if (redisAtomicLong == null) {
      return null;
    }
    try {
      Long seqIdLong = redisAtomicLong.incrementAndGet();
      return StringBuilderUtil.buildSerialNo(prefix, seqIdLong.toString(), length);
    } catch (Exception e) {
      LOGGER.error("", e);
    }
    return null;
  }
}

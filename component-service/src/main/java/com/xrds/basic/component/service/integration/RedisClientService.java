/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.integration;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.net.RPCException;

/**
 * 
 * @author liukai
 * @version $Id: RedisClientService.java, v 0.1 2017年7月14日 上午9:37:24 liukai Exp $
 */
public interface RedisClientService {
  /**
   * 存值
   * 
   * @param key
   * @param value
   * @param expireTime
   * @return
   * @throws RPCException
   * @throws CommonException
   */
  public void setValue(String key, Object value, Long expireTime) throws BusinessException;

  /**
   * 
   * 
   * @param key
   * @param value
   * @param expireTime
   * @param timeUnit
   * @return
   * @throws RPCException
   */
  public void setValue(final String key, final Object value, final Long expireTime,
      TimeUnit timeUnit) throws BusinessException;

  /**
   * 
   * 
   * @param key
   * @param value
   * @param expireTime
   * @param timeUnit
   * @return
   * @throws RPCException
   */
  public void setValue(final String key, final Object value) throws BusinessException;

  /**
   * 
   * 
   * @param key
   * @return
   * @throws RPCException
   */
  public Object getValue(String key) throws BusinessException;

  /**
   * 
   * 
   * @param key
   * @return
   * @throws RPCException
   */
  public void remove(String key) throws BusinessException;

  /**
   * 初始化自增
   * 
   * @param key
   * @return
   * @throws RPCException
   */
  public RedisAtomicLong initRedisAtomicLong(String key) throws BusinessException;


}

/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.intergration.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

import com.kunpu.frameworks.commons.exception.net.RPCException;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.intergration.RedisClientIntegration;

/**
 * 
 * @author liukai
 * @version $Id: RedisClientServiceImpl.java, v 0.1 2017年7月13日 上午9:14:14 liukai Exp $
 */
@Component("redisClientIntegration")
public class RedisClientIntegrationImpl implements RedisClientIntegration {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  /**
   * 
   * @see com.xrds.basic.cip.intergration.RedisClientService#setValue(java.lang.String,
   *      java.lang.String, java.lang.Long)
   */
  @Override
  public void setValue(final String key, final Object value, final Long expireTime)
      throws RPCException {
    try {
      BoundValueOperations<String, Object> boundValue = redisTemplate.boundValueOps(key);
      boundValue.set(value);
      boundValue.expire(expireTime, TimeUnit.MINUTES);
    } catch (Exception e) {
      throw new RPCException(BusErrorCode.ERROR_300, e);
    }
  }

  /**
   * 
   * @see com.xrds.basic.cip.intergration.RedisClientService#setValue(java.lang.String,
   *      java.lang.String, java.lang.Long, java.util.concurrent.TimeUnit)
   */
  @Override
  public void setValue(final String key, final Object value, final Long expireTime,
      TimeUnit timeUnit) throws RPCException {
    try {
      BoundValueOperations<String, Object> boundValue = redisTemplate.boundValueOps(key);
      boundValue.set(value);
      boundValue.expire(expireTime, timeUnit);
    } catch (Exception e) {
      throw new RPCException(BusErrorCode.ERROR_300, e);
    }

  }


  @Override
  public Object getValue(final String key) throws RPCException {
    try {
      BoundValueOperations<String, Object> boundValue = redisTemplate.boundValueOps(key);
      // Object objectT = redisTemplate.opsForValue().get(key);
      // Object object = boundValue.get();

      return boundValue != null ? boundValue.get() : null;
    } catch (Exception e) {
      throw new RPCException(BusErrorCode.ERROR_300, e);
    }
  }

  @Override
  public void remove(final String key) throws RPCException {
    try {
      redisTemplate.delete(key);
    } catch (Exception e) {
      throw new RPCException(BusErrorCode.ERROR_300, e);
    }
  }

  @Override
  public RedisAtomicLong initRedisAtomicLong(String key) throws RPCException {
    try {
      RedisAtomicLong redisAtomicLong =
          new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
      return redisAtomicLong;
    } catch (Exception e) {
      throw new RPCException(BusErrorCode.ERROR_300, e);
    }
  }

  @Override
  public void setValue(String key, Object value) throws RPCException {
    try {
      BoundValueOperations<String, Object> boundValue = redisTemplate.boundValueOps(key);
      boundValue.set(value);
    } catch (Exception e) {
      throw new RPCException(BusErrorCode.ERROR_300, e);
    }
  }

}

/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.integration.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.net.RPCException;
import com.xrds.basic.component.intergration.RedisClientIntegration;
import com.xrds.basic.component.service.integration.RedisClientService;

/**
 * 
 * @author liukai
 * @version $Id: RedisClientServiceImpl.java, v 0.1 2017年7月14日 上午10:03:06 liukai Exp $
 */
@Service("redisClientService")
public class RedisClientServiceImpl implements RedisClientService {

  @Autowired
  private RedisClientIntegration redisClientIntegration;

  /**
   * @throws CommonException
   * @see com.xrds.basic.cip.service.integration.RedisClientService#setValue(java.lang.String,
   *      java.lang.String, java.lang.Long)
   */
  @Override
  public void setValue(String key, Object value, Long expireTime) throws BusinessException {
    try {
      redisClientIntegration.setValue(key, value, expireTime);
    } catch (RPCException e) {
      throw new BusinessException(e.getErrorCodeObj(), e);
    }
  }

  /**
   * @see com.xrds.basic.cip.service.integration.RedisClientService#setValue(java.lang.String,
   *      java.lang.String, java.lang.Long, java.util.concurrent.TimeUnit)
   */
  @Override
  public void setValue(String key, Object value, Long expireTime, TimeUnit timeUnit)
      throws BusinessException {
    try {
      redisClientIntegration.setValue(key, value, expireTime, timeUnit);
    } catch (RPCException e) {
      throw new BusinessException(e.getErrorCodeObj(), e);
    }
  }

  /**
   * @see com.xrds.basic.cip.service.integration.RedisClientService#getValue(java.lang.String)
   */
  @Override
  public Object getValue(String key) throws BusinessException {
    try {
      return redisClientIntegration.getValue(key);
    } catch (RPCException e) {
      throw new BusinessException(e.getErrorCodeObj(), e);
    }
  }

  /**
   * @see com.xrds.basic.cip.service.integration.RedisClientService#remove(java.lang.String)
   */
  @Override
  public void remove(String key) throws BusinessException {
    try {
      redisClientIntegration.remove(key);
    } catch (RPCException e) {
      throw new BusinessException(e.getErrorCodeObj(), e);
    }
  }

  /**
   * 
   * @see com.xrds.basic.cip.service.integration.RedisClientService#initRedisAtomicLong(java.lang.String)
   */
  @Override
  public RedisAtomicLong initRedisAtomicLong(String key) throws BusinessException {
    try {
      return redisClientIntegration.initRedisAtomicLong(key);
    } catch (RPCException e) {
      throw new BusinessException(e.getErrorCodeObj(), e);
    }
  }

  @Override
  public void setValue(String key, Object value) throws BusinessException {
    try {
      redisClientIntegration.setValue(key, value);
    } catch (RPCException e) {
      throw new BusinessException(e.getErrorCodeObj(), e);
    }
  }

}

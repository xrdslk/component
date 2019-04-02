/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.intergration;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import com.kunpu.frameworks.commons.exception.net.RPCException;

/**
 * 
 * @author liukai
 * @version $Id: RedisClientService.java, v 0.1 2017年7月13日 上午9:12:18 liukai Exp $
 */
public interface RedisClientIntegration {
  /**
   * 存值
   * 
   * @param key
   * @param value
   * @param expireTime
   * @return
   * @throws RPCException
   */
  public void setValue(String key, Object value, Long expireTime) throws RPCException;

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
      TimeUnit timeUnit) throws RPCException;

  /**
   * 
   * 
   * @param key
   * @return
   * @throws RPCException
   */
  public Object getValue(String key) throws RPCException;

  /**
   * 
   * 
   * @param key
   * @return
   * @throws RPCException
   */
  public void remove(String key) throws RPCException;

  /**
   * 初始化自增
   * 
   * @param key
   * @return
   * @throws RPCException
   */
  public RedisAtomicLong initRedisAtomicLong(String key) throws RPCException;

  /**
   * 封装值
   * 
   * @param key
   * @param value
   * @throws RPCException
   */
  public void setValue(String key, Object value) throws RPCException;


}

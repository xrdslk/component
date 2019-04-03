/**
 * 
 * 坤普 Copyright (c) 2016-2019 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.test.redis;

import org.junit.Test;

import com.xrds.basic.test.BaseTest;

/**
 * 
 * @author GSZY
 * @version $Id: RedisTest.java, v 0.1 2019年4月3日 下午5:52:40 GSZY Exp $
 */
public class RedisTest extends BaseTest {



  @Test
  public void testRedis() {

    long appId = 10000;
    /**
     * 自定义配置
     */
    // GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
    // JedisSentinelPool sentinelPool =RedisSentinelBuilder(appId)
    // ClientBuilder.redisSentinel(appId).setConnectionTimeout(1000).setSoTimeout(1000)
    // .setPoolConfig(poolConfig).build();
    // Jedis jedis = null;
    // try {
    // jedis = sentinelPool.getResource();
    // System.out.println(jedis.get("key1"));
    // // 1.字符串value
    // jedis.set("key1", "1");
    //
    // } catch (Exception e) {
    // e.printStackTrace();
    // } finally {
    // if (jedis != null) jedis.close();
    // }



  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {

  }

}

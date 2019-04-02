package com.xrds.basic.component.common.config;

/**
 * MQ队列配置信息
 * 
 * @author xuwei
 *
 */
public class RabbitConfig {

  /**
   * payment服务交换机
   */
  public static final String SERVICE_SWITCHBOARD = "payment.x.notify";

  /**
   * 绑卡队列
   */
  public static final String BINDCARD = "notify.rk.register.card";

}

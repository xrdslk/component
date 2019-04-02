/**
 * 
 * 坤普 Copyright (c) 2016-2018 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.test.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;

import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.middleware.rabbit.RabbitMessage;
import com.xrds.basic.component.common.config.RabbitConfig;
import com.xrds.basic.test.BaseTest;

/**
 * 
 * @author yuyangzhi
 * @version $Id: MqTemplateTest.java, v 0.1 2018年6月26日 下午3:08:23 yuyangzhi Exp $
 */
public class MqTemplateTest extends BaseTest {

  private static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_BIZ
      .getLogName());

  @Resource(name = "providerMqTemplate")
  private AmqpTemplate providerMqTemplate;

  @Test
  public void sendMq() {
    Map<String, Object> param = new HashMap<String, Object>();
    RabbitMessage message = new RabbitMessage();
    message.setReceivedExchange(RabbitConfig.SERVICE_SWITCHBOARD);
    message.setReceivedRoutingKey(RabbitConfig.BINDCARD);
    message.setBody(param);
    param.put("test", "test");
    providerMqTemplate.send(message.getReceivedExchange(), message.getReceivedRoutingKey(),
        message.createMessage());
    LOGGER.info("发送MQ通知  param=[{}]", param);
    System.out.println(param);
  }

}

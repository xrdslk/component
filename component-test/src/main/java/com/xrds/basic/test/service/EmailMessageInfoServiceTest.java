/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.test.service;

import javax.annotation.Resource;

import org.junit.Test;

import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.xrds.basic.component.common.enums.DefaultLevel;
import com.xrds.basic.component.service.bussiness.EmailMessageBusService;
import com.xrds.basic.test.BaseTest;

/**
 * 
 * @author liukai
 * @version $Id: EmailMessageInfoServiceTest.java, v 0.1 2017年11月26日 下午2:06:04 liukai Exp $
 */
public class EmailMessageInfoServiceTest extends BaseTest {

  @Resource(name = "emailMessageBusService")
  private EmailMessageBusService emailMessageBusService;

  @Test
  public void testEmailSendMessage() {
    try {
      Boolean result =
          emailMessageBusService.sendAndSaveMessageByConfig(DefaultLevel.HIGH.getCode(), "邮件测试内容");
      System.out.println(result);
    } catch (BusinessException e) {
      LOGGER.error("", e);
    }
  }

}

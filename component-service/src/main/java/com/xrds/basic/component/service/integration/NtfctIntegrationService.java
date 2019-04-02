/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.integration;

import com.kunpu.frameworks.commons.exception.CommonException;
import com.xrds.basic.component.service.integration.bean.EmailSendBean;
import com.xrds.basic.component.service.integration.bean.SmsSendBean;

/**
 * 
 * @author liukai
 * @version $Id: NtfctIntegrationService.java, v 0.1 2017年7月19日 下午4:56:08 liukai Exp $
 */
public interface NtfctIntegrationService {

  /**
   * 发送短信
   * 
   * @param smsSendBean
   * @return
   * @throws CommonException
   */
  public void sendSms(SmsSendBean smsSendBean) throws CommonException;


  /**
   * 发送邮件
   * 
   * @param emailSendBean
   * @throws CommonException
   */
  public void sendEmail(EmailSendBean emailSendBean) throws CommonException;
}

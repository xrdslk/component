/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.intergration;

import com.kunpu.frameworks.commons.exception.net.RPCException;
import com.kunpu.ntfct.facade.bean.SendEmailReq;
import com.kunpu.ntfct.facade.bean.SendEmailRes;
import com.kunpu.ntfct.facade.bean.SendRequest;
import com.kunpu.ntfct.facade.bean.SendResponse;

/**
 * 
 * @author liukai
 * @version $Id: NtfctIntegration.java, v 0.1 2017年7月19日 上午9:53:46 liukai Exp $
 */
public interface NtfctIntegration {
  /**
   * 发送短信息
   * 
   * @param request
   * @return
   * @throws RPCException
   */
  public SendResponse sendSMS(SendRequest request) throws RPCException;

  /**
   * 发送邮件信息
   * 
   * @param request
   * @return
   * @throws RPCException
   */
  public SendEmailRes sendEmail(SendEmailReq request) throws RPCException;
}

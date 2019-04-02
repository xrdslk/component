/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.intergration.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kunpu.frameworks.commons.code.CommonErrorCode;
import com.kunpu.frameworks.commons.exception.net.RPCException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.ntfct.facade.SendEmailFacade;
import com.kunpu.ntfct.facade.SendFacade;
import com.kunpu.ntfct.facade.bean.SendEmailReq;
import com.kunpu.ntfct.facade.bean.SendEmailRes;
import com.kunpu.ntfct.facade.bean.SendRequest;
import com.kunpu.ntfct.facade.bean.SendResponse;
import com.xrds.basic.component.intergration.NtfctIntegration;

/**
 * 通知
 * 
 * @author liukai
 * @version $Id: NtfctIntegrationImpl.java, v 0.1 2017年7月19日 上午9:58:27 liukai Exp $
 */
@Component("ntfctIntegration")
public class NtfctIntegrationImpl implements NtfctIntegration {

  private static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_INTEGRATION.getLogName());
  @Autowired
  private SendFacade sendFacade;

  @Autowired
  private SendEmailFacade sendEmailFacade;

  /**
   * 
   * @throws RPCException
   * @see com.xrds.basic.cip.integration.NtfctIntegration#sendSMS(com.xrds.basic.ntfct.facade.bean.SendRequest)
   */
  @Override
  public SendResponse sendSMS(SendRequest request) throws RPCException {
    try {
      return sendFacade.send(request);
    } catch (Exception e) {
      LOGGER.error("NtfctIntegration.sendSMS Exception req={}", request, e);
      throw new RPCException(CommonErrorCode.COMMON_300, e);
    }
  }


  /**
   * 
   * @throws RPCException
   * @see com.xrds.basic.cip.integration.NtfctIntegration#sendEmail(com.xrds.basic.ntfct.facade.bean.SendEmailReq)
   */
  @Override
  public SendEmailRes sendEmail(SendEmailReq request) throws RPCException {
    try {
      return sendEmailFacade.sendEmail(request);
    } catch (Exception e) {
      LOGGER.error("NtfctIntegration.sendEmail Exception req={}", request, e);
      throw new RPCException(CommonErrorCode.COMMON_300, e);
    }
  }
}

/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.integration.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kunpu.frameworks.commons.enums.APPCodeEnum;
import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.net.RPCException;
import com.kunpu.frameworks.commons.utils.BeanCopierUtils;
import com.kunpu.ntfct.facade.bean.SendEmailReq;
import com.kunpu.ntfct.facade.bean.SendEmailRes;
import com.kunpu.ntfct.facade.bean.SendRequest;
import com.kunpu.ntfct.facade.bean.SendResponse;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.common.code.BusOutRespCode;
import com.xrds.basic.component.common.util.StringBuilderUtil;
import com.xrds.basic.component.intergration.NtfctIntegration;
import com.xrds.basic.component.service.bussiness.SequenceBusService;
import com.xrds.basic.component.service.integration.NtfctIntegrationService;
import com.xrds.basic.component.service.integration.bean.EmailSendBean;
import com.xrds.basic.component.service.integration.bean.SmsSendBean;

/**
 * 
 * @author liukai
 * @version $Id: NtfctIntegrationServiceImpl.java, v 0.1 2017年7月19日 下午5:06:06 liukai Exp $
 */
@Component("ntfctIntegrationService")
public class NtfctIntegrationServiceImpl implements NtfctIntegrationService {

  @Autowired
  private NtfctIntegration ntfctIntegration;
  @Autowired
  private SequenceBusService sequenceBusService;

  public static String fromEmail = "ntfct@gszygroup.com";

  /**
   * 
   * @see com.xrds.basic.component.service.integration.NtfctIntegrationService#sendSms(com.xrds.basic.component.service.integration.bean.SmsSendBean)
   */
  @Override
  public void sendSms(SmsSendBean smsSendBean) throws RPCException, BusinessException {
    SendRequest sendRequest = new SendRequest();
    sendRequest.setEventId(smsSendBean.getEventId());
    sendRequest.setParamMap(smsSendBean.getParamMap());
    sendRequest.setPlatformChannel(smsSendBean.getPlatformChannel());

    // 目前只支持单条发送
    if (!ArrayUtils.isEmpty(smsSendBean.getPhoneNum()) || smsSendBean.getPhoneNum().length > 1) {
      for (String aMobile : smsSendBean.getPhoneNum()) {
        if (StringUtils.isBlank(aMobile)) {
          continue;
        }
        sendRequest.setPhoneNum(aMobile);
      }
    }

    sendRequest.setSysId(APPCodeEnum.KPFN_FMSP.sysId);
    sendRequest.setReqSerial(sequenceBusService.getCommonSeq());
    SendResponse response = ntfctIntegration.sendSMS(sendRequest);
    // 如果返回NULL
    if (response == null || StringUtils.isBlank(response.getCode())) {
      throw new BusinessException(BusErrorCode.ERROR_900);
    }
    if (BusErrorCode.SUCCESS_000000.getCode().equals(response.getCode())) {
      return;
    }
    checkErrorScenario(response, 0, sendRequest);
  }

  /**
   * 错误场景处理
   * 
   * @param response
   * @throws RPCException
   * @throws BusinessException
   */
  private void checkErrorScenario(SendResponse response, int retryCount, SendRequest smsRequest)
      throws RPCException, BusinessException {

    if (BusOutRespCode.SmsRespCode.SMS_ERROR_PARAM_RETRY.containsKey(response.getCode())) {
      // if (retryCount < 3) {
      // SmsResponse responseT = smsCenterIntegration.sendSms(smsRequest);
      // int retryTemp = retryCount;
      // retryTemp++;
      // this.checkErrorScenario(responseT, retryTemp, smsRequest);
      // }
      throw new BusinessException(response.getCode(), response.getMsg());
    }

    if (BusOutRespCode.SmsRespCode.SMS_PARAM_ERROR_UNRETRY.containsKey(response.getCode())) {
      throw new BusinessException(response.getCode(), response.getMsg());
    } else if (BusOutRespCode.SmsRespCode.SMS_INTER_ERROR_PARAM_UNRETRY.containsKey(response
        .getCode())) {

      throw new BusinessException(response.getCode(), response.getMsg());
    } else {
      throw new BusinessException(response.getCode(), response.getMsg());
    }
  }

  /**
   * 
   * @see com.xrds.basic.component.service.integration.NtfctIntegrationService#sendEmail(com.xrds.basic.component.service.integration.bean.EmailSendBean)
   */
  @Override
  public void sendEmail(EmailSendBean emailSendBean) throws CommonException {
    emailSendBean.setSysId(APPCodeEnum.KPFN_FMSP.sysId);
    emailSendBean.setReqSerial(sequenceBusService.getCommonSeq());
    SendEmailReq sendEmailReq = BeanCopierUtils.copyOne2One(emailSendBean, SendEmailReq.class);
    sendEmailReq.setTo(fillReceivers(emailSendBean.getTo()));
    sendEmailReq.setBcc(fillReceivers(emailSendBean.getBcc()));
    sendEmailReq.setCc(fillReceivers(emailSendBean.getCc()));
    sendEmailReq.setContent(emailSendBean.getParams().get("content"));
    SendEmailRes sendEmailRes = ntfctIntegration.sendEmail(sendEmailReq);
    // 如果返回NULL
    if (sendEmailRes == null || StringUtils.isBlank(sendEmailRes.getCode())) {
      throw new BusinessException(BusErrorCode.ERROR_900);
    }
    if (BusErrorCode.SUCCESS_000000.getCode().equals(sendEmailRes.getCode())) {
      return;
    }
    if (BusOutRespCode.SmsRespCode.SMS_ERROR_PARAM_RETRY.containsKey(sendEmailRes.getCode())) {
      throw new BusinessException(sendEmailRes.getCode(), sendEmailRes.getMsg());
    }
    if (BusOutRespCode.SmsRespCode.SMS_PARAM_ERROR_UNRETRY.containsKey(sendEmailRes.getCode())) {
      throw new BusinessException(sendEmailRes.getCode(), sendEmailRes.getMsg());
    } else if (BusOutRespCode.SmsRespCode.SMS_INTER_ERROR_PARAM_UNRETRY.containsKey(sendEmailRes
        .getCode())) {
      throw new BusinessException(sendEmailRes.getCode(), sendEmailRes.getMsg());
    } else {
      throw new BusinessException(sendEmailRes.getCode(), sendEmailRes.getMsg());
    }
  }

  /**
   * 
   * 
   * @param receiverList
   * @return
   */
  private String fillReceivers(String[] receiverList) {
    if (receiverList == null || receiverList.length < 0) return null;
    StringBuilder receivers = new StringBuilder();
    for (String receiver : receiverList) {
      if (StringUtils.isBlank(receiver)) continue;
      receivers.append(receiver).append(StringBuilderUtil.SYMBOL_SEMICOLON);
    }
    if (receivers.toString().endsWith(StringBuilderUtil.SYMBOL_SEMICOLON)) {
      return receivers.toString().substring(0, receivers.toString().length() - 1);
    }
    return null;
  }
}

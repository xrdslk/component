/**
 * 
 */
package com.xrds.basic.component.service.bussiness.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.utils.BeanCopierUtils;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.common.constants.CommonApplicationConstants;
import com.xrds.basic.component.common.enums.DefaultLevel;
import com.xrds.basic.component.common.enums.MessageSendStatus;
import com.xrds.basic.component.common.util.ApplicationContextConfigUtil;
import com.xrds.basic.component.service.bussiness.SmsMessageBusService;
import com.xrds.basic.component.service.integration.NtfctIntegrationService;
import com.xrds.basic.component.service.integration.bean.SmsSendBean;
import com.xrds.basic.component.service.repository.SmsMessageInfoService;
import com.xrds.basic.component.service.repository.bean.MessageBaseInfo;
import com.xrds.basic.component.service.repository.bean.MessageReceiverInfo;
import com.xrds.basic.component.service.repository.bean.SmsMessageInfo;

/**
 * 
 * 
 * @author liukai
 * @version $Id: SmsMessageBusServiceImpl.java, v 0.1 2017年11月26日 下午1:47:43 liukai Exp $
 */
@Service("smsMessageBusService")
public class SmsMessageBusServiceImpl extends AbsMessageServiceImpl implements SmsMessageBusService {

  @Autowired
  private SmsMessageInfoService smsMessageInfoService;
  @Autowired
  private NtfctIntegrationService ntfctIntegrationService;


  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.MessageBusService#saveMessage(com.xrds.basic.component.service.repository.bean.MessageBaseInfo)
   */
  @Override
  public boolean saveMessage(MessageBaseInfo messageinfo) {
    try {
      final SmsMessageInfo smsMessageInfo = this.getSmsMessageInfoObj(messageinfo);
      transactionTemplate.execute(new TransactionCallback<Integer>() {
        @Override
        public Integer doInTransaction(TransactionStatus status) {
          LOGGER.info("call {} value =[{}]",
              ToStringBuilder.reflectionToString(smsMessageInfo, ToStringStyle.SHORT_PREFIX_STYLE));
          int emailMessageResult = saveSmsMesssageInfo(smsMessageInfo);
          if (emailMessageResult < 1) {
            LOGGER.dbError("添加短信信息失败！消息信息[{}]", smsMessageInfo);
            throw new DBException(BusErrorCode.ERROR_201);
          }
          return emailMessageResult;
        }
      });
      return true;
    } catch (Exception e) {
      LOGGER.error("保存短信异常，异常信息[{}]", e.getMessage(), e);
    }
    return false;
  }

  /**
   * 
   * 
   * @param smsMessageInfo
   * @return
   */
  public int saveSmsMesssageInfo(SmsMessageInfo smsMessageInfo) {
    // 保存基本消息信息
    MessageBaseInfo baseInfo = BeanCopierUtils.copyOne2One(smsMessageInfo, MessageBaseInfo.class);
    saveBaseMessage(baseInfo);
    // 保存接收者信息
    saveMessageReceiverList(smsMessageInfo.getMessageReceiverList(), baseInfo.getId());
    // 保存短信消息信息
    smsMessageInfo.setMessageId(baseInfo.getId());
    int emailMessageResult = smsMessageInfoService.addData(smsMessageInfo);
    return emailMessageResult;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.MessageBusService#sendMessage(com.xrds.basic.component.service.repository.bean.MessageBaseInfo)
   */
  @Override
  public boolean sendMessage(MessageBaseInfo messageinfo) {
    try {
      ntfctIntegrationService.sendSms(this.fillSmsSendBean(this.getSmsMessageInfoObj(messageinfo)));
      return true;
    } catch (Exception e) {
      LOGGER.error("发送短信异常，发送消息体[{}],异常信息[{}]", messageinfo, e.getMessage(), e);
    }
    return false;
  }



  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.MessageBusService#sendAndSaveMessage(com.xrds.basic.component.service.repository.bean.MessageBaseInfo)
   */
  @Override
  public boolean sendAndSaveMessage(MessageBaseInfo messageinfo) {
    if (this.sendMessage(messageinfo)) {
      return true;
    }
    // 发送失败保存消息信息
    if (!this.saveMessage(messageinfo)) {
      LOGGER.error("保存短信消息失败，消息体[{}]", messageinfo);
    }
    return false;
  }

  /**
   * 
   * @see SmsMessageBusService#sendAndSaveMessageByConfig(String, String, String, String[], String)
   */
  @Override
  public boolean sendAndSaveMessageByConfig(String configKey, String content, String eventId,
      String[] phoneNum, String platformChannel) {
    return this.sendAndSaveMessageByConfigLevel(DefaultLevel.HIGH.getCode(), configKey, content,
        eventId, phoneNum, platformChannel);
  }


  /**
   * 转换sms消息对象
   * 
   * @param messageinfo
   * @return
   * @throws BusinessException
   */
  private SmsMessageInfo getSmsMessageInfoObj(MessageBaseInfo messageinfo) throws BusinessException {
    try {
      return (SmsMessageInfo) messageinfo;
    } catch (Exception e) {
      LOGGER.error("转换短信消息对象异常！消息[{}]", messageinfo, e);
      throw new BusinessException(BusErrorCode.ERROR_405, e);
    }
  }

  /**
   * 封装短信对象信息
   * 
   * @param emailMessage
   * @return
   */
  private SmsSendBean fillSmsSendBean(SmsMessageInfo smsMessage) {
    List<MessageReceiverInfo> messageReceiverList =
        messageReceiverService.queryListByMessageId(smsMessage.getMessageId());
    SmsSendBean smsSendBean = new SmsSendBean();
    String[] receivers = this.fillMessageReceiver(messageReceiverList);
    smsSendBean.setPhoneNum(receivers);
    if (StringUtils.isBlank(smsMessage.getBusinessId())) {
      smsSendBean.setBusinessId(UUID.randomUUID().toString());
    } else {
      smsSendBean.setBusinessId(smsMessage.getBusinessId());
    }
    Map<String, Object> paramMap = null;
    if (StringUtils.isNotBlank(smsMessage.getContent())) {
      paramMap = JSON.parseObject(smsMessage.getContent(), Map.class);
    } else {
      paramMap = new HashMap<String, Object>();
    }
    smsSendBean.setParamMap(paramMap);
    smsSendBean.setEventId(smsMessage.getEventId());
    return smsSendBean;
  }

  /**
   * 
   * 获取短信发送配置项信息
   * 
   * @param configKey 配置名称
   * @return
   */
  private SmsSendBean getSmsSendByConfig(String configKey) {
    String smsSendConfig = ApplicationContextConfigUtil.getString(configKey, null);
    if (StringUtils.isBlank(smsSendConfig)) {
      LOGGER.error("获取短信配置项信息为空，配置项[{}]", configKey);
      return null;
    }
    SmsSendBean smsSend = null;
    try {
      smsSend = JSON.parseObject(smsSendConfig, SmsSendBean.class);
    } catch (Exception e) {
      LOGGER.error("转换短信配置项信息异常，配置项名称[{}]", configKey, e);
      return null;
    }

    if (smsSend == null || StringUtils.isBlank(smsSend.getEventId())) {
      LOGGER.error("配置项信息为空，配置项名称[{}]", configKey);
      return null;
    }

    if (StringUtils.isBlank(smsSend.getConfigName())) {
      smsSend.setConfigName(configKey);
    }
    return smsSend;
  }

  /**
   * 根据配置项封装短信消息信息
   * 
   * @param emailSendBean
   * @return
   */
  private SmsMessageInfo fillSmsMessage(String noticeLevel, SmsSendBean smsSendBean) {
    SmsMessageInfo smsMessage = new SmsMessageInfo();
    smsMessage.setBusinessId(smsSendBean.getBusinessId());
    List<MessageReceiverInfo> messageReceivers = new ArrayList<MessageReceiverInfo>();
    if (!ArrayUtils.isEmpty(smsSendBean.getPhoneNum()) || smsSendBean.getPhoneNum().length > 1) {
      for (int i = 0; i < smsSendBean.getPhoneNum().length; i++) {
        if (StringUtils.isBlank(smsSendBean.getPhoneNum()[i])) {
          continue;
        }
        MessageReceiverInfo messageReceiver = new MessageReceiverInfo();
        messageReceiver.setReceiver(smsSendBean.getPhoneNum()[i]);
        messageReceivers.add(messageReceiver);
      }
    }
    smsMessage.setEventId(smsSendBean.getEventId());
    smsMessage.setMessageReceiverList(messageReceivers);
    Date currentTime = new Date();
    smsMessage.setUpdateTime(currentTime);
    smsMessage.setCreateTime(currentTime);
    smsMessage.setPreSendTime(currentTime);
    smsMessage.setSendTimes(1);
    smsMessage.setNoticeLevel(noticeLevel);
    smsMessage.setStatus(MessageSendStatus.UNDO.getCode());
    smsMessage.setMessageType(CommonApplicationConstants.MessageTypeContents.MESSAGE_SMS_TYPE);
    return smsMessage;
  }

  /**
   * 封装发件人信息
   * 
   * @param messageReceiverList
   * @return
   */
  private String[] fillMessageReceiver(List<MessageReceiverInfo> messageReceiverList) {
    String[] receivers = new String[messageReceiverList.size()];
    for (int i = 0; i < messageReceiverList.size(); i++) {
      MessageReceiverInfo messageReceiver = messageReceiverList.get(i);
      if (messageReceiver == null || StringUtils.isBlank(messageReceiver.getReceiver())) {
        continue;
      }
      receivers[i] = messageReceiver.getReceiver();
    }
    return receivers;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.MessageBusService#getMessageDetailInfo(java.math.BigInteger)
   */
  @Override
  public SmsMessageInfo getMessageDetailInfo(BigInteger messageId) throws DBException {
    return smsMessageInfoService.getSmsMessageByMessageId(messageId);
  }

  /**
   * 
   * @see SmsMessageBusService#sendAndSaveMessageByConfigLevel(String, String, String, String,
   *      String[], String)
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean sendAndSaveMessageByConfigLevel(String noticeLevel, String configKey,
      String content, String eventId, String[] phoneNum, String platformChannel) {
    SmsSendBean smsSendBean = this.getSmsSendByConfig(configKey);
    if (smsSendBean == null) {
      return false;
    }
    smsSendBean.setEventId(eventId);
    smsSendBean.setPhoneNum(phoneNum);
    smsSendBean.setPlatformChannel(platformChannel);
    Map<String, Object> paramMap = JSONObject.parseObject(content, Map.class);
    smsSendBean.setParamMap(paramMap);
    if (!sendSmsMessage(smsSendBean)) {
      SmsMessageInfo smsMessageInfo = this.fillSmsMessage(noticeLevel, smsSendBean);
      smsMessageInfo.setContent(content);
      if (!this.saveMessage(smsMessageInfo)) {
        LOGGER.error("保存SMS消息信息失败,配置项[{}]，消息体[{}]", content);
      }
    }
    return true;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.SmsMessageBusService#sendAndSaveMessageByPhone(java.lang.String,
   *      java.lang.String, java.lang.String[], java.lang.String)
   */
  @Override
  public boolean sendAndSaveMessageByPhone(String noticeLevel, String configKey, String[] phone,
      String content) {
    SmsSendBean smsSendBean = this.getSmsSendByConfig(configKey);
    if (smsSendBean == null) {
      return false;
    }
    Map<String, Object> paramMap = JSON.parseObject(content, Map.class);
    smsSendBean.setParamMap(paramMap);
    smsSendBean.setPhoneNum(phone);
    if (!sendSmsMessage(smsSendBean)) {
      SmsMessageInfo smsMessageInfo = this.fillSmsMessage(noticeLevel, smsSendBean);
      smsMessageInfo.setContent(content);
      if (!this.saveMessage(smsMessageInfo)) {
        LOGGER.error("保存SMS消息信息失败,配置项[{}]，消息体[{}]", content);
      }
    }
    return false;
  }


  /**
   * 发送短信
   * 
   * @param smsSendBean
   * @return
   */
  private boolean sendSmsMessage(SmsSendBean smsSendBean) {
    try {
      ntfctIntegrationService.sendSms(smsSendBean);
      return true;
    } catch (RpcException e) {
      LOGGER.error("发送短信异常，异常信息[{}],异常编码[{}]", e.getMessage(), e.getCode(), e);
    } catch (Exception e) {
      LOGGER.error("发送短信异常，异常信息[{}]", e.getMessage(), e);
    }
    return false;
  }

}

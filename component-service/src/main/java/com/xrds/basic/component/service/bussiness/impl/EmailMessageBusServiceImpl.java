package com.xrds.basic.component.service.bussiness.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.frameworks.commons.utils.BeanCopierUtils;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.common.constants.CommonApplicationConstants;
import com.xrds.basic.component.common.constants.ConfigCenterConstants;
import com.xrds.basic.component.common.enums.DefaultLevel;
import com.xrds.basic.component.common.enums.MessageSendStatus;
import com.xrds.basic.component.common.enums.MessageSendType;
import com.xrds.basic.component.common.util.ApplicationContextConfigUtil;
import com.xrds.basic.component.common.util.StringBuilderUtil;
import com.xrds.basic.component.service.bussiness.EmailMessageBusService;
import com.xrds.basic.component.service.integration.NtfctIntegrationService;
import com.xrds.basic.component.service.integration.bean.EmailSendBean;
import com.xrds.basic.component.service.repository.EmailMessageInfoService;
import com.xrds.basic.component.service.repository.bean.EmailMessageInfo;
import com.xrds.basic.component.service.repository.bean.MessageBaseInfo;
import com.xrds.basic.component.service.repository.bean.MessageReceiverInfo;

/**
 * 
 * 
 * @author liukai
 * @version $Id: EmailMessageBusServiceImpl.java, v 0.1 2017年11月25日 下午11:15:19 liukai Exp $
 */
@Service("emailMessageBusService")
public class EmailMessageBusServiceImpl extends AbsMessageServiceImpl
    implements
      EmailMessageBusService {
  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_SERVICE.getLogName());
  @Autowired
  private EmailMessageInfoService emailMessageInfoService;

  @Autowired
  private NtfctIntegrationService ntfctIntegrationService;



  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.MessageBusService#saveMessage(com.xrds.basic.component.service.repository.bean.MessageBaseInfo)
   */
  @Override
  public boolean saveMessage(MessageBaseInfo messageinfo) {
    boolean saveFlag = false;
    try {
      final EmailMessageInfo emailMessageInfo = this.getEmailMessageInfoObj(messageinfo);
      transactionTemplate.execute(new TransactionCallback<Integer>() {
        @Override
        public Integer doInTransaction(TransactionStatus status) {
          LOGGER.info("call {} value =[{}]", ToStringBuilder.reflectionToString(emailMessageInfo,
              ToStringStyle.SHORT_PREFIX_STYLE));
          return saveMessages(emailMessageInfo);
        }
      });
      saveFlag = true;
    } catch (Exception e) {
      LOGGER.error("保存邮件异常，异常信息[{}]", e.getMessage(), e);
    }
    return saveFlag;
  }

  /**
   * 
   * 
   * @param emailMessageInfo
   * @return
   * @throws DBException
   */
  public int saveMessages(EmailMessageInfo emailMessageInfo) throws DBException {
    // 保存基本消息信息
    MessageBaseInfo baseInfo = BeanCopierUtils.copyOne2One(emailMessageInfo, MessageBaseInfo.class);
    saveBaseMessage(baseInfo);

    // 保存接收者信息
    saveMessageReceiverList(emailMessageInfo.getMessageReceiverList(), baseInfo.getId());

    // 保存邮件消息信息
    emailMessageInfo.setMessageId(baseInfo.getId());
    int emailMessageResult = emailMessageInfoService.addData(emailMessageInfo);
    if (emailMessageResult < 1) {
      throw new DBException(BusErrorCode.ERROR_201);
    }
    return emailMessageResult;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.MessageBusService#sendMessage(com.xrds.basic.component.service.repository.bean.MessageBaseInfo)
   */
  @Override
  public boolean sendMessage(MessageBaseInfo messageinfo) {
    try {
      ntfctIntegrationService.sendEmail(this.fillEmailSendBean(this
          .getEmailMessageInfoObj(messageinfo)));
      return true;
    } catch (RpcException e) {
      LOGGER.error("发送邮件异常，异常信息[{}],异常编码[{}]", e.getMessage(), e.getCode(), e);
    } catch (Exception e) {
      LOGGER.error("发送邮件异常，异常信息[{}]", e.getMessage(), e);
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
    LOGGER.error("发送邮件信息失败，消息体[{}]", messageinfo);
    if (!this.saveMessage(messageinfo)) {
      LOGGER.error("保存邮件信息失败，消息体[{}]", messageinfo);
    }
    return false;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.EmailMessageBusService#sendAndSaveMessageByConfig(java.lang.String,
   *      java.lang.String, java.lang.String)
   */
  @Override
  public boolean sendAndSaveMessageByConfig(String noticeLevel, String configKey, String content)
      throws BusinessException {
    EmailSendBean emailSendBean = this.getEmailSendByConfig(configKey);
    emailSendBean.setTraceId(UUID.randomUUID().toString());
    emailSendBean.setReqSerial(UUID.randomUUID().toString());
    EmailMessageInfo emailMessageInfo = this.fillEmailMessage(emailSendBean);
    emailMessageInfo.setNoticeLevel(noticeLevel);
    emailMessageInfo.setEmailType(Integer.valueOf(MessageSendType.EMAIL_NORMO.getType()));
    emailMessageInfo.setContent(content);
    if (!this.sendMessage(emailMessageInfo)) {
      return this.saveMessage(emailMessageInfo);
    }
    return true;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.EmailMessageBusService#sendMessageByConfig(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public boolean sendMessageByConfig(String configKey, String content) {
    try {
      EmailSendBean sendEmailRequest = this.getEmailSendByConfig(configKey);
      ntfctIntegrationService.sendEmail(sendEmailRequest);
      return true;
    } catch (RpcException e) {
      LOGGER.error("发送邮件异常，异常信息[{}],异常编码[{}]", e.getMessage(), e.getCode(), e);
    } catch (Exception e) {
      LOGGER.error("发送邮件异常，异常信息[{}]", e.getMessage(), e);
    }
    return false;
  }

  /**
   * 转换email消息对象
   * 
   * @param messageinfo
   * @return
   * @throws BusinessException
   */
  private EmailMessageInfo getEmailMessageInfoObj(MessageBaseInfo messageinfo)
      throws BusinessException {
    try {
      return (EmailMessageInfo) messageinfo;
    } catch (Exception e) {
      LOGGER.error("转换邮件消息对象异常！", e);
      throw new BusinessException(BusErrorCode.ERROR_400, e);
    }
  }

  /**
   * 封装邮件对象信息
   * 
   * @param emailMessage
   * @return
   */
  @SuppressWarnings("unchecked")
  private EmailSendBean fillEmailSendBean(EmailMessageInfo emailMessage) {
    List<MessageReceiverInfo> messageReceiverList = emailMessage.getMessageReceiverList();
    EmailSendBean emailSendBean = new EmailSendBean();
    emailSendBean.setFrom(emailMessage.getSender());
    emailSendBean.setReqSerial(emailMessage.getBusinessId());
    emailSendBean.setConfigName(emailMessage.getConfigName());
    emailSendBean.setType(emailMessage.getStrategy());
    String[] receivers = new String[messageReceiverList.size()];
    for (int i = 0; i < messageReceiverList.size(); i++) {
      MessageReceiverInfo messageReceiver = messageReceiverList.get(i);
      if (messageReceiver == null || StringUtils.isBlank(messageReceiver.getReceiver())) {
        continue;
      }
      receivers[i] = messageReceiver.getReceiver();
    }
    emailSendBean.setTo(receivers);
    if (ConfigCenterConstants.EMAIL_COMMON_TEMPLATE.equals(emailSendBean.getConfigName())) {
      Map<String, String> map = new HashMap<String, String>();
      map.put("content", emailMessage.getContent());
      emailSendBean.setParams(map);
    } else {
      emailSendBean.setParams((Map<String, String>) JSONUtils.parse(emailMessage.getContent()));
    }
    emailSendBean.setEventId(emailMessage.getEventId());
    emailSendBean.setTitle(emailMessage.getTitle());
    if (StringUtils.isNotBlank(emailMessage.getFilesPath())) {
      String[] fileName =
          StringBuilderUtil.convertToArray(emailMessage.getFilesPath(),
              StringBuilderUtil.SYMBOL_COMMA);
      emailSendBean.setFiles(fileName);
    }
    return emailSendBean;
  }

  /**
   * 获取邮件发送配置项信息
   * 
   * @param configKey 配置名称
   * @return
   * @throws BusinessException
   */
  private EmailSendBean getEmailSendByConfig(String configKey) throws BusinessException {
    LOGGER.info("获取邮件配置项信息，配置项名称[{}]", configKey);
    String emailSendConfig = ApplicationContextConfigUtil.getString(configKey, null);
    LOGGER.info("获取邮件配置项信息，配置项[{}]", emailSendConfig);
    if (StringUtils.isBlank(emailSendConfig)) {
      throw new BusinessException(BusErrorCode.ERROR_402);
    }
    EmailSendBean emailSend = null;
    try {
      emailSend = JSON.parseObject(emailSendConfig, EmailSendBean.class);
    } catch (Exception e) {
      LOGGER.error("转换邮件配置项信息异常，配置项名称[{}]", configKey);
      throw new BusinessException(BusErrorCode.ERROR_400, e);
    }
    return emailSend;
  }

  /**
   * 根据配置项封装邮件消息信息
   * 
   * @param emailSendBean
   * @return
   */
  private EmailMessageInfo fillEmailMessage(EmailSendBean emailSendBean) {
    EmailMessageInfo emailMessage = new EmailMessageInfo();
    emailMessage.setBusinessId(UUID.randomUUID().toString());
    emailMessage.setConfigName(emailSendBean.getConfigName());
    StringBuilder filesPath = new StringBuilder();
    if (emailSendBean.getFiles() != null && emailSendBean.getFiles().length > 1) {
      for (int i = 0; i < emailSendBean.getFiles().length; i++) {
        if (StringUtils.isBlank(emailSendBean.getFiles()[i])) {
          continue;
        }
        filesPath.append(emailSendBean.getFiles()[i] + ",");
      }
    }
    emailMessage.setFilesPath(StringUtils.isNotBlank(emailMessage.getFilesPath()) ? filesPath
        .toString() : null);
    if (StringUtils.isNotBlank(emailMessage.getFilesPath())
        && emailMessage.getFilesPath().endsWith(",")) {
      emailMessage.setFilesPath(emailMessage.getFilesPath().substring(0,
          emailMessage.getFilesPath().length() - 1));
    }
    List<MessageReceiverInfo> messageReceivers = new ArrayList<MessageReceiverInfo>();
    if (emailSendBean.getTo() != null && emailSendBean.getTo().length > 0) {
      for (int i = 0; i < emailSendBean.getTo().length; i++) {
        if (StringUtils.isBlank(emailSendBean.getTo()[i])) {
          continue;
        }
        MessageReceiverInfo messageReceiver = new MessageReceiverInfo();
        messageReceiver.setReceiver(emailSendBean.getTo()[i]);
        messageReceivers.add(messageReceiver);
      }
    }
    emailMessage.setEventId(emailSendBean.getEventId());
    emailMessage.setEmailLevel(emailSendBean.getLevel());
    emailMessage.setMessageReceiverList(messageReceivers);
    emailMessage.setSender(emailSendBean.getFrom());
    emailMessage.setEmailId(UUID.randomUUID().toString());
    Date currentTime = new Date();
    emailMessage.setUpdateTime(currentTime);
    emailMessage.setCreateTime(currentTime);
    emailMessage.setPreSendTime(currentTime);
    emailMessage.setSendTimes(1);
    emailMessage.setStatus(MessageSendStatus.UNDO.getCode());
    emailMessage.setMessageType(CommonApplicationConstants.MessageTypeContents.MESSAGE_EMAIL_TYPE);
    emailMessage.setStrategy(emailSendBean.getType());
    emailMessage.setTitle(emailSendBean.getTitle());
    return emailMessage;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.MessageBusService#getMessageDetailInfo(java.math.BigInteger)
   */
  @Override
  public MessageBaseInfo getMessageDetailInfo(BigInteger messageId) throws DBException {
    return emailMessageInfoService.getEmailMessageByMessageId(messageId);
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.EmailMessageBusService#sendAndSaveMessageByConfig(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public boolean sendAndSaveMessageByConfig(String noticeLevel, String content)
      throws BusinessException {
    return this.sendAndSaveMessageByConfig(noticeLevel,
        ConfigCenterConstants.EMAIL_COMMON_TEMPLATE, content);
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.EmailMessageBusService#sendMessageByConfig(java.lang.String)
   */
  @Override
  public boolean sendMessageByConfig(String content) {
    return sendMessageByConfig(ConfigCenterConstants.EMAIL_COMMON_TEMPLATE, content);
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.EmailMessageBusService#sendAndSaveExceptionMessageByConfig(java.lang.String,
   *      java.lang.String, java.lang.Throwable)
   */
  @Override
  public boolean sendAndSaveExceptionMessageByConfig(String noticeLevel, String configKey,
      Throwable content) throws BusinessException {
    EmailSendBean emailSendBean = this.getEmailSendByConfig(configKey);
    EmailMessageInfo emailMessageInfo = this.fillEmailMessage(emailSendBean);
    emailMessageInfo.setNoticeLevel(noticeLevel);
    emailMessageInfo.setEmailType(Integer.valueOf(MessageSendType.EMAIL_ERROR.getType()));
    emailMessageInfo.setContent(StringBuilderUtil.filterChineseStr(content, 2000));
    if (!this.sendMessage(emailMessageInfo)) {
      return this.saveMessage(emailMessageInfo);
    }
    return true;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.bussiness.EmailMessageBusService#sendAndSaveExceptionMessageByConfig(java.lang.String,
   *      java.lang.Throwable)
   */
  @Override
  public boolean sendAndSaveExceptionMessageByConfig(String noticeLevel, Throwable content)
      throws BusinessException {
    return this.sendAndSaveExceptionMessageByConfig(DefaultLevel.HIGH.getCode(),
        ConfigCenterConstants.EMAIL_COMMON_TEMPLATE, content);
  }



}

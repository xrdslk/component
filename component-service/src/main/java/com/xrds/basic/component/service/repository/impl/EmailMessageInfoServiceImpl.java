package com.xrds.basic.component.service.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.utils.BeanCopierUtils;
import com.kunpu.frameworks.db.pagination.Page;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.dal.EmailMessageInfoDao;
import com.xrds.basic.component.dal.IBaseDao;
import com.xrds.basic.component.dal.MessageReceiverDao;
import com.xrds.basic.component.dal.model.EmailMessageInfoDo;
import com.xrds.basic.component.dal.model.MessageReceiverDo;
import com.xrds.basic.component.service.repository.EmailMessageInfoService;
import com.xrds.basic.component.service.repository.bean.EmailMessageInfo;
import com.xrds.basic.component.service.repository.bean.MessageReceiverInfo;

/**
 * 
 * @author liukai
 * @version $Id: EmailMessageInfoServiceImpl.java, v 0.1 2016年3月17日 下午1:35:27 liukai Exp $
 */
@Service("emailMessageInfoService")
public class EmailMessageInfoServiceImpl
    extends BaseServiceImpl<EmailMessageInfo, EmailMessageInfoDo>
    implements
      EmailMessageInfoService {

  @Autowired
  private EmailMessageInfoDao emailMessageInfoDao;

  @Autowired
  private MessageReceiverDao messageReceiverDao;

  /**
   * 
   * @see com.xrds.basic.component.service.repository.EmailMessageInfoService#queryEmailMessageTotalRecords(com.xrds.basic.component.service.repository.bean.EmailMessageInfo)
   */
  @Override
  public int queryEmailMessageTotalRecords(EmailMessageInfo emailMessageInfo) throws DBException {
    LOGGER.info("Start calling EmailMessageInfoService {}:queryEmailMessageTotalRecords()", this
        .getClass().getName());
    LOGGER.info("call {} value =[{}]",
        ToStringBuilder.reflectionToString(emailMessageInfo, ToStringStyle.SHORT_PREFIX_STYLE));
    EmailMessageInfoDo emailMessageInfoDo =
        BeanCopierUtils.copyOne2One(emailMessageInfo, EmailMessageInfoDo.class);
    try {
      int totalRecords = emailMessageInfoDao.queryEmailMessageTotalRecords(emailMessageInfoDo);
      LOGGER.info("{} resule value =[{}]", totalRecords);
      return totalRecords;
    } catch (Exception e) {
      LOGGER.dbError(BusErrorCode.ERROR_203.getMsg() + "查询待发送消息信息记录数失败！邮件信息[{}]", emailMessageInfo,
          e);
      throw new DBException(BusErrorCode.ERROR_203, e);
    } finally {
      LOGGER.info("End calling EmailMessageInfoService {}:queryEmailMessageTotalRecords()");
    }
  }

  @Override
  public List<EmailMessageInfo> queryEmailMessagePageList(EmailMessageInfo emailMessageInfo,
      Page page) throws DBException {
    LOGGER.info("Start calling EmailMessageInfoService {}:queryEmailMessagePageList()", this
        .getClass().getName());
    LOGGER.info("call {} value =[{}]",
        ToStringBuilder.reflectionToString(emailMessageInfo, ToStringStyle.SHORT_PREFIX_STYLE));
    Page pageDO = BeanCopierUtils.copyOne2One(page, Page.class);

    EmailMessageInfoDo emailMessageInfoDo =
        BeanCopierUtils.copyOne2One(emailMessageInfo, EmailMessageInfoDo.class);

    List<EmailMessageInfoDo> emailMessageList = new ArrayList<EmailMessageInfoDo>();
    try {
      emailMessageList = emailMessageInfoDao.queryEmailMessagePageList(emailMessageInfoDo, pageDO);
      if (CollectionUtils.isEmpty(emailMessageList)) {
        return null;
      }
    } catch (Exception e) {
      LOGGER.dbError(BusErrorCode.ERROR_203.getMsg() + "查询分页消息信息失败！邮件信息[{}],分页信息[{}]",
          emailMessageInfo, page, e);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }

    List<EmailMessageInfo> list =
        BeanCopierUtils.copyList2List(emailMessageList, EmailMessageInfo.class);
    for (EmailMessageInfo emailMessageTemp : list) {
      List<MessageReceiverDo> messageReceiverDOList = new ArrayList<MessageReceiverDo>();
      try {
        boolean flag = isMessageReceiverNull(emailMessageTemp);
        if (flag) {
          continue;
        }
        messageReceiverDOList =
            messageReceiverDao.queryListByMessageId(emailMessageTemp.getMessageId());
      } catch (Exception e) {
        throw new DBException(BusErrorCode.ERROR_203, e);
      }
      emailMessageTemp.setMessageReceiverList(BeanCopierUtils.copyList2List(messageReceiverDOList,
          MessageReceiverInfo.class));
    }
    LOGGER.info("{} resule value =[{}]", list);
    LOGGER.info("End calling EmailMessageInfoService {}:queryEmailMessagePageList()");
    return list;
  }

  /**
   * 
   * 
   * @param emailMessageTemp
   * @return
   */
  public boolean isMessageReceiverNull(EmailMessageInfo emailMessageTemp) {
    boolean flag = false;
    if (emailMessageTemp == null) {
      flag = true;
    }
    List<MessageReceiverDo> messageReceiverDOList =
        messageReceiverDao.queryListByMessageId(emailMessageTemp.getMessageId());
    if (CollectionUtils.isEmpty(messageReceiverDOList)) {
      flag = true;
    }
    return flag;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.EmailMessageInfoService#getEmailMessageByMessageId(java.math.BigInteger)
   */
  @Override
  public EmailMessageInfo getEmailMessageByMessageId(BigInteger messageId) throws DBException {
    LOGGER.info("Start calling EmailMessageInfoService {}:getEmailMessageByMessageId()", this
        .getClass().getName());
    LOGGER.info("call {} value =[{}]",
        ToStringBuilder.reflectionToString(messageId, ToStringStyle.SHORT_PREFIX_STYLE));
    EmailMessageInfoDo emailMessageInfoDo = null;
    try {
      emailMessageInfoDo = emailMessageInfoDao.getEmailMessageByMessageId(messageId);
      LOGGER.info("{} resule value =[{}]", emailMessageInfoDo);
      if (emailMessageInfoDo == null) {
        return null;
      }
    } catch (Exception e) {
      LOGGER.dbError(BusErrorCode.ERROR_203.getMsg() + "根据messageId查询邮件详情失败！messageId[{}]",
          messageId, e);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }

    EmailMessageInfo emailMessageInfo =
        BeanCopierUtils.copyOne2One(emailMessageInfoDo, EmailMessageInfo.class);

    List<MessageReceiverDo> messageReceiverDOList = new ArrayList<MessageReceiverDo>();
    try {
      messageReceiverDOList =
          messageReceiverDao.queryListByMessageId(emailMessageInfo.getMessageId());
    } catch (Exception e) {
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
    emailMessageInfo.setMessageReceiverList(BeanCopierUtils.copyList2List(messageReceiverDOList,
        MessageReceiverInfo.class));
    LOGGER.info("End calling EmailMessageInfoService {}:getEmailMessageByMessageId()");
    return emailMessageInfo;
  }


  @Override
  protected IBaseDao<EmailMessageInfoDo> getBaseDAO() {
    return emailMessageInfoDao;
  }

  @Override
  protected Class<EmailMessageInfoDo> getDoClass() {
    return EmailMessageInfoDo.class;
  }

  @Override
  protected Class<EmailMessageInfo> getServiceModelClass() {
    return EmailMessageInfo.class;
  }



}

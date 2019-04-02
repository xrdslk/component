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
import com.xrds.basic.component.dal.IBaseDao;
import com.xrds.basic.component.dal.MessageReceiverDao;
import com.xrds.basic.component.dal.SmsMessageInfoDao;
import com.xrds.basic.component.dal.model.MessageReceiverDo;
import com.xrds.basic.component.dal.model.SmsMessageInfoDo;
import com.xrds.basic.component.service.repository.SmsMessageInfoService;
import com.xrds.basic.component.service.repository.bean.MessageReceiverInfo;
import com.xrds.basic.component.service.repository.bean.SmsMessageInfo;

/**
 * 
 * 
 * @author liukai
 * @version $Id: SmsMessageInfoServiceImpl.java, v 0.1 2017年11月25日 下午10:43:14 liukai Exp $
 */
@Service("smsMessageInfoService")
public class SmsMessageInfoServiceImpl extends BaseServiceImpl<SmsMessageInfo, SmsMessageInfoDo>
    implements
      SmsMessageInfoService {

  @Autowired
  private SmsMessageInfoDao smsMessageInfoDao;

  @Autowired
  private MessageReceiverDao messageReceiverDao;

  @Override
  public List<SmsMessageInfo> querySmsMessagePageList(SmsMessageInfo smsMessageInfo, Page page)
      throws DBException {
    LOGGER.info("Start calling SmsMessageInfoService {}:querySmsMessagePageList()", this.getClass()
        .getName());
    LOGGER.info("call {} value =[{}]", this.getClass().getName(),
        ToStringBuilder.reflectionToString(smsMessageInfo, ToStringStyle.SHORT_PREFIX_STYLE));
    Page pageDO = BeanCopierUtils.copyOne2One(page, Page.class);
    SmsMessageInfoDo smsMessageInfoDo =
        BeanCopierUtils.copyOne2One(smsMessageInfo, SmsMessageInfoDo.class);

    List<SmsMessageInfoDo> smsMessageList = new ArrayList<SmsMessageInfoDo>();
    try {
      smsMessageList = smsMessageInfoDao.querySmsMessagePageList(smsMessageInfoDo, pageDO);
      if (CollectionUtils.isEmpty(smsMessageList)) {
        return null;
      }
    } catch (Exception e) {
      LOGGER.dbError("查询分页消息信息失败！消息信息[{}],分页信息[{}]", smsMessageInfo, page, e);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }

    List<SmsMessageInfo> list = BeanCopierUtils.copyList2List(smsMessageList, SmsMessageInfo.class);
    for (SmsMessageInfo smsMessageInfoTmp : list) {
      List<MessageReceiverDo> messageReceiverDOList = new ArrayList<MessageReceiverDo>();
      try {
        boolean flag = isSmsSmsMessageInfoNULL(smsMessageInfoTmp);
        if (flag) {
          continue;
        }
        messageReceiverDOList =
            messageReceiverDao.queryListByMessageId(smsMessageInfoTmp.getMessageId());
      } catch (Exception e) {
        LOGGER.dbError("查询分页消息信息失败！消息信息[{}],分页信息[{}]", smsMessageInfo, page, e);
        throw new DBException(BusErrorCode.ERROR_203, e);
      }
      smsMessageInfoTmp.setMessageReceiverList(BeanCopierUtils.copyList2List(messageReceiverDOList,
          MessageReceiverInfo.class));
    }
    LOGGER.info("{} resule value =[{}]", this.getClass().getName(), list);
    LOGGER.info("End calling SmsMessageInfoService {}:querySmsMessagePageList()");
    return list;
  }

  /**
   * 
   * 
   * @param smsMessageInfoTmp
   * @return
   */
  public boolean isSmsSmsMessageInfoNULL(SmsMessageInfo smsMessageInfoTmp) {
    boolean flag = false;
    if (smsMessageInfoTmp == null) {
      flag = true;
    }
    List<MessageReceiverDo> messageReceiverDOList =
        messageReceiverDao.queryListByMessageId(smsMessageInfoTmp.getMessageId());
    if (CollectionUtils.isEmpty(messageReceiverDOList)) {
      flag = true;
    }
    return flag;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.SmsMessageInfoService#querySmsMessageTotalRecords(com.xrds.basic.component.service.repository.bean.SmsMessageInfo)
   */
  @Override
  public int querySmsMessageTotalRecords(SmsMessageInfo smsMessageInfo) throws DBException {
    LOGGER.info("Start calling SmsMessageInfoService {}:querySmsMessageTotalRecords()", this
        .getClass().getName());
    LOGGER.info("call {} value =[{}]",
        ToStringBuilder.reflectionToString(smsMessageInfo, ToStringStyle.SHORT_PREFIX_STYLE));
    SmsMessageInfoDo smsMessageInfoDo =
        BeanCopierUtils.copyOne2One(smsMessageInfo, SmsMessageInfoDo.class);
    try {
      int totalRecords = smsMessageInfoDao.querySmsMessageTotalRecords(smsMessageInfoDo);
      LOGGER.info("{} resule value =[{}]", totalRecords);
      return totalRecords;
    } catch (Exception e) {
      LOGGER.dbError("查询待发送消息信息记录数失败！短信消息[{}]", smsMessageInfo, e);
      throw new DBException(BusErrorCode.ERROR_203, e);
    } finally {
      LOGGER.info("End calling SmsMessageInfoService {}:querySmsMessageTotalRecords()");
    }
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.SmsMessageInfoService#getSmsMessageByMessageId(java.math.BigInteger)
   */
  @Override
  public SmsMessageInfo getSmsMessageByMessageId(BigInteger messageId) throws DBException {
    LOGGER.info("Start calling SmsMessageInfoService {}:getSmsMessageByMessageId()", this
        .getClass().getName());
    LOGGER.info("call {} value =[{}]",
        ToStringBuilder.reflectionToString(messageId, ToStringStyle.SHORT_PREFIX_STYLE));

    SmsMessageInfoDo smsMessageInfoDo = null;
    try {
      smsMessageInfoDo = smsMessageInfoDao.getSmsMessageByMessageId(messageId);
      LOGGER.info("{} resule value =[{}]", smsMessageInfoDo);
      if (smsMessageInfoDo == null) {
        return null;
      }
    } catch (Exception e) {
      LOGGER.dbError("根据消息ID查询短信详细信息失败！messageId[{}]", messageId, e);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }

    SmsMessageInfo smsMessageInfo =
        BeanCopierUtils.copyOne2One(smsMessageInfoDo, SmsMessageInfo.class);
    List<MessageReceiverDo> messageReceiverDOList = new ArrayList<MessageReceiverDo>();
    try {
      messageReceiverDOList =
          messageReceiverDao.queryListByMessageId(smsMessageInfo.getMessageId());
    } catch (Exception e) {
      LOGGER.dbError("根据消息ID查询短信详细信息失败！messageId[{}]", messageId, e);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
    smsMessageInfo.setMessageReceiverList(BeanCopierUtils.copyList2List(messageReceiverDOList,
        MessageReceiverInfo.class));
    LOGGER.info("End calling SmsMessageInfoService {}:getSmsMessageByMessageId()");
    return smsMessageInfo;
  }


  @Override
  protected IBaseDao<SmsMessageInfoDo> getBaseDAO() {
    return smsMessageInfoDao;
  }

  @Override
  protected Class<SmsMessageInfoDo> getDoClass() {
    return SmsMessageInfoDo.class;
  }

  @Override
  protected Class<SmsMessageInfo> getServiceModelClass() {
    return SmsMessageInfo.class;
  }

}

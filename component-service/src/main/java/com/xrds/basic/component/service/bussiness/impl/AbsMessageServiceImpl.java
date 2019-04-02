package com.xrds.basic.component.service.bussiness.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.business.BusinessRuntimeException;
import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.frameworks.commons.utils.BeanCopierUtils;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.common.constants.CommonApplicationConstants;
import com.xrds.basic.component.common.util.StringBuilderUtil;
import com.xrds.basic.component.dal.model.MessageReceiverDo;
import com.xrds.basic.component.service.bussiness.MessageBusService;
import com.xrds.basic.component.service.repository.MessageBaseService;
import com.xrds.basic.component.service.repository.MessageReceiverService;
import com.xrds.basic.component.service.repository.bean.MessageBaseInfo;
import com.xrds.basic.component.service.repository.bean.MessageReceiverInfo;

/**
 * 
 * 
 * @author liukai
 * @version $Id: AbsMessageServiceImpl.java, v 0.1 2017年11月25日 下午10:57:57 liukai Exp $
 */
public abstract class AbsMessageServiceImpl implements MessageBusService {
  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_SERVICE.getLogName());

  @Autowired
  protected MessageBaseService messageBaseService;

  @Autowired
  protected MessageReceiverService messageReceiverService;

  @Autowired
  protected TransactionTemplate transactionTemplate;

  /**
   * 保存基本信息
   * 
   * @param baseInfo
   * @throws BusinessException
   */
  protected void saveBaseMessage(MessageBaseInfo baseInfo) throws DBException {
    int messageBaseResult = messageBaseService.addData(baseInfo);
    if (messageBaseResult < 1) {
      LOGGER.dbError("添加消息基本信息锁业务Id失败！消息基本信息[{}]", baseInfo);
      throw new DBException(BusErrorCode.ERROR_201);
    }
    String lockBizId =
        StringBuilderUtil.buildDefaultSerialNo(CommonApplicationConstants.LockPrefix.DEFAULT_PREFIX
            + baseInfo.getMessageType(), String.valueOf(baseInfo.getId()));
    int updateResult = messageBaseService.updateLockBizId(baseInfo.getId(), lockBizId);
    if (updateResult < 1) {
      LOGGER.dbError("更新消息基本信息锁业务Id异常！消息基本信息[{}]", baseInfo);
      throw new DBException(BusErrorCode.ERROR_202);
    }

  }

  /**
   * 保存接收者信息
   * 
   * @param messageReceiverInfoList 接收者列表
   * @param messageId 消息Id
   * @throws BusinessException
   */
  protected void saveMessageReceiverList(List<MessageReceiverInfo> messageReceiverInfoList,
      BigInteger messageId) {
    if (CollectionUtils.isEmpty(messageReceiverInfoList))
      throw new BusinessRuntimeException(BusErrorCode.ERROR_404);
    for (MessageReceiverInfo messageReceiverInfoT : messageReceiverInfoList) {
      messageReceiverInfoT.setMessageId(messageId);
    }
    List<MessageReceiverDo> messageReceiverList =
        BeanCopierUtils.copyList2List(messageReceiverInfoList, MessageReceiverDo.class);
    int receiverListResult = messageReceiverService.batchInsertList(messageReceiverInfoList);
    if (receiverListResult != messageReceiverList.size()) {
      throw new DBException(BusErrorCode.ERROR_201);
    }
  }
}

package com.xrds.basic.component.service.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.utils.BeanCopierUtils;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.dal.IBaseDao;
import com.xrds.basic.component.dal.MessageReceiverDao;
import com.xrds.basic.component.dal.model.MessageReceiverDo;
import com.xrds.basic.component.service.repository.MessageReceiverService;
import com.xrds.basic.component.service.repository.bean.MessageReceiverInfo;

/**
 * 
 * 
 * @author liukai
 * @version $Id: MessageReceiverServiceImpl.java, v 0.1 2017年11月25日 下午10:19:39 liukai Exp $
 */
@Service("messageReceiverService")
public class MessageReceiverServiceImpl
    extends BaseServiceImpl<MessageReceiverInfo, MessageReceiverDo>
    implements
      MessageReceiverService {
  @Autowired
  private MessageReceiverDao messageReceiverDao;

  /**
   * 
   * @see com.xrds.basic.component.service.repository.MessageReceiverService#queryListByMessageId(java.math.BigInteger)
   */
  @Override
  public List<MessageReceiverInfo> queryListByMessageId(BigInteger messageId) {
    List<MessageReceiverDo> messageReceiverList = new ArrayList<MessageReceiverDo>();
    try {
      messageReceiverList = messageReceiverDao.queryListByMessageId(messageId);
    } catch (Exception e) {
      LOGGER.error("根据消息ID查询消息发送者异常!messageId[{}]", messageId, e);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
    List<MessageReceiverInfo> list =
        BeanCopierUtils.copyList2List(messageReceiverList, MessageReceiverInfo.class);
    return list;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.impl.BaseServiceImpl#getBaseDAO()
   */
  @Override
  protected IBaseDao<MessageReceiverDo> getBaseDAO() {
    return messageReceiverDao;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.impl.BaseServiceImpl#getDoClass()
   */
  @Override
  protected Class<MessageReceiverDo> getDoClass() {
    return MessageReceiverDo.class;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.impl.BaseServiceImpl#getServiceModelClass()
   */
  @Override
  protected Class<MessageReceiverInfo> getServiceModelClass() {
    return MessageReceiverInfo.class;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.MessageReceiverService#batchInsertList(java.util.List)
   */
  @Override
  public int batchInsertList(List<MessageReceiverInfo> messageReceiverInfoList) {
    try {
      if (CollectionUtils.isEmpty(messageReceiverInfoList)) {
        return 0;
      }
      List<MessageReceiverDo> receiverList =
          BeanCopierUtils.copyList2List(messageReceiverInfoList, MessageReceiverDo.class);
      return messageReceiverDao.insertList(receiverList);
    } catch (Exception e) {
      LOGGER.dbError("批量插入消息发送者异常!接收者列表[{}]", messageReceiverInfoList, e);
      throw new DBException(BusErrorCode.ERROR_201, e);
    }
  }
}

package com.xrds.basic.component.service.repository.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.utils.BeanCopierUtils;
import com.kunpu.frameworks.db.pagination.Page;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.dal.IBaseDao;
import com.xrds.basic.component.dal.MessageBaseInfoDao;
import com.xrds.basic.component.dal.model.MessageBaseInfoDo;
import com.xrds.basic.component.service.repository.MessageBaseService;
import com.xrds.basic.component.service.repository.bean.MessageBaseInfo;

/**
 * 
 * 
 * @author liukai
 * @version $Id: MessageBaseServiceImpl.java, v 0.1 2017年11月25日 下午10:13:21 liukai Exp $
 */
@Service("messageBaseService")
public class MessageBaseServiceImpl extends BaseServiceImpl<MessageBaseInfo, MessageBaseInfoDo>
    implements
      MessageBaseService {

  @Autowired
  private MessageBaseInfoDao messageBaseInfoDao;

  @Autowired
  private TransactionTemplate transactionTemplate;

  /**
   * 
   * @see com.xrds.basic.component.service.repository.MessageBaseService#updateMessageStatus(java.math.BigInteger,
   *      java.lang.String)
   */
  @Override
  public int updateMessageStatus(BigInteger messageId, String status) throws DBException {
    MessageBaseInfo messageBaseInfo = new MessageBaseInfo();
    messageBaseInfo.setUpdateTime(new Date());
    messageBaseInfo.setId(messageId);
    messageBaseInfo.setStatus(status);
    MessageBaseInfoDo messageBaseDo =
        BeanCopierUtils.copyOne2One(messageBaseInfo, MessageBaseInfoDo.class);
    try {
      return messageBaseInfoDao.updateStatusByKey(messageBaseDo);
    } catch (Exception e) {
      LOGGER.dbError("更新消息基本信息状态异常！message[{}],status[{}]", messageId, status, e);
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
  }

  @Override
  protected IBaseDao<MessageBaseInfoDo> getBaseDAO() {
    return messageBaseInfoDao;
  }

  @Override
  protected Class<MessageBaseInfoDo> getDoClass() {
    return MessageBaseInfoDo.class;
  }

  @Override
  protected Class<MessageBaseInfo> getServiceModelClass() {
    return MessageBaseInfo.class;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.MessageBaseService#updateLockBizId(java.math.BigInteger,
   *      java.lang.String)
   */
  @Override
  public int updateLockBizId(BigInteger messageId, String lockBizId) throws DBException {
    MessageBaseInfoDo messageBaseInfo = new MessageBaseInfoDo();
    messageBaseInfo.setUpdateTime(new Date());
    messageBaseInfo.setId(messageId);
    messageBaseInfo.setLockBizId(lockBizId);
    try {
      return messageBaseInfoDao.updateLockBizId(messageBaseInfo);
    } catch (Exception e) {
      LOGGER.dbError("更新消息基本信息锁业务Id异常！id[{}],lockBizId[{}]", messageId, lockBizId, e);
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.MessageBaseService#lockMessageInfo(java.lang.String,
   *      java.lang.String, java.util.Date, int, int)
   */
  @Override
  public int lockMessageInfo(String noticeLevel, String lockId, Date executeTime, int updateMaxNum,
      int maxSendTimes) throws DBException {
    final MessageBaseInfo messageBaseInfo = new MessageBaseInfo();
    messageBaseInfo.setUpdateTime(new Date());
    messageBaseInfo.setNoticeLevel(noticeLevel);
    messageBaseInfo.setLockId(lockId);
    messageBaseInfo.setLockTime(new Date());
    messageBaseInfo.setLastDealTime(executeTime);
    messageBaseInfo.setUpdateMaxNum(updateMaxNum);
    messageBaseInfo.setSendTimes(maxSendTimes);
    int resultVal = transactionTemplate.execute(new TransactionCallback<Integer>() {
      @Override
      public Integer doInTransaction(TransactionStatus status) {
        MessageBaseInfoDo messageBaseInfoDo =
            BeanCopierUtils.copyOne2One(messageBaseInfo, MessageBaseInfoDo.class);
        int updateResult = 0;
        try {
          updateResult = messageBaseInfoDao.lockMessageInfo(messageBaseInfoDo);
        } catch (Exception e) {
          LOGGER
              .dbError(
                  "锁定待处理消息异常,noticeLevel[{}],lockId[{}],executeTime[{}],updateMaxNum[{}],maxSendTimes[{}]",
                  messageBaseInfo.getNoticeLevel(), messageBaseInfo.getLockId(),
                  messageBaseInfo.getPreSendTime(), messageBaseInfo.getUpdateMaxNum(),
                  messageBaseInfo.getLastDealTime(), e);
          throw new DBException(BusErrorCode.ERROR_202, e);
        }
        return updateResult;
      }
    });
    return resultVal;
  }

  @Override
  public List<MessageBaseInfo> queryLockPageList(String lockId, Page page) throws DBException {
    LOGGER.info("Start calling MessageBaseServiceImpl {}:queryLockPageList()", this.getClass()
        .getName());
    MessageBaseInfoDo messageBaseInfoDo = new MessageBaseInfoDo();
    messageBaseInfoDo.setLockId(lockId);
    Page pageDO = BeanCopierUtils.copyOne2One(page, Page.class);
    List<MessageBaseInfoDo> messageBaseInfoList = new ArrayList<MessageBaseInfoDo>();
    try {
      messageBaseInfoList = messageBaseInfoDao.queryLockPageList(messageBaseInfoDo, pageDO);
    } catch (Exception e) {
      LOGGER.dbError("查询分页消息信息失败,lockId[{}],page[{}]", lockId, page, e);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
    List<MessageBaseInfo> list =
        BeanCopierUtils.copyList2List(messageBaseInfoList, MessageBaseInfo.class);
    LOGGER.info("{} resule value =[{}]", list);
    LOGGER.info("End calling MessageBaseServiceImpl {}:queryLockPageList()");
    return list;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.MessageBaseService#releaseLockRecords(java.lang.String)
   */
  @Override
  public int releaseLockRecords(String lockId) throws DBException {
    final MessageBaseInfo messageBaseInfo = new MessageBaseInfo();
    messageBaseInfo.setLockId(lockId);
    int resultVal = transactionTemplate.execute(new TransactionCallback<Integer>() {
      @Override
      public Integer doInTransaction(TransactionStatus status) {
        MessageBaseInfoDo messageBaseInfoDo =
            BeanCopierUtils.copyOne2One(messageBaseInfo, MessageBaseInfoDo.class);
        int updateResult = 0;
        try {
          updateResult = messageBaseInfoDao.releaseLockRecords(messageBaseInfoDo);
        } catch (Exception e) {
          LOGGER.dbError("释放锁定待处理消息异常！lockId[{}]", messageBaseInfo.getLockId(), e);
          throw new DBException(BusErrorCode.ERROR_202, e);
        }
        return updateResult;
      }
    });
    return resultVal;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.MessageBaseService#releaseLockRecord(java.math.BigInteger)
   */
  @Override
  public int releaseLockRecord(BigInteger id) throws DBException {
    MessageBaseInfoDo messageBaseInfo = new MessageBaseInfoDo();
    messageBaseInfo.setUpdateTime(new Date());
    messageBaseInfo.setId(id);
    int updateResult = 0;
    try {
      updateResult = messageBaseInfoDao.releaseLockRecord(messageBaseInfo);
    } catch (Exception e) {
      LOGGER.dbError("释放单个锁定待处理消息异常！id[{}]", id, e);
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
    return updateResult;
  }

  /**
   * 
   * @see com.xrds.basic.component.service.repository.MessageBaseService#updateMessageSendTimes(java.math.BigInteger)
   */
  @Override
  public int updateMessageSendTimes(BigInteger id) throws DBException {
    try {
      MessageBaseInfoDo messageBaseInfoDo = new MessageBaseInfoDo();
      messageBaseInfoDo.setId(id);
      messageBaseInfoDo.setUpdateTime(new Date());
      return messageBaseInfoDao.updateMessageSendTimes(messageBaseInfoDo);
    } catch (Exception e) {
      LOGGER.dbError("更新发送次数失败异常！id[{}]", id, e);
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
  }

}

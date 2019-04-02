/**
 * 
 */
package com.xrds.basic.component.service.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.db.pagination.Page;
import com.xrds.basic.component.dal.model.MessageBaseInfoDo;
import com.xrds.basic.component.service.repository.bean.MessageBaseInfo;

/**
 * 
 * 
 * @author liukai
 * @version $Id: MessageBaseService.java, v 0.1 2017年11月25日 下午10:04:28 liukai Exp $
 */
public interface MessageBaseService extends BaseService<MessageBaseInfo, MessageBaseInfoDo> {

  /**
   * 更新消息基础信息状态
   * 
   * @param messageId
   * @param status
   * @throws DBException
   * @throws BusinessException
   */
  public int updateMessageStatus(BigInteger messageId, String status) throws DBException;

  /**
   * 更新处理次数
   * 
   * @param id
   * @throws DBException
   * @throws BusinessException
   */
  public int updateMessageSendTimes(BigInteger id) throws DBException;

  /**
   * 更新锁业务Id
   * 
   * @param messageId
   * @param lockBizId
   * @throws DBException
   */
  public int updateLockBizId(BigInteger messageId, String lockBizId) throws DBException;

  /**
   * 锁定记录
   * 
   * @param noticeLevel 消息级别
   * @param lockId 锁Id
   * @param executeTime 执行时间
   * @param updateMaxNum 最大可更新数量
   * @param maxSendTimes 最大发送次数
   * @return
   * @throws HssException
   */
  public int lockMessageInfo(String noticeLevel, String lockId, Date executeTime, int updateMaxNum,
      int maxSendTimes) throws DBException;

  /**
   * 查询分页列表
   * 
   * @param lockId 锁ID
   * @param page 分页对象
   * @return
   * @throws HssException
   */
  public List<MessageBaseInfo> queryLockPageList(String lockId, Page page) throws DBException;

  /**
   * 批量释放记录
   * 
   * @param lockId 锁ID
   * @return
   * @throws HssException
   */
  public int releaseLockRecords(String lockId) throws DBException;

  /**
   * 释放单个记录
   * 
   * @param id
   * @return
   * @throws DBException
   */
  public int releaseLockRecord(BigInteger id) throws DBException;



}

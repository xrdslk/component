/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.db.pagination.Page;
import com.xrds.basic.component.dal.config.BaseDB;
import com.xrds.basic.component.dal.model.MessageBaseInfoDo;

/**
 * 消息基本信息
 * 
 * @author liukai
 * @version $Id: MessageBaseInfoDao.java, v 0.1 2017年11月25日 上午8:51:45 liukai Exp $
 */
@BaseDB
@Qualifier("messageBaseInfoDao")
public interface MessageBaseInfoDao extends IBaseDao<MessageBaseInfoDo> {
  /**
   * 根据主键更新消息基本信息
   * 
   * @param messageBaseInfoDo
   * @return
   */
  public Integer updateStatusByKey(MessageBaseInfoDo messageBaseInfoDo);

  /**
   * 更新业务锁ID
   * 
   * @param messageBaseInfoDo
   * @return
   */
  public int updateLockBizId(MessageBaseInfoDo messageBaseInfoDo);

  /**
   * 锁定记录
   * 
   * @param messageBaseInfoDo
   * @return
   * @throws DBException
   */
  public int lockMessageInfo(MessageBaseInfoDo messageBaseInfoDo) throws DBException;

  /**
   * 查询分页列表
   * 
   * @param messageBaseInfoDo
   * @param page
   * @return
   * @throws DBException
   */
  public List<MessageBaseInfoDo> queryLockPageList(MessageBaseInfoDo messageBaseInfoDo, Page page)
      throws DBException;

  /**
   * 批量释放记录
   * 
   * @param messageBaseInfoDo
   * @return
   * @throws DBException
   */
  public int releaseLockRecords(MessageBaseInfoDo messageBaseInfoDo) throws DBException;

  /**
   * 释放单个记录
   * 
   * @param messageBaseInfoDo
   * @return
   * @throws DBException
   */
  public int releaseLockRecord(MessageBaseInfoDo messageBaseInfoDo) throws DBException;

  /**
   * 更新发送次数
   * 
   * @param messageBaseInfoDo
   * @return
   */
  public int updateMessageSendTimes(MessageBaseInfoDo messageBaseInfoDo);


}

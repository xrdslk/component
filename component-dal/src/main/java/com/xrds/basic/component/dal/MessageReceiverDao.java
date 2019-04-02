/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.xrds.basic.component.dal.config.BaseDB;
import com.xrds.basic.component.dal.model.MessageReceiverDo;

/**
 * 接收者
 * 
 * @author liukai
 * @version $Id: MessageReceiverDao.java, v 0.1 2017年11月25日 上午8:56:15 liukai Exp $
 */
@BaseDB
@Qualifier("messageReceiverDao")
public interface MessageReceiverDao extends IBaseDao<MessageReceiverDo> {
  /**
   * 根据消息ID查询接收者列表
   * 
   * @param messageId
   * @return
   */
  public List<MessageReceiverDo> queryListByMessageId(BigInteger messageId);

  /**
   * 批量插入
   * 
   * @param list
   * @return
   */
  public Integer insertList(List<MessageReceiverDo> list);
}

/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.kunpu.frameworks.db.pagination.Page;
import com.xrds.basic.component.dal.config.BaseDB;
import com.xrds.basic.component.dal.model.SmsMessageInfoDo;

/**
 * 
 * @author liukai
 * @version $Id: SmsMessageInfoDao.java, v 0.1 2017年11月25日 上午8:58:10 liukai Exp $
 */
@BaseDB
@Qualifier("smsMessageInfoDao")
public interface SmsMessageInfoDao extends IBaseDao<SmsMessageInfoDo> {
  /**
   * 查询待发送短信总数
   * 
   * @param smsMessageInfoDo
   * @return
   */
  public int querySmsMessageTotalRecords(SmsMessageInfoDo smsMessageInfoDo);

  /**
   * 查询待发送短信分页列表
   * 
   * @param smsMessageInfoDo
   * @param page
   * @return
   */
  public List<SmsMessageInfoDo> querySmsMessagePageList(SmsMessageInfoDo smsMessageInfoDo, Page page);

  /**
   * 根据消息Id查询短信基本信息
   * 
   * @param messageId
   * @return
   */
  public SmsMessageInfoDo getSmsMessageByMessageId(BigInteger messageId);



}

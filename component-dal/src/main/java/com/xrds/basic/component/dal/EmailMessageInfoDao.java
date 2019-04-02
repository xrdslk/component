/**
 * 
 * 平安付 Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.kunpu.frameworks.db.pagination.Page;
import com.xrds.basic.component.dal.config.BaseDB;
import com.xrds.basic.component.dal.model.EmailMessageInfoDo;

/**
 * 
 * @author liukai
 * @version $Id: EmailMessageInfoDao.java, v 0.1 2016年3月17日 上午10:27:33 liukai Exp $
 */
@BaseDB
@Qualifier("emailMessageInfoDao")
public interface EmailMessageInfoDao extends IBaseDao<EmailMessageInfoDo> {
  /**
   * 
   * 查询待发送邮件总数S
   * 
   * @param emailMessageInfo
   * @param page
   * @return
   */
  public int queryEmailMessageTotalRecords(EmailMessageInfoDo emailMessageInfo);

  /**
   * 查询待发送邮件分页列表
   * 
   * @param emailMessageInfo
   * @param page
   * @return
   */
  public List<EmailMessageInfoDo> queryEmailMessagePageList(EmailMessageInfoDo emailMessageInfo,
      Page page);

  /**
   * 根据消息Id查询邮件基本信息
   * 
   * @param messageId
   * @return
   */
  public EmailMessageInfoDo getEmailMessageByMessageId(BigInteger messageId);

}

/**
 * 
 */
package com.xrds.basic.component.service.repository;

import java.math.BigInteger;
import java.util.List;

import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.db.pagination.Page;
import com.xrds.basic.component.dal.model.EmailMessageInfoDo;
import com.xrds.basic.component.service.repository.bean.EmailMessageInfo;

/**
 * 
 * @author liukai
 * @version $Id: EmailMessageInfoService.java, v 0.1 2016年3月17日 上午10:49:33 liukai Exp $
 */
public interface EmailMessageInfoService extends BaseService<EmailMessageInfo, EmailMessageInfoDo> {

  /**
   * 查询分页信息
   * 
   * @param emailMessageInfo
   * @param page
   * @return
   * @throws DBException
   * @throws BusinessException
   */
  public List<EmailMessageInfo> queryEmailMessagePageList(EmailMessageInfo emailMessageInfo,
      Page page) throws DBException;

  /**
   * 查询可发送总数
   * 
   * @param emailMessageInfo
   * @return
   * @throws DBException
   * @throws BusinessException
   */
  public int queryEmailMessageTotalRecords(EmailMessageInfo emailMessageInfo) throws DBException;

  /**
   * 查询email详细信息
   * 
   * @param messageId
   * @throws DBException
   */
  public EmailMessageInfo getEmailMessageByMessageId(BigInteger messageId) throws DBException;



}

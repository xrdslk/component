package com.xrds.basic.component.service.repository;

import java.math.BigInteger;
import java.util.List;

import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.db.pagination.Page;
import com.xrds.basic.component.dal.model.SmsMessageInfoDo;
import com.xrds.basic.component.service.repository.bean.SmsMessageInfo;

/**
 * 
 * @author liukai
 * @version $Id: SmsMessageInfoService.java, v 0.1 2016年3月17日 上午10:49:33 liukai Exp $
 */
public interface SmsMessageInfoService extends BaseService<SmsMessageInfo, SmsMessageInfoDo> {

  /**
   * 查询分页信息
   * 
   * @param smsMessageInfo
   * @param page
   * @return
   * @throws DBException
   * @throws BusinessException
   */
  public List<SmsMessageInfo> querySmsMessagePageList(SmsMessageInfo smsMessageInfo, Page page)
      throws DBException;

  /**
   * 查询可发送总数
   * 
   * @param smsMessageInfo
   * @return
   * @throws DBException
   * @throws BusinessException
   */
  public int querySmsMessageTotalRecords(SmsMessageInfo smsMessageInfo) throws DBException;

  /**
   * 根据消息ID查询短信基本信息
   * 
   * @param messageId
   * @return
   * @throws DBException
   */
  public SmsMessageInfo getSmsMessageByMessageId(BigInteger messageId) throws DBException;
}

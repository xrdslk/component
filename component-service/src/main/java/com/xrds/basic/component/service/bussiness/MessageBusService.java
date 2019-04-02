package com.xrds.basic.component.service.bussiness;

import java.math.BigInteger;

import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.xrds.basic.component.service.repository.bean.MessageBaseInfo;

/**
 * 
 * 
 * @author liukai
 * @version $Id: MessageBusService.java, v 0.1 2017年11月26日 下午2:04:07 liukai Exp $
 */
public interface MessageBusService {
  /**
   * 保存邮件信息
   * 
   * @param messageinfo
   * @return
   */
  public boolean saveMessage(MessageBaseInfo messageinfo);

  /**
   * 发送邮件信息
   * 
   * @param messageinfo
   * @return
   * @throws BusinessException
   */
  public boolean sendMessage(MessageBaseInfo messageinfo) throws BusinessException;

  /**
   * 发送保存邮件信息
   * 
   * @param messageinfo
   * @return
   * @throws BusinessException
   */
  public boolean sendAndSaveMessage(MessageBaseInfo messageinfo) throws BusinessException;

  /**
   * 查询消息具体信息
   * 
   * @param messageId
   * @return
   * @throws HssException
   */
  public MessageBaseInfo getMessageDetailInfo(BigInteger messageId) throws DBException;


}

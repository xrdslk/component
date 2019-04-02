/**
 * 
 */
package com.xrds.basic.component.service.repository;

import java.math.BigInteger;
import java.util.List;

import com.xrds.basic.component.dal.model.MessageReceiverDo;
import com.xrds.basic.component.service.repository.bean.MessageReceiverInfo;

/**
 * 
 * 
 * @author liukai
 * @version $Id: MessageReceiverService.java, v 0.1 2017年11月25日 下午10:06:03 liukai Exp $
 */
public interface MessageReceiverService extends BaseService<MessageReceiverInfo, MessageReceiverDo> {

  /**
   * 添加列表
   * 
   * @param messageReceiverInfoList
   * @return
   */
  public int batchInsertList(List<MessageReceiverInfo> messageReceiverInfoList);

  /**
   * 查询接收者列表
   * 
   * @param messageId
   * @return
   */
  public List<MessageReceiverInfo> queryListByMessageId(BigInteger messageId);

}

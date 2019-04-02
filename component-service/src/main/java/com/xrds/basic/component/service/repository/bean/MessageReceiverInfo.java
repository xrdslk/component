/**
 * 
 * 坤普 Copyright (c) 2013-2016 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.repository.bean;

import java.math.BigInteger;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 
 * @author liukai
 * @version $Id: MessageReceiverInfo.java, v 0.1 2017年11月25日 下午10:00:36 liukai Exp $
 */
public class MessageReceiverInfo {

  /** 主键ID **/
  private BigInteger id;

  /** 消息ID **/
  private BigInteger messageId;

  /** 接收者 **/
  private String receiver;

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  /**
   * Getter method for property <tt>messageId</tt>.
   * 
   * @return property value of messageId
   */
  public BigInteger getMessageId() {
    return messageId;
  }

  /**
   * Setter method for property <tt>messageId</tt>.
   * 
   * @param messageId value to be assigned to property messageId
   */
  public void setMessageId(BigInteger messageId) {
    this.messageId = messageId;
  }

  /**
   * Getter method for property <tt>receiver</tt>.
   * 
   * @return property value of receiver
   */
  public String getReceiver() {
    return receiver;
  }

  /**
   * Setter method for property <tt>receiver</tt>.
   * 
   * @param receiver value to be assigned to property receiver
   */
  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }

  /**
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}

/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal.model;

import java.math.BigInteger;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author liukai
 * @version $Id: MessageReceiverDo.java, v 0.1 2017年11月25日 上午8:45:09 liukai Exp $
 */
public class MessageReceiverDo extends LockBaseDo {
  /** 主键ID **/
  private BigInteger id;
  /** 消息ID **/
  private BigInteger messageId;
  /** 接收者 **/
  private String receiver;



  /**
   * Getter method for property <tt>id</tt>.
   * 
   * @return property value of id
   */
  public BigInteger getId() {
    return id;
  }



  /**
   * Setter method for property <tt>id</tt>.
   * 
   * @param id value to be assigned to property id
   */
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

/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal.model;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author liukai
 * @version $Id: MessageBaseInfoDo.java, v 0.1 2017年11月25日 上午8:43:11 liukai Exp $
 */
public class MessageBaseInfoDo extends LockBaseDo {
  /** 主键ID */
  private BigInteger id;
  /** 异步通知Q **/
  private String mq;
  /** 消息类型 sms：短信，email：邮件 */
  private String messageType;
  /** 发送状态 undo;doing;success,fail */
  private String status;
  /** 预发送时间 */
  private Date preSendTime;



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
   * Getter method for property <tt>mq</tt>.
   * 
   * @return property value of mq
   */
  public String getMq() {
    return mq;
  }


  /**
   * Setter method for property <tt>mq</tt>.
   * 
   * @param mq value to be assigned to property mq
   */
  public void setMq(String mq) {
    this.mq = mq;
  }


  /**
   * Getter method for property <tt>messageType</tt>.
   * 
   * @return property value of messageType
   */
  public String getMessageType() {
    return messageType;
  }


  /**
   * Setter method for property <tt>messageType</tt>.
   * 
   * @param messageType value to be assigned to property messageType
   */
  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }


  /**
   * Getter method for property <tt>status</tt>.
   * 
   * @return property value of status
   */
  public String getStatus() {
    return status;
  }


  /**
   * Setter method for property <tt>status</tt>.
   * 
   * @param status value to be assigned to property status
   */
  public void setStatus(String status) {
    this.status = status;
  }


  /**
   * Getter method for property <tt>preSendTime</tt>.
   * 
   * @return property value of preSendTime
   */
  public Date getPreSendTime() {
    return preSendTime;
  }


  /**
   * Setter method for property <tt>preSendTime</tt>.
   * 
   * @param preSendTime value to be assigned to property preSendTime
   */
  public void setPreSendTime(Date preSendTime) {
    this.preSendTime = preSendTime;
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

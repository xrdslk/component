/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal.model;

import java.math.BigInteger;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.xrds.basic.component.dal.model.MessageBaseInfoDo;

/**
 * 短信消息
 * 
 * @author liukai
 * @version $Id: SmsMessageInfoDo.java, v 0.1 2017年11月25日 上午8:59:15 liukai Exp $
 */
public class SmsMessageInfoDo extends MessageBaseInfoDo {
  /** 消息基本信息主键 **/
  private BigInteger messageId;
  /** 模板ID **/
  private String eventId;
  /** 业务ID **/
  private String businessId;
  /** 发送参数 **/
  private String content;

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
   * Getter method for property <tt>eventId</tt>.
   * 
   * @return property value of eventId
   */
  public String getEventId() {
    return eventId;
  }


  /**
   * Setter method for property <tt>eventId</tt>.
   * 
   * @param eventId value to be assigned to property eventId
   */
  public void setEventId(String eventId) {
    this.eventId = eventId;
  }


  /**
   * Getter method for property <tt>businessId</tt>.
   * 
   * @return property value of businessId
   */
  public String getBusinessId() {
    return businessId;
  }


  /**
   * Setter method for property <tt>businessId</tt>.
   * 
   * @param businessId value to be assigned to property businessId
   */
  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }


  /**
   * Getter method for property <tt>content</tt>.
   * 
   * @return property value of content
   */
  public String getContent() {
    return content;
  }


  /**
   * Setter method for property <tt>content</tt>.
   * 
   * @param content value to be assigned to property content
   */
  public void setContent(String content) {
    this.content = content;
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

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
 * @version $Id: EmailMessageInfoDo.java, v 0.1 2017年11月25日 上午8:46:43 liukai Exp $
 */
public class EmailMessageInfoDo extends MessageBaseInfoDo {
  /** 消息基本信息表Id **/
  private BigInteger messageId;
  /** 模板Id **/
  private String eventId;
  /** 发送者 **/
  private String sender;
  /** 策略 **/
  private String strategy;
  /** 标题 **/
  private String title;
  /** 等级 **/
  private String emailLevel;
  /** 邮件唯一ID **/
  private String emailId;
  /** 业务ID **/
  private String businessId;
  /** 模板配置项 **/
  private String configName;
  /** 邮件内容 **/
  private String content;
  /** 附件路径 **/
  private String filesPath;
  /** 邮件类型（0：不含附件、1：含附件） **/
  private Integer emailType;

  // /**
  // * Getter method for property <tt>bizId</tt>.
  // *
  // * @return property value of bizId
  // */
  // public String getBizId() {
  // return bizId;
  // }
  //
  // /**
  // * Setter method for property <tt>bizId</tt>.
  // *
  // * @param bizId value to be assigned to property bizId
  // */
  // public void setBizId(String bizId) {
  // this.bizId = bizId;
  // }

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
   * Getter method for property <tt>sender</tt>.
   * 
   * @return property value of sender
   */
  public String getSender() {
    return sender;
  }

  /**
   * Setter method for property <tt>sender</tt>.
   * 
   * @param sender value to be assigned to property sender
   */
  public void setSender(String sender) {
    this.sender = sender;
  }

  /**
   * Getter method for property <tt>strategy</tt>.
   * 
   * @return property value of strategy
   */
  public String getStrategy() {
    return strategy;
  }

  /**
   * Setter method for property <tt>strategy</tt>.
   * 
   * @param strategy value to be assigned to property strategy
   */
  public void setStrategy(String strategy) {
    this.strategy = strategy;
  }

  /**
   * Getter method for property <tt>title</tt>.
   * 
   * @return property value of title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Setter method for property <tt>title</tt>.
   * 
   * @param title value to be assigned to property title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Getter method for property <tt>emailLevel</tt>.
   * 
   * @return property value of emailLevel
   */
  public String getEmailLevel() {
    return emailLevel;
  }

  /**
   * Setter method for property <tt>emailLevel</tt>.
   * 
   * @param emailLevel value to be assigned to property emailLevel
   */
  public void setEmailLevel(String emailLevel) {
    this.emailLevel = emailLevel;
  }

  /**
   * Getter method for property <tt>emailId</tt>.
   * 
   * @return property value of emailId
   */
  public String getEmailId() {
    return emailId;
  }

  /**
   * Setter method for property <tt>emailId</tt>.
   * 
   * @param emailId value to be assigned to property emailId
   */
  public void setEmailId(String emailId) {
    this.emailId = emailId;
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
   * Getter method for property <tt>configName</tt>.
   * 
   * @return property value of configName
   */
  public String getConfigName() {
    return configName;
  }

  /**
   * Setter method for property <tt>configName</tt>.
   * 
   * @param configName value to be assigned to property configName
   */
  public void setConfigName(String configName) {
    this.configName = configName;
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
   * Getter method for property <tt>filesPath</tt>.
   * 
   * @return property value of filesPath
   */
  public String getFilesPath() {
    return filesPath;
  }

  /**
   * Setter method for property <tt>filesPath</tt>.
   * 
   * @param filesPath value to be assigned to property filesPath
   */
  public void setFilesPath(String filesPath) {
    this.filesPath = filesPath;
  }

  /**
   * Getter method for property <tt>emailType</tt>.
   * 
   * @return property value of emailType
   */
  public Integer getEmailType() {
    return emailType;
  }

  /**
   * Setter method for property <tt>emailType</tt>.
   * 
   * @param emailType value to be assigned to property emailType
   */
  public void setEmailType(Integer emailType) {
    this.emailType = emailType;
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

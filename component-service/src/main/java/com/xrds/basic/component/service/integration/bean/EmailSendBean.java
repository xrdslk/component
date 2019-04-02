package com.xrds.basic.component.service.integration.bean;

import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class EmailSendBean {

  /**
   * 客户端请求流水
   */
  private String reqSerial;

  /**
   * 交易ID
   */
  private String traceId;

  /**
   * 系统描述
   */
  private String sysDesc;

  /**
   * 系统编号
   */
  private String sysId;

  /**
   * 主题
   */
  private String title;

  /** 模板对应的配置项 */
  private String configName;

  /** 模板id */
  private String eventId;

  /** 等级 */
  private String level;
  /**
   * 发件人
   */
  private String from;

  /**
   * 收件人
   */
  private String[] to;

  /**
   * 抄送
   */
  private String[] cc;

  /**
   * 密送
   */
  private String[] bcc;

  /**
   * 邮件内容
   */
  private Map<String, String> params;

  /**
   * 附件
   */
  private String[] files;

  /**
   * 邮件的文本内容
   */
  private String type;


  /**
   * Getter method for property <tt>reqSerial</tt>.
   * 
   * @return property value of reqSerial
   */
  public String getReqSerial() {
    return reqSerial;
  }


  /**
   * Setter method for property <tt>reqSerial</tt>.
   * 
   * @param reqSerial value to be assigned to property reqSerial
   */
  public void setReqSerial(String reqSerial) {
    this.reqSerial = reqSerial;
  }


  /**
   * Getter method for property <tt>traceId</tt>.
   * 
   * @return property value of traceId
   */
  public String getTraceId() {
    return traceId;
  }


  /**
   * Setter method for property <tt>traceId</tt>.
   * 
   * @param traceId value to be assigned to property traceId
   */
  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }


  /**
   * Getter method for property <tt>sysDesc</tt>.
   * 
   * @return property value of sysDesc
   */
  public String getSysDesc() {
    return sysDesc;
  }


  /**
   * Setter method for property <tt>sysDesc</tt>.
   * 
   * @param sysDesc value to be assigned to property sysDesc
   */
  public void setSysDesc(String sysDesc) {
    this.sysDesc = sysDesc;
  }


  /**
   * Getter method for property <tt>sysId</tt>.
   * 
   * @return property value of sysId
   */
  public String getSysId() {
    return sysId;
  }


  /**
   * Setter method for property <tt>sysId</tt>.
   * 
   * @param sysId value to be assigned to property sysId
   */
  public void setSysId(String sysId) {
    this.sysId = sysId;
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
   * Getter method for property <tt>level</tt>.
   * 
   * @return property value of level
   */
  public String getLevel() {
    return level;
  }


  /**
   * Setter method for property <tt>level</tt>.
   * 
   * @param level value to be assigned to property level
   */
  public void setLevel(String level) {
    this.level = level;
  }


  /**
   * Getter method for property <tt>from</tt>.
   * 
   * @return property value of from
   */
  public String getFrom() {
    return from;
  }


  /**
   * Setter method for property <tt>from</tt>.
   * 
   * @param from value to be assigned to property from
   */
  public void setFrom(String from) {
    this.from = from;
  }


  /**
   * Getter method for property <tt>to</tt>.
   * 
   * @return property value of to
   */
  public String[] getTo() {
    return to;
  }


  /**
   * Setter method for property <tt>to</tt>.
   * 
   * @param to value to be assigned to property to
   */
  public void setTo(String[] to) {
    this.to = to;
  }


  /**
   * Getter method for property <tt>cc</tt>.
   * 
   * @return property value of cc
   */
  public String[] getCc() {
    return cc;
  }


  /**
   * Setter method for property <tt>cc</tt>.
   * 
   * @param cc value to be assigned to property cc
   */
  public void setCc(String[] cc) {
    this.cc = cc;
  }


  /**
   * Getter method for property <tt>bcc</tt>.
   * 
   * @return property value of bcc
   */
  public String[] getBcc() {
    return bcc;
  }


  /**
   * Setter method for property <tt>bcc</tt>.
   * 
   * @param bcc value to be assigned to property bcc
   */
  public void setBcc(String[] bcc) {
    this.bcc = bcc;
  }


  /**
   * Getter method for property <tt>params</tt>.
   * 
   * @return property value of params
   */
  public Map<String, String> getParams() {
    return params;
  }


  /**
   * Setter method for property <tt>params</tt>.
   * 
   * @param params value to be assigned to property params
   */
  public void setParams(Map<String, String> params) {
    this.params = params;
  }


  /**
   * Getter method for property <tt>files</tt>.
   * 
   * @return property value of files
   */
  public String[] getFiles() {
    return files;
  }


  /**
   * Setter method for property <tt>files</tt>.
   * 
   * @param files value to be assigned to property files
   */
  public void setFiles(String[] files) {
    this.files = files;
  }


  /**
   * Getter method for property <tt>type</tt>.
   * 
   * @return property value of type
   */
  public String getType() {
    return type;
  }


  /**
   * Setter method for property <tt>type</tt>.
   * 
   * @param type value to be assigned to property type
   */
  public void setType(String type) {
    this.type = type;
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

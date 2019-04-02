/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.facade.bean.abs;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author yuyangzhi
 * @version $Id: AbstractRequest.java, v 0.1 2018年6月28日 上午11:52:56 yuyangzhi Exp $
 */
public abstract class AbstractRequest implements Serializable {

  /**  */
  private static final long serialVersionUID = -6400344467702269682L;


  // 客户端请求流水
  @NotBlank(message = "reqSerial 不能为空或NULL")
  private String reqSerial;
  /** 交易ID **/
  private String traceId;
  /** 系统编号 */
  private String sysId;
  /** 系统描述 **/
  private String sysDesc;



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
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }



}

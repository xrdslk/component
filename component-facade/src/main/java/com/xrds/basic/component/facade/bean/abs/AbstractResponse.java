/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.facade.bean.abs;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author yuyangzhi
 * @version $Id: AbstractResponse.java, v 0.1 2018年6月28日 上午11:52:56 yuyangzhi Exp $
 */
public abstract class AbstractResponse implements Serializable {

  /**  */
  private static final long serialVersionUID = 1341637335349660962L;
  // 客户端请求流水
  private String reqSerial;

  /** 系统返回码 */
  private String code;

  /** 系统返回描述 */
  private String memo;

  /**
   * Getter method for property <tt>code</tt>.
   * 
   * @return property value of code
   */
  public String getCode() {
    return code;
  }

  /**
   * Setter method for property <tt>code</tt>.
   * 
   * @param code value to be assigned to property code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * Getter method for property <tt>memo</tt>.
   * 
   * @return property value of memo
   */
  public String getMemo() {
    return memo;
  }

  /**
   * Setter method for property <tt>memo</tt>.
   * 
   * @param memo value to be assigned to property memo
   */
  public void setMemo(String memo) {
    this.memo = memo;
  }


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

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}

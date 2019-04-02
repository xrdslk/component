/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.facade.bean.abs;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @author yuyangzhi
 * @version $Id: AbstractPageRequest.java, v 0.1 2018年6月28日 上午11:52:56 yuyangzhi Exp $
 */
public class AbstractPageRequest implements Serializable {
  private static final long serialVersionUID = 1574760690096604210L;
  // 客户端请求流水
  @NotBlank(message = "reqSerial 不能为空或NULL")
  private String reqSerial;
  // 开始页数
  @NotBlank(message = "bgPage 不能为空或NULL")
  private Integer bgPage;
  // 每页行数
  @NotBlank(message = "pageSize 不能为空或NULL")
  private Integer pageSize;
  /** 开始时间 **/
  // @NotBlank(message = "beginDate 不能为空或NULL")
  private Date beginDate;
  /** 结束时间 **/
  // @NotBlank(message = "endDate 不能为空或NULL")
  private Date endDate;

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
   * Getter method for property <tt>bgPage</tt>.
   * 
   * @return property value of bgPage
   */
  public Integer getBgPage() {
    return bgPage;
  }

  /**
   * Setter method for property <tt>bgPage</tt>.
   * 
   * @param bgPage value to be assigned to property bgPage
   */
  public void setBgPage(Integer bgPage) {
    this.bgPage = bgPage;
  }

  /**
   * Getter method for property <tt>pageSize</tt>.
   * 
   * @return property value of pageSize
   */
  public Integer getPageSize() {
    return pageSize;
  }

  /**
   * Setter method for property <tt>pageSize</tt>.
   * 
   * @param pageSize value to be assigned to property pageSize
   */
  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  /**
   * Getter method for property <tt>beginDate</tt>.
   * 
   * @return property value of beginDate
   */
  public Date getBeginDate() {
    return beginDate;
  }

  /**
   * Setter method for property <tt>beginDate</tt>.
   * 
   * @param beginDate value to be assigned to property beginDate
   */
  public void setBeginDate(Date beginDate) {
    this.beginDate = beginDate;
  }

  /**
   * Getter method for property <tt>endDate</tt>.
   * 
   * @return property value of endDate
   */
  public Date getEndDate() {
    return endDate;
  }

  /**
   * Setter method for property <tt>endDate</tt>.
   * 
   * @param endDate value to be assigned to property endDate
   */
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  /**
   * 
   * @see com.xrds.basic.cip.facade.bean.abs.AbstractResponse#toString()
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

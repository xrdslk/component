package com.xrds.basic.component.facade.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.xrds.basic.component.facade.bean.abs.AbstractResponse;


/**
 * 
 * 
 * @author yuyangzhi
 * @version $Id: DemoResponse.java, v 0.1 2018年6月28日 下午4:03:33 yuyangzhi Exp $
 */
public class DemoResponse extends AbstractResponse {
  private static final long serialVersionUID = -179172629010318593L;

  /** 业务类型 */
  private String businessType;
  /** 业务id */
  private String businessId;
  /** 任务状态，0未锁定，1已锁定 */
  private Integer taskStatus;
  /** 任务是否结束 0未结束1结束 */
  private Integer isFinished;

  /**
   * Getter method for property <tt>businessType</tt>.
   * 
   * @return property value of businessType
   */
  public String getBusinessType() {
    return businessType;
  }

  /**
   * Setter method for property <tt>businessType</tt>.
   * 
   * @param businessType value to be assigned to property businessType
   */
  public void setBusinessType(String businessType) {
    this.businessType = businessType;
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
   * Getter method for property <tt>taskStatus</tt>.
   * 
   * @return property value of taskStatus
   */
  public Integer getTaskStatus() {
    return taskStatus;
  }

  /**
   * Setter method for property <tt>taskStatus</tt>.
   * 
   * @param taskStatus value to be assigned to property taskStatus
   */
  public void setTaskStatus(Integer taskStatus) {
    this.taskStatus = taskStatus;
  }

  /**
   * Getter method for property <tt>isFinished</tt>.
   * 
   * @return property value of isFinished
   */
  public Integer getIsFinished() {
    return isFinished;
  }

  /**
   * Setter method for property <tt>isFinished</tt>.
   * 
   * @param isFinished value to be assigned to property isFinished
   */
  public void setIsFinished(Integer isFinished) {
    this.isFinished = isFinished;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

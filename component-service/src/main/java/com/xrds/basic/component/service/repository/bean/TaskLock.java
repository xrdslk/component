/**
 * 
 */
package com.xrds.basic.component.service.repository.bean;

import java.math.BigInteger;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.xrds.basic.component.service.bussiness.bean.BaseBean;

/**
 * 
 * 
 * @author liukai
 * @version $Id: TaskLock.java, v 0.1 2017年6月13日 上午10:34:43 liukai Exp $
 */
public class TaskLock extends BaseBean {
  /** 主键ID */
  private BigInteger id;
  /** 业务类型 */
  private String businessType;
  /** 业务id */
  private String businessId;
  /** 任务状态，0未锁定，1已锁定 */
  private Integer taskStatus;
  /** 任务是否结束 0未结束1结束 */
  private Integer isFinished;

  /**
   * 
   */
  public TaskLock() {
    super();
  }

  public TaskLock(String businessType, String businessId, int taskStatus, int isFinished) {
    this.businessId = businessId;
    this.businessType = businessType;
    this.taskStatus = taskStatus;
    this.isFinished = isFinished;

  }

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

  /**
   * 
   * @see com.pinganfu.iip.sqb.service.model.BaseBean#toString()
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}

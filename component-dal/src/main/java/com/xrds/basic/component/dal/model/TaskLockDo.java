package com.xrds.basic.component.dal.model;

import java.math.BigInteger;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class TaskLockDo extends BaseDo {
  public static final Integer UNLOCKED = new Integer(0);
  public static final Integer LOCKED = new Integer(1);
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
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

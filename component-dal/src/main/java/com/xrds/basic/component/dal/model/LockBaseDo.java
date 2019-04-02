/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author liukai
 * @version $Id: LockBaseDo.java, v 0.1 2017年11月25日 上午8:40:54 liukai Exp $
 */
public class LockBaseDo extends BaseDo {
  /** 锁ID **/
  private String lockId;
  /** 锁业务ID **/
  private String lockBizId;
  /** 锁状态1:已锁0:未锁 **/
  private Integer lockStatus;
  /** 通知级别 **/
  private String noticeLevel;
  /** 异步Q消息 **/
  private String mq;
  /** 锁定时间 **/
  private Date lockTime;
  /** 发送次数 **/
  private int sendTimes;
  /** 上次执行时间 **/
  private Date lastDealTime;
  /** 最大可更更新数量 **/
  private int updateMaxNum;


  /**
   * Getter method for property <tt>lockId</tt>.
   * 
   * @return property value of lockId
   */
  public String getLockId() {
    return lockId;
  }


  /**
   * Setter method for property <tt>lockId</tt>.
   * 
   * @param lockId value to be assigned to property lockId
   */
  public void setLockId(String lockId) {
    this.lockId = lockId;
  }


  /**
   * Getter method for property <tt>lockBizId</tt>.
   * 
   * @return property value of lockBizId
   */
  public String getLockBizId() {
    return lockBizId;
  }


  /**
   * Setter method for property <tt>lockBizId</tt>.
   * 
   * @param lockBizId value to be assigned to property lockBizId
   */
  public void setLockBizId(String lockBizId) {
    this.lockBizId = lockBizId;
  }


  /**
   * Getter method for property <tt>lockStatus</tt>.
   * 
   * @return property value of lockStatus
   */
  public Integer getLockStatus() {
    return lockStatus;
  }


  /**
   * Setter method for property <tt>lockStatus</tt>.
   * 
   * @param lockStatus value to be assigned to property lockStatus
   */
  public void setLockStatus(Integer lockStatus) {
    this.lockStatus = lockStatus;
  }


  /**
   * Getter method for property <tt>noticeLevel</tt>.
   * 
   * @return property value of noticeLevel
   */
  public String getNoticeLevel() {
    return noticeLevel;
  }


  /**
   * Setter method for property <tt>noticeLevel</tt>.
   * 
   * @param noticeLevel value to be assigned to property noticeLevel
   */
  public void setNoticeLevel(String noticeLevel) {
    this.noticeLevel = noticeLevel;
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
   * Getter method for property <tt>lockTime</tt>.
   * 
   * @return property value of lockTime
   */
  public Date getLockTime() {
    return lockTime;
  }


  /**
   * Setter method for property <tt>lockTime</tt>.
   * 
   * @param lockTime value to be assigned to property lockTime
   */
  public void setLockTime(Date lockTime) {
    this.lockTime = lockTime;
  }


  /**
   * Getter method for property <tt>sendTimes</tt>.
   * 
   * @return property value of sendTimes
   */
  public int getSendTimes() {
    return sendTimes;
  }


  /**
   * Setter method for property <tt>sendTimes</tt>.
   * 
   * @param sendTimes value to be assigned to property sendTimes
   */
  public void setSendTimes(int sendTimes) {
    this.sendTimes = sendTimes;
  }


  /**
   * Getter method for property <tt>lastDealTime</tt>.
   * 
   * @return property value of lastDealTime
   */
  public Date getLastDealTime() {
    return lastDealTime;
  }


  /**
   * Setter method for property <tt>lastDealTime</tt>.
   * 
   * @param lastDealTime value to be assigned to property lastDealTime
   */
  public void setLastDealTime(Date lastDealTime) {
    this.lastDealTime = lastDealTime;
  }


  /**
   * Getter method for property <tt>updateMaxNum</tt>.
   * 
   * @return property value of updateMaxNum
   */
  public int getUpdateMaxNum() {
    return updateMaxNum;
  }


  /**
   * Setter method for property <tt>updateMaxNum</tt>.
   * 
   * @param updateMaxNum value to be assigned to property updateMaxNum
   */
  public void setUpdateMaxNum(int updateMaxNum) {
    this.updateMaxNum = updateMaxNum;
  }


  /**
   * 
   * @see com.xrds.basic.component.dal.model.BaseDo#toString()
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

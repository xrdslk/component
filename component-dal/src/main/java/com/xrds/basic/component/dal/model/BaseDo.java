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
 * @version $Id: BaseDo.java, v 0.1 2017年6月13日 上午10:04:28 liukai Exp $
 */
public class BaseDo {
  /** 创建时间 **/
  private Date createTime;
  /** 更新时间 **/
  private Date updateTime;

  /**
   * Getter method for property <tt>createTime</tt>.
   * 
   * @return property value of createTime
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * Setter method for property <tt>createTime</tt>.
   * 
   * @param createTime value to be assigned to property createTime
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * Getter method for property <tt>updateTime</tt>.
   * 
   * @return property value of updateTime
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * Setter method for property <tt>updateTime</tt>.
   * 
   * @param updateTime value to be assigned to property updateTime
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
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

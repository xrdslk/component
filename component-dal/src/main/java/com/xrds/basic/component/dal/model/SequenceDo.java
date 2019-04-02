/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal.model;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author liukai
 * @version $Id: SequenceDo.java, v 0.1 2017年6月13日 下午8:14:42 liukai Exp $
 */
public class SequenceDo {
  /** 主键ID **/
  private BigInteger id;
  /** 内容 **/
  private String content;
  /** 创建时间 **/
  private Date createTime;

  public SequenceDo(String content) {
    this.content = content;
    this.createTime = new Date();
  }

  public SequenceDo(Date createTime) {
    this.createTime = createTime;
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
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

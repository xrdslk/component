/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.repository.bean;

import java.math.BigInteger;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.xrds.basic.component.service.bussiness.bean.BaseBean;

/**
 * 交互请求日志对象
 * 
 * @author liukai
 * @version $Id: TransRequestLog.java, v 0.1 2017年12月3日 上午10:55:51 liukai Exp $
 */
public class TransRequestLog extends BaseBean {
  /**
   * 自增主键
   */
  private BigInteger id;

  /**
   * 业务类型(根据业务性质区分，可以定义明确的业务类型或者使用接口替换)
   */
  private String busType;

  /**
   * 业务请求流水（可使用关键字段拼接方式）
   */
  private String busId;

  /**
   * 请求渠道名称
   */
  private String reqChannel;

  /**
   * 请求服务
   */
  private String reqServerId;

  /**
   * 请求时间
   */
  private Date reqTime;

  /**
   * 响应时间
   */
  private Date respTime;
  /**
   * 请求参数,json格式（大字段数据可不存储）
   */
  private String reqParam;

  /**
   * 响应参数,json格式（大字段数据可不存储）
   * 
   * /** 响应码
   */
  private String respCode;

  /**
   * 响应参数,json格式（大字段数据可不存储）
   */
  private String respParam;

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
   * Getter method for property <tt>busType</tt>.
   * 
   * @return property value of busType
   */
  public String getBusType() {
    return busType;
  }

  /**
   * Setter method for property <tt>busType</tt>.
   * 
   * @param busType value to be assigned to property busType
   */
  public void setBusType(String busType) {
    this.busType = busType;
  }

  /**
   * Getter method for property <tt>busId</tt>.
   * 
   * @return property value of busId
   */
  public String getBusId() {
    return busId;
  }

  /**
   * Setter method for property <tt>busId</tt>.
   * 
   * @param busId value to be assigned to property busId
   */
  public void setBusId(String busId) {
    this.busId = busId;
  }

  /**
   * Getter method for property <tt>reqChannel</tt>.
   * 
   * @return property value of reqChannel
   */
  public String getReqChannel() {
    return reqChannel;
  }

  /**
   * Setter method for property <tt>reqChannel</tt>.
   * 
   * @param reqChannel value to be assigned to property reqChannel
   */
  public void setReqChannel(String reqChannel) {
    this.reqChannel = reqChannel;
  }

  /**
   * Getter method for property <tt>reqServerId</tt>.
   * 
   * @return property value of reqServerId
   */
  public String getReqServerId() {
    return reqServerId;
  }

  /**
   * Setter method for property <tt>reqServerId</tt>.
   * 
   * @param reqServerId value to be assigned to property reqServerId
   */
  public void setReqServerId(String reqServerId) {
    this.reqServerId = reqServerId;
  }

  /**
   * Getter method for property <tt>reqTime</tt>.
   * 
   * @return property value of reqTime
   */
  public Date getReqTime() {
    return reqTime;
  }

  /**
   * Setter method for property <tt>reqTime</tt>.
   * 
   * @param reqTime value to be assigned to property reqTime
   */
  public void setReqTime(Date reqTime) {
    this.reqTime = reqTime;
  }

  /**
   * Getter method for property <tt>respTime</tt>.
   * 
   * @return property value of respTime
   */
  public Date getRespTime() {
    return respTime;
  }

  /**
   * Setter method for property <tt>respTime</tt>.
   * 
   * @param respTime value to be assigned to property respTime
   */
  public void setRespTime(Date respTime) {
    this.respTime = respTime;
  }

  /**
   * Getter method for property <tt>reqParam</tt>.
   * 
   * @return property value of reqParam
   */
  public String getReqParam() {
    return reqParam;
  }

  /**
   * Setter method for property <tt>reqParam</tt>.
   * 
   * @param reqParam value to be assigned to property reqParam
   */
  public void setReqParam(String reqParam) {
    this.reqParam = reqParam;
  }

  /**
   * Getter method for property <tt>respCode</tt>.
   * 
   * @return property value of respCode
   */
  public String getRespCode() {
    return respCode;
  }

  /**
   * Setter method for property <tt>respCode</tt>.
   * 
   * @param respCode value to be assigned to property respCode
   */
  public void setRespCode(String respCode) {
    this.respCode = respCode;
  }

  public String getRespParam() {
    return respParam;
  }

  public void setRespParam(String respParam) {
    this.respParam = respParam;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}

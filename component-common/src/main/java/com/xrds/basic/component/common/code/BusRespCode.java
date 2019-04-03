/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.code;

import org.apache.commons.lang3.StringUtils;

import com.kunpu.frameworks.commons.code.IRespCode;

/**
 * 
 * @author liukai
 * @version $Id: CipRespCode.java, v 0.1 2017年6月13日 下午9:51:57 liukai Exp $
 */
public enum BusRespCode implements IRespCode {
  /** 成功 */
  RESP_000000("000000", "成功"), //
  /** 未知原因失败 */
  RESP_999("999", "未知原因失败"), //

  /**
   * data check code 001-099
   */
  /** 参数不完整 */
  RESP_001("001", "参数错误"), //

  /** 参数不完整 */
  RESP_010("010", "MQ发送失败"), //
  /**
   * bussiness check code 100-199
   */
  /** 无访问权限 */
  RESP_100("100", "无访问权限"), //
  /** 有重复请求数据 */
  RESP_101("101", "有重复请求数据"), //
  /** 资源竞争 */
  RESP_102("102", "资源竞争"), //
  /** 查询记录不存在 */
  RESP_103("103", "查询记录不存在"), //
  /**
   * database code 200-299
   */
  /** 数据库异常 */
  RESP_200("200", "数据库异常"), //

  /** 理财产品不存在 */
  RESP_205("205", "理财产品不存在"), //
  /** 更新理财产品状态失败 */
  RESP_206("206", "更新理财产品状态失败"), //
  /** 数据库数据有误 */
  RESP_207("207", "数据库数据有误"), //
  /**
   * call remote service code 300-399
   */
  /** 调用外部系统服务超时或异常 */
  RESP_300("300", "调用外部系统服务超时或异常"), //
  /**
   * system code 400-499
   */
  /** 系统内部异常 */
  RESP_400("400", "系统内部异常"), //


  /** 银行卡格式错误 */
  RESP_752("752", "银行卡格式错误"),

  /** 身份证信息错误 */
  RESP_753("753", "身份证信息错误"),

  /** 手机号格式错误 */
  RESP_754("754", "手机号格式错误"),



  /** 初始化redis自增失败 */
  RESP_801("801", "初始化redis自增失败"), //
  /** 获取redis序列号失败 */
  RESP_802("802", "获取redis序列号失败"), //

  /** 系统返回异常 */
  RESP_900("900", "NTFCT系统异常(返回信息为空或NULL)"), //
  /** 返回未知错误 */
  RESP_901("901", "返回未知错误");

  private String code;

  private String desc;

  private BusRespCode(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public String getRespCode() {
    return this.code;
  }

  @Override
  public String getRespMsg() {
    return this.desc;
  }

  public String getFullRespCode(String appCode) {
    return appCode + this.code;
  }

  @Override
  public IRespCode getCurrentCodeObj() {
    return this;
  }

  @Override
  public IRespCode getCurrentObjByCode(String code) {
    for (BusRespCode respCode : BusRespCode.values()) {
      if (StringUtils.isNotBlank(code) && StringUtils.equals(code, respCode.getCode())) {
        return respCode;
      }
    }
    return null;
  }
}

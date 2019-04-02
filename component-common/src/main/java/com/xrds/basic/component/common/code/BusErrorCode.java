/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.code;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.kunpu.frameworks.commons.code.CommonErrorCode;
import com.kunpu.frameworks.commons.code.IErrorCode;
import com.kunpu.frameworks.commons.code.IRespCode;


/**
 * 
 * @author liukai
 * @version $Id: CipErrorCode.java, v 0.1 2017年6月13日 下午4:35:20 liukai Exp $
 */
public enum BusErrorCode implements IErrorCode {
  /** 成功 */
  SUCCESS_000000("000000", "成功", BusRespCode.RESP_000000), //
  /** 未知原因失败 */
  ERROR_999("999", "未知原因失败", BusRespCode.RESP_999), //

  /**
   * data check code 001-099
   */

  /** 入参数值不合法 */
  ERROR_001("001", "入参数值不合法", BusRespCode.RESP_001), //
  /** 必要参数不能为空 */
  ERROR_002("002", "必要参数不能为空", BusRespCode.RESP_001), //
  /**
   * bussiness check code 100-199
   */

  /** 无访问权限 */
  ERROR_100("100", "无访问权限", BusRespCode.RESP_100), //
  /** 加锁失败 **/
  ERROR_101("101", "加锁失败", BusRespCode.RESP_102),
  /** 查询记录不存在 **/
  ERROR_102("102", "查询记录不存在", BusRespCode.RESP_103),

  /** 数据库操作添加失败 */
  ERROR_201("201", "数据库操作添加失败", BusRespCode.RESP_200), //
  /** 数据库操作修改失败 */
  ERROR_202("202", "数据库操作修改失败", BusRespCode.RESP_200), //
  /** 数据库操作查询失败 */
  ERROR_203("203", "数据库操作查询失败", BusRespCode.RESP_200), //
  /** 数据库操作删除失败 */
  ERROR_204("204", "数据库操作删除失败", BusRespCode.RESP_200), //
  /** 理财产品不存在 */
  ERROR_205("205", "理财产品不存在", BusRespCode.RESP_205), //
  /** 更新理财产品状态失败 */
  ERROR_206("206", "更新理财产品状态失败", BusRespCode.RESP_206), //
  /** 数据库数据有误 */
  ERROR_207("207", "数据库数据有误", BusRespCode.RESP_207), //

  /**
   * call remote service code 300-399
   */
  ERROR_300("300", "调用外部系统服务超时或异常", BusRespCode.RESP_300), //
  /** RPC URL不存在 */
  ERROR_301("301", "RPC URL不存在", BusRespCode.RESP_300), //
  /** RPC连接超时 */
  ERROR_302("302", "RPC连接超时", BusRespCode.RESP_300), //
  /** 无权限访问服务 */
  ERROR_303("303", "无权限访问服务", BusRespCode.RESP_300), //
  /** 服务方业务异常 */
  ERROR_304("304", "服务方业务异常", BusRespCode.RESP_300), //
  /** RPC调用方法时响应超时 */
  ERROR_305("305", "RPC调用方法时响应超时", BusRespCode.RESP_300), //
  /** 远程服务异常 */
  ERROR_306("306", "远程服务异常", BusRespCode.RESP_300), //
  /** RPC网络异常 */
  ERROR_307("307", "RPC网络异常", BusRespCode.RESP_300),
  /** RPC时序列化异常 */
  ERROR_308("308", "RPC时序列化异常", BusRespCode.RESP_300),
  /** RPC参数错误 */
  ERROR_309("309", "RPC参数错误", BusRespCode.RESP_300),



  /** 转换邮件配置项信息异常 **/
  ERROR_400("400", "转换邮件配置项信息异常", BusRespCode.RESP_400), //
  /** 获取邮件配置模板信息错误 **/
  ERROR_401("401", "获取邮件配置模板信息错误", BusRespCode.RESP_400), //
  /** 获取短信配置模板信息错误 **/
  ERROR_402("402", "获取短信配置模板信息错误", BusRespCode.RESP_400), //
  /** 发送Q消息异常 **/
  ERROR_403("403", "发送Q消息异常", BusRespCode.RESP_400), //
  /** 消息接收者为空 **/
  ERROR_404("404", "消息接收者为空 ", BusRespCode.RESP_400), //
  /** 配置转换失败 **/
  ERROR_405("405", "配置转换失败 ", BusRespCode.RESP_400), //
  /** 通知处理加锁处理失败 **/
  ERROR_406("406", "通知处理加锁处理失败 ", BusRespCode.RESP_400), //
  /** 消息处理加锁处理失败 **/
  ERROR_407("407", "消息处理加锁处理失败 ", BusRespCode.RESP_400), //
  /** 更新通知状态信息失败 **/
  ERROR_408("408", "更新通知状态信息失败  ", BusRespCode.RESP_400), //
  /** 更新消息状态信息失败 **/
  ERROR_409("409", "更新消息状态信息失败 ", BusRespCode.RESP_400), //
  /** 保存消息信息失败 **/
  ERROR_410("410", "保存消息信息失败", BusRespCode.RESP_400), //

  /** 网络端口校验工具错误异常 **/
  ERROR_501("501", "网络工具类流操作错误", BusRespCode.RESP_400),
  /** 非法ip指定 **/
  ERROR_502("502", "非法ip指定", BusRespCode.RESP_400),
  /** 无有效的IP **/
  ERROR_504("504", "无有效的IP", BusRespCode.RESP_400),

  /** 初始化redis自增失败 */
  ERROR_801("801", "初始化redis自增失败 ", BusRespCode.RESP_801), //
  /** 获取redis序列号失败 */
  ERROR_802("802", "获取redis序列号失败 ", BusRespCode.RESP_802), //

  ERROR_900("900", "NTFCT返回信息为空", BusRespCode.RESP_900), //
  ERROR_901("901", "NTFCT返回未知错误", BusRespCode.RESP_901), //

  ;
  private String code;

  private String msg;

  private BusRespCode respCode;

  public static final List<String> RESULT_DETAIL_MSG_CODE_LIST = Arrays.asList(new String[] {"001",
      "002"});

  private BusErrorCode(String code, String msg, BusRespCode respCode) {
    this.code = code;
    this.msg = msg;
    this.respCode = respCode;
  }

  public String wrapperErrorMsg(String system, String errorMsg) {
    return this.getMsg() + "[异常系统:[" + system + "],异常信息:" + errorMsg + "]";
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  /**
   * Getter method for property <tt>cipRespCode</tt>.
   * 
   * @return property value of cipRespCode
   */
  public BusRespCode getRespCode() {
    return this.respCode;
  }

  @Override
  public String getErrorCode() {
    return this.code;
  }

  @Override
  public String getErrorMsg() {
    return this.msg;
  }

  @Override
  public IRespCode getRespCodeObj() {
    return this.respCode;
  }

  @Override
  public IErrorCode getCurrentCodeObj() {
    return this;
  }

  @Override
  public IErrorCode getCurrentObjByCode(String code) {
    for (CommonErrorCode errorCode : CommonErrorCode.values()) {
      if (StringUtils.isNotBlank(code) && StringUtils.equals(code, errorCode.getCode())) {
        return errorCode;

      }
    }
    return null;
  }

}

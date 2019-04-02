/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.code;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * @author GSZY
 * @version $Id: BusOutRespCode.java, v 0.1 2018年8月21日 下午7:22:47 GSZY Exp $
 */
public class BusOutRespCode {

  public interface SmsRespCode {
    public static final String SMS_RESP_000000 = "000000"; // 成功
    public static final String SMS_RESP_208099 = "208999"; // 未知原因异常
    public static final String SMS_RESP_208001 = "208001"; // 参数错误
    public static final String SMS_RESP_208100 = "208100"; // 无访问权限
    public static final String SMS_RESP_208101 = "208101"; // 有重复请求数据
    public static final String SMS_RESP_208102 = "208102"; // 资源竞争
    public static final String SMS_RESP_208200 = "208200"; // 数据库异常
    public static final String SMS_RESP_208300 = "208300"; // 调用外部系统服务超时或异常
    public static final String SMS_RESP_208400 = "208400"; // 系统内部异常
    public static final String SMS_RESP_208500 = "208500"; // 缓存异常
    /** 可重试错误 **/
    @SuppressWarnings("serial")
    public static final Map<String, String> SMS_ERROR_PARAM_RETRY = new HashMap<String, String>() {
      {}
    };
    /** 不可重试异常 参数错误 **/
    @SuppressWarnings("serial")
    public static final Map<String, String> SMS_PARAM_ERROR_UNRETRY =
        new HashMap<String, String>() {
          {
            put(SMS_RESP_208001, "参数错误");
            put(SMS_RESP_208101, "有重复请求数据");
            put(SMS_RESP_208102, "资源竞争");
            put(SMS_RESP_208200, "数据库异常");
            put(SMS_RESP_208500, "缓存异常");
          }
        };
    /** 不可重试异常 (内部错误) **/
    @SuppressWarnings("serial")
    public static final Map<String, String> SMS_INTER_ERROR_PARAM_UNRETRY =
        new HashMap<String, String>() {
          {
            put(SMS_RESP_208100, "无访问权限");
            put(SMS_RESP_208099, "未知原因异常");
            put(SMS_RESP_208400, "系统内部异常");
            put(SMS_RESP_208300, "调用外部系统服务超时或异常");
          }
        };
  }

}

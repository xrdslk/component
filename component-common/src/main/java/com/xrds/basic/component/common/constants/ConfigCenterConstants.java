/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.constants;

/**
 * 
 * @author liukai
 * @version $Id: ConfigCenterConstants.java, v 0.1 2017年6月14日 下午2:38:11 liukai Exp $
 */
public class ConfigCenterConstants {
  /** 服务器网段 **/
  public static final String CIP_IP_NETWORK_SEGMENT = "cip_ip_network_segment";


  /** 短信发送服务码 **/
  public static final String SMS_SEND_SERVER_CODE = "400-920-8285";

  /** 登陆短信验证码校验配置项 **/
  public static final String LOGIN_SMS_CHECK_CONFIG = "login_sms_check_config";

  /** 一天内同一手机号请求次数配置项 **/
  public static final String SAME_TELEPHONE_VERIFICATION_TIMES =
      "same_telephone_verification_times";
  /** 短信验证码白名单 **/
  public static final String VERIFICATION_CODE_WHITE_LIST = "verification_code_white_list";


  /** 设备信息中的表情符号过滤 **/
  public static final String DEVICE_INFO_WIFI_FILTER_PREG = "device_info_wifi_filter_preg";

  /** 邮件默认属性配置 **/
  public static final String EMAIL_COMMON_TEMPLATE = "email_common_template";// 通用模板

  /** 外部接口重试次数 */
  public static final String RETRY_TIMES = "retry_times";

  /** 外部系统返回码列表 */
  public static final String COMMON_RESPCODE_NAMES = "common_respcode_names";
  /** 外部系统返回描述列表 */
  public static final String COMMON_RESPMSG_NAMES = "common_respmsg_names";
  /** 交互请求流水 */
  public static final String COMMON_REQUEST_NO = "common_request_no";
  /** 交互业务ID */
  public static final String COMMON_BIZ_ID = "common_biz_id";
  /** 需排除的的外部异常码 */
  public static final String EXCLUED_ERROR_CODES = "exclued_error_codes";
  /** 批处理数量 */
  public static final Integer BATCH_NUM = 1000;


  /** 加锁超时邮件通知 */
  public static final String LOCK_OVER_TIME_NOTICE = "lock_over_time_notice";
  /** 默认超时时间 */
  public static final String DEFAULT_OVER_TIME = "default_over_time";
  /** 默认超时时间 */
  public static final String LOCK_OVER_TIME = "lock_over_time";

}

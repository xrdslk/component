/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.constants;


/**
 * 
 * @author liukai
 * @version $Id: CommonApplicationConstants.java, v 0.1 2017年6月13日 上午11:43:32 liukai Exp $
 */
public abstract interface CommonApplicationConstants {

  public static abstract interface SysContents {
    // public static final String APP_NAME_CONFIG = "STRUCTURE";
  }

  public static abstract interface RedisKeyContants {
    public static final String KEY_CUSTOMER_ID = "customerId";
    public static final String KEY_EXPIRE_DATE = "expireDate";
  }

  public static abstract interface TaskLockContents {
    public static final int TASK_LOCK_FINISHED = 1;
    public static final int TASK_LOCK_UNFINISHED = 0;
  }

  public static abstract interface OverTimeContants {
    /** 区域信息过期时间 180天，单位为天 **/
    public static final long REDIS_OVER_TIME_AREA_INFO = 180l;
  }

  public static abstract interface SequencePrefix {
    public static final String REDIS_SERIAL_KEY = "REDIS_SERIAL_KEY";
    public static final String COMMON_PREFIX = "C";
    public static final String PLATFORM_USER_PREFIX = "P";
    public static final String BANK_CARD_BIND_PREFIX = "B";
    public static final String RECHARGE_PREFIX = "R";
    public static final String WITHDRAW_PREFIX = "W";
    public static final String INVESTMENT_INFO_PREFIX = "PT";
    public static final String PRODUCT_TRANS_PREFIX = "PI";
    public static final String TRADE_BATCH_PREFIX = "TB";
    public static final String PROJECT_TRANS_PREFIX = "BI";
    public static final String LOAN_NO_PREFIX = "DR";
    public static final String FINANCIAL_PRODUCT_PREFIX = "FP";
    public static final String PROJECT_NO_PREFIX = "BD";
    public static final String BORROW_INFO_PREFIX = "BI";
    public static final String COLLATERAL_INFO_PREFIX = "CI";
    public static final String PROJECT_AUDIT_PREFIX = "PA";
    public static final String AUDIT_CERTIFICATION_PREFIX = "AC";
    public static final String ASSET_MATERIAL_PREFIX = "AM";
    public static final String BORROW_REPAYMENT_PLAN_PREFIX = "CP";
    public static final String PROJECT_TRADE_ORDER_PREFIX = "BT";
    public static final String BUSSESS_AUDIT_PREFIX = "PA";
    public static final String CAPITAL_RECORD_LOG_PREFIX = "CL";
    public static final String PRODUCT_CASH_PLAN_PREFIX = "PC";
    public static final String RECONCILIATIONS_FILE_RECORD_PREFIX = "RF";
    public static final String ADVANCE_WITHDRAWAL_PREFIX = "DR";
    public static final String AGREEMENT_NO_PREFIX = "XY";
    public static final String CONTRACT_NO_PREFIX = "HT";
    public static final String WITHDRAW_AUDIT_NO_PREFIX = "WA";
    public static final String FUNDS_TRANSFER_NO_PREFIX = "FT";
  }
  public interface SequenceConstants {
    public static final int SEQUENCE_LENGTH_7 = 7;
    public static final int SEQUENCE_LENGTH_13 = 13;
    public static final int SEQUENCE_LENGTH_14 = 14;
  }
  /** 锁前缀 **/
  public interface LockPrefix {
    public static final String DEFAULT_PREFIX = "L";
  }

  public static abstract interface NoticeResutlDealType {
    public static final String DEAL_TYEP_OVER_TIME = "overTime";
    public static final String DEAL_TYEP_FAILURE = "failure";
    public static final String DEAL_TYEP_LOCK_OVER_TIME = "lockOverTime";
  }


  public interface CustTimesConstants {
    /** 默认密码输入错误次数 **/
    public static final int DEFAULT_PASSWORD_ERROR_TIMES_LENGTH = 5;

    /** 1分钟验证码生成间隔 **/
    public static final long DEFAULT_VERIFICATION_CODE_INTERVAL_MIN = 1l;
    /** 1天验证码错误次数刷新 **/
    public static final long DEFAULT_VERIFICATION_CODE_ERROR_TIMES_MIN = 24 * 60l;
    /** 30分钟验证码失效刷新 **/
    public static final long DEFAULT_VERIFICATION_CODE_MIN = 30l;
  }

  public interface CustTokenConstants {
    /** 默认令牌过期时间,单位为小时 **/
    public static final int DEFAULT_EXPIRE_TIME = 8;
    /** 默认令牌过期时间,单位为分钟 **/
    public static final long DEFAULT_EXPIRE_TIME_BY_MIN = 8 * 60l;

  }

  public interface LockConstants {
    // 锁业务类型
    public static final String LOCK_BUSINESS_TYPE = "lockBusinessType";
    // 锁业务ID
    public static final String LOCK_BUSINESS_ID = "lockBusinessId";

  }

  public interface CustBeanConstants {
    // 响应信息
    public static final String RESPONSE_OBJECT = "responseObject";
    // 会员基本信息
    public static final String CUST_BASE_INFO = "lockBusinessType";
    // 个人会员信息
    public static final String PERSONAL_BASE_INFO = "personalBaseInfo";
    // 会员登录信息
    public static final String CUST_LOGIN_INFO = "personalBaseInfo";
    // 操作员信息
    public static final String CUST_OPERATER_INFO = "custOperaterInfo";

  }


  public interface PregConstants {

    // 过滤表情符正则
    public static final String DEFAULT_DEVICE_INFO_WIFI_FILTER_PREG =
        "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
  }
  /** 消息类型 */
  public interface MessageTypeContents {
    public static final String MESSAGE_EMAIL_TYPE = "email";// 邮件类型
    public static final String MESSAGE_SMS_TYPE = "sms";// 短信类型
    public static final String MESSAGE_MQ_TYPE = "mq";// mq类型
  }

  /** MQ 内容类型 */
  public interface MqTypeContents {
    public static final String MQ_STRING_TYPE = "String";// String类型
    public static final String MQ_MAP_TYPE = "Map";// Map类型
  }

  public interface SystemConstants {
    /** 幂等性接口请求次数 */
    public static final int CONNET_NUMBER_SYS = 3;
    /** 超时时间（根据创建时间）分钟为单位 **/
    public static final int DEFAULT_OVER_TIME = 30;
    /** 锁定超时时间（根据创建时间）分钟为单位 **/
    public static final int LOCK_OVER_TIME = 30;
    /** 通知默认最大次数 **/
    public static final int DEFAULT_MAX_SEND_TIMES = 6;
    /** UTF-8 encoding */
    public static final String UTF_8 = "UTF-8";
    /** GBK encoding */
    public static final String GBK = "GBK";

    public static final String THIRD_INSTITUTION_CHANNEL = "10001003";

    public static final String PLATFORM_CHANNEL = "01005";

    public static final String CDSBO_SWITCH = "HK";

    public static final String CDSBO_SWITCH_NAME = "cdsboSwitch";

    public static final String CDSBO_CONN_MODE_NAME = "cdsboConnMode";

    public static final String CDSBO_CONN_DIRECT_MODE = "DIRECT";

    public static final String CDSBO_CONN_GATEWAY_MODE = "GATEWAY";

    public static String IDENTITY_TYPE_IDCARD = "111";

    public static String DOMAIN_ID = "GS001";
  }

  public interface ServiceNameConstants {

    public static final String CDSBOHK_INTEGRATION_SERVICE = "cdsbohkIntegrationService";
    public static final String CDSBOS_INTEGRATION_SERVICE = "cdsbosIntegrationService";

    public static final String CHECKFILEHK_INTEGRATION_SERVICE = "checkFilehkIntegrationService";
    public static final String CHECKFILE_INTEGRATION_SERVICE = "checkFileIntegrationService";


    public static final String QUERYHK_INTEGRATION_SERVICE = "queryhkIntegrationService";
    public static final String QUERY_INTEGRATION_SERVICE = "queryIntegrationService";

    public static final String TRADEHK_INTEGRATION_SERVICE = "tradehkIntegrationService";
    public static final String TRADE_INTEGRATION_SERVICE = "tradeIntegrationService";

  }

}

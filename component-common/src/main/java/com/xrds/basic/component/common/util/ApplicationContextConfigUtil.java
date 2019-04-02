/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.util;

import org.apache.commons.lang3.StringUtils;

import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.frameworks.commons.startup.ApplicationContextConfig;

/**
 * 
 * @author liukai
 * @version $Id: ApplicationContextConfigUtil.java, v 0.1 2017年6月14日 下午2:34:49 liukai Exp $
 */
public class ApplicationContextConfigUtil {

  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_COMMON.getLogName());

  public static String getString(String config, String defaultValue) {
    LOGGER.info("获取配置项信息,配置项名称config=[{}],默认值defaultValue=[{}]", config, defaultValue);
    try {
      if (StringUtils.isBlank(config)) {
        LOGGER.info("配置项为空，返回默认值[{}]", defaultValue);
        return defaultValue;
      }
      String returnStr = ApplicationContextConfig.get(config);
      if (StringUtils.isBlank(returnStr)) {
        LOGGER.info("配置项[{}]，默认值[{}]", config, defaultValue);
        return defaultValue;
      }
      LOGGER.info("获取配置项信息,配置项名称config=[{}],返回值returnStr[{}]", config, returnStr);
      return returnStr;
    } catch (Exception e) {
      LOGGER.error("获取配置异常", e);
      return defaultValue;
    }
  }

  public static Long getLong(String config, Long defaultValue) {
    LOGGER.info("获取配置项信息,配置项名称config=[{}],默认值defaultValue=[{}]", config, defaultValue);
    try {
      if (StringUtils.isBlank(config)) {
        LOGGER.info("配置项为空，返回默认值[{}]", defaultValue);
        return defaultValue;
      }
      String returnStr = ApplicationContextConfig.get(config);
      if (StringUtils.isBlank(returnStr)) {
        LOGGER.info("配置项[{}]，默认值[{}]", config, defaultValue);
        return defaultValue;
      }
      LOGGER.info("获取配置项信息,配置项名称config=[{}],返回值returnStr[{}]", config, returnStr);
      return Long.parseLong(returnStr);
    } catch (Exception e) {
      LOGGER.error("获取配置异常", e);
      return defaultValue;
    }
  }

  public static Double getDouble(String config, Double defaultValue) {
    LOGGER.info("获取配置项信息,配置项名称config=[{}],默认值defaultValue=[{}]", config, defaultValue);
    try {
      if (StringUtils.isBlank(config)) {
        LOGGER.info("配置项为空，返回默认值[{}]", defaultValue);
        return defaultValue;
      }
      String returnStr = ApplicationContextConfig.get(config);
      if (StringUtils.isBlank(returnStr)) {
        LOGGER.info("配置项[{}]，默认值[{}]", config, defaultValue);
        return defaultValue;
      }
      LOGGER.info("获取配置项信息,配置项名称config=[{}],返回值returnStr[{}]", config, returnStr);
      return Double.parseDouble(returnStr);
    } catch (Exception e) {
      LOGGER.error("获取配置异常", e);
      return defaultValue;
    }
  }

  public static Boolean getBoolean(String config, Boolean defaultValue) {
    LOGGER.info("获取配置项信息,配置项名称config=[{}],默认值defaultValue=[{}]", config, defaultValue);
    try {
      if (StringUtils.isBlank(config)) {
        LOGGER.info("配置项为空，返回默认值[{}]", defaultValue);
        return defaultValue;
      }
      String returnStr = ApplicationContextConfig.get(config);
      if (StringUtils.isBlank(returnStr)) {
        LOGGER.info("配置项[{}]，默认值[{}]", config, defaultValue);
        return defaultValue;
      }
      Boolean b = false;
      if ("true".equalsIgnoreCase(returnStr)) {
        b = true;
      }
      LOGGER.info("获取配置项信息,配置项名称config=[{}],返回值returnStr[{}]", config, returnStr);
      return b;
    } catch (Exception e) {
      LOGGER.error("获取配置异常", e);
      return defaultValue;
    }
  }



  public static Integer getInteger(String config, Integer defaultValue) {
    LOGGER.info("获取配置项信息,配置项名称config=[{}],默认值defaultValue=[{}]", config, defaultValue);
    try {
      if (StringUtils.isBlank(config)) {
        LOGGER.info("配置项为空，返回默认值[{}]", defaultValue);
        return defaultValue;
      }
      String returnStr = ApplicationContextConfig.get(config);
      if (StringUtils.isBlank(returnStr)) {
        LOGGER.info("配置项[{}]，默认值[{}]", config, defaultValue);
        return defaultValue;
      }
      LOGGER.info("获取配置项信息,配置项名称config=[{}],返回值returnStr[{}]", config, returnStr);
      return Integer.parseInt(returnStr);
    } catch (Exception e) {
      LOGGER.error("获取配置异常", e);
      return defaultValue;
    }
  }



}

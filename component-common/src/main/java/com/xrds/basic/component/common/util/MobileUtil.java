package com.xrds.basic.component.common.util;

/**
 * 
 * 坤普 Copyright (c) 2016-2018 KunPu,Inc.All Rights Reserved.
 */

import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.exception.CommonRuntimeException;
import com.xrds.basic.component.common.code.BusErrorCode;

/**
 * 手机号验证工具类
 * 
 * @author xiaosheng.zhang
 * @version $Id: MobileUtil.java, v 0.1 2018年3月7日 下午2:43:04 xiaosheng.zhang Exp $
 */
public class MobileUtil {
  private static final String MOBILE_REGEX = "^1[34578]\\d{9}$";

  /**
   * 校验手机号格式是正确
   * 
   * @param cardNo
   * @return
   */
  public static boolean verify(String mobile) throws CommonException {
    if (mobile == null) {
      throw new CommonRuntimeException(BusErrorCode.ERROR_754);
    }
    return mobile.matches(MOBILE_REGEX);
  }

}

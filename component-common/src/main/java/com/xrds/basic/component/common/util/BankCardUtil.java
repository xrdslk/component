package com.xrds.basic.component.common.util;

/**
 * 
 * 坤普 Copyright (c) 2016-2018 KunPu,Inc.All Rights Reserved.
 */

import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.exception.CommonRuntimeException;
import com.xrds.basic.component.common.code.BusErrorCode;

/**
 * 银行卡工具类
 * 
 * @author yuyangzhi
 * @version $Id: BankCardUtil.java, v 0.1 2018年1月12日 下午2:43:04 yuyangzhi Exp $
 */
public class BankCardUtil {

  /**
   * 校验银行卡格式是正确
   * 
   * @param cardNo
   * @return
   */
  public static boolean verify(String cardNo) throws CommonException {
    if (cardNo == null || !(cardNo.matches("[0-9]+"))) {
      throw new CommonRuntimeException(BusErrorCode.ERROR_752);
    }
    int[] cardNoArr = new int[cardNo.length()];
    for (int i = 0; i < cardNo.length(); i++) {
      cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
    }
    for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
      cardNoArr[i] <<= 1;
      cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
    }
    int sum = 0;
    for (int i = 0; i < cardNoArr.length; i++) {
      sum += cardNoArr[i];
    }
    return sum % 10 == 0;
  }



}

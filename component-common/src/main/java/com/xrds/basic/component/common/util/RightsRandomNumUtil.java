/**
 * 
 * 坤普 Copyright (c) 2016-2018 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;

/**
 * 随机权益金额工具类
 * 
 * @author GSZY
 * @version $Id: RightsRandomNumUtil.java, v 0.1 2018年12月20日 下午3:06:02 GSZY Exp $
 */
public class RightsRandomNumUtil {
  private static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_COMMON.getLogName());

  private static final String UPPER_LIMIT_AMOUNT = "5";

  private static final String LOWER_LIMIT_AMOUNT = "0.01";


  /**
   * 随机红包
   * 
   * @param remainSize 剩余的红包数量
   * @param remainMoney 剩余的钱
   * @return
   */
  public static double getRandomMoney(int remainSize, double remainMoney) {
    if (remainSize == 1) {
      // return (double) Math.round(remainMoney * 100) / 100;
      return Math.floor(remainMoney * 100) / 100;
    }
    double min = 0.01; //
    double max = remainMoney / remainSize * 2;
    double money = new Random().nextDouble() * max;
    money = money <= min ? 0.01 : money;
    money = Math.floor(money * 100) / 100;
    return money;
  }

  /**
   * 随机红包
   * 
   * @param remainSize 剩余的红包数量
   * @param remainMoney 剩余的钱
   * @return
   */
  public static BigDecimal getRandomMoney(int remainSize, BigDecimal remainMoney) {
    if (remainSize == 1) {
      if (remainMoney.compareTo(new BigDecimal(UPPER_LIMIT_AMOUNT)) >= 0) {
        return new BigDecimal(new Random().nextDouble()).multiply(
            new BigDecimal(UPPER_LIMIT_AMOUNT)).setScale(2, BigDecimal.ROUND_DOWN);
      } else {
        return new BigDecimal(new Random().nextDouble()).multiply(remainMoney).setScale(2,
            BigDecimal.ROUND_DOWN);
      }

      // return remainMoney.setScale(2, BigDecimal.ROUND_DOWN);
    }
    BigDecimal min = new BigDecimal(LOWER_LIMIT_AMOUNT); //
    BigDecimal max =
        remainMoney.divide(new BigDecimal(remainSize).setScale(2, BigDecimal.ROUND_HALF_UP), 2,
            BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("2"));
    BigDecimal money =
        new BigDecimal(new Random().nextDouble()).multiply(max).setScale(2, BigDecimal.ROUND_DOWN);

    if (money.compareTo(new BigDecimal(UPPER_LIMIT_AMOUNT)) >= 0) {
      money =
          new BigDecimal(new Random().nextDouble()).multiply(new BigDecimal(UPPER_LIMIT_AMOUNT))
              .setScale(2, BigDecimal.ROUND_DOWN);
    }

    money = money.compareTo(min) <= 0 ? min : money;
    return money;
  }

  /**
   * 发红包算法，金额参数以分为单位
   * 
   * @param totalAmount
   * @param totalPeopleNum
   * @return
   */
  public static List<Double> getRandomMoneyList(int totalSize, double totalAmount) {
    double remainMoney = totalAmount;
    int remainSize = totalSize;
    List<Double> amountList = new ArrayList<Double>();
    for (int i = 0; i < totalSize; i++) {
      if (remainSize == 1) {
        // amountList.add(new BigDecimal(Math.round(remainMoney * 100) / 100).setScale(2,
        // BigDecimal.ROUND_DOWN).doubleValue());
        amountList
            .add(new BigDecimal(remainMoney).setScale(2, BigDecimal.ROUND_DOWN).doubleValue());
        break;
      }
      double min = 0.01; //
      double max = remainMoney / remainSize * 2;
      double money = new Random().nextDouble() * max;
      money = money <= min ? 0.01 : money;
      money = Math.floor(money * 100) / 100;
      remainSize--;
      remainMoney -= money;
      amountList.add(money);
    }
    return amountList;
  }

  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    // LOGGER.info("金额：[{}]", new BigDecimal("0.01"));
    // LOGGER.info("金额：[{}]", new BigDecimal(0.01));
    // LOGGER.info("金额：[{}]", new BigDecimal(0.01d));
    int totalCount = 200;
    int remainCount = totalCount;
    BigDecimal totalAmount = new BigDecimal(100);
    BigDecimal remainAmount = totalAmount;
    BigDecimal sendAmount = BigDecimal.ZERO;
    // double sendAmount = 0d;
    // double totalAmount = 10000d;
    // double remainAmount = totalAmount;
    for (int i = 0; i < totalCount; i++) {
      BigDecimal moneyAmount = getRandomMoney(remainCount, remainAmount);
      remainAmount = remainAmount.subtract(moneyAmount);
      sendAmount = sendAmount.add(moneyAmount);
      // double moneyAmount = getRandomMoney(remainCount, remainAmount);
      // remainAmount = remainAmount - moneyAmount;
      // sendAmount = sendAmount + moneyAmount;
      remainCount--;
      LOGGER.info("抢到金额：[{}],剩余个数:[{}],剩余金额[{}],红包总量[{}]", moneyAmount, remainCount, remainAmount,
          sendAmount);
    }



    // List<Double> amountList = getRandomMoneyList(3, 1);
    //
    // for (Double amount : amountList) {
    // LOGGER.info("抢到金额：[{}]", amount);
    // }

  }

}

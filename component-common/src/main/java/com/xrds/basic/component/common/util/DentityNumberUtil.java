package com.xrds.basic.component.common.util;

/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.kunpu.frameworks.commons.code.CommonErrorCode;
import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.exception.CommonRuntimeException;
import com.kunpu.frameworks.commons.exception.check.CheckException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.xrds.basic.component.common.code.BusErrorCode;

/**
 * 
 * @author liukai
 * @version $Id: DentityNumberUtil.java, v 0.1 2017年6月18日 下午1:43:47 liukai Exp $
 */
public class DentityNumberUtil {

  private DentityNumberUtil() {}

  private static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_COMMON.getLogName());
  /** 中国公民身份证号码最小长度。 */
  public static final int CHINA_ID_MIN_LENGTH = 15;

  /** 中国公民身份证号码最大长度。 */
  public static final int CHINA_ID_MAX_LENGTH = 18;

  /** 每位加权因子 */
  private static final int[] POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

  /**
   * 身份证号码验证 1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码， 八位数字出生日期码，三位数字顺序码和一位数字校验码。
   * 2、地址码(前六位数） 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 3、出生日期码（第七位至十四位）
   * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。 4、顺序码（第十五位至十七位）
   * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号， 顺序码的奇数分配给男性，偶数分配给女性。 5、校验码（第十八位数） （1）十七位数字本体码加权求和公式 S =
   * Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和 Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8
   * 4 2 1 6 3 7 9 10 5 8 4 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1
   * 0 X 9 8 7 6 5 4 3 2
   */

  /**
   * 功能：身份证的有效验证
   * 
   * @param idStr 身份证号
   * @return 有效：返回true 无效：返回RuntimeException
   * @throws CheckException
   * @throws ParseException
   */
  public static boolean idCardValidate(String idStr) throws CheckException {
    String[] valCodeArr = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
    String[] wi =
        {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
    String ai = "";
    // ================ 号码的长度 15位或18位 ================
    if (idStr.length() != 15 && idStr.length() != 18) {
      // throw new
      // CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":身份证号码长度应该为15位或18位。"));
      throw new CheckException(BusErrorCode.ERROR_753);
    }
    // =======================(end)========================

    // ================ 数字 除最后以为都为数字 ================
    if (idStr.length() == 18) {
      ai = idStr.substring(0, 17);
    } else if (idStr.length() == 15) {
      ai = idStr.substring(0, 6) + "19" + idStr.substring(6, 15);
    }
    if (!isNumeric(ai)) {
      // throw new CommonRuntimeException(
      // BusErrorCode.ERROR_753.extendMsg(":身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。"));
      throw new CheckException(BusErrorCode.ERROR_753);
    }
    // =======================(end)========================

    // ================ 出生年月是否有效 ================
    String strYear = ai.substring(6, 10);// 年份
    String strMonth = ai.substring(10, 12);// 月份
    String strDay = ai.substring(12, 14);// 月份
    if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
      // throw new CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":身份证生日无效。"));
      throw new CheckException(BusErrorCode.ERROR_753);
    }

    GregorianCalendar gc = new GregorianCalendar();
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    try {
      if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
          || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
        // throw new
        // CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":身份证生日不在有效范围。"));
        throw new CheckException(BusErrorCode.ERROR_753);
      }
      if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
        // throw new CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":身份证月份无效。"));
        throw new CheckException(BusErrorCode.ERROR_753);
      }
      if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
        // throw new CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":身份证日期无效。"));
        throw new CheckException(BusErrorCode.ERROR_753);
      }
      // =====================(end)=====================

      // ================ 地区码时候有效 ================
      Map<String, String> h = getAreaCode();
      if (h.get(ai.substring(0, 2)) == null) {
        // throw new
        // CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":身份证地区编码错误。"));
        throw new CheckException(BusErrorCode.ERROR_753);
      }
      // ==============================================

      // ================ 判断最后一位的值 ================
      int totalmulAiWi = 0;
      for (int i = 0; i < 17; i++) {
        totalmulAiWi =
            totalmulAiWi + Integer.parseInt(String.valueOf(ai.charAt(i))) * Integer.parseInt(wi[i]);
      }
      int modValue = totalmulAiWi % 11;
      String strVerifyCode = valCodeArr[modValue];
      ai = ai + strVerifyCode;

      if (idStr.length() == 18) {
        if (!ai.equals(idStr)) {
          // throw new
          // CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":身份证无效，不是合法的身份证号码。"));
          throw new CheckException(BusErrorCode.ERROR_753);
        }
      } else {
        return true;
      }
      // =====================(end)=====================
      return true;
    } catch (NumberFormatException e) {
      // throw new CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":数字格式化异常。"));
      throw new CheckException(BusErrorCode.ERROR_753);
    } catch (java.text.ParseException e) {
      // throw new CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":数字转化异常。"));
      throw new CheckException(BusErrorCode.ERROR_753);
    }
  }

  /**
   * 功能：设置地区编码
   * 
   * @return Hashtable 对象
   */
  private static Map<String, String> getAreaCode() {
    Map<String, String> hashMap = new HashMap<String, String>();
    hashMap.put("11", "北京");
    hashMap.put("12", "天津");
    hashMap.put("13", "河北");
    hashMap.put("14", "山西");
    hashMap.put("15", "内蒙古");
    hashMap.put("21", "辽宁");
    hashMap.put("22", "吉林");
    hashMap.put("23", "黑龙江");
    hashMap.put("31", "上海");
    hashMap.put("32", "江苏");
    hashMap.put("33", "浙江");
    hashMap.put("34", "安徽");
    hashMap.put("35", "福建");
    hashMap.put("36", "江西");
    hashMap.put("37", "山东");
    hashMap.put("41", "河南");
    hashMap.put("42", "湖北");
    hashMap.put("43", "湖南");
    hashMap.put("44", "广东");
    hashMap.put("45", "广西");
    hashMap.put("46", "海南");
    hashMap.put("50", "重庆");
    hashMap.put("51", "四川");
    hashMap.put("52", "贵州");
    hashMap.put("53", "云南");
    hashMap.put("54", "西藏");
    hashMap.put("61", "陕西");
    hashMap.put("62", "甘肃");
    hashMap.put("63", "青海");
    hashMap.put("64", "宁夏");
    hashMap.put("65", "新疆");
    hashMap.put("71", "台湾");
    hashMap.put("81", "香港");
    hashMap.put("82", "澳门");
    hashMap.put("91", "国外");
    return hashMap;
  }

  /**
   * 功能：判断字符串是否为数字
   * 
   * @param str
   * @return
   */
  private static boolean isNumeric(String str) {
    Pattern pattern = Pattern.compile("[0-9]*");
    Matcher isNum = pattern.matcher(str);
    return isNum.matches();
  }

  /**
   * 功能：判断字符串是否为日期格式
   * 
   * @param str
   * @return
   */
  public static boolean isDate(String strDate) {
    Pattern pattern =
        Pattern
            .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    Matcher m = pattern.matcher(strDate);
    return m.matches();
  }

  /**
   * 根据15位的身份证号码获得18位身份证号码
   * 
   * @param fifteenIDCard 15位的身份证号码
   * @return 升级后的18位身份证号码
   * @throws Exception 如果不是15位的身份证号码，则抛出异常
   */
  public static String getEighteenIDCard(String fifteenIDCard) throws CommonException {
    if (fifteenIDCard != null && fifteenIDCard.length() == 15) {
      StringBuilder sb = new StringBuilder();
      sb.append(fifteenIDCard.substring(0, 6)).append("19").append(fifteenIDCard.substring(6));
      sb.append(getVerifyCode(sb.toString()));
      return sb.toString();
    } else {
      // throw new CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":不是15位的身份证。"));
      throw new CommonRuntimeException(BusErrorCode.ERROR_753);
    }
  }

  /**
   * 获取校验码
   * 
   * @param idCardNumber 不带校验位的身份证号码（17位）
   * @return 校验码
   * @throws Exception 如果身份证没有加上19，则抛出异常
   */
  public static char getVerifyCode(String idCardNumber) throws CommonException {
    if (idCardNumber == null || idCardNumber.length() < 17) {
      // throw new CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":不合法的身份证号码。"));
      throw new CommonRuntimeException(BusErrorCode.ERROR_753);
    }
    char[] ai = idCardNumber.toCharArray();
    int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    char[] verifyCode = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    int s = 0;
    int y;
    for (int i = 0; i < wi.length; i++) {
      s += (ai[i] - '0') * wi[i];
    }
    y = s % 11;
    return verifyCode[y];
  }

  /**
   * 校验18位的身份证号码的校验位是否正确
   * 
   * @param idCardNumber 18位的身份证号码
   * @return
   * @throws Exception
   */
  public static boolean verify(String idCardNumber) throws CommonException {
    if (idCardNumber == null || idCardNumber.length() != 18) {
      // throw new CommonRuntimeException(BusErrorCode.ERROR_753.extendMsg(":不是18位的身份证号码。"));
      throw new CommonRuntimeException(BusErrorCode.ERROR_753);
    }
    idCardNumber = idCardNumber.toUpperCase();
    return getVerifyCode(idCardNumber) == idCardNumber.charAt(idCardNumber.length() - 1);
  }

  /**
   * 根据身份编号获取年龄
   * 
   * @param idCard 身份编号
   * @return 年龄
   */
  public static int getAgeByIdCard(String idCard) {

    if (StringUtils.isBlank(idCard)) {
      return 0;
    }

    int iAge = 0;
    String idCardConver = idCard;
    if (idCard.length() == CHINA_ID_MIN_LENGTH) {
      idCardConver = conver15CardTo18(idCard);
    }
    String year = idCardConver.substring(6, 10);
    Calendar cal = Calendar.getInstance();
    int iCurrYear = cal.get(Calendar.YEAR);
    iAge = iCurrYear - Integer.valueOf(year);
    return iAge;
  }

  /**
   * 根据身份编号获取生日
   * 
   * @param idCard 身份编号
   * @return 生日(yyyyMMdd)
   */
  public static String getBirthByIdCard(String idCard) {
    if (StringUtils.isBlank(idCard)) {
      return null;
    }

    Integer len = idCard.length();
    String idCardConver = idCard;
    if (len < CHINA_ID_MIN_LENGTH) {
      return null;
    } else if (len == CHINA_ID_MIN_LENGTH) {
      idCardConver = conver15CardTo18(idCard);
    }

    return idCardConver.substring(6, 14);
  }

  /**
   * 根据身份编号获取性别
   * 
   * @param idCard 身份编号
   * @return 性别(1：男; 0：女; N：未知)
   */
  public static String getGenderByIdCard(String idCard) {
    if (StringUtils.isBlank(idCard)) {
      return null;
    }
    String sGender = "N";
    String idCardConver = idCard;
    if (idCard.length() == CHINA_ID_MIN_LENGTH) {
      idCardConver = conver15CardTo18(idCard);
    }
    String sCardNum = idCardConver.substring(16, 17);
    if (Integer.parseInt(sCardNum) % 2 != 0) {
      sGender = "1";
    } else {
      sGender = "0";
    }
    return sGender;
  }

  /**
   * 将15位身份证号码转换为18位
   * 
   * @param idCard 15位身份编码
   * @return 18位身份编码
   */
  public static String conver15CardTo18(String idCard) {
    String idCard18 = null;

    if (StringUtils.isBlank(idCard)) {
      LOGGER.warn("需要转换的15位身份证号为空");
      return null;
    }

    if (idCard.length() != CHINA_ID_MIN_LENGTH) {
      LOGGER.warn("身份证号不为15位");
      return null;
    }
    if (isNum(idCard)) {
      // 获取出生年月日
      String birthday = idCard.substring(6, 12);
      Date birthDate = null;
      try {
        birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
      } catch (ParseException e) {
        LOGGER.error(e.getMessage(), e);
        throw new CommonRuntimeException(CommonErrorCode.COMMON_402, e);
      }
      Calendar cal = Calendar.getInstance();
      if (birthDate != null) {
        cal.setTime(birthDate);
      }
      // 获取出生年(完全表现形式,如：2010)
      String sYear = String.valueOf(cal.get(Calendar.YEAR));
      idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
      // 转换字符数组
      char[] cArr = idCard18.toCharArray();
      if (cArr != null) {
        int[] iCard = converCharToInt(cArr);
        int iSum17 = getPowerSum(iCard);
        // 获取校验位
        String sVal = getCheckCode18(iSum17);
        if (sVal.length() > 0) {
          idCard18 += sVal;
        } else {
          LOGGER.warn("转换18位身份证最后一位错误,[{}]", iSum17);
          return null;
        }
      }
    } else {
      LOGGER.warn("15位身份证号不是全数字");
      return null;
    }
    return idCard18;
  }

  /**
   * 把yyyymmdd转成yyyy-MM-dd格式
   * 
   * @param yyyymmdd
   * 
   * @return yyyy-MM-dd
   */
  private static String formatDate(String str) {
    SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
    String sfstr = null;
    try {
      sfstr = sf2.format(sf1.parse(str));
    } catch (ParseException e) {
      LOGGER.error(e.getMessage(), e);
      throw new CommonRuntimeException(CommonErrorCode.COMMON_402, e);
    }
    return sfstr;
  }

  /**
   * 将字符数组转换成数字数组
   * 
   * @param ca 字符数组
   * @return 数字数组
   */
  private static int[] converCharToInt(char[] ca) {
    int len = ca.length;
    int[] iArr = new int[len];
    try {
      for (int i = 0; i < len; i++) {
        iArr[i] = Integer.valueOf(String.valueOf(ca[i]));
      }
    } catch (NumberFormatException e) {
      LOGGER.error(e.getMessage(), e);
      throw new CommonRuntimeException(CommonErrorCode.COMMON_402, e);
    }
    return iArr;
  }

  /**
   * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
   * 
   * @param iArr
   * @return 身份证编码。
   */
  private static int getPowerSum(int[] iArr) {
    int iSum = 0;
    if (POWER.length == iArr.length) {
      for (int i = 0; i < iArr.length; i++) {
        for (int j = 0; j < POWER.length; j++) {
          if (i == j) {
            iSum = iSum + iArr[i] * POWER[j];
          }
        }
      }
    }
    return iSum;
  }

  /**
   * 数字验证
   * 
   * @param val
   * @return 提取的数字。
   */
  private static boolean isNum(String val) {
    return StringUtils.isBlank(val) ? false : val.matches("^[0-9]*$");
  }

  /**
   * 将power和值与11取模获得余数进行校验码判断
   * 
   * @param iSum
   * @return 校验位
   */
  private static String getCheckCode18(int iSum) {
    String sCode = "";
    switch (iSum % 11) {
      case 10:
        sCode = "2";
        break;
      case 9:
        sCode = "3";
        break;
      case 8:
        sCode = "4";
        break;
      case 7:
        sCode = "5";
        break;
      case 6:
        sCode = "6";
        break;
      case 5:
        sCode = "7";
        break;
      case 4:
        sCode = "8";
        break;
      case 3:
        sCode = "9";
        break;
      case 2:
        sCode = "x";
        break;
      case 1:
        sCode = "0";
        break;
      case 0:
        sCode = "1";
        break;
      default:
        break;
    }
    return sCode;
  }

}

/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.frameworks.commons.utils.KpDateUtil;
import com.xrds.basic.component.common.constants.CommonApplicationConstants.SequenceConstants;

/**
 * 
 * @author liukai
 * @version $Id: StringBuilderUtil.java, v 0.1 2017年6月14日 下午2:14:49 liukai Exp $
 */
public class StringBuilderUtil {
  private StringBuilderUtil() {

  }

  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_COMMON.getLogName());

  public static final String SYMBOL_COMMA = ",";
  public static final String SYMBOL_PERIOD = ".";
  public static final String SYMBOL_COLON = "：";
  public static final String SYMBOL_UNDERLINE = "_";
  public static final String SYMBOL_SEMICOLON = ";";

  public static final String CHINESE_STRING_REGULAR = "[\u0391-\uFFE5]";
  public static final String DATE_STRING_REGULAR = "\\d{4}[^\\d]{1}\\d{1,2}[^\\d]{1}\\d{1,2}";


  /**
   * 生成默认系统流水 默认生成seq的长度为7位
   * 
   * @param prefix 业务前缀
   * @param seqNo 系统
   * @return
   */
  public static String buildDefaultSerialNo(String prefix, String seqNo) {
    return buildSerialNo(prefix, seqNo, SequenceConstants.SEQUENCE_LENGTH_13);
  }

  /**
   * 生成系统流水
   * 
   * @param prefix 业务前缀
   * @param seqNo sequence
   * @param length 后缀长度
   * @return
   */
  public static String buildSerialNo(String prefix, String seqNo, int length) {
    String timestampStr = KpDateUtil.formatDate2Str(KpDateUtil.YYYY_MM_DD_HH_MM_SS_SSS);
    StringBuilder sb = new StringBuilder();
    sb.append(prefix);
    sb.append(timestampStr);
    int nSize = seqNo.length();
    if (nSize < length) {
      for (int i = 0; i < length - nSize; i++) {
        sb.append("0");
      }
      sb.append(seqNo);
    } else if (nSize > length) {
      sb.append(seqNo.substring(nSize - length, nSize));
    } else {
      sb.append(seqNo);
    }
    return sb.toString();
  }

  /**
   * 分割字符串
   * 
   * @param str
   * @return
   */
  public static String[] convertToArray(String str, String symbol) {
    if (StringUtils.isBlank(str)) {
      return null;
    }
    String[] arr = str.split(symbol);
    return arr;
  }


  /**
   * 
   * 获取Exception的堆栈新息。用于显示出错来源时使用。
   * 
   * @param e Exception对象
   * @param length 需要的信息长度，如果 <=0,表示全部信息
   * @return String 返回该Exception的堆栈新息
   * @return
   */
  public static String getErrorStack(Exception e) {
    return StringBuilderUtil.getErrorStack(e);
  }

  /**
   * 
   * 获取Exception的堆栈新息。用于显示出错来源时使用。
   * 
   * @param e
   * @return
   */
  public static String getErrorStack(Throwable e) {
    String error = "";
    ByteArrayOutputStream baos = null;
    PrintStream ps = null;
    if (e != null) {
      try {
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        e.printStackTrace(ps);
        return baos.toString();
      } catch (Exception e1) {
        LOGGER.error(e1.getMessage(), e1);
        error = e.toString();
      } finally {
        try {
          if (baos != null) {
            baos.close();
          }
        } catch (IOException e1) {
          LOGGER.error(e1.getMessage(), e1);
        }
        if (ps != null) {
          ps.close();
        }
      }
    }
    return error;
  }

  /**
   * 截取字符
   * 
   * @param value
   * @return
   */
  public static String filterChineseStr(String value, int length) {
    StringBuilder sbStringBuilder = new StringBuilder();
    int valueLength = 0;
    // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
    for (int i = 0; i < value.length(); i++) {
      // 获取一个字符
      String temp = String.valueOf(value.charAt(i));
      // 判断是否为中文字符
      if (temp.matches(CHINESE_STRING_REGULAR))// 中文字符长度为2
        valueLength += 2;
      else
        // 其他字符长度为1
        valueLength += 1;
      if (valueLength > length) {
        break;
      }
      sbStringBuilder.append(temp);
    }
    return sbStringBuilder.toString();
  }

  public static String filterChineseStr(Throwable e, int length) {
    StringBuilder sbStringBuilder = new StringBuilder();
    Thread currentThread = Thread.currentThread();
    sbStringBuilder.append("[").append(currentThread.getId()).append("]");
    sbStringBuilder.append("[").append(currentThread.getName()).append("]");
    sbStringBuilder.append("[").append(KpNetUtil.getCurrentSysIp()).append("]");
    sbStringBuilder.append("[")
        .append(KpDateUtil.formatDate2Str(KpDateUtil.YYYY_MM_DD_HH_MM_SS_SSS)).append("]");
    sbStringBuilder.append(StringBuilderUtil.getErrorStack(e));
    String value = sbStringBuilder.toString();
    int valueLength = 0;
    // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
    for (int i = 0; i < value.length(); i++) {
      if (valueLength > length) {
        break;
      }
      // 获取一个字符
      String temp = String.valueOf(value.charAt(i));
      // 判断是否为中文字符
      if (temp.matches(CHINESE_STRING_REGULAR)) // 中文字符长度为2
        valueLength += 2;
      else
        // 其他字符长度为1
        valueLength += 1;
      sbStringBuilder.append(temp);
    }
    return sbStringBuilder.toString();
  }


  public static String hidePartly(String source, int headSize, int tailSize) {
    if (StringUtils.isBlank(source) || headSize < 0 || tailSize < 0) return source;
    tailSize = tailSize <= source.length() ? tailSize : source.length();
    if ((headSize + tailSize) >= source.length()) return source;
    StringBuilder temp = new StringBuilder();
    temp.append(StringUtils.left(source, headSize));
    // temp.append(StringUtils.repeat("*", source.length() - headSize - tailSize));
    temp.append("*****");
    temp.append(StringUtils.right(source, tailSize));
    return temp.toString();
  }

  public static String hideNamePartly(String source) {
    if (StringUtils.isBlank(source)) return source;
    return source.substring(0, 1) + "**";
  }

  /**
   * 从时间字符串中获取日期
   * 
   * @param time 时间字符串
   * @return 日期字符串
   */
  public static String getDateStringFromTime(String time) {
    if (StringUtils.isBlank(time)) return time;
    Pattern p = Pattern.compile(DATE_STRING_REGULAR);
    Matcher matcher = p.matcher(time);
    return matcher.find() ? matcher.group() : time;
  }
}

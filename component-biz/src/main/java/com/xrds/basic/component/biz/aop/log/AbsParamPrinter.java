package com.xrds.basic.component.biz.aop.log;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;

import com.kunpu.frameworks.commons.utils.BeanCopierUtils;


/**
 * 
 * @author yuyangzhi
 * @version $Id: AbsParamPrinter.java, v 0.1 2018年6月28日 上午11:57:43 yuyangzhi Exp $
 */
public abstract class AbsParamPrinter extends KunPuStopWatch {

  /**
   * 参数打印
   * 
   * @param pj
   * @return
   * @throws Throwable
   */
  public abstract Object printIntegrationParam(ProceedingJoinPoint pj) throws Throwable;

  /**
   * 获取类名
   * 
   * @param pj
   * @return
   */
  protected String getClassName(ProceedingJoinPoint pj) {
    return pj.getSignature().getDeclaringTypeName();
  }

  /**
   * 获取方法名
   * 
   * @param pj
   * @return
   */
  protected String getMethodName(ProceedingJoinPoint pj) {
    return pj.getSignature().getName();
  }

  /**
   * 获取请求的详细类信息
   * 
   * @param pj
   * @return
   */
  protected String getDetailInfo(ProceedingJoinPoint pj) {
    return getClassName(pj) + "." + getMethodName(pj);
  }

  /**
   * 获取请求参数
   * 
   * @param pj
   * @return
   */
  protected String getRequestParam(ProceedingJoinPoint pj) {

    // 获取执行方法的参数
    Object[] args = pj.getArgs();

    StringBuilder reqStr = new StringBuilder();
    for (int i = 0; i < args.length; i++) {
      reqStr.append("parametes" + i + "={"
          + ToStringBuilder.reflectionToString(args[i], ToStringStyle.SHORT_PREFIX_STYLE) + "}");
    }

    return reqStr.toString();
  }

  /**
   * 获取返回参数
   * 
   * @param pj
   * @return
   * @throws Throwable
   */
  protected Object getResponse(ProceedingJoinPoint pj) throws Throwable {
    return pj.proceed();
  }

  protected String getParamString(ProceedingJoinPoint pj) {
    try {
      // 获取执行方法的参数
      Object[] args = pj.getArgs();

      StringBuilder reqStr = new StringBuilder();
      for (int i = 0; i < args.length; i++) {
        reqStr
            .append("parametes")
            .append(i)
            .append("={")
            .append(
                ToStringBuilder.reflectionToString(fillObject(args[i]),
                    ToStringStyle.SHORT_PREFIX_STYLE)).append("}");
      }

      return reqStr.toString();
    } catch (Exception e) {
      return getRequestParam(pj);
    }

  }


  private Object fillObject(Object obj) throws SecurityException, NoSuchMethodException,
      IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    Object paramObj = BeanCopierUtils.copyOne2One(obj, obj.getClass());

    // Field[] field = paramObj.getClass().getDeclaredFields();
    // for (int j = 0; j < field.length; j++) {
    // // 获取属性的名字
    // String fieldName = field[j].getName();
    // if (PARAMS_STRINGS.contains(fieldName)) {
    // // 将属性的首字符大写，方便构造get，set方法
    // fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    // // 获取属性的类型
    // String type = field[j].getGenericType().toString();
    // // 如果type是类类型，则前面包含"class "，后面跟类名
    // Method getMethod = paramObj.getClass().getMethod("get" + fieldName);
    // Method setMethod = paramObj.getClass().getMethod("set" + fieldName, String.class);
    // setMethod.invoke(paramObj,
    // HssMedicalCardCipherAESUtils.getEncrypt4AesStr((String) getMethod.invoke(paramObj)));
    // }
    // }
    return paramObj;
  }

}

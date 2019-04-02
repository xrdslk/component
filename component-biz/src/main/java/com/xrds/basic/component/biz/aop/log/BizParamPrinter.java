package com.xrds.basic.component.biz.aop.log;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;


/**
 * 
 * @author yuyangzhi
 * @version $Id: BizParamPrinter.java, v 0.1 2018年6月28日 上午11:57:43 yuyangzhi Exp $
 */
@Aspect
@Component
public class BizParamPrinter extends AbsParamPrinter {

  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_BIZ.getLogName());


  @Around("execution(* com.xrds.basic.component.biz.impl.*Biz.*(..)) ")
  @Override
  public Object printIntegrationParam(ProceedingJoinPoint pj) throws Throwable {
    String className = null;
    String methodName = null;
    String info = null;
    try {
      className = getClassName(pj);
      methodName = getMethodName(pj);
      info = className + "." + methodName;
      String req = getRequestParam(pj);
      if ("agenciesMedicalCardBind".equals(methodName)) {
        req = getParamString(pj);
      } else {
        req = getRequestParam(pj);
      }
      LOGGER.info("[BIZ] calling start " + info + " request=[{}]", req);
    } catch (Exception e) {
      LOGGER.error("Biz AOP error", e);
    }

    StopWatch watch = new StopWatch();
    Object result = null;
    try {
      watch.start();
      result = getResponse(pj);
      watch.stop();
      return result;
    } finally {
      LOGGER.info("[BIZ] calling end " + info + " result=[{}]",
          ToStringBuilder.reflectionToString(result, ToStringStyle.SHORT_PREFIX_STYLE));
    }


  }

}

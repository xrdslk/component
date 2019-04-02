package com.xrds.basic.component.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;

/**
 * web层异常处理
 * 
 * @author fuli
 * @version 1.0
 * @date 2017-02-20 19:28
 */
@Component
public class ExceptionHandlerResolver implements HandlerExceptionResolver, Ordered {
  private static LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_CONTROLLER
      .getLogName());

  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
      Object obj, Exception ex) {
    response.setStatus(500);
    LOGGER.error("web error!", ex);
    return new ModelAndView("error/500").addObject("exception", ex);
  }

  @Override
  public int getOrder() {
    return Ordered.HIGHEST_PRECEDENCE;
  }
}

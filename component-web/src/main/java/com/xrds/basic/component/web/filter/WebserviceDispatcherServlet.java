/**
 * 
 */
package com.xrds.basic.component.web.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;

/**
 * @author LIUKAI737
 *
 */
public class WebserviceDispatcherServlet extends DispatcherServlet {
  /**  */
  private static final long serialVersionUID = 1L;
  private static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_CONTROLLER.getLogName());

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      String str = request.getRequestURL().toString().trim();
      if (StringUtils.isNotBlank(str) && (str.endsWith("/services/") || str.endsWith("/services"))) {
        LOGGER.error("地址不充分导致异常请求：" + request.getRemoteAddr());
      }
    } catch (Exception e) {
      LOGGER.error("异常请求地址监控失败", e);
    }

    super.service(request, response);
  }

}

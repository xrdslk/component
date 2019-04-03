
package com.xrds.basic.component.biz.aop.log;

import org.apache.commons.lang3.time.StopWatch;

/**
 * 
 * @author admin
 *
 */
public class BasicStopWatch {

  /**
   * 性能日志监控
   * 
   * @param simpleName
   * @param methodName
   * @param watch
   * @param isException
   * @return
   */
  protected String calculateTime(String simpleName, String methodName, StopWatch watch,
      boolean isException) {
    StringBuilder message  = new StringBuilder();
    message.append(simpleName).append(".").append(methodName).append(";") // 服务名
        .append("耗时：").append(watch.getTime()).append("毫秒;"); // 时间

    if (isException) {
      message.append("N").append(";");
    } else {
      message.append("Y").append(";");
    }
    message.append("").append(";") // 错误码
        .append("").append(";") // 业务流水号
        .append("").append(";"); // 业务名称
    String messageStr = message.toString();
    return messageStr;
  }
}

package com.xrds.basic.component.web.controller;

import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;

public abstract class BaseController {
  public LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_CONTROLLER
      .getLogName());

}

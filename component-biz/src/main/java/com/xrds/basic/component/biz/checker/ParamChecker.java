/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.biz.checker;


import org.springframework.stereotype.Component;

import com.kunpu.frameworks.commons.exception.check.ParamCheckException;
import com.kunpu.frameworks.commons.utils.validator.ParamCheckTool;
import com.xrds.basic.component.common.code.BusErrorCode;

/**
 * 
 * @author liukai
 * @version $Id: ParamChecker.java, v 0.1 2017年6月16日 上午11:23:14 liukai Exp $
 */
@Component("paramchecker")
public class ParamChecker implements Checker {
  /**
   * 检查参数
   * 
   * @param validatorobj
   * @param groups
   * @throws ParamCheckException
   */
  public void checkParam(Object validatorobj, Class<?>... groups) throws ParamCheckException {
    if (validatorobj == null) throw new ParamCheckException(BusErrorCode.ERROR_001);
    ParamCheckTool.validatorBean(validatorobj, groups);
  }
}

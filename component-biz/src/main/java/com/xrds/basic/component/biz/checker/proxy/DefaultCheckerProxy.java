/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.biz.checker.proxy;


import org.springframework.beans.factory.annotation.Autowired;

import com.kunpu.frameworks.commons.exception.check.ParamCheckException;
import com.kunpu.frameworks.commons.exception.check.RepeatCheckException;
import com.xrds.basic.component.biz.checker.Checker;
import com.xrds.basic.component.biz.checker.IdempotentChecker;
import com.xrds.basic.component.biz.checker.ParamChecker;

/**
 * 默认检查代理
 * 
 * @author liukai
 * @version $Id: DefaultCheckerProxy.java, v 0.1 2017年6月16日 上午11:33:43 liukai Exp $
 */
public class DefaultCheckerProxy implements Checker {
  @Autowired
  private ParamChecker paramChecker;
  @Autowired
  private IdempotentChecker idempotentChecker;

  /**
   * 检查参数
   * 
   * @param validatorobj
   * @param groups
   * @throws ParamCheckException
   */
  public void checkParam(Object validatorobj, Class<?>... groups) throws ParamCheckException {
    paramChecker.checkParam(validatorobj, groups);
  }

  /**
   * 检查幂等
   * 
   * @param obj
   * @param busType
   * @throws RepeatCheckException
   */
  public void checkRepeat(Object obj, String busType) throws RepeatCheckException {
    idempotentChecker.checkRepeat(obj, busType);
  }
}

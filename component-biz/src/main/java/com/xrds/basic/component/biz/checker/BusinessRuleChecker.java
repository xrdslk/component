/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.biz.checker;

import java.util.Map;

import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.exception.check.CheckException;


/**
 * 
 * @author liukai
 * @version $Id: BusinessRuleChecker.java, v 0.1 2017年6月16日 上午11:29:32 liukai Exp $
 */
public interface BusinessRuleChecker<T> extends Checker {

  /**
   * 验证业务规则
   * 
   * @param t
   * @param checkMap
   * @throws CheckException
   * @throws CommonException
   */
  public void checkBusRule(T t, Map<String, Object> checkMap) throws CheckException,
      CommonException;

}

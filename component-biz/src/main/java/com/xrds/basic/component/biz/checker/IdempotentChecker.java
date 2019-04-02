/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.biz.checker;

import org.springframework.stereotype.Component;

import com.kunpu.frameworks.commons.exception.check.RepeatCheckException;



/**
 * 
 * @author liukai
 * @version $Id: IdempotentChecker.java, v 0.1 2017年6月16日 上午11:26:02 liukai Exp $
 */
@Component("idempotentChecker")
public class IdempotentChecker implements Checker {
  /**
   * 检查幂等
   * 
   * @param obj
   * @param busType
   * @throws RepeatCheckException
   */
  public void checkRepeat(Object obj, String busType) throws RepeatCheckException {

  }


}

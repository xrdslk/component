/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.biz.action.abs;

import com.kunpu.frameworks.commons.exception.CommonException;
import com.xrds.basic.component.facade.bean.abs.AbstractRequest;
import com.xrds.basic.component.facade.bean.abs.AbstractResponse;

/**
 * 
 * @author yuyangzhi
 * @version $Id: IAbstractLockAction.java, v 0.1 2018年6月28日 上午11:57:43 yuyangzhi Exp $
 */
public interface IAbstractLockAction<T extends AbstractRequest, V extends AbstractResponse> {
  /**
   * 逻辑处理
   * 
   * @param req
   * @param businessType
   * @param businessId
   * @return
   * @throws CommonException
   */
  public AbstractResponse execute(AbstractRequest req, String businessType, String businessId)
      throws CommonException;

}

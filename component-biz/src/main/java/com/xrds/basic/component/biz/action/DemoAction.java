package com.xrds.basic.component.biz.action;


import com.kunpu.frameworks.commons.exception.biz.BizException;
import com.xrds.basic.component.facade.bean.DemoRequest;
import com.xrds.basic.component.facade.bean.DemoResponse;

/**
 * 
 * @author yuyangzhi
 * @version $Id: DemoAction.java, v 0.1 2018年6月28日 上午11:57:43 yuyangzhi Exp $
 */
public interface DemoAction {

  /**
   * 
   * 
   * @param request
   * @return
   * @throws BizException
   */
  public DemoResponse queryTaskLockByTypeAndId(DemoRequest request) throws BizException;

}

package com.xrds.basic.component.facade;

import javax.jws.WebService;

import com.xrds.basic.component.facade.bean.DemoRequest;
import com.xrds.basic.component.facade.bean.DemoResponse;

/**
 * DemoFcade
 * 
 * @author admin
 *
 */
@WebService
public interface DemoFacade {
  /**
   * demo
   * 
   * @param request
   * @return
   */
  public DemoResponse queryTaskLockByTypeAndId(DemoRequest request);

}

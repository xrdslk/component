package com.xrds.basic.test.facade;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xrds.basic.component.facade.DemoFacade;
import com.xrds.basic.component.facade.bean.DemoRequest;
import com.xrds.basic.component.facade.bean.DemoResponse;
import com.xrds.basic.test.BaseTest;



public class FacadeTester extends BaseTest {
  @Autowired
  DemoFacade demoFacade;

  @Test
  public void test() {
    DemoRequest request = new DemoRequest();
    request.setBusinessId("");
    request.setBusinessType("");
    try {
      DemoResponse response = demoFacade.queryTaskLockByTypeAndId(request);
      System.out.println(response);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}

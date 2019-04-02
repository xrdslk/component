package com.xrds.basic.component.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xrds.basic.component.facade.DemoFacade;
import com.xrds.basic.component.facade.bean.DemoRequest;
import com.xrds.basic.component.facade.bean.DemoResponse;

/**
 * demo
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping("/demo")
public class DemoController extends BaseController {

  @Autowired
  private DemoFacade demoFacade;

  @RequestMapping(value = "req", method = RequestMethod.POST, consumes = {"application/json;charset=UTF-8"})
  public DemoResponse test(@RequestBody DemoRequest request) throws Exception {
    return demoFacade.queryTaskLockByTypeAndId(request);
  }
  
  

  @RequestMapping(value = "view")
  public ModelAndView view(HttpServletRequest request) throws Exception {
    LOGGER.info("view test [{}]", request.getQueryString());
    DemoResponse response = new DemoResponse();
    response.setBusinessId((String) request.getParameter("businessId"));
    response.setBusinessType((String) request.getParameter("businessType"));
    return new ModelAndView("/demo/demo").addObject("response", response);
  }

}

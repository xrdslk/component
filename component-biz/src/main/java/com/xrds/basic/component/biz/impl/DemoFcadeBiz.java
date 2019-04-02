package com.xrds.basic.component.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kunpu.frameworks.commons.code.CommonRespCode;
import com.kunpu.frameworks.commons.enums.APPCodeEnum;
import com.kunpu.frameworks.commons.exception.biz.BizException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.xrds.basic.component.biz.action.DemoAction;
import com.xrds.basic.component.facade.DemoFacade;
import com.xrds.basic.component.facade.bean.DemoRequest;
import com.xrds.basic.component.facade.bean.DemoResponse;

/**
 * 
 * 
 * @author yuyangzhi
 * @version $Id: DemoFcadeBiz.java, v 0.1 2018年6月28日 下午4:03:33 yuyangzhi Exp $
 */
@Component("demoFacade")
public class DemoFcadeBiz implements DemoFacade {
  private static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_BIZ
      .getLogName());

  @Autowired
  DemoAction demoAction;

  @Override
  public DemoResponse queryTaskLockByTypeAndId(DemoRequest request) {
    DemoResponse resp = new DemoResponse();
    try {
      resp = demoAction.queryTaskLockByTypeAndId(request);
    } catch (BizException e) {
      LOGGER.error("call demoTest error", e);
      resp.setCode(e.getCode());
      resp.setMemo(e.getMsg());
      return resp;
    } catch (Exception e) {
      LOGGER.error("call demoTest error", e);
      resp.setCode(APPCodeEnum.KPBF_STRUCTRUE.sysId + CommonRespCode.RESP_999.getCode());
      resp.setMemo(CommonRespCode.RESP_999.getDesc());
      return resp;
    }
    resp.setCode(CommonRespCode.RESP_000000.getCode());
    resp.setMemo(CommonRespCode.RESP_000000.getDesc());
    return resp;
  }

}

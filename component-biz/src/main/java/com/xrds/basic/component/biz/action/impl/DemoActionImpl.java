package com.xrds.basic.component.biz.action.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kunpu.frameworks.commons.enums.APPCodeEnum;
import com.kunpu.frameworks.commons.exception.biz.BizException;
import com.kunpu.frameworks.commons.exception.check.ParamCheckException;
import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.exception.handle.BizExceptionHandler;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.frameworks.commons.utils.validator.ParamCheckTool;
import com.xrds.basic.component.biz.action.DemoAction;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.facade.bean.DemoRequest;
import com.xrds.basic.component.facade.bean.DemoResponse;
import com.xrds.basic.component.service.repository.TaskLockService;
import com.xrds.basic.component.service.repository.bean.TaskLock;

/**
 * 
 * @author yuyangzhi
 * @version $Id: DemoActionImpl.java, v 0.1 2018年6月28日 上午11:57:43 yuyangzhi Exp $
 */
@Component("demoAction")
public class DemoActionImpl implements DemoAction {
  private static final LoggerAdapter LOGGER = LoggerAdapterFactory.getLogger(CommonLogType.SYS_BIZ
      .getLogName());
  @Autowired
  TaskLockService taskLockService;

  @Override
  public DemoResponse queryTaskLockByTypeAndId(DemoRequest request) throws BizException {
    DemoResponse resp = new DemoResponse();
    try {
      ParamCheckTool.validatorBean(request);
    } catch (ParamCheckException e) {
      LOGGER.warn("param check error code=[{}],msg=[{}]", e.getCode(), e.getMsg());
      BizExceptionHandler.commonExceptionHandle(APPCodeEnum.KPBF_STRUCTRUE, e);
    }
    try {
      TaskLock taskLock =
          taskLockService.queryByTypeAndId(request.getBusinessType(), request.getBusinessId());
      if (taskLock == null) {
        throw new BizException(BusErrorCode.ERROR_102);
      }
      resp.setBusinessId(taskLock.getBusinessId());
      resp.setBusinessType(taskLock.getBusinessType());
      resp.setIsFinished(taskLock.getIsFinished());
      resp.setTaskStatus(taskLock.getTaskStatus());
    } catch (DBException e) {
      LOGGER.error("call demoTest error :request={},错误信息={}", request, e.getMessage(), e);
      BizExceptionHandler.commonRuntimeExceptionHandle(APPCodeEnum.KPBF_STRUCTRUE, e);
    }
    return resp;
  }

}

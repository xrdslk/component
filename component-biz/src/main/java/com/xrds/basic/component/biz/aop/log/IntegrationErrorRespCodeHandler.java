/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.biz.aop.log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.Cat;
import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.xrds.basic.component.biz.aop.filter.SpringInitHandler;
import com.xrds.basic.component.common.code.BusRespCode;
import com.xrds.basic.component.common.constants.ConfigCenterConstants;
import com.xrds.basic.component.common.util.ApplicationContextConfigUtil;
import com.xrds.basic.component.common.util.StringBuilderUtil;
import com.xrds.basic.component.service.repository.TransRequestLogService;
import com.xrds.basic.component.service.repository.bean.TransRequestLog;

/**
 * 
 * @author liukai
 * @version $Id: IntegrationErrorRespCodeHandler.java, v 0.1 2017年12月3日 上午11:12:52 liukai Exp $
 */
@Aspect
public class IntegrationErrorRespCodeHandler extends AbsParamPrinter {

  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_INTEGRATION.getLogName());

  @Around("execution(* com.xrds.basic.component.integration.impl.*Impl.*(..))")
  @Override
  public Object printIntegrationParam(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    // 方法名
    String methodName = this.getMethodName(proceedingJoinPoint);

    // 获取执行方法的参数
    Object[] args = proceedingJoinPoint.getArgs();

    LOGGER.info(
        "IntegrationErrorRespCodeHandler printIntegrationParam start, methodName=[{}], args=[{}]",
        methodName, JSONObject.toJSONString(args));

    Object result = getResponse(proceedingJoinPoint);

    // 处理调用失败拦截
    if (result != null) {
      // reflectObject(methodName, result, args);
      handleResult(methodName, result, args);
    } else {
      LOGGER.error("Call Integration return NULL,method:[{}],params:[{}]", methodName, args[0]);
    }

    return result;
  }

  private void handleResult(String methodName, Object result, Object[] args) {
    Map<String, String> errorStatusMap = new HashMap<String, String>();
    try {
      boolean ifError = checkIfError(result, errorStatusMap);
      if (ifError) {
        insertSwapLog(methodName, result, args, errorStatusMap.get("requestNo"),
            errorStatusMap.get("bizId"), errorStatusMap.get("sysId"),
            errorStatusMap.get("respCode"), errorStatusMap.get("respMsg"));
      }
    } catch (Exception e) {
      LOGGER.error("Integration inteceptor handle result error,method:[{}],params:[{}]",
          methodName, args[0]);
      Cat.logEvent("EMERGENCY_EVENT", "Integration handle result error");
    }
  }

  private boolean checkIfError(Object result, Map<String, String> errorStatusMap) {
    String respCodeString =
        ApplicationContextConfigUtil.getString(ConfigCenterConstants.COMMON_RESPCODE_NAMES,
            "Code,RespCode,ErrorCode");
    String respMsgString =
        ApplicationContextConfigUtil.getString(ConfigCenterConstants.COMMON_RESPMSG_NAMES,
            "Msg,Memo");
    String requestNoString =
        ApplicationContextConfigUtil
            .getString(ConfigCenterConstants.COMMON_REQUEST_NO, "ReqSerial");
    String bizIdString =
        ApplicationContextConfigUtil.getString(ConfigCenterConstants.COMMON_BIZ_ID, "TraceId");

    String[] respCodeNames = respCodeString.split(StringBuilderUtil.SYMBOL_COMMA);
    String[] respMsgNames = respMsgString.split(StringBuilderUtil.SYMBOL_COMMA);
    String[] requestNos = requestNoString.split(StringBuilderUtil.SYMBOL_COMMA);
    String[] bizIds = bizIdString.split(StringBuilderUtil.SYMBOL_COMMA);

    String excluedCodes =
        ApplicationContextConfigUtil.getString(ConfigCenterConstants.EXCLUED_ERROR_CODES, "");

    Field[] fields = result.getClass().getDeclaredFields();
    ArrayList<String> fieldNameList = new ArrayList<String>();
    for (Field fieldTmp : fields) {
      fieldNameList.add(fieldTmp.getName());
    }

    for (String respCodeName : respCodeNames) {
      if (!fieldNameList.contains(respCodeName)) continue;
      try {
        Method m = result.getClass().getMethod("get" + respCodeName);
        if (m != null) {
          String fieldValue = (String) m.invoke(result);
          if (StringUtils.isNotBlank(fieldValue)
              && !BusRespCode.RESP_000000.getCode().equals(fieldValue)
              && !excluedCodes.contains(fieldValue)) {

            errorStatusMap.put("respCode", fieldValue);
            errorStatusMap.put("respMsg", getRespMsg(respMsgNames, result) == null
                ? ""
                : getRespMsg(respMsgNames, result));
            errorStatusMap.put("requestNo",
                getReqNo(requestNos, result) == null ? "" : getReqNo(requestNos, result));
            errorStatusMap.put("bizId",
                getBizId(bizIds, result) == null ? "" : getBizId(bizIds, result));
            errorStatusMap.put("sysId", getParamVaule("SysId", result) == null
                ? ""
                : getParamVaule("SysId", result));
            return true;
          }
        }
      } catch (Exception e) {
        LOGGER.error("返回结果{}解析异常", result);
        LOGGER.error("getMethod error", e);
      }
    }
    return false;
  }

  /**
   * 获取相应信息
   * 
   * @param respMsgNames
   * @param result
   * @return
   */
  private String getRespMsg(String[] respMsgNames, Object result) {
    for (String respMsgName : respMsgNames) {
      Method m;
      try {
        m = result.getClass().getMethod("get" + respMsgName);
        if (m != null) {
          String fieldValue = (String) m.invoke(result);
          return fieldValue;
        }
      } catch (Exception e) {
        continue;
      }
    }

    return null;
  }

  /**
   * 获取请求流水
   * 
   * @param reqNos
   * @param result
   * @return
   */
  private String getReqNo(String[] reqNos, Object result) {
    for (String reqNo : reqNos) {
      Method m;
      try {
        m = result.getClass().getMethod("get" + reqNo);
        if (m != null) {
          String fieldValue = (String) m.invoke(result);
          return fieldValue;
        }
      } catch (Exception e) {
        continue;
      }
    }

    return null;
  }

  /**
   * 获取参数值
   * 
   * @param reqNos
   * @param result
   * @return
   */
  private String getParamVaule(String paramName, Object result) {
    Method m;
    try {
      m = result.getClass().getMethod("get" + paramName);
      if (m != null) {
        String fieldValue = (String) m.invoke(result);
        return fieldValue;
      }
    } catch (Exception e) {

    }
    return null;
  }

  /**
   * 获取业务id
   * 
   * @param bizIds
   * @param result
   * @return
   */
  private String getBizId(String[] bizIds, Object result) {
    for (String reqNo : bizIds) {
      Method m;
      try {
        m = result.getClass().getMethod("get" + reqNo);
        if (m != null) {
          String fieldValue = (String) m.invoke(result);
          return fieldValue;
        }
      } catch (Exception e) {
        continue;
      }
    }

    return null;
  }


  private void insertSwapLog(String methodName, Object result, Object[] args, String reqestNo,
      String bizId, String sysId, String respCode, String respMsg) throws CommonException {

    TransRequestLogService transRequestLogService =
        (TransRequestLogService) SpringInitHandler.getBean("transRequestLogService");
    TransRequestLog transRequestLog = new TransRequestLog();
    transRequestLog.setBusId(bizId);
    transRequestLog.setBusType(methodName);
    transRequestLog.setReqChannel(sysId);
    transRequestLog.setReqParam(JSONObject.toJSONString(args));
    transRequestLog.setRespParam(JSONObject.toJSONString(result));
    Date currentTime = new Date();
    transRequestLog.setCreateTime(currentTime);
    transRequestLog.setUpdateTime(currentTime);
    transRequestLog.setReqTime(currentTime);
    transRequestLog.setRespTime(currentTime);
    transRequestLog.setRespCode(respCode);
    transRequestLog.setReqServerId(sysId);

    transRequestLogService.addData(transRequestLog);
  }

}

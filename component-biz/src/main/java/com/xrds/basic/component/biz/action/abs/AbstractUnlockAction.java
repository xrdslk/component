/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.biz.action.abs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.kunpu.frameworks.commons.exception.CommonException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.xrds.basic.component.biz.checker.BusinessRuleChecker;
import com.xrds.basic.component.biz.checker.proxy.DefaultCheckerProxy;
import com.xrds.basic.component.facade.bean.abs.AbstractRequest;
import com.xrds.basic.component.facade.bean.abs.AbstractResponse;
import com.xrds.basic.component.service.bussiness.TaskLockBusService;

/**
 * 
 * @author yuyangzhi
 * @version $Id: AbstractUnlockAction.java, v 0.1 2018年6月28日 上午11:57:43 yuyangzhi Exp $
 */
public abstract class AbstractUnlockAction<T, V> extends DefaultCheckerProxy
    implements
      IAbstractUnlockAction<AbstractRequest, AbstractResponse>,
      BusinessRuleChecker<T> {
  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_BIZ.getLogName());
  @Autowired
  private TaskLockBusService taskLockBusService;

  /**
   * 接口开始
   * 
   * @param req
   * @param paramMap
   * @throws CommonException
   */
  public abstract void before(T req, Map<String, Object> paramMap) throws CommonException;

  /**
   * 检查
   * 
   * @param req
   * @param paramMap
   * @throws CommonException
   */
  @SuppressWarnings("unchecked")
  public void check(AbstractRequest req, Map<String, Object> paramMap) throws CommonException {
    checkParam(req);
    checkBusRule((T) req, paramMap);
  }

  @SuppressWarnings("unchecked")
  @Override
  public AbstractResponse execute(AbstractRequest req) throws CommonException {
    Map<String, Object> paramMap = new HashMap<String, Object>();

    // 业务逻辑处理前动作
    before((T) req, paramMap);

    // 校验动作
    check(req, paramMap);

    // 业务处理动作
    AbstractResponse resp = businessExecute((T) req, paramMap);

    // 业务逻辑处理后动作
    after((T) req, (V) resp, paramMap);
    return resp;
  }

  /**
   * 业务处理
   * 
   * @param req
   * @param paramMap
   * @return
   * @throws HssException
   */
  public abstract AbstractResponse businessExecute(T req, Map<String, Object> paramMap)
      throws CommonException;


  /**
   * 执行后
   * 
   * @param req
   * @param resp
   * @param paramMap
   * @throws CommonException
   */
  public abstract void after(T req, V resp, Map<String, Object> paramMap) throws CommonException;

}

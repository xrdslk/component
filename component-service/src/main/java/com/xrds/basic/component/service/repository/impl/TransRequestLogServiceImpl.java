/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xrds.basic.component.dal.IBaseDao;
import com.xrds.basic.component.dal.TransRequestLogDao;
import com.xrds.basic.component.dal.model.TransRequestLogDo;
import com.xrds.basic.component.service.repository.TransRequestLogService;
import com.xrds.basic.component.service.repository.bean.TransRequestLog;

/**
 * 
 * @author liukai
 * @version $Id: TransRequestLogServiceImpl.java, v 0.1 2017年12月3日 上午11:05:50 liukai Exp $
 */
@Service("transRequestLogService")
public class TransRequestLogServiceImpl extends BaseServiceImpl<TransRequestLog, TransRequestLogDo>
    implements
      TransRequestLogService {

  @Autowired
  private TransRequestLogDao transRequestLogDao;


  @Override
  protected IBaseDao<TransRequestLogDo> getBaseDAO() {
    return transRequestLogDao;
  }

  @Override
  protected Class<TransRequestLogDo> getDoClass() {
    return TransRequestLogDo.class;
  }

  @Override
  protected Class<TransRequestLog> getServiceModelClass() {
    return TransRequestLog.class;
  }


}

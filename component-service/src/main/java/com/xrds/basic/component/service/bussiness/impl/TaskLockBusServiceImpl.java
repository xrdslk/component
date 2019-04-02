/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.bussiness.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.service.bussiness.TaskLockBusService;
import com.xrds.basic.component.service.repository.TaskLockService;

/**
 * 
 * @author liukai
 * @version $Id: TaskLockBusServiceImpl.java, v 0.1 2017年6月13日 下午12:30:22 liukai Exp $
 */
@Service("taskLockBusService")
public class TaskLockBusServiceImpl implements TaskLockBusService {
  private static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_SERVICE.getLogName());
  @Autowired
  private TaskLockService taskLockService;
  @Autowired
  protected TransactionTemplate transactionTemplate;

  /**
   * @see com.pinganfu.hss.medicare.service.bus.TaskLockBusService#taskLock(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public boolean lockTask(String businessType, String businessId) {
    final String sendBusinessType = businessType;
    final String sendBusinessId = businessId;
    boolean isExist = false;
    try {
      isExist = taskLockService.isExistsByTypeAndId(sendBusinessType, sendBusinessId);
    } catch (DBException e) {
      LOGGER.error("尝试锁定任务出错,businessType=[{}],businessId=[{}]", businessType, businessId, e);
      return false;
    }
    if (!isExist) {
      try {
        boolean resultVal = transactionTemplate.execute(new TransactionCallback<Boolean>() {
          @Override
          public Boolean doInTransaction(TransactionStatus status) {
            taskLockService.addData(sendBusinessType, sendBusinessId);
            boolean updateFlag = taskLockService.updateLockTask(sendBusinessType, sendBusinessId);
            if (!updateFlag) {
              throw new DBException(BusErrorCode.ERROR_202);
            }
            return updateFlag;
          }
        });
        return resultVal;
      } catch (Exception e) {
        LOGGER.error("尝试锁定任务出错,businessType=[{}],businessId=[{}]", businessType, businessId, e);
        return false;
      }
    } else {
      try {
        return taskLockService.updateLockTask(businessType, businessId);
      } catch (Exception e) {
        LOGGER.error("尝试锁定任务出错,businessType=[{}],businessId=[{}]", businessType, businessId, e);
        return false;
      }
    }
  }

  /**
   * @see com.pinganfu.hss.medicare.service.bus.TaskLockBusService#unLockTask(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public boolean unLockTask(String businessType, String businessId) {
    try {
      return taskLockService.updateUnLockTask(businessType, businessId);
    } catch (Exception e) {
      LOGGER.error("尝试解锁任务出错,businessType=[{}],businessId=[{}]", businessType, businessId, e);
      return false;
    }
  }

}

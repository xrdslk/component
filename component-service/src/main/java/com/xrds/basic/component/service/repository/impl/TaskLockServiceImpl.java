/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.repository.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.utils.BeanCopierUtils;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.common.constants.CommonApplicationConstants.TaskLockContents;
import com.xrds.basic.component.dal.IBaseDao;
import com.xrds.basic.component.dal.TaskLockDao;
import com.xrds.basic.component.dal.model.TaskLockDo;
import com.xrds.basic.component.service.repository.TaskLockService;
import com.xrds.basic.component.service.repository.bean.TaskLock;

/**
 * 
 * @author liukai
 * @version $Id: TaskLockServiceImpl.java, v 0.1 2017年6月13日 上午11:38:45 liukai Exp $
 */
@Service("taskLockService")
public class TaskLockServiceImpl extends BaseServiceImpl<TaskLock, TaskLockDo>
    implements
      TaskLockService {
  @Autowired
  private TaskLockDao taskLockDao;

  /**
   * 
   * @see com.pinganfu.IIP.medicare.service.dal.TaskLockService#addData(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public Integer addData(String businessType, String businessId) throws DBException {
    Date cur = new Date();
    TaskLockDo o = new TaskLockDo();
    o.setBusinessType(businessType);
    o.setBusinessId(businessId);
    o.setIsFinished(TaskLockContents.TASK_LOCK_UNFINISHED);
    o.setTaskStatus(TaskLockDo.UNLOCKED);
    o.setCreateTime(cur);
    o.setUpdateTime(cur);
    try {
      return taskLockDao.insertData(o);
    } catch (Exception e) {
      LOGGER.error("新增一条加锁记录异常", e);
      throw new DBException(BusErrorCode.ERROR_201, e);
    }
  }

  /**
   * 
   * @see com.pinganfu.IIP.medicare.service.dal.TaskLockService#queryByTypeAndId(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public TaskLock queryByTypeAndId(String businessType, String businessId) throws DBException {
    TaskLockDo TaskLockDo = new TaskLockDo();
    TaskLockDo.setBusinessType(businessType);
    TaskLockDo.setBusinessId(businessId);
    try {
      TaskLockDo = taskLockDao.queryByTypeAndId(TaskLockDo);
      if (TaskLockDo == null || TaskLockDo.getId() == null) {
        return null;
      }
    } catch (Exception e) {
      LOGGER.error("根据业务类型和业务ID查询一条锁信息异常,businessType=[{}],businessId=[{}]", businessType,
          businessId);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
    TaskLock tl = BeanCopierUtils.copyOne2One(TaskLockDo, TaskLock.class);
    return tl;
  }

  /**
   * 
   * @see com.pinganfu.IIP.medicare.service.dal.TaskLockService#taskLock(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public boolean updateLockTask(String businessType, String businessId) throws DBException {
    TaskLockDo o = new TaskLockDo();
    o.setBusinessType(businessType);
    o.setBusinessId(businessId);
    o.setUpdateTime(new Date());
    try {
      return taskLockDao.updateLock(o) > 0 ? true : false;
    } catch (Exception e) {
      LOGGER.error("尝试锁定任务出错,businessType=[{}],businessId=[{}]", businessType, businessId, e);
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
  }

  /**
   * @see com.pinganfu.IIP.medicare.service.dal.TaskLockService#unLockTask(com.pinganfu.IIP.medicare.service.model.TaskLock)
   */
  @Override
  public boolean updateUnLockTask(String businessType, String businessId) throws DBException {
    TaskLockDo o = new TaskLockDo();
    o.setBusinessType(businessType);
    o.setBusinessId(businessId);
    o.setUpdateTime(new Date());
    try {
      return taskLockDao.updateUnLock(o) > 0 ? true : false;
    } catch (Exception e) {
      LOGGER.error("尝试解锁任务出错,businessType=[{}],businessId=[{}]", businessType, businessId);
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
  }

  /**
   * 
   * @see com.pinganfu.IIP.medicare.service.dal.TaskLockService#updateStatusByPk(com.pinganfu.IIP.medicare.service.dal.model.TaskLock)
   */
  @Override
  public int updateStatusByPk(TaskLock o) throws DBException {
    TaskLockDo TaskLockDo = new TaskLockDo();
    TaskLockDo.setId(o.getId());
    TaskLockDo.setTaskStatus(o.getTaskStatus());
    TaskLockDo.setUpdateTime(new Date());
    try {
      return taskLockDao.updateStatusByPk(TaskLockDo);
    } catch (Exception e) {
      LOGGER.error("根据主键更新状态出错,id=[{}]", TaskLockDo.getId());
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
  }

  /**
   * @see com.pinganfu.IIP.medicare.service.dal.TaskLockService#isExistsByTypeAndId(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public boolean isExistsByTypeAndId(String businessType, String businessId) throws DBException {
    TaskLockDo o = new TaskLockDo();
    o.setBusinessType(businessType);
    o.setBusinessId(businessId);
    o.setUpdateTime(new Date());
    try {
      return taskLockDao.isExistsByTypeAndId(o) > 0 ? true : false;
    } catch (Exception e) {
      LOGGER.error("根据businessType和businessId判断是否存在数据出错,businessType=[{}],businessId=[{}]",
          businessType, businessId);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
  }

  /**
   * @see com.pinganfu.IIP.medicare.service.dal.TaskLockService#queryOverTimeTask(int)
   */
  @Override
  public List<TaskLock> queryOverTimeTask(int overMinutes) throws DBException {
    List<TaskLockDo> returnListDO = null;
    try {
      returnListDO = taskLockDao.queryOverTimeTask(overMinutes);
      if (CollectionUtils.isEmpty(returnListDO)) {
        return null;
      }
    } catch (Exception e) {
      LOGGER.error("查询超时时间的锁信息异常,overMinutes=[{}]", overMinutes);
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
    List<TaskLock> returnList = BeanCopierUtils.copyList2List(returnListDO, TaskLock.class);
    return returnList;
  }

  /**
   * 
   * @see com.pinganfu.IIP.medicare.service.dal.impl.BaseServiceImpl#getBaseDAO()
   */
  @Override
  protected IBaseDao<TaskLockDo> getBaseDAO() {
    return taskLockDao;
  }

  /**
   * 
   * @see com.pinganfu.IIP.medicare.service.dal.impl.BaseServiceImpl#getDOClass()
   */
  @Override
  protected Class<TaskLockDo> getDoClass() {
    return TaskLockDo.class;
  }

  /**
   * 
   * @see com.pinganfu.IIP.medicare.service.dal.impl.BaseServiceImpl#getServiceModleClass()
   */
  @Override
  protected Class<TaskLock> getServiceModelClass() {
    return TaskLock.class;
  }


}

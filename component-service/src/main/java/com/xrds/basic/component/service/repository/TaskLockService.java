/**
 * 
 * 平安付 Copyright (c) 2013-2016 PingAnFu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.repository;

import java.util.List;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.xrds.basic.component.dal.model.TaskLockDo;
import com.xrds.basic.component.service.repository.bean.TaskLock;

/**
 * 
 * 
 * @author liukai
 * @version $Id: TaskLockService.java, v 0.1 2017年6月13日 上午10:34:31 liukai Exp $
 */
public interface TaskLockService extends BaseService<TaskLock, TaskLockDo> {

  /**
   * 新增一条加锁记录
   * 
   * @param businessType
   * @param businessId
   * @return
   */
  public Integer addData(String businessType, String businessId) throws DBException;

  /**
   * 根据业务类型和业务ID查询一条锁信息
   * 
   * @param o
   * @return
   */

  public TaskLock queryByTypeAndId(String businessType, String businessId) throws DBException;

  /**
   * 加锁
   * 
   * @param o
   * @return
   */
  public boolean updateLockTask(String businessType, String businessId) throws DBException;

  /**
   * 解锁
   * 
   * @param o
   * @return
   */
  public boolean updateUnLockTask(String businessType, String businessId) throws DBException;

  /**
   * 根据主键更新状态
   * 
   * @param o
   * @return
   */
  public int updateStatusByPk(TaskLock o) throws DBException;

  /**
   * 根据businessType和businessId判断是否存在数据
   * 
   * @param businessType
   * @param businessId
   * @return
   * @throws DBException
   */
  public boolean isExistsByTypeAndId(String businessType, String businessId) throws DBException;

  /**
   * 查询超时时间的锁信息
   * 
   * @param overMinutes
   * @return
   * @throws DBException
   */
  public List<TaskLock> queryOverTimeTask(int overMinutes) throws DBException;

}

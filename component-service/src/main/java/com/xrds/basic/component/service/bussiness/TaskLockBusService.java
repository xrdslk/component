/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.bussiness;

/**
 * 
 * @author liukai
 * @version $Id: TaskLockBusService.java, v 0.1 2017年6月13日 下午12:02:06 liukai Exp $
 */
public interface TaskLockBusService {
  /**
   * 根据businessType和businessId查询一条锁记录，没有则插入再加锁.存在，则直接加锁
   * 
   * @param businessType
   * @param businessId
   * @return
   */
  public boolean lockTask(String businessType, String businessId);

  /**
   * 解锁
   * 
   * @param businessType
   * @param businessId
   * @return
   */
  public boolean unLockTask(String businessType, String businessId);
}

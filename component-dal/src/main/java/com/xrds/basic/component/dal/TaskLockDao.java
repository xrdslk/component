package com.xrds.basic.component.dal;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;

import com.xrds.basic.component.dal.config.BaseDB;
import com.xrds.basic.component.dal.model.TaskLockDo;

@BaseDB
@Qualifier("taskLockDao")
public interface TaskLockDao extends IBaseDao<TaskLockDo> {
  /**
   * 根据业务类型和业务ID查询一条锁信息
   * 
   * @param o
   * @return
   */
  public TaskLockDo queryByTypeAndId(TaskLockDo o);

  /**
   * 根据业务类型和业务ID设置锁信息为无效/有效
   * 
   * @param o
   * @return
   */
  public int updateFinishedByTypeAndId(TaskLockDo o);

  /**
   * 加锁
   * 
   * @param o
   * @return
   */
  public int updateLock(TaskLockDo o);

  /**
   * 解锁
   * 
   * @param o
   * @return
   */
  public int updateUnLock(TaskLockDo o);

  /**
   * 根据主键更新状态
   * 
   * @param o
   * @return
   */
  public int updateStatusByPk(TaskLockDo o);

  /**
   * 根据businessType和businessId判断是否存在数据
   * 
   * @param o
   * @return
   */
  public int isExistsByTypeAndId(TaskLockDo o);

  /**
   * 查询超时时间的锁信息
   * 
   * @param overMinutes
   * @return
   */
  public List<TaskLockDo> queryOverTimeTask(int overMinutes);
}

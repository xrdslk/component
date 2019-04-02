package com.xrds.basic.component.dal;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import tk.mybatis.mapper.common.Mapper;

/**
 * 公用Dao
 *
 * @author zhangyangyang
 * @version 2017/11/22
 */
public interface IBaseDao<T> extends Mapper<T> {

  public abstract int insertData(T paramT);

  public abstract int deleteDataByPK(Object paramObject);

  public abstract int deleteData(T paramT);

  public abstract int updateData(T paramT);

  public abstract int updateDataByPK(T paramT);

  public abstract Object queryObjectByPK(Object paramObject);

  public abstract int queryCount(T paramT);

  public abstract int queryForInt(T paramT);

  public abstract List<T> queryForListAll();

  public abstract List<T> queryForPageList(T paramT, RowBounds rowBounds);

  public abstract T queryObject(T paramT);

  public abstract List<T> queryForList(T paramT);

  public abstract int batchInsertData(List<T> paramList);

  public abstract int batchUpdateData(List<T> paramList);

  public abstract void flushSession();

}

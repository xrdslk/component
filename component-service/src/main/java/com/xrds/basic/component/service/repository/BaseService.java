/**
 * 
 * 坤普 Copyright (c) 2016-2017 kunpu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.repository;

import java.util.List;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.db.pagination.Page;
import com.kunpu.frameworks.db.pagination.PaginationBean;

/**
 * 
 * 
 * @author LIUKAI737
 * @version $Id: BaseService.java, v 0.1 2017年2月10日 上午9:58:31 LIUKAI737 Exp $
 */
public interface BaseService<T, DO> {

  /**
   * 添加数据
   * 
   * @param o
   * @return
   */
  public Integer addData(T o) throws DBException;

  /**
   * 根据主键删除数据
   * 
   * @param o
   */
  public int removeDataByPK(Object o) throws DBException;

  /**
   * 根据主键修改数据
   * 
   * @param o
   */
  public int modifyDataByPK(T o) throws DBException;

  /**
   * 查询数据个数
   * 
   * @param o
   * @return
   */
  public int getInt(T o) throws DBException;

  /**
   * 
   * @param o
   * @param page
   * @return
   */
  public PaginationBean<T> getListByPagination(T o, Page page) throws DBException;

  /**
   * 查询数据列表
   * 
   * @param o
   * @return
   */
  public List<T> getList(T o) throws DBException;

  /**
   * 查询数据列表
   * 
   * @param o
   * @param offSet
   * @param maxRow
   * @return
   */
  public List<T> getList(T o, int offSet, int maxRow) throws DBException;

  /**
   * 获取全部的数据
   * 
   * @return
   */
  public List<T> getAllList() throws DBException;

  /**
   * 获取一个对象
   * 
   * @param o
   * @return
   */
  public T getObject(T o) throws DBException;

  /**
   * 获取一个对象
   * 
   * @param o
   * @return
   */
  public T getObjectByPK(Object pk) throws DBException;

  /**
   * 批量添加
   * 
   * @param list
   */
  public void batchAdd(List<T> list) throws DBException;

  /**
   * 批量修改
   * 
   * @param list
   */
  public void batchModify(List<T> list) throws DBException;


}

/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.kunpu.frameworks.commons.exception.CommonRuntimeException;
import com.kunpu.frameworks.commons.exception.business.BusinessException;
import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.kunpu.frameworks.commons.utils.BeanCopierUtils;
import com.kunpu.frameworks.db.pagination.Page;
import com.kunpu.frameworks.db.pagination.PaginationBean;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.dal.IBaseDao;
import com.xrds.basic.component.service.repository.BaseService;

/**
 * 
 * @author liukai
 * @version $Id: BaseServiceImpl.java, v 0.1 2017年6月13日 上午10:36:26 liukai Exp $
 */
@Service
public abstract class BaseServiceImpl<T, DO> implements BaseService<T, DO> {
  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_SERVICE.getLogName());

  protected abstract IBaseDao<DO> getBaseDAO();

  protected abstract Class<DO> getDoClass();

  protected abstract Class<T> getServiceModelClass();

  @Override
  public Integer addData(T o) {
    int count = 0;
    DO doObj = BeanCopierUtils.copyOne2One(o, getDoClass());
    try {
      count = getBaseDAO().insertData(doObj);
    } catch (Exception e) {
      LOGGER.error("addData [{}] error," + this.getClass().getName() + "=[{}]", this.getClass()
          .getName(), ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE));
      throw new DBException(BusErrorCode.ERROR_201, e);
    }
    BeanCopierUtils.copyProperties(doObj, o);
    return count;
  }

  @Override
  public int removeDataByPK(Object o) {
    DO doObj = BeanCopierUtils.copyOne2One(o, getDoClass());
    try {
      return getBaseDAO().deleteDataByPK(doObj);
    } catch (Exception e) {
      LOGGER.error("removeDataByPK [{}] error," + this.getClass().getName() + "=[{}]", this
          .getClass().getName(), ToStringBuilder.reflectionToString(o,
          ToStringStyle.SHORT_PREFIX_STYLE));
      throw new DBException(BusErrorCode.ERROR_204, e);
    }
  }

  @Override
  public int modifyDataByPK(T o) {
    DO doObj = BeanCopierUtils.copyOne2One(o, getDoClass());
    try {
      return getBaseDAO().updateDataByPK(doObj);
    } catch (Exception e) {
      LOGGER.error("modifyDataByPK [{}] error," + this.getClass().getName() + "=[{}]", this
          .getClass().getName(), ToStringBuilder.reflectionToString(o,
          ToStringStyle.SHORT_PREFIX_STYLE));
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
  }

  @Override
  public int getInt(T o) {
    DO doObj = BeanCopierUtils.copyOne2One(o, getDoClass());
    try {
      return getBaseDAO().queryForInt(doObj);
    } catch (Exception e) {
      LOGGER.error("getInt [{}] error," + this.getClass().getName() + "=[{}]", this.getClass()
          .getName(), ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE));
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public PaginationBean<T> getListByPagination(T o, Page page) {
    DO doObj = BeanCopierUtils.copyOne2One(o, getDoClass());
    PaginationBean<DO> paginationBeanDO = null;
    try {
      int totalRecords = getBaseDAO().queryCount(doObj);
      paginationBeanDO = new PaginationBean<DO>(page, totalRecords);
      List<DO> resultList =
          getBaseDAO().queryForPageList(
              doObj,
              new RowBounds((paginationBeanDO.getCurrentPage() - 1)
                  * paginationBeanDO.getPageSize(), paginationBeanDO.getPageSize()));
      paginationBeanDO.setPageList(resultList);
    } catch (Exception e) {
      LOGGER.error("getListByPagination [{}] error," + this.getClass().getName() + "=[{}]", this
          .getClass().getName(), ToStringBuilder.reflectionToString(o,
          ToStringStyle.SHORT_PREFIX_STYLE));
      throw new DBException(BusErrorCode.ERROR_203.getCode(), BusErrorCode.ERROR_203.getMsg(), e);
    }
    PaginationBean<T> newPageing = null;
    try {
      newPageing = (PaginationBean<T>) copyPaging2Paging(paginationBeanDO, o.getClass());
    } catch (BusinessException e) {
      throw new CommonRuntimeException(BusErrorCode.ERROR_203, e);
    }
    return newPageing;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> getList(T o) {
    DO doObj = BeanCopierUtils.copyOne2One(o, getDoClass());
    List<DO> listDO = new ArrayList<DO>();
    try {
      listDO = getBaseDAO().queryForList(doObj);
      if (CollectionUtils.isEmpty(listDO)) return null;
    } catch (Exception e) {
      LOGGER.error("getList [{}] error," + this.getClass().getName() + "=[{}]", this.getClass()
          .getName(), ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE));
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
    return (List<T>) BeanCopierUtils.copyList2List(listDO, o.getClass());
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> getList(T o, int offSet, int maxRow) {
    DO doObj = BeanCopierUtils.copyOne2One(o, getDoClass());
    List<DO> listDO = new ArrayList<DO>();
    try {
      listDO = getBaseDAO().selectByRowBounds(doObj, new RowBounds(offSet, maxRow));
    } catch (Exception e) {
      LOGGER.error("getList [{}] error," + this.getClass().getName() + "=[{}]", this.getClass()
          .getName(), ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE));
      throw new DBException(BusErrorCode.ERROR_203.getCode(), BusErrorCode.ERROR_203.getMsg(), e);
    }
    return (List<T>) BeanCopierUtils.copyList2List(listDO, o.getClass());
  }

  @Override
  public T getObject(T o) {
    DO doObj = BeanCopierUtils.copyOne2One(o, getDoClass());
    DO d = null;
    try {
      d = getBaseDAO().queryObject(doObj);
      if (d == null) {
        return null;
      }
    } catch (Exception e) {
      LOGGER.error("getObject [{}] error," + this.getClass().getName() + "=[{}]", this.getClass()
          .getName(), ToStringBuilder.reflectionToString(o, ToStringStyle.SHORT_PREFIX_STYLE));
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
    return BeanCopierUtils.copyOne2One(d, getServiceModelClass());
  }

  @Override
  public List<T> getAllList() {
    List<DO> doList = new ArrayList<DO>();
    try {
      doList = getBaseDAO().queryForListAll();
    } catch (Exception e) {
      LOGGER.error("getAllList [{}] error", this.getClass().getName());
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
    List<T> resultList = BeanCopierUtils.copyList2List(doList, getServiceModelClass());
    LOGGER.info("{} resule value =[{}]", getBaseDAO().getClass().getName(),
        ToStringBuilder.reflectionToString(resultList, ToStringStyle.SHORT_PREFIX_STYLE));
    return resultList;
  }

  @Override
  public T getObjectByPK(Object pk) {
    Object o = null;
    try {
      o = getBaseDAO().queryObjectByPK(pk);
      if (o == null) {
        return null;
      }
    } catch (Exception e) {
      LOGGER.error("getObjectByPK [{}] error," + this.getClass().getName() + "=[{}]", this
          .getClass().getName(), pk);
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
    return BeanCopierUtils.copyOne2One(o, getServiceModelClass());
  }

  @Override
  public void batchAdd(List<T> list) {
    List<DO> doList = (List<DO>) BeanCopierUtils.copyList2List(list, getDoClass());
    try {
      getBaseDAO().batchInsertData(doList);
    } catch (Exception e) {
      LOGGER.error("batchAdd [{}] error," + this.getClass().getName() + "=[{}]", this.getClass()
          .getName(), list.size());
      throw new DBException(BusErrorCode.ERROR_201, e);
    }
  }

  @Override
  public void batchModify(List<T> list) {
    List<DO> doList = (List<DO>) BeanCopierUtils.copyList2List(list, getDoClass());
    try {
      getBaseDAO().batchUpdateData(doList);
    } catch (Exception e) {
      LOGGER.error("batchModify [{}] error," + this.getClass().getName() + "=[{}]", this.getClass()
          .getName(), list.size());
      throw new DBException(BusErrorCode.ERROR_202, e);
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> PaginationBean<T> copyPaging2Paging(PaginationBean<?> pageing, Class<T> class1)
      throws BusinessException {
    PaginationBean<T> paging = new PaginationBean<>();
    paging = (PaginationBean<T>) BeanCopierUtils.copyOne2One(pageing, paging.getClass());
    paging.setPageList(BeanCopierUtils.copyList2List(pageing.getPageList(), class1));
    return paging;
  }
}

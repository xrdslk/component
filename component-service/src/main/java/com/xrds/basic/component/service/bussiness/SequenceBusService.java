/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.bussiness;

import com.kunpu.frameworks.commons.exception.dao.DBException;


/**
 * 
 * @author liukai
 * @version $Id: SequenceBusService.java, v 0.1 2017年6月14日 下午3:21:24 liukai Exp $
 */
public interface SequenceBusService {

  /**
   * 自定义数据库序列号
   *
   * @param prefix
   * @param length
   * @return
   * @throws DBException
   */
  public String getSequenceByPrefixAndLength(String prefix, int length) throws DBException;

  /**
   * 获取通用序列号
   *
   * @return
   * @throws DBException
   */
  public String getCommonSeq() throws DBException;

}

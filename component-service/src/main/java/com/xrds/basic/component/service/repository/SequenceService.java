/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.repository;

import java.math.BigInteger;

import com.kunpu.frameworks.commons.exception.dao.DBException;

/**
 * 
 * @author liukai
 * @version $Id: SequenceService.java, v 0.1 2017年6月13日 下午9:19:26 liukai Exp $
 */
public interface SequenceService {
  /**
   * 公共Seq
   * 
   * @return
   */
  public BigInteger getCommonSeq() throws DBException;

}

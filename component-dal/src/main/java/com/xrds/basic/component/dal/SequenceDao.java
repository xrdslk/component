/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal;

import org.springframework.beans.factory.annotation.Qualifier;

import com.xrds.basic.component.dal.config.BaseDB;
import com.xrds.basic.component.dal.model.SequenceDo;

/**
 * 
 * @author liukai
 * @version $Id: SeqDao.java, v 0.1 2017年6月13日 下午7:59:49 liukai Exp $
 */
@BaseDB
@Qualifier("sequenceDao")
public interface SequenceDao extends IBaseDao<SequenceDo> {
  /**
   * 公共Seq
   * 
   * @return
   */
  public Integer getCommonSeq(SequenceDo o);
}

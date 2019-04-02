/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.repository.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.dal.SequenceDao;
import com.xrds.basic.component.dal.model.SequenceDo;
import com.xrds.basic.component.service.repository.SequenceService;

/**
 * 
 * @author liukai
 * @version $Id: SequenceServiceImpl.java, v 0.1 2017年6月13日 下午9:20:50 liukai Exp $
 */
@Service("sequenceService")
public class SequenceServiceImpl implements SequenceService {
  private static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_SERVICE.getLogName());

  @Qualifier("transactionTemplate1")
  private TransactionTemplate transactionTemplate;

  @Autowired
  private SequenceDao sequenceDao;

  @Override
  public BigInteger getCommonSeq() throws DBException {
    try {
      SequenceDo sequenceDo = initSequenceDo();
      sequenceDao.getCommonSeq(sequenceDo);
      return sequenceDo.getId();
    } catch (Exception e) {
      throw new DBException(BusErrorCode.ERROR_203, e);
    }
  }

  private SequenceDo initSequenceDo() {
    return new SequenceDo("S");
  }


}

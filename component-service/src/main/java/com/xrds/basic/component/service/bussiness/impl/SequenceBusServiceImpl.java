/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.service.bussiness.impl;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunpu.frameworks.commons.exception.dao.DBException;
import com.kunpu.frameworks.commons.log.CommonLogType;
import com.kunpu.frameworks.commons.log.LoggerAdapter;
import com.kunpu.frameworks.commons.log.LoggerAdapterFactory;
import com.xrds.basic.component.common.code.BusErrorCode;
import com.xrds.basic.component.common.constants.CommonApplicationConstants.SequenceConstants;
import com.xrds.basic.component.common.constants.CommonApplicationConstants.SequencePrefix;
import com.xrds.basic.component.common.util.StringBuilderUtil;
import com.xrds.basic.component.service.bussiness.SequenceBusService;
import com.xrds.basic.component.service.bussiness.util.RedisSerialUtil;
import com.xrds.basic.component.service.repository.SequenceService;

/**
 * 
 * @author liukai
 * @version $Id: SequenceBusServiceImpl.java, v 0.1 2017年6月14日 下午4:34:04 liukai Exp $
 */
@Service("sequenceBusService")
public class SequenceBusServiceImpl implements SequenceBusService {
  protected static final LoggerAdapter LOGGER = LoggerAdapterFactory
      .getLogger(CommonLogType.SYS_SERVICE.getLogName());


  @Autowired
  private SequenceService sequenceService;
  @Autowired
  private RedisSerialUtil redisSerialUtil;

  /**
   * 获取通用序列号
   *
   * @return
   * @throws DBException
   */
  @Override
  public String getCommonSeq() throws DBException {
    String commonSeq = redisSerialUtil.getCommonRedisAtomicLong();
    if (StringUtils.isNotBlank(commonSeq)) return commonSeq;
    return getSequenceByPrefixAndLength(SequencePrefix.COMMON_PREFIX,
        SequenceConstants.SEQUENCE_LENGTH_14);
  }

  /**
   * 生成流水
   *
   * @param prefix
   * @param sequenceId
   * @param length
   * @return
   * @throws DBException
   */
  private String getSerialNo(String prefix, BigInteger sequenceId, int length) throws DBException {
    if (sequenceId == null || StringUtils.isBlank(sequenceId.toString())) {
      LOGGER.dbError("获取sequence为空！prefix[{}],sequenceId[{}]", prefix, sequenceId);
      throw new DBException(BusErrorCode.ERROR_203);
    }
    return StringBuilderUtil.buildSerialNo(prefix, sequenceId.toString(), length);
  }


  /**
   * 自定义序列号
   *
   * @param prefix
   * @param length
   * @return
   * @throws DBException
   */
  @Override
  public String getSequenceByPrefixAndLength(String prefix, int length) throws DBException {
    BigInteger sequenceId = sequenceService.getCommonSeq();
    return getSerialNo(prefix, sequenceId, length);
  }



}

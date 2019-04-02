/**
 * 
 * 坤普 Copyright (c) 2016-2018 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.test.service;

import java.math.BigInteger;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xrds.basic.component.service.repository.SequenceService;
import com.xrds.basic.test.BaseTest;

/**
 * 
 * @author yuyangzhi
 * @version $Id: SequenceServiceTest.java, v 0.1 2018年6月26日 下午8:30:45 yuyangzhi Exp $
 */
public class SequenceServiceTest extends BaseTest {

  @Autowired
  private SequenceService sequenceService;

  @Test
  public void testQuery() {
    BigInteger rs = sequenceService.getCommonSeq();
    System.out.println(rs);
  }
}

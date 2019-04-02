/**
 * 
 * 坤普 Copyright (c) 2016-2018 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.test.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xrds.basic.component.service.bussiness.SequenceBusService;
import com.xrds.basic.test.BaseTest;

/**
 * 
 * @author yuyangzhi
 * @version $Id: SequenceBusServiceTest.java, v 0.1 2018年6月25日 下午3:50:35 yuyangzhi Exp $
 */
public class SequenceBusServiceTest extends BaseTest {

  @Autowired
  private SequenceBusService sequenceBusService;

  @Test
  public void getSeqTest() {
    String commonSeq = sequenceBusService.getCommonSeq();
    System.out.println(commonSeq);
  }

}

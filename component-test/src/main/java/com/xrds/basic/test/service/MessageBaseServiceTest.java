/**
 * 
 * 坤普 Copyright (c) 2016-2018 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.test.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xrds.basic.component.service.repository.MessageBaseService;
import com.xrds.basic.component.service.repository.bean.MessageBaseInfo;
import com.xrds.basic.test.BaseTest;

/**
 * 
 * @author yuyangzhi
 * @version $Id: MessageBaseServiceTest.java, v 0.1 2018年6月26日 下午8:23:12 yuyangzhi Exp $
 */
public class MessageBaseServiceTest extends BaseTest {

  @Autowired
  protected MessageBaseService messageBaseService;

  @Test
  public void testSave() {
    MessageBaseInfo baseInfo = new MessageBaseInfo();
    baseInfo.setMessageType("email");
    baseInfo.setStatus("0");
    baseInfo.setPreSendTime(new Date());
    baseInfo.setCreateTime(new Date());
    baseInfo.setUpdateTime(new Date());
    Integer rs = messageBaseService.addData(baseInfo);
    System.out.println(rs);
  };

}

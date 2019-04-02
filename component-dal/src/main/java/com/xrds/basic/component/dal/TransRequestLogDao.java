/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.dal;

import org.springframework.beans.factory.annotation.Qualifier;

import com.xrds.basic.component.dal.config.BaseDB;
import com.xrds.basic.component.dal.model.TransRequestLogDo;

/**
 * 外部渠道接口访问出入参记录
 * 
 * @author liukai
 * @version $Id: TransRequestLogDao.java, v 0.1 2017年12月3日 上午10:59:39 liukai Exp $
 */
@BaseDB
@Qualifier("transRequestLogDao")
public interface TransRequestLogDao extends IBaseDao<TransRequestLogDo> {

}

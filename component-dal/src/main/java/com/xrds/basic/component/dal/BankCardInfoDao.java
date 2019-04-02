package com.xrds.basic.component.dal;

import org.springframework.beans.factory.annotation.Qualifier;

import com.xrds.basic.component.dal.config.BaseDB;
import com.xrds.basic.component.dal.model.BankcardInfoDo;


/**
 * 银行卡信息
 * 
 * @author admin
 *
 */
@BaseDB
@Qualifier("bankCardInfoDao")
public interface BankCardInfoDao extends IBaseDao<BankcardInfoDo> {
  public int insert(BankcardInfoDo bankcardInfo);

  public BankcardInfoDo queryByCardNumber(String cardNumber);

  public int updateByCardNumber(BankcardInfoDo bankcardInfo);

  public int updateByCityNo(BankcardInfoDo bankcardInfo);
}

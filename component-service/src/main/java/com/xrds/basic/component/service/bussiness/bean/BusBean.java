package com.xrds.basic.component.service.bussiness.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author admin
 *
 */

public class BusBean {
  private Integer id;
  private String cardNumber;
  private String bankCode;
  private String insCd;
  private String cityNo;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getBankCode() {
    return bankCode;
  }

  public void setBankCode(String bankCode) {
    this.bankCode = bankCode;
  }

  public String getInsCd() {
    return insCd;
  }

  public void setInsCd(String insCd) {
    this.insCd = insCd;
  }

  public String getCityNo() {
    return cityNo;
  }

  public void setCityNo(String cityNo) {
    this.cityNo = cityNo;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

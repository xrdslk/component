/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.enums;

/**
 * 
 * @author liukai
 * @version $Id: EmailCategoryEnum.java, v 0.1 2017年11月26日 下午2:35:16 liukai Exp $
 */
public enum EmailCategoryEnum {
  TEXT("txt", "文本"), HTML("html", "网页"), ATTACHMENT("attachment", "");

  private EmailCategoryEnum(String value, String name) {
    this.value = value;
    this.name = name;
  }

  private String value;
  private String name;

  public String getCode() {
    return this.value;
  }

  public String getName() {
    return this.name;
  }

  public boolean equal(String code) {
    return ((null == code) ? false : getCode().equals(code));
  }
}

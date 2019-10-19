package com.xrds.basic.component.common.seetaface.face.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 
 * @author GSZY
 * @version $Id: RegisterData.java, v 0.1 2019年10月19日 下午5:54:39 GSZY Exp $
 */
public class RegisterData {
  public byte[] data;
  public int index;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

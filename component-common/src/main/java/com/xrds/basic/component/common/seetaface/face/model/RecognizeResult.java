package com.xrds.basic.component.common.seetaface.face.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 
 * @author GSZY
 * @version $Id: RecognizeResult.java, v 0.1 2019年10月19日 下午5:54:26 GSZY Exp $
 */
public class RecognizeResult {
  public int index;
  public float similar;


  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

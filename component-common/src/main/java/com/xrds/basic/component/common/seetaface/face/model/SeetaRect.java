package com.xrds.basic.component.common.seetaface.face.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 
 * @author GSZY
 * @version $Id: SeetaRect.java, v 0.1 2019年10月19日 下午5:55:16 GSZY Exp $
 */
public class SeetaRect {
  public int x;
  public int y;
  public int width;
  public int height;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

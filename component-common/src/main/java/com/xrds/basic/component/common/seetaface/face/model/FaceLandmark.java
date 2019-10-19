package com.xrds.basic.component.common.seetaface.face.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 
 * @author GSZY
 * @version $Id: FaceLandmark.java, v 0.1 2019年10月19日 下午5:54:13 GSZY Exp $
 */
public class FaceLandmark {
  public SeetaRect[] rects;
  public SeetaPointF[] points;


  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

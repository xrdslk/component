package com.xrds.basic.component.common.seetaface.face.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 
 * @author GSZY
 * @version $Id: SeetaImageData.java, v 0.1 2019年10月19日 下午5:54:51 GSZY Exp $
 */
public class SeetaImageData {
  public SeetaImageData() {

  }

  public SeetaImageData(int width, int height, int channels) {
    this.data = new byte[width * height * channels];
    this.width = width;
    this.height = height;
    this.channels = channels;
  }

  public SeetaImageData(int width, int height) {
    this(width, height, 3);
  }

  public byte[] data;
  public int width;
  public int height;
  public int channels;

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

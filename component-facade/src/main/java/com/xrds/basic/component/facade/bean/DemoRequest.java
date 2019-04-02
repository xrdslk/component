package com.xrds.basic.component.facade.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;

import com.xrds.basic.component.facade.bean.abs.AbstractRequest;


/**
 * 
 * 
 * @author yuyangzhi
 * @version $Id: DemoRequest.java, v 0.1 2018年6月28日 下午4:03:33 admin Exp $
 */
public class DemoRequest extends AbstractRequest {
  private static final long serialVersionUID = 8702588950707359872L;

  @NotBlank(message = "businessId 不能为空或NULL")
  private String businessId;

  @NotBlank(message = "businessType 不能为空或NULL")
  private String businessType;

  /**
   * Getter method for property <tt>businessId</tt>.
   * 
   * @return property value of businessId
   */
  public String getBusinessId() {
    return businessId;
  }

  /**
   * Setter method for property <tt>businessId</tt>.
   * 
   * @param businessId value to be assigned to property businessId
   */
  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }

  /**
   * Getter method for property <tt>businessType</tt>.
   * 
   * @return property value of businessType
   */
  public String getBusinessType() {
    return businessType;
  }

  /**
   * Setter method for property <tt>businessType</tt>.
   * 
   * @param businessType value to be assigned to property businessType
   */
  public void setBusinessType(String businessType) {
    this.businessType = businessType;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }
}

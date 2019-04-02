/**
 * 
 */
package com.xrds.basic.component.service.integration.bean;

import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 
 * @author liukai
 * @version $Id: SmsSendBean.java, v 0.1 2017年11月25日 下午11:02:50 liukai Exp $
 */
public class SmsSendBean {
  /** 模板ID **/
  private String eventId;

  /** 业务ID **/
  private String businessId;

  /** 手机号 **/
  private String[] phoneNum;

  /** 参数配置名称 **/
  private String configName;

  /** 发送参数 **/
  private Map<String, Object> paramMap;

  /** 平台渠道 **/
  private String platformChannel;

  public String getEventId() {
    return eventId;
  }

  public void setEventId(String eventId) {
    this.eventId = eventId;
  }

  public String getBusinessId() {
    return businessId;
  }

  public void setBusinessId(String businessId) {
    this.businessId = businessId;
  }

  public String[] getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String[] phoneNum) {
    this.phoneNum = phoneNum;
  }

  public String getConfigName() {
    return configName;
  }

  public void setConfigName(String configName) {
    this.configName = configName;
  }

  public Map<String, Object> getParamMap() {
    return paramMap;
  }

  public void setParamMap(Map<String, Object> paramMap) {
    this.paramMap = paramMap;
  }

  public String getPlatformChannel() {
    return platformChannel;
  }

  public void setPlatformChannel(String platformChannel) {
    this.platformChannel = platformChannel;
  }

  /**
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}

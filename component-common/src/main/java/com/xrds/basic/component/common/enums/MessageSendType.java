/**
 * 
 */
package com.xrds.basic.component.common.enums;

/**
 * 
 * 
 * @author liukai
 * @version $Id: MessageSendType.java, v 0.1 2017年11月25日 下午11:05:34 liukai Exp $
 */
public enum MessageSendType {
  EMAIL("email", "邮件类型"), SMS("sms", "短信类型"), MQ("mq", "Q消息类型"), MQ_QUEUE("queue", "队列"), MQ_TOPIC(
      "topic", "topic"), EMAIL_NORMO("0", "正常邮件"), EMAIL_ERROR("1", "异常邮件");

  private MessageSendType(String type, String msg) {
    this.type = type;
    this.msg = msg;
  }

  private String type;
  private String msg;

  /**
   * Getter method for property <tt>type</tt>.
   * 
   * @return property value of type
   */
  public String getType() {
    return type;
  }

  /**
   * Setter method for property <tt>type</tt>.
   * 
   * @param type value to be assigned to property type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Getter method for property <tt>msg</tt>.
   * 
   * @return property value of msg
   */
  public String getMsg() {
    return msg;
  }

  /**
   * Setter method for property <tt>msg</tt>.
   * 
   * @param msg value to be assigned to property msg
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }

}

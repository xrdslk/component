/**
 * 
 */
package com.xrds.basic.component.common.enums;

/**
 * 消息发送状态
 * 
 * @author liukai
 * @version $Id: MessageSendStatus.java, v 0.1 2017年11月25日 下午11:05:13 liukai Exp $
 */
public enum MessageSendStatus {
  UNDO("待发送", "I"), SUCCESS("发送成功", "S"), FAIL("发送失败", "F"), DELETE("作废", "D");

  private MessageSendStatus(String name, String code) {
    this.name = name;
    this.code = code;
  }

  private String name;
  private String code;

  /**
   * Getter method for property <tt>name</tt>.
   * 
   * @return property value of name
   */
  public String getName() {
    return name;
  }

  /**
   * Getter method for property <tt>code</tt>.
   * 
   * @return property value of code
   */
  public String getCode() {
    return code;
  }

}

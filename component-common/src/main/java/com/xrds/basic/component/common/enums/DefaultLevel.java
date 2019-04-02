/**
 * 
 */
package com.xrds.basic.component.common.enums;

/**
 * 
 * 
 * @author liukai
 * @version $Id: DefaultLevel.java, v 0.1 2017年11月25日 下午11:13:51 liukai Exp $
 */
public enum DefaultLevel {

  HIGH("高", "H"), MIDDLE("中", "M"), LOW("低", "L");

  private DefaultLevel(String msg, String code) {
    this.msg = msg;
    this.code = code;
  }

  /** 级别描述 **/
  private String msg;
  /** 级别编码 **/
  private String code;

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

  /**
   * Getter method for property <tt>code</tt>.
   * 
   * @return property value of code
   */
  public String getCode() {
    return code;
  }

  /**
   * Setter method for property <tt>code</tt>.
   * 
   * @param code value to be assigned to property code
   */
  public void setCode(String code) {
    this.code = code;
  }

}

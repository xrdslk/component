/**
 * 
 */
package com.xrds.basic.test.common;

/**
 * @author GSZY
 *
 */
public class CallDuration {
	
	private String customer_id;
	private String callName;
	private String callNumber;
	private String callType;
	private String callDate;
	private String callDuration;
	/**
	 * @return the customer_id
	 */
	public String getCustomer_id() {
		return customer_id;
	}
	/**
	 * @param customer_id the customer_id to set
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	/**
	 * @return the callName
	 */
	public String getCallName() {
		return callName;
	}
	/**
	 * @param callName the callName to set
	 */
	public void setCallName(String callName) {
		this.callName = callName;
	}
	/**
	 * @return the callNumber
	 */
	public String getCallNumber() {
		return callNumber;
	}
	/**
	 * @param callNumber the callNumber to set
	 */
	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}
	/**
	 * @return the callType
	 */
	public String getCallType() {
		return callType;
	}
	/**
	 * @param callType the callType to set
	 */
	public void setCallType(String callType) {
		this.callType = callType;
	}
	/**
	 * @return the callDate
	 */
	public String getCallDate() {
		return callDate;
	}
	/**
	 * @param callDate the callDate to set
	 */
	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}
	/**
	 * @return the callDuration
	 */
	public String getCallDuration() {
		return callDuration;
	}
	/**
	 * @param callDuration the callDuration to set
	 */
	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CallDuration [customer_id=" + customer_id + ", callName="
				+ callName + ", callNumber=" + callNumber + ", callType="
				+ callType + ", callDate=" + callDate + ", callDuration="
				+ callDuration + "]";
	}
	

}

/**
 * 
 */
package com.xrds.basic.test.common;

/**
 * @author GSZY
 *
 */
public class CList {
	
	private String contactName;
	
	private String contactPhone;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Override
	public String toString() {
		return "CList [contactName=" + contactName + ", contactPhone="
				+ contactPhone + "]";
	}
	
	

}

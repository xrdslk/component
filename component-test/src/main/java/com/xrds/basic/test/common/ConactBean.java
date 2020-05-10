/**
 * 
 */
package com.xrds.basic.test.common;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author GSZY
 *
 */
public class ConactBean {
	
	private String loanOrderNo;
	private String mobile;
	private String customerId;
	private Date createTime;
	private String relativesMobile;
	private String socialPhone;
	private String colleaguePhone;
	private String companyPhone;
	private String guarantorPhone;
	private String contactList;
	
	private Map<String,String> cList;
	public String getLoanOrderNo() {
		return loanOrderNo;
	}
	public void setLoanOrderNo(String loanOrderNo) {
		this.loanOrderNo = loanOrderNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getRelativesMobile() {
		return relativesMobile;
	}
	public void setRelativesMobile(String relativesMobile) {
		this.relativesMobile = relativesMobile;
	}
	public String getSocialPhone() {
		return socialPhone;
	}
	public void setSocialPhone(String socialPhone) {
		this.socialPhone = socialPhone;
	}
	public String getColleaguePhone() {
		return colleaguePhone;
	}
	public void setColleaguePhone(String colleaguePhone) {
		this.colleaguePhone = colleaguePhone;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getGuarantorPhone() {
		return guarantorPhone;
	}
	public void setGuarantorPhone(String guarantorPhone) {
		this.guarantorPhone = guarantorPhone;
	}
	public String getContactList() {
		return contactList;
	}
	public void setContactList(String contactList) {
		this.contactList = contactList;
	}
	
	
	public Map<String, String> getcList() {
		return cList;
	}
	public void setcList(Map<String, String> cList) {
		this.cList = cList;
	}
	@Override
	public String toString() {
		return "ConactBean [loanOrderNo=" + loanOrderNo + ", mobile=" + mobile
				+ ", customerId=" + customerId + ", createTime=" + createTime
				+ ", relativesMobile=" + relativesMobile + ", socialPhone="
				+ socialPhone + ", colleaguePhone=" + colleaguePhone
				+ ", companyPhone=" + companyPhone + ", guarantorPhone="
				+ guarantorPhone + ", contactList=" + contactList + "]";
	}
	

}

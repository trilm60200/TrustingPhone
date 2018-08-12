package com.trusting.csv.entities.input;

import java.util.Date;

import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.NullString;
import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Trim;

public class Phone {
	
	@Trim
	@Parsed(field = "PHONE_NUMBER")
	private String phoneNumber;
	@Trim
	@Parsed(field = "ACTIVATION_DATE")
	@Format(formats = {"yyyy-MM-dd"}, options = "locale=en;lenient=false")
	private Date activateDate;
	@Trim
	@Parsed(field = "DEACTIVATION_DATE")
	@NullString(nulls = {"?", "-", "", " "})
	@Format(formats = {"yyyy-MM-dd"}, options = "locale=en;lenient=false")
	private Date deactiveDate;
	
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Date getActivateDate() {
		return activateDate;
	}
	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}
	public Date getDeactiveDate() {
		return deactiveDate;
	}
	public void setDeactiveDate(Date deactiveDate) {
		this.deactiveDate = deactiveDate;
	}
	public Phone(String phoneNumber, Date activateDate) {
		this.phoneNumber = phoneNumber;
		this.activateDate = activateDate;
	}
	
	public Phone() {
		
	}
	@Override
	public String toString() {
		return "Phone [phoneNumber=" + phoneNumber + ", activateDate=" + activateDate + ", deactiveDate=" + deactiveDate
				+ "]";
	}
	
}

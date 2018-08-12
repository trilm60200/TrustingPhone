package com.trusting.csv.entities.output;

import java.util.Date;


public class Phone {

	private String phoneNumber;
	private Date realActivateDate;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getActivateDate() {
		return realActivateDate;
	}

	public void setActivateDate(Date activateDate) {
		this.realActivateDate = activateDate;
	}

	public Phone(String phoneNumber, Date activateDate) {
		this.phoneNumber = phoneNumber;
		this.realActivateDate = activateDate;
	}

	public Phone() {

	}

	@Override
	public String toString() {
		return "Phone [phoneNumber=" + phoneNumber + ", activateDate=" + realActivateDate + "]";
	}

}

package com.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ContactInfo implements java.io.Serializable {

	private String address;
	private String phone1;
	private String phone2;

	public ContactInfo() {
	}

	public ContactInfo(String address, String phone1, String phone2) {

		this.address = address;
		this.phone1 = phone1;
		this.phone2 = phone2;
	}

	@Column(name = "address", length = 45)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "phone_1", length = 45)
	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	@Column(name = "phone_2", length = 45)
	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

}

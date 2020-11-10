package com.blz.addressbook;

class Person {
	String firstName;
	String lastName;
	String address;
	String city;
	String state;
	String zip;
	String phone;


	Person(String firstName, String lastName, String address, String city, String state, String zip, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phone = phone;
	}

	String getFirstName() {
		return firstName;
	}

	String getLastName() {
		return lastName;
	}

	String getAddress() {
		return address;
	}

	String getCity() {
		return city;
	}

	String getState() {
		return state;
	}

	String getZip() {
		return zip;
	}

	String getPhone() {
		return phone;
	}

	public String toString() {
		return "\n" + getFirstName() + " " + getLastName() + "\n" + getAddress() + "\n" + getCity() + " " + getState()
				+ "-" + getZip() + "\n" + getPhone() + "\n";
	}
}

package pl.boboli.engineer.test.external.model;

public class Address {
	String streetName;
	String streetNumber;
	String postCode;
	
	
	
	public Address() {
		super();
	}
	
	public Address(String streetName, String streetNumber, String postCode) {
		super();
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.postCode = postCode;
	}

	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	

}

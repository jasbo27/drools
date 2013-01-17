package pl.boboli.engineer.test.external.model;

public class CustomerFact {
	Integer customerId;
	String firstName;
	String lastName;
	String nip;
	String pesel;
	Address address;
	
	
	
	public CustomerFact() {
		super();
	}
	
	
	public CustomerFact(Integer customerId, String firstName, String lastName,
			String nip, String pesel, Address address) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nip = nip;
		this.pesel = pesel;
		this.address = address;
	}


	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}

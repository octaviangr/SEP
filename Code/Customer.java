import java.io.Serializable;


public class Customer extends Person implements Serializable{
	
	private String address;
	private String phoneNumber;
	private String email;
	
	public Customer(String name, String address, String phoneNumber, String email)
	{
		super(name);
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [address=" + address + ", phoneNumber=" + phoneNumber
				+ ", email=" + email + "]";
	}

}

import java.io.Serializable;


public class Customer extends Person implements Serializable{
	
	private String address;
	private String phoneNumber;
	private String email;
	private int numberOfBookings;
	private int moneySpent;
	
	public Customer(String name, String address, String phoneNumber, String email)
	{
		super(name);
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	public String getFrequencyInfo()
	{
		String all = String.format("This customer made %d bookings and spent %d DKK in the past", numberOfBookings,moneySpent);
		return all;
	}
	
	public int getNumberOfBookings()
	{
		return numberOfBookings;
	}
	public void setNumberOfBookings(int number)
	{
		this.numberOfBookings = number;
	}
	public int getMoneySpent()
	{
		return this.moneySpent;
	}
	public void setMoneySpent(int money)
	{
		this.moneySpent = money;
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

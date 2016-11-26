
public class Passenger extends Person{
	
	private String address;
	private MyDate birthday;
	
	public Passenger(String name, String address, MyDate birthday)
	{
		super(name);
		this.address = address;
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public MyDate getBirthday() {
		return birthday;
	}

	public void setBirthday(MyDate birthday) {
		this.birthday = birthday;
	}

}

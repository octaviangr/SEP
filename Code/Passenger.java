import java.io.Serializable;


public class Passenger extends Person implements Serializable{
	
	private String address;
	private int age;
	
	public Passenger(String name, String address, int age)
	{
		super(name);
		this.address = address;
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	public String toString()
	{
		String all = String.format("%s (Age: %d)", super.getName(),this.age);
		return all;
	}
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Passenger))
		{
			return false;
		}
		else
		{
			Passenger other = (Passenger) obj;
			return (this.address.equalsIgnoreCase(other.address) && this.age == other.age && super.getName().equalsIgnoreCase(other.getName()));
		}
	}

}

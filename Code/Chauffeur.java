import java.io.Serializable;
import java.util.ArrayList;


public class Chauffeur extends Person implements Serializable{

	private String phoneNumber;
	private String employmentType;
	private String preferences;
	private int id;
	private ArrayList<MyDate> schedule;
	
	public ArrayList<MyDate> getSchedule() {
		return schedule;
	}

	public void setSchedule(ArrayList<MyDate> schedule) {
		this.schedule = schedule;
	}

	public Chauffeur(String name, String phoneNumber,String employmentType, String preferences, int id)
	{
		super(name);
		this.phoneNumber = phoneNumber;
		this.employmentType = employmentType;
		this.preferences = preferences;
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public String getPreferences() {
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String toString()
	{
		String all = String.format("%s - ID: %d", super.getName(),this.id);
		return all;
	}
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Chauffeur))
		{
			return false;
		}
		else
		{
			Chauffeur other = (Chauffeur) obj;
			return (this.phoneNumber.equalsIgnoreCase(other.phoneNumber) && this.employmentType.equalsIgnoreCase(other.employmentType) && this.id == other.id && this.preferences.equalsIgnoreCase(other.preferences));
		}
	}
}

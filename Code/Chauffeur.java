
public class Chauffeur extends Person{

	private String phoneNumber;
	private String employmentType;
	private String preferences;
	private int id;
	
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
}

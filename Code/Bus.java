import java.util.ArrayList;


public class Bus {

	private String type;
	private String make;
	private String model;
	private ArrayList<MyDate> bookings;// same as the chauffeur schedule - days in which is not available
	
	public Bus(String type, String make, String model)
	{
		this.type = type;
		this.make = make;
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}

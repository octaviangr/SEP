import java.util.*;

public class Tour {

	private ArrayList<Chauffeur> chauffeurs;
	private Bus bus; //to be changed if we want to save the busses properly
	private MyDate departureTime;
	private MyDate returnTime;
	private String departureLocation;
	private String returnLocation;
	private ArrayList<String> stops;
	private int numberOfSeats;
	private String extras;
	
	public Tour(MyDate departureTime, MyDate returnTime,String departureLocation, String returnLocation, ArrayList<String> stops,Bus bus, int numberOfSeats, String extras)
	{
		this.departureTime = departureTime;
		this.returnTime = returnTime;
		this.departureLocation = departureLocation;
		this.returnLocation = returnLocation;
		this.bus = bus;
		this.stops = stops;
		this.numberOfSeats = numberOfSeats;
		this.extras = extras;
	}

	public String getDepartureLocation() {
		return departureLocation;
	}

	public void setDepartureLocation(String departureLocation) {
		this.departureLocation = departureLocation;
	}

	public String getReturnLocation() {
		return returnLocation;
	}

	public void setReturnLocation(String returnLocation) {
		this.returnLocation = returnLocation;
	}

	public ArrayList<Chauffeur> getChauffeurs() {
		return chauffeurs;
	}

	public void setChauffeurs(ArrayList<Chauffeur> chauffeurs) {
		this.chauffeurs = chauffeurs;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public MyDate getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(MyDate departureTime) {
		this.departureTime = departureTime;
	}

	public MyDate getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(MyDate returnTime) {
		this.returnTime = returnTime;
	}

	public ArrayList<String> getStops() {
		return stops;
	}

	public void setStops(ArrayList<String> stops) {
		this.stops = stops;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tour other = (Tour) obj;
		if (bus == null) {
			if (other.bus != null)
				return false;
		} else if (!bus.equals(other.bus))
			return false;
		if (chauffeurs == null) {
			if (other.chauffeurs != null)
				return false;
		} else if (!chauffeurs.equals(other.chauffeurs))
			return false;
		if (departureLocation == null) {
			if (other.departureLocation != null)
				return false;
		} else if (!departureLocation.equals(other.departureLocation))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (extras == null) {
			if (other.extras != null)
				return false;
		} else if (!extras.equals(other.extras))
			return false;
		if (numberOfSeats != other.numberOfSeats)
			return false;
		if (returnLocation == null) {
			if (other.returnLocation != null)
				return false;
		} else if (!returnLocation.equals(other.returnLocation))
			return false;
		if (returnTime == null) {
			if (other.returnTime != null)
				return false;
		} else if (!returnTime.equals(other.returnTime))
			return false;
		if (stops == null) {
			if (other.stops != null)
				return false;
		} else if (!stops.equals(other.stops))
			return false;
		return true;
	}
	
}

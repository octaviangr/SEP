import java.io.Serializable;
import java.util.*;

public class Tour implements Serializable{

	private ArrayList<Chauffeur> chauffeurs;
	private String busType;
	private MyDate departureTime;
	private MyDate returnTime;
	private String departureLocation;
	private String arrivalLocation;
	private String stops;
	private int numberOfSeats;
	private String extras;
	
	public Tour(MyDate departureTime, MyDate returnTime,String departureLocation, String returnLocation, String stops,String busType, int numberOfSeats, String extras)
	{
		this.departureTime = departureTime;
		this.returnTime = returnTime;
		this.departureLocation = departureLocation;
		this.arrivalLocation = returnLocation;
		this.busType = busType;
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

	public String getArrivalLocation() {
		return arrivalLocation;
	}

	public void setArrivalLocation(String returnLocation) {
		this.arrivalLocation = returnLocation;
	}

	public ArrayList<Chauffeur> getChauffeurs() {
		return chauffeurs;
	}

	public void setChauffeurs(ArrayList<Chauffeur> chauffeurs) {
		this.chauffeurs = chauffeurs;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String bus) {
		this.busType = bus;
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

	public String getStops() {
		return stops;
	}

	public void setStops(String stops) {
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
		if (busType == null) {
			if (other.busType != null)
				return false;
		} else if (!busType.equals(other.busType))
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
		if (arrivalLocation == null) {
			if (other.arrivalLocation != null)
				return false;
		} else if (!arrivalLocation.equals(other.arrivalLocation))
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
	public String toString() {
		String all = String.format("%s > %s - seats %d - %s", departureLocation, arrivalLocation, numberOfSeats, departureTime.toString());
		return all;
	}
	
}

import java.util.*;


public class Tour {

	private ArrayList<Chauffeur> chauffeurs;
	private String busType; //to be changed if we want to save the busses properly
	private MyDate departureTime;
	private MyDate returnTime;
	private ArrayList<String> stops; // all the stops on the route
	private int numberOfSeats;
	private String extras;
	
	public Tour(MyDate departureTime, MyDate returnTime, ArrayList<String> stops, int numberOfSeats, String extras)
	{
		this.departureTime = departureTime;
		this.returnTime = returnTime;
		this.stops = stops;
		this.numberOfSeats = numberOfSeats;
		this.extras = extras;
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

	public void setBusType(String busType) {
		this.busType = busType;
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
	
}

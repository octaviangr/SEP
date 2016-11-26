import java.util.*;


public class Reservation {

	private Customer customer;
	private ArrayList<Passenger> passengers;
	private Tour tour;
	private int price;
	private int seats;
	private String preferences;
	
	public Reservation(Customer customer, ArrayList<Passenger> passengers, Tour tour, int price, int seats, String preferences)
	{
		this.customer = customer;
		this.passengers = passengers;
		this.tour = tour;
		this.price = price;
		this.seats = seats;
		this.preferences = preferences;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ArrayList<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(ArrayList<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getPreferences() {
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}
}

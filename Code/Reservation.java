import java.io.Serializable;
import java.util.*;


public class Reservation implements Serializable{

	private Customer customer;
	private ArrayList<Passenger> passengers;
	private Tour tour;
	private int price;
	private int seats;
	private String preferences;
	
	public Reservation(Customer customer, ArrayList<Passenger> passengers, Tour tour, int price, int seats, String preferences)
	{
		this.customer = customer;
		int oldBookings = this.customer.getNumberOfBookings();
		this.customer.setNumberOfBookings(oldBookings++);
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
	public String toString() {
		String all = String.format("Reservation by %s", this.customer.toString());
		return all;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (passengers == null) {
			if (other.passengers != null)
				return false;
		} else if (!passengers.equals(other.passengers))
			return false;
		if (preferences == null) {
			if (other.preferences != null)
				return false;
		} else if (!preferences.equals(other.preferences))
			return false;
		if (price != other.price)
			return false;
		if (seats != other.seats)
			return false;
		if (tour == null) {
			if (other.tour != null)
				return false;
		} else if (!tour.equals(other.tour))
			return false;
		return true;
	}
}

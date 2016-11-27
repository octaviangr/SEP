import java.io.IOException;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) throws IOException {

		Customer bob = new Customer("Bob","Str.Kollegievaenget","45678955","bob@gmail.dk");
		ArrayList<Passenger> passengers = new ArrayList<>();
		MyDate bday = new MyDate();
		Passenger pas1 = new Passenger("Bob","Str.Kollegievaenget",bday);
		Passenger pas2 = new Passenger("Bob","Str.Kollegievaenget",bday);
		Passenger pas3 = new Passenger("Bob","Str.Kollegievaenget",bday);
		passengers.add( pas1);
		passengers.add(pas2);
		passengers.add(pas3);
		ArrayList<String> stops = new ArrayList<>();
		stops.add("Berlin");
		Bus bus = new Bus("MiniBus","Mercedes","A69");
		Tour tour = new Tour(bday,bday,"Horsens","Budapest",stops,bus,50,"sex slaves");
		Reservation reservation = new Reservation(bob,passengers,tour,500,3,"sex slave");
		System.out.println(reservation);
		CustomerList customers = new CustomerList();
		customers.addCustomer(bob);
		customers.SaveData();
		System.out.println(customers.getCustomerByName("Bob").getEmail());
	}

}

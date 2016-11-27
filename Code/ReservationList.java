import java.io.*;
import java.util.*;


public class ReservationList {

	private ArrayList<Reservation> reservations = new ArrayList<>();
	
	public void addReservation(Reservation reservation)
	{
		reservations.add(reservation);
	}
	public void removeReservation(Reservation reservation)
	{
		for(int i = 0; i<reservations.size(); i++)
		{
			if(reservations.get(i).equals(reservation))
			{
				reservations.remove(i);
			}
		}
	}
	
	public ArrayList<Customer> getCustomersOnTour(Tour tour)
	{
		ArrayList<Customer> list = new ArrayList<>();
		for(int i = 0; i<reservations.size(); i++)
		{
			if(reservations.get(i).getTour().equals(tour))
			{
				list.add(reservations.get(i).getCustomer());
			}
		}
		return list;
	}
	public ArrayList<Passenger> getPassengersOnTour(Tour tour)
	{
		ArrayList<Passenger> list = new ArrayList<>();
		for(int i = 0; i<reservations.size(); i++)
		{
			if(reservations.get(i).getTour().equals(tour))
			{
				for(int j = 0; j<reservations.get(j).getPassengers().size(); j++)
				{
					list.add(reservations.get(j).getPassengers().get(j));
				}
			}
		}
		return list;
	}
	public void LoadData() throws IOException, ClassNotFoundException
	{
		String filename = "Reservations.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);	
		reservations = (ArrayList)in.readObject();
		in.close();
	}
	public void SaveData() throws IOException
	{
		String filename = "Reservations.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(reservations);
		out.close();
	}
}

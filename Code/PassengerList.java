import java.io.*;
import java.util.*;


public class PassengerList {

	private ArrayList<Passenger> passengers = new ArrayList<>();
	public Passenger getPassengerByName(String passengerName)
	{
		for(int i = 0; i<passengers.size(); i++)
		{
			if(passengers.get(i).getName().equalsIgnoreCase(passengerName))
			{
				return passengers.get(i);
			}
		}
		return null;
	}
	public void addPassenger(Passenger passenger)
	{
		passengers.add(passenger);
	}
	public void removePassenger(Passenger passenger)
	{
		for(int i = 0; i<passengers.size(); i++)
		{
			if(passengers.get(i).equals(passengers))
			{
				passengers.remove(i);
			}
		}
	}
	public void LoadData() throws IOException, ClassNotFoundException
	{
		String filename = "Passengers.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);	
		passengers = (ArrayList)in.readObject();
		in.close();
	}
	public void SaveData() throws IOException
	{
		String filename = "Passengers.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(passengers);
		out.close();
	}
}


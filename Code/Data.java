import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Data implements Serializable{

	public Data()
	{
		
	}
	public static ArrayList<Tour> LoadToursData() throws IOException, ClassNotFoundException
	{
		ArrayList<Tour> tours = new ArrayList<>();
		try
		{
			String filename = "Tours.bin";
			File file  = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fis);	
			tours = (ArrayList<Tour>)in.readObject();
			in.close();
		}
		catch(Exception e1)
		{
		}
		return tours;
	}
	public static void SaveToursData(ArrayList<Tour> list) throws IOException
	{
		String filename = "Tours.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(list);
		out.close();
	}
	public static ArrayList<Chauffeur> LoadChauffeursData() throws IOException, ClassNotFoundException
	{
		ArrayList<Chauffeur> chauffeurs = new ArrayList<>();
		try
		{
			String filename = "Chauffeurs.bin";
			File file  = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fis);	
			chauffeurs = (ArrayList<Chauffeur>)in.readObject();
			in.close();
		}
		catch(Exception e1)
		{
		}
		return chauffeurs;
	}
	public static void SaveChauffeursData(ArrayList<Chauffeur> list) throws IOException
	{
		String filename = "Chauffeurs.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(list);
		out.close();
	}
	public static ArrayList<Passenger> LoadPassengersData() throws IOException, ClassNotFoundException
	{
		ArrayList<Passenger> pas = new ArrayList<>();
		try
		{
			String filename = "Passengers.bin";
			File file  = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fis);	
			pas = (ArrayList<Passenger>)in.readObject();
			in.close();
		}
		catch(Exception e1)
		{
		}
		return pas;
	}
	public static void SavePassengersData(ArrayList<Passenger> list) throws IOException
	{
		String filename = "Passengers.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(list);
		out.close();
	}
	public static ArrayList<Customer> LoadCustomersData() throws IOException, ClassNotFoundException
	{
		ArrayList<Customer> cust = new ArrayList<>();
		try
		{
			String filename = "Customers.bin";
			File file  = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fis);	
			cust = (ArrayList<Customer>)in.readObject();
			in.close();
		}
		catch(Exception e1)
		{
		}
		return cust;
	}
	public static void SaveCustomersData(ArrayList<Customer> list) throws IOException
	{
		String filename = "Customers.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(list);
		out.close();
	}
	public static ArrayList<Reservation> LoadReservationsData() throws IOException, ClassNotFoundException
	{
		ArrayList<Reservation> res = new ArrayList<>();
		try
		{
			String filename = "Reservations.bin";
			File file  = new File(filename);
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fis);	
			res = (ArrayList<Reservation>)in.readObject();
			in.close();
		}
		catch(Exception e1)
		{
		}
		return res;
	}
	public static void SaveReservationsData(ArrayList<Reservation> list) throws IOException
	{
		String filename = "Reservations.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(list);
		out.close();
	}

}

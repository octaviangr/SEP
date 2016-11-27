import java.io.*;
import java.util.*;


public class CustomerList implements Serializable{

	private ArrayList<Customer> customers = new ArrayList<>();
	
	public Customer getCustomerByName(String customerName)
	{
		for(int i = 0; i<customers.size(); i++)
		{
			if(customers.get(i).getName().equalsIgnoreCase(customerName))
			{
				return customers.get(i);
			}
		}
		return null;
	}
	public void addCustomer(Customer customer)
	{
		try
		{
			customers.add(customer);
		}
		catch(Exception e1)
		{
			System.out.println("saved");
		}
	}
	public void removeCustomer(Customer customer)
	{
		for(int i = 0; i<customers.size(); i++)
		{
			if(customers.get(i).equals(customer))
			{
				customers.remove(i);
			}
		}
	}
	public void LoadData() throws IOException, ClassNotFoundException
	{
		String filename = "Customers.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);	
		customers = (ArrayList)in.readObject();
		in.close();
	}
	public void SaveData() throws IOException
	{
		String filename = "Customers.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(customers);
		out.close();
	}
}

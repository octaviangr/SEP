import java.io.*;
import java.util.*;


public class BusList {

	private ArrayList<Bus> busses = new ArrayList<>();
	public void addBus(Bus bus)
	{
		busses.add(bus);
	}
	public void removeBus(Bus bus)
	{
		for(int i = 0; i<busses.size(); i++)
		{
			if(busses.get(i).equals(busses))
			{
				busses.remove(i);
			}
		}
	}
	public void LoadData() throws IOException, ClassNotFoundException
	{
		String filename = "Busses.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);	
		busses = (ArrayList)in.readObject();
		in.close();
	}
	public void SaveData() throws IOException
	{
		String filename = "Tours.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(busses);
		out.close();
	}
}

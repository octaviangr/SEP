import java.io.*;
import java.util.*;


public class TourList {

	private ArrayList<Tour> tours = new ArrayList<>();
	public void addTour(Tour tour)
	{
		tours.add(tour);
	}
	public void removeTour(Tour tour)
	{
		for(int i = 0; i<tours.size(); i++)
		{
			if(tours.get(i).equals(tour))
			{
				tours.remove(i);
			}
		}
	}
	public void LoadData() throws IOException, ClassNotFoundException
	{
		String filename = "Tours.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);	
		tours = (ArrayList)in.readObject();
		in.close();
	}
	public void SaveData() throws IOException
	{
		String filename = "Tours.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(tours);
		out.close();
	}
}

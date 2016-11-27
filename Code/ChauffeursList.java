import java.io.*;
import java.util.*;


public class ChauffeursList {

	private ArrayList<Chauffeur> chauffeurs = new ArrayList<>();
	public Chauffeur getChauffeurByName(String chauffeurName)
	{
		for(int i = 0; i<chauffeurs.size(); i++)
		{
			if(chauffeurs.get(i).getName().equalsIgnoreCase(chauffeurName))
			{
				return chauffeurs.get(i);
			}
		}
		return null;
	}
	public void addChauffeur(Chauffeur chauffeur)
	{
		chauffeurs.add(chauffeur);
	}
	public void removeChauffeur(Chauffeur chauffeur)
	{
		for(int i = 0; i<chauffeurs.size(); i++)
		{
			if(chauffeurs.get(i).equals(chauffeurs))
			{
				chauffeurs.remove(i);
			}
		}
	}
	public void LoadData() throws IOException, ClassNotFoundException
	{
		String filename = "Chauffeurs.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fis);	
		chauffeurs = (ArrayList)in.readObject();
		in.close();
	}
	public void SaveData() throws IOException
	{
		String filename = "Chauffeurs.bin";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(fos);
		out.writeObject(chauffeurs);
		out.close();
	}
}

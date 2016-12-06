import java.io.*;
import java.util.*;


public class BusList implements Serializable{

	private int MiniBusses;
	private int RegularBusses;
	private int LuxuryBusses;
	private int PartyBusses;
	public BusList() throws ClassNotFoundException, IOException
	{
		this.LoadData();
	}
	public void addBus(String typeOfBus)
	{
		if(typeOfBus.equalsIgnoreCase("mini"))
		{
			MiniBusses++;
		}
		else if(typeOfBus.equalsIgnoreCase("regular"))
		{
			RegularBusses++;
		}
		else if(typeOfBus.equalsIgnoreCase("luxury"))
		{
			LuxuryBusses++;
		}
		else if(typeOfBus.equalsIgnoreCase("party"))
		{
			PartyBusses++;
		}
	}
	public void removeBus(String typeOfBus)
	{
		if(typeOfBus.equalsIgnoreCase("mini"))
		{
			MiniBusses--;
		}
		else if(typeOfBus.equalsIgnoreCase("regular"))
		{
			RegularBusses--;
		}
		else if(typeOfBus.equalsIgnoreCase("luxury"))
		{
			LuxuryBusses--;
		}
		else if(typeOfBus.equalsIgnoreCase("party"))
		{
			PartyBusses--;
		}
	}
	public void LoadData() throws IOException, ClassNotFoundException
	{
		String filename = "Busses.txt";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		Scanner in = new Scanner(file);
		while (in.hasNext())
		{
			String line = in.nextLine();
			String[] token = line.split(",");
			MiniBusses = Integer.parseInt(token[0]);
			RegularBusses = Integer.parseInt(token[1]);
			LuxuryBusses = Integer.parseInt(token[2]);
			PartyBusses = Integer.parseInt(token[3]);
		}
		in.close();
		in.close();
	}
	public void SaveData() throws IOException
	{
		String filename = "Busses.txt";
		File file  = new File(filename);
		if(!file.exists()) file.createNewFile();
		PrintWriter out = new PrintWriter(file);
		
		String busses = String.format("%d,%d,%d,%d", MiniBusses,RegularBusses,LuxuryBusses,PartyBusses);
		out.println(busses);
		out.close();
	}
	public int getMiniBusses() {
		return MiniBusses;
	}
	public void setMiniBusses(int miniBusses) {
		MiniBusses = miniBusses;
	}
	public int getRegularBusses() {
		return RegularBusses;
	}
	public void setRegularBusses(int regularBusses) {
		RegularBusses = regularBusses;
	}
	public int getLuxuryBusses() {
		return LuxuryBusses;
	}
	public void setLuxuryBusses(int luxuryBusses) {
		LuxuryBusses = luxuryBusses;
	}
	public int getPartyBusses() {
		return PartyBusses;
	}
	public void setPartyBusses(int partyBusses) {
		PartyBusses = partyBusses;
	}
}

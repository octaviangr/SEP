import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GUI extends JFrame implements Serializable{

	//private static ArrayList<Tour> tourslist = new ArrayList<>();
	//private static ArrayList<Chauffeur> chauffeurslist = new ArrayList<>();
	public static void main (String[] args) throws ClassNotFoundException, IOException
	{
		
		//Lists:
		ArrayList<Tour> tourslist = Data.LoadToursData();
		ArrayList<Chauffeur> chauffeurslist = Data.LoadChauffeursData();
		ArrayList<Customer> customerslist = Data.LoadCustomersData();
		ArrayList<Reservation> reservationslist = Data.LoadReservationsData();
		ArrayList<Reservation> tripjourneyreservations = new ArrayList<>();
		ArrayList<Reservation> buschauffeurreservations = new ArrayList<>();
		for(int i = 0; i<reservationslist.size(); i++)
		{
			if(reservationslist.get(i).getType() == 1)
			{
				tripjourneyreservations.add(reservationslist.get(i));
			}
			else
			{
				buschauffeurreservations.add(reservationslist.get(i));
			}
		}
		ArrayList<Tour> tripjourneytours = new ArrayList<>();
		ArrayList<Tour> buschauffeurtours = new ArrayList<>();
		for(int i = 0; i<tourslist.size(); i++)
		{
			if(tourslist.get(i).getType() == 1)
			{
				tripjourneytours.add(tourslist.get(i));
			}
			else
			{
				buschauffeurtours.add(tourslist.get(i));
			}
		}
		ArrayList<Passenger> passengerslisttemp = new ArrayList<>();
		BusList allbusses = new BusList();
		ArrayList<Chauffeur> availableChauffeurs = new ArrayList<>();
		
		JList CustomersList = new JList(customerslist.toArray());
		JList TripJourneyList = new JList(tripjourneytours.toArray());
		
		JFrame frame = new JFrame("VIA BUS");
		// TOURS TAB
		JTabbedPane tabbed = new JTabbedPane();
		JPanel ToursTab = new JPanel();
		tabbed.addTab("Tours",ToursTab);
		//CREATING TOURS LIST
		JPanel ToursListPanel = new JPanel(new BorderLayout());
		JLabel label1 = new JLabel("Tours List:");
		ToursListPanel.add(label1, BorderLayout.NORTH);
		JList ToursList = new JList(tripjourneytours.toArray());
		ToursList.setVisibleRowCount(15);
		ToursList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(ToursList);
		ToursListPanel.add(scroll,BorderLayout.SOUTH);
		ToursTab.add(ToursListPanel);
		//CREATING TOURS FORM
		JPanel ToursInfoPanel = new JPanel(new GridLayout(10,2));
		JLabel infolabel1 = new JLabel("Departure Location:");
		JLabel infolabel2 = new JLabel("Arrival Location:");
		JLabel infolabel3 = new JLabel("Stops:");
		JLabel infolabel4 = new JLabel("Departure Date & Time:");
		JLabel infolabel5 = new JLabel("Arrival Date & Time:");
		JLabel infolabel6 = new JLabel("Number Of Seats:");
		JLabel infolabel7 = new JLabel("Extras:");
		JLabel infolabel8 = new JLabel("Bus Type:");
		JLabel infolabel9 = new JLabel("Chauffeur 1:");
		JLabel infolabel10 = new JLabel("Chauffeur 2:");
		JTextField fields1 = new JTextField(10);
		JTextField fields2 = new JTextField(10);
		JTextField fields3 = new JTextField(10);
		JTextField fields4 = new JTextField(10);
		fields4.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e)
            {
            	String[] content = fields4.getText().split("/");
            	if(content.length == 3)
            	{
	            	if(Integer.parseInt(content[0]) > 0 && Integer.parseInt(content[1]) > 0 && Integer.parseInt(content[2]) > 0)
	            	{
	            		MyDate ndate = new MyDate(0,0,0,0,0);
	            		if(ndate.checkDate(Integer.parseInt(content[0]), Integer.parseInt(content[1]), Integer.parseInt(content[2])))
	            		{
	            			System.out.println("Departure date was inserted OK");
	            			//departuredate = true;
	            		}
	            	}
            	}
            }  
        });
		JTextField fields5 = new JTextField(10);
		JTextField fields6 = new JTextField(10);
		JTextField fields7 = new JTextField(10);
		ArrayList<String> bussesTypes = new ArrayList<>();
		bussesTypes.add("None");
		bussesTypes.add("Mini");
		bussesTypes.add("Regular");
		bussesTypes.add("Luxury");
		bussesTypes.add("Party");
		JComboBox<String> bussesCombo = new JComboBox<>();
		bussesCombo.setModel(new DefaultComboBoxModel(bussesTypes.toArray()));
		JComboBox<String> chauffeurCombo1 = new JComboBox<>();
		chauffeurCombo1.setModel(new DefaultComboBoxModel(availableChauffeurs.toArray()));
		JComboBox<String> chauffeurCombo2 = new JComboBox<>();
		chauffeurCombo2.setModel(new DefaultComboBoxModel(availableChauffeurs.toArray()));
		fields5.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e)
            {
            	String[] content = fields5.getText().split("/");
            	if(content.length == 3)
            	{
	            	if(Integer.parseInt(content[0]) > 0 && Integer.parseInt(content[1]) > 0 && Integer.parseInt(content[2]) > 0)
	            	{
	            		MyDate ndate = new MyDate(0,0,0,0,0);
	            		if(ndate.checkDate(Integer.parseInt(content[0]), Integer.parseInt(content[1]), Integer.parseInt(content[2])))
	            		{
	            			try
	            			{
		            			System.out.println("Arrival date was inserted OK");
		            			System.out.println("Display list of availableChauffeurs");
		            			String[] content1 = fields4.getText().split("/");
		    	            	String[] content2 = fields5.getText().split("/");
		    	            	MyDate Date1 = new MyDate(Integer.parseInt(content1[0]),Integer.parseInt(content1[1]),Integer.parseInt(content1[2]),0,0);
		    	            	MyDate Date2 = new MyDate(Integer.parseInt(content2[0]),Integer.parseInt(content2[1]),Integer.parseInt(content2[2]),0,0);
		            			ArrayList<Chauffeur> availableChauffeurs2 = new ArrayList<>();
		            			// get available chauffeurs
		            			boolean available = true;
		            			for(int i = 0; i<chauffeurslist.size(); i++)
		            			{
		            				if(chauffeurslist.get(i).getSchedule().isEmpty())
		            				{
		            					availableChauffeurs.add(chauffeurslist.get(i));
		            					available = false;
		            				}
		            				ArrayList<MyDate> schedule = chauffeurslist.get(i).getSchedule();
		            				ArrayList<MyDate> dates = Date1.getDatesBetween(Date2);
		            				for(int j = 0; j<schedule.size(); j++)
		            				{
		            					for(int z = 0; z<dates.size(); z++)
		            					{
		            						if(schedule.get(j).equals(dates.get(z)))
		            						{
		            							available = false;
		            							break;
		            						}
		            					}
		            					if(available == false)
		            					{
		            						break;
		            					}
		            				}
		            				if(available)
		            				{
		            					availableChauffeurs2.add(chauffeurslist.get(i));
		            				}
		            				available = true;
		            			}
		            			// end get available chauffeurs
		            			chauffeurCombo1.setModel(new DefaultComboBoxModel(availableChauffeurs2.toArray()));
	            			}
	            			catch(Exception e1)
	            			{
	            				System.out.println("Checking dates");
	            			}
	            		}
	            	}
            	}
            }  
        });
		ToursInfoPanel.add(infolabel1);
		ToursInfoPanel.add(fields1);
		ToursInfoPanel.add(infolabel2);
		ToursInfoPanel.add(fields2);
		ToursInfoPanel.add(infolabel3);
		ToursInfoPanel.add(fields3);
		ToursInfoPanel.add(infolabel4);
		ToursInfoPanel.add(fields4);
		ToursInfoPanel.add(infolabel5);
		ToursInfoPanel.add(fields5);
		ToursInfoPanel.add(infolabel6);
		ToursInfoPanel.add(fields6);
		ToursInfoPanel.add(infolabel7);
		ToursInfoPanel.add(fields7);
		ToursInfoPanel.add(infolabel8);
		ToursInfoPanel.add(bussesCombo);
		ToursInfoPanel.add(infolabel9);
		ToursInfoPanel.add(chauffeurCombo1);
		ToursInfoPanel.add(infolabel10);
		ToursInfoPanel.add(chauffeurCombo2);
		ToursTab.add(ToursInfoPanel);
		//TOURS BUTTONS
		JPanel ToursButtonPanel = new JPanel(new GridLayout(3,1));
		JButton buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	if(fields1.getText().isEmpty() || fields2.getText().isEmpty() || fields3.getText().isEmpty() || fields4.getText().isEmpty() || fields5.getText().isEmpty() || fields6.getText().isEmpty() || fields7.getText().isEmpty())
            	{
            		System.out.println("Some fields are empty");
            	}
            	else
            	{
            		try
            		{
	            		String depLoc = fields1.getText();
		            	String arrLoc = fields2.getText();
		            	String[] content = fields4.getText().split("/");
		            	String[] content2 = fields5.getText().split("/");
		            	MyDate depDate = new MyDate(Integer.parseInt(content[0]),Integer.parseInt(content[1]),Integer.parseInt(content[2]),0,0);
		            	MyDate retDate = new MyDate(Integer.parseInt(content2[0]),Integer.parseInt(content2[1]),Integer.parseInt(content2[2]),0,0);
		            	int seats = Integer.parseInt(fields6.getText());
		            	String stops = fields3.getText();
		            	String extras = fields7.getText();
		            	Tour ntour = new Tour(depDate, retDate, depLoc, arrLoc, stops,(String)bussesCombo.getSelectedItem(), seats, extras,1);
		            	ArrayList<Chauffeur> nchauffeurs = new ArrayList<>();
		            	nchauffeurs.add((Chauffeur) chauffeurCombo1.getSelectedItem());
		            	nchauffeurs.add((Chauffeur) chauffeurCombo2.getSelectedItem());
		            	ntour.setChauffeurs(nchauffeurs);
		            	tripjourneytours.add(ntour);
		            	tourslist.add(ntour);
		            	try
			            {
		            		ToursList.setListData(tripjourneytours.toArray());
			            }
			            catch(Exception e1){}
			            try
			            {
			            	Data.SaveToursData(tourslist);
			            }
			            catch(Exception e1){}
			            try
			            {
			            	TripJourneyList.setListData(tripjourneytours.toArray());
			            }
			            catch(Exception e1){}
            		}
            		catch(Exception e1)
            		{
            			System.out.println("Add tour");
            		}
            	}
            }  
        });
		JButton buttonSave = new JButton("Save");
		buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	if(fields1.getText().isEmpty() || fields2.getText().isEmpty() || fields3.getText().isEmpty() || fields4.getText().isEmpty() || fields5.getText().isEmpty() || fields6.getText().isEmpty() || fields7.getText().isEmpty())
            	{
            		System.out.println("Some fields are empty");
            	}
            	else
            	{
            		try
            		{
	            		Tour selected = tourslist.get(ToursList.getSelectedIndex());
	            		selected.setDepartureLocation(fields1.getText());
	            		selected.setArrivalLocation(fields2.getText());
	            		String[] content = fields4.getText().split("/");
		            	String[] content2 = fields5.getText().split("/");
		            	MyDate depDate = new MyDate(Integer.parseInt(content[0]),Integer.parseInt(content[1]),Integer.parseInt(content[2]),0,0);
		            	MyDate retDate = new MyDate(Integer.parseInt(content2[0]),Integer.parseInt(content2[1]),Integer.parseInt(content2[2]),0,0);
	            		selected.setDepartureTime(depDate);
	            		selected.setReturnTime(retDate);
	            		selected.setStops(fields3.getText());
	            		selected.setNumberOfSeats(Integer.parseInt(fields6.getText()));
	            		selected.setExtras(fields7.getText());
	            		selected.setBusType(bussesCombo.getSelectedItem().toString());
	            		try
			            {
		            		ToursList.setListData(tripjourneytours.toArray());
			            }
			            catch(Exception e1){}
			            try
			            {
			            	Data.SaveToursData(tourslist);
			            }
			            catch(Exception e1){}
			            try
			            {
			            	TripJourneyList.setListData(tripjourneytours.toArray());
			            }
			            catch(Exception e1){}
            		}
            		catch(Exception e1)
            		{
            			System.out.println("Save tour");
            		}
            	}
            }   
        });
		JButton buttonRemove = new JButton("Remove");
		buttonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	try
            	{
            		for(int i=0; i<tourslist.size(); i++)
            		{
            			if(tourslist.get(i).equals(tripjourneytours.get(ToursList.getSelectedIndex())))
            			{
            				tourslist.remove(i);
           				}
            		}
            		tripjourneytours.remove(ToursList.getSelectedIndex());
	            	try
		            {
	            		ToursList.setListData(tripjourneytours.toArray());
		            }
		            catch(Exception e1){}
		            try
		            {
		            	Data.SaveToursData(tourslist);
		            }
		            catch(Exception e1){}
		            try
		            {
		            	TripJourneyList.setListData(tripjourneytours.toArray());
		            }
		            catch(Exception e1){}
            	}
            	catch(Exception e1)
            	{
            		System.out.println("No field selected");
            	}
            }   
        });
		ToursList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
            {
            	try
            	{
	            	fields1.setText(tripjourneytours.get(ToursList.getSelectedIndex()).getDepartureLocation());
	            	fields2.setText(tripjourneytours.get(ToursList.getSelectedIndex()).getArrivalLocation());
	            	fields3.setText(tripjourneytours.get(ToursList.getSelectedIndex()).getStops());
	            	fields4.setText(tripjourneytours.get(ToursList.getSelectedIndex()).getDepartureTime().toString());
	            	fields5.setText(tripjourneytours.get(ToursList.getSelectedIndex()).getReturnTime().toString());
	            	fields6.setText(Integer.toString(tripjourneytours.get(ToursList.getSelectedIndex()).getNumberOfSeats()));
	            	fields7.setText(tripjourneytours.get(ToursList.getSelectedIndex()).getExtras());
	            	if(tripjourneytours.get(ToursList.getSelectedIndex()).getBusType().equals("Mini"))
	            	{
	            		bussesCombo.setSelectedIndex(1);
	            	}
	            	else if(tripjourneytours.get(ToursList.getSelectedIndex()).getBusType().equals("Regular"))
	            	{
	            		bussesCombo.setSelectedIndex(2);
	            	}
	            	else if(tripjourneytours.get(ToursList.getSelectedIndex()).getBusType().equals("Luxury"))
	            	{
	            		bussesCombo.setSelectedIndex(3);
	            	}
	            	else if(tripjourneytours.get(ToursList.getSelectedIndex()).getBusType().equals("Party"))
	            	{
	            		bussesCombo.setSelectedIndex(4);
	            	}
	            	else if(tripjourneytours.get(ToursList.getSelectedIndex()).getBusType().equals("None"))
	            	{
	            		bussesCombo.setSelectedIndex(0);
	            	}
	            	chauffeurCombo1.setSelectedItem(tripjourneytours.get(ToursList.getSelectedIndex()).getChauffeurs().get(0));
	            	chauffeurCombo2.setSelectedItem(tripjourneytours.get(ToursList.getSelectedIndex()).getChauffeurs().get(1));
            	}
            	catch(Exception e1)
            	{
            		
            	}
            }
        });
		ToursButtonPanel.add(buttonAdd);
		ToursButtonPanel.add(buttonSave);
		ToursButtonPanel.add(buttonRemove);
		ToursTab.add(ToursButtonPanel);
		
		//CHAUFFEURS TAB
		JPanel ChauffeursPanel = new JPanel();
		tabbed.add("Chauffeurs",ChauffeursPanel);
		//CHAUFFEURS PANEL LIST
		JPanel ChauffeurListPanel = new JPanel(new BorderLayout());
		JLabel label21 = new JLabel("Chauffeurs List:");
		ChauffeurListPanel.add(label21, BorderLayout.NORTH);
		JList ChauffeurList = new JList(chauffeurslist.toArray());
		ChauffeurList.setVisibleRowCount(15);
		ChauffeurList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll2 = new JScrollPane(ChauffeurList);
		ChauffeurListPanel.add(scroll2,BorderLayout.SOUTH);
		ChauffeursPanel.add(ChauffeurListPanel);
		//CHAUFFEURS FORM PANEL
		JPanel ChauffeursInfoPanel = new JPanel(new GridLayout(5,2));
		JLabel infolabel21 = new JLabel("Name:");
		JLabel infolabel22 = new JLabel("Phone Number:");
		JLabel infolabel23 = new JLabel("Employment Type:");
		JLabel infolabel24 = new JLabel("Employee ID:");
		JLabel infolabel25 = new JLabel("Preferences:");
		JTextField fields21 = new JTextField(10);
		JTextField fields22 = new JTextField(10);
		JTextField fields23 = new JTextField(10);
		JTextField fields24 = new JTextField(10);
		ArrayList<String> employmentTypes = new ArrayList<>();
		employmentTypes.add("Full-Time");
		employmentTypes.add("Part-Time");
		JComboBox<String> employmentCombo = new JComboBox<>();
		employmentCombo.setModel(new DefaultComboBoxModel(employmentTypes.toArray()));
		ChauffeursInfoPanel.add(infolabel21);
		ChauffeursInfoPanel.add(fields21);
		ChauffeursInfoPanel.add(infolabel22);
		ChauffeursInfoPanel.add(fields22);
		ChauffeursInfoPanel.add(infolabel23);
		ChauffeursInfoPanel.add(employmentCombo);
		ChauffeursInfoPanel.add(infolabel24);
		ChauffeursInfoPanel.add(fields23);
		ChauffeursInfoPanel.add(infolabel25);
		ChauffeursInfoPanel.add(fields24);
		ChauffeursPanel.add(ChauffeursInfoPanel);
		//CHAUFFEURS BUTTON PANEL
		//TOURS BUTTONS
		JPanel ChauffeursButtonPanel = new JPanel(new GridLayout(3,1));
		JButton buttonAdd2 = new JButton("Add");
		buttonAdd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(fields21.getText().isEmpty() || fields22.getText().isEmpty() || fields23.getText().isEmpty())
			    {
			          System.out.println("Some fields are empty");
			    }
			    else
			    {
			    	try
			    	{
					    String name = fields21.getText();
						String phone = fields22.getText();
						int id = Integer.parseInt(fields23.getText());
						String employment = "";
						String preferences = fields24.getText();
						if(employmentCombo.getSelectedIndex() == 0)
		            	{
		            		employment = "Full-Time";
		            	}
		            	else if(employmentCombo.getSelectedIndex() == 1)
		            	{
		            		employment = "Part-Time";
		            	}
						Chauffeur newchauffeur = new Chauffeur(name,phone,employment,preferences,id);
						chauffeurslist.add(newchauffeur);
						try
			            {
							ChauffeurList.setListData(chauffeurslist.toArray());
			            }
			            catch(Exception e1){}
			            try
			            {
			            	Data.SaveChauffeursData(chauffeurslist);
			            }
			            catch(Exception e1){}
			    	}
					catch(Exception e1)
				    {
						
				    }
			    }
			}  
		});
		JButton buttonSave2 = new JButton("Save");
		buttonSave2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				if(fields21.getText().isEmpty() || fields22.getText().isEmpty() || fields23.getText().isEmpty() || fields24.getText().isEmpty())
		        {
					System.out.println("Some fields are empty");
		        }
		        else
		        {
		        	try
		        	{
			        	Chauffeur selected = chauffeurslist.get(ChauffeurList.getSelectedIndex());
			            selected.setName(fields21.getText());
			            selected.setPhoneNumber(fields22.getText());
			            selected.setId(Integer.parseInt(fields23.getText()));
			            selected.setPreferences((fields24.getText()));
			            if(employmentCombo.getSelectedIndex() == 0)
			            {
			            	selected.setEmploymentType("Full-Time");
			            }
			            else if(employmentCombo.getSelectedIndex() == 1)
			            {
			            	selected.setEmploymentType("Part-Time");
			            }
			            try
			            {
							ChauffeurList.setListData(chauffeurslist.toArray());
			            }
			            catch(Exception e1){}
			            try
			            {
			            	Data.SaveChauffeursData(chauffeurslist);
			            }
			            catch(Exception e1){}
		        	}
		        	catch(Exception e1)
		        	{
		        		
		        	}
		        }
		    }   
		});
		JButton buttonRemove2 = new JButton("Remove");
		buttonRemove2.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
		    {
				if(!ChauffeurList.isSelectionEmpty())
		        {
					try
					{
						chauffeurslist.remove(ChauffeurList.getSelectedIndex());
						try
			            {
							ChauffeurList.setListData(chauffeurslist.toArray());
			            }
			            catch(Exception e1){}
			            try
			            {
			            	Data.SaveChauffeursData(chauffeurslist);
			            }
			            catch(Exception e1){}
					}
					catch(Exception e1)
					{
						
					}
		        }
		        else System.out.println("No field selected");
		    }   
		});
		ChauffeurList.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e)
		{
			fields21.setText(chauffeurslist.get(ChauffeurList.getSelectedIndex()).getName());
		    fields22.setText(chauffeurslist.get(ChauffeurList.getSelectedIndex()).getPhoneNumber());
		    fields23.setText(Integer.toString(chauffeurslist.get(ChauffeurList.getSelectedIndex()).getId()));
		    fields24.setText(chauffeurslist.get(ChauffeurList.getSelectedIndex()).getPreferences());
		    if(chauffeurslist.get(ChauffeurList.getSelectedIndex()).getEmploymentType().equals("Full-Time"))
		    {
		    	employmentCombo.setSelectedIndex(0);
		    }
		    else if(chauffeurslist.get(ChauffeurList.getSelectedIndex()).getEmploymentType().equals("Part-Time"))
		    {
		    	employmentCombo.setSelectedIndex(1);
		    }
		}
		});
		ChauffeursButtonPanel.add(buttonAdd2);
		ChauffeursButtonPanel.add(buttonSave2);
		ChauffeursButtonPanel.add(buttonRemove2);
		ChauffeursPanel.add(ChauffeursButtonPanel);
		
		/*JTabbedPane TABS = new JTabbedPane();
		JPanel tab1 = new  JPanel();
		JPanel tab2 = new  JPanel();
		JTabbedPane subTABS = new JTabbedPane();
		JPanel tab11 = new  JPanel();
		JPanel tab21 = new  JPanel();
		JPanel tab31 = new  JPanel();
		subTABS.addTab("sub1", tab11);
		subTABS.addTab("sub2", tab21);
		subTABS.addTab("sub3", tab31);
		TABS.addTab("tab1", tab1);
		TABS.addTab("tab2", tab2);
		TABS.addTab("subTABS", subTABS);*/
		
		JTabbedPane makereservations = new JTabbedPane();
		tabbed.add("Make Reservation",makereservations);
		JPanel reservetripjourney = new JPanel();
		makereservations.add("Trips & Journeys",reservetripjourney);
		JPanel reservebuschauffeur = new JPanel();
		makereservations.add("Bus and Chauffeur",reservebuschauffeur);
		//RESERVATIONS FOR TRIP & JOURNEY
		//// ALL RESERVATIONS
		JPanel AllReservationsListPanel = new JPanel(new BorderLayout());
		JLabel labelres1 = new JLabel("Trips & Journeys Reservations:");
		AllReservationsListPanel.add(labelres1, BorderLayout.NORTH);
		JList AllReservationsList = new JList(tripjourneyreservations.toArray());
		AllReservationsList.setVisibleRowCount(15);
		AllReservationsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll55 = new JScrollPane(AllReservationsList);
		AllReservationsListPanel.add(scroll55,BorderLayout.SOUTH);
		reservetripjourney.add(AllReservationsListPanel);
		//// T&J list
		JPanel TripJourneyListPanel = new JPanel(new BorderLayout());
		JLabel labeltour1 = new JLabel("Trips & Journeys List:");
		TripJourneyListPanel.add(labeltour1, BorderLayout.NORTH);
		//JList TripJourneyList = new JList(tourslist.toArray());
		TripJourneyList.setVisibleRowCount(15);
		TripJourneyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll22 = new JScrollPane(TripJourneyList);
		TripJourneyListPanel.add(scroll22,BorderLayout.SOUTH);
		reservetripjourney.add(TripJourneyListPanel);
		//// T&J form
		JPanel TripJourneyInfoPanel = new JPanel(new GridLayout(11,2));
		JLabel infolabel221 = new JLabel("Customer Name:");
		JLabel infolabel222 = new JLabel("Address:");
		JLabel infolabel223 = new JLabel("E-Mail:");
		JLabel infolabel224 = new JLabel("Phone Number:");
		JLabel infolabel225 = new JLabel("Number of seats:");
		JLabel infolabel226 = new JLabel("Preferences:");
		JLabel pasnote = new JLabel("ADD PASSENGERS");
		JLabel pasnote2 = new JLabel("BELOW");
		JTextField fields221 = new JTextField(10);
		JTextField fields222 = new JTextField(10);
		JTextField fields223 = new JTextField(10);
		JTextField fields224 = new JTextField(10);
		JTextField fields225 = new JTextField(10);
		JTextField fields226 = new JTextField(10);
		TripJourneyInfoPanel.add(infolabel221);
		TripJourneyInfoPanel.add(fields221);
		TripJourneyInfoPanel.add(infolabel222);
		TripJourneyInfoPanel.add(fields222);
		TripJourneyInfoPanel.add(infolabel223);
		TripJourneyInfoPanel.add(fields223);
		TripJourneyInfoPanel.add(infolabel224);
		TripJourneyInfoPanel.add(fields224);
		TripJourneyInfoPanel.add(infolabel225);
		TripJourneyInfoPanel.add(fields225);
		TripJourneyInfoPanel.add(infolabel226);
		TripJourneyInfoPanel.add(fields226);
		TripJourneyInfoPanel.add(pasnote);
		TripJourneyInfoPanel.add(pasnote2);
		//// T&J passengers form
		JLabel infolabel227 = new JLabel("Passenger Name:");
		JLabel infolabel228 = new JLabel("Passenger Age:");
		JLabel infolabel229 = new JLabel("Passenger Address:");
		JTextField fields227 = new JTextField(10);
		JTextField fields228 = new JTextField(10);
		JTextField fields229 = new JTextField(10);
		JButton buttonAddPassenger = new JButton("Add Passenger");
		JList TripJourneyListPas = new JList();
		buttonAddPassenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(fields227.getText().isEmpty() || fields228.getText().isEmpty())
			    {
			          System.out.println("Some fields are empty");
			    }
			    else
			    {
			    	try
			    	{
			    		ArrayList<Passenger> passengerslisttemp = new ArrayList<>();
			    		int size = TripJourneyListPas.getModel().getSize();
			    	    for (int i = 0; i < size; i++) {
			    	      passengerslisttemp.add((Passenger)TripJourneyListPas.getModel().getElementAt(i));
			    	    }
					    String name = fields227.getText();
						int age = Integer.parseInt(fields228.getText());
						String address = fields229.getText();
						Passenger pas = new Passenger(name,address,age);
						passengerslisttemp.add(pas);
						try
			            {
							TripJourneyListPas.setListData(passengerslisttemp.toArray());
			            }
			            catch(Exception e1){}
			    	}
					catch(Exception e1)
				    {
						
				    }
			    }
			}  
		});
		JButton buttonRemovePassenger = new JButton("Remove Passenger");
		buttonRemovePassenger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				if(!TripJourneyListPas.isSelectionEmpty())
		        {
					try
					{
						passengerslisttemp.remove(TripJourneyListPas.getSelectedIndex());
						try
			            {
							TripJourneyListPas.setListData(passengerslisttemp.toArray());
			            }
			            catch(Exception e1){}
					}
					catch(Exception e1)
					{
						
					}
		        }
		        else System.out.println("No field selected");
		    }   
		});
		TripJourneyInfoPanel.add(infolabel227);
		TripJourneyInfoPanel.add(fields227);
		TripJourneyInfoPanel.add(infolabel228);
		TripJourneyInfoPanel.add(fields228);
		TripJourneyInfoPanel.add(infolabel229);
		TripJourneyInfoPanel.add(fields229);
		TripJourneyInfoPanel.add(buttonAddPassenger);
		TripJourneyInfoPanel.add(buttonRemovePassenger);
		reservetripjourney.add(TripJourneyInfoPanel);
		//// T&J passenger list
		JPanel TripJourneyListPasPanel = new JPanel(new BorderLayout());
		JLabel labelpas = new JLabel("Passengers List:");
		TripJourneyListPasPanel.add(labelpas, BorderLayout.NORTH);
		//JList TripJourneyListPas = new JList();
		TripJourneyListPas.setVisibleRowCount(15);
		TripJourneyListPas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll222 = new JScrollPane(TripJourneyListPas);
		TripJourneyListPasPanel.add(scroll222,BorderLayout.SOUTH);
		reservetripjourney.add(TripJourneyListPasPanel);
		//// T&J buttons
		JPanel TripJourneyButtonPanel = new JPanel(new GridLayout(3,1));
		JButton buttonCreateRes = new JButton("Create");
		buttonCreateRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			    try
			    {
			    	if(TripJourneyListPas.getModel().getSize() != 0 && TripJourneyListPas.getModel().getSize() == Integer.parseInt(fields225.getText()))
				    {
			    		if(!(TripJourneyList.isSelectionEmpty()))
						{
				    		Tour tour = tripjourneytours.get(TripJourneyList.getSelectedIndex());
				    		Customer customer = null;
				    		int found = -1;
				    		for(int i = 0; i<customerslist.size(); i++)
				    		{
				    			if(customerslist.get(i).getName().equalsIgnoreCase(fields221.getText()))
				    			{
				    				customer = customerslist.get(i);
				    				found = i;
				    			}
				    		}
				    		if(found == -1)
				    		{
				    			customer = new Customer(fields221.getText(), fields222.getText(), fields224.getText(),fields223.getText());
				    			customerslist.add(customer);
				    			try
				    			{
				    				Data.SaveCustomersData(customerslist);
				    			}
				    			catch(Exception e1){}
				    			try
				    			{
				    				CustomersList.setListData(customerslist.toArray());
				    			}
				    			catch(Exception e1){}
				    		}
						    found = 0;
						    ArrayList<Passenger> passengerslisttemp = new ArrayList<>();
				    		int size = TripJourneyListPas.getModel().getSize();
				    	    for (int i = 0; i < size; i++) {
				    	      passengerslisttemp.add((Passenger)TripJourneyListPas.getModel().getElementAt(i));
				    	    }
				    		Reservation res = new Reservation(customer,passengerslisttemp,tour,696969,Integer.parseInt(fields225.getText()),fields226.getText(),1);
				    		reservationslist.add(res);
				    		tripjourneyreservations.add(res);
							try
				            {
								Data.SaveReservationsData(reservationslist);
				            }
				            catch(Exception e1){}
							try
				            {
								AllReservationsList.setListData(tripjourneyreservations.toArray());
				            }
				            catch(Exception e1){}
						}
				    }
			    }
			    catch(Exception e1)
			    {
					
			    }
			}  
		});
		AllReservationsList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				try
				{
					Reservation res = reservationslist.get(AllReservationsList.getSelectedIndex());
					Customer cust = res.getCustomer();
					ArrayList<Passenger> passengerslisttemp = res.getPassengers();
					fields221.setText(cust.getName());
					fields222.setText(cust.getAddress());
					fields223.setText(cust.getEmail());
					fields224.setText(cust.getPhoneNumber());
					fields225.setText(Integer.toString(res.getSeats()));
					fields226.setText(res.getPreferences());
					TripJourneyListPas.setListData(passengerslisttemp.toArray());
				}
				catch(Exception e1){}
			}
		});
		JButton buttonSaveRes = new JButton("Save");
		buttonSaveRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			    try
			    {
			    	if(!(AllReservationsList.isSelectionEmpty()))
			    	{
			    		if(TripJourneyListPas.getModel().getSize() != 0 && TripJourneyListPas.getModel().getSize() == Integer.parseInt(fields225.getText()))
					    {
				    		Reservation res = tripjourneyreservations.get(AllReservationsList.getSelectedIndex());
				    		Customer customer = null;
				    		int found = -1;
				    		for(int i = 0; i<customerslist.size(); i++)
				    		{
				    			if(customerslist.get(i).getName().equalsIgnoreCase(fields221.getText()))
				    			{
				    				customer = customerslist.get(i);
				    				customer.setAddress(fields222.getText());
				    				customer.setEmail(fields223.getText());
				    				customer.setPhoneNumber(fields224.getText());
				    				found = i;
				    			}
				    		}
				    		if(found == -1)
				    		{
				    			customer = new Customer(fields221.getText(), fields222.getText(), fields224.getText(),fields223.getText());
				    			customerslist.add(customer);
				    		}
				    		try
			    			{
			    				Data.SaveCustomersData(customerslist);
			    			}
			    			catch(Exception e1){}
			    			try
			    			{
			    				CustomersList.setListData(customerslist.toArray());
			    			}
			    			catch(Exception e1){}
			    			ArrayList<Passenger> passengerslisttemp = new ArrayList<>();
				    		int size = TripJourneyListPas.getModel().getSize();
				    	    for (int i = 0; i < size; i++) {
				    	      passengerslisttemp.add((Passenger)TripJourneyListPas.getModel().getElementAt(i));
				    	    }
				    		res.setCustomer(customer);
				    		res.setPassengers(passengerslisttemp);
				    		res.setSeats(Integer.parseInt(fields225.getText()));
				    		res.setPreferences(fields226.getText());
							try
				            {
								Data.SaveReservationsData(reservationslist);
				            }
				            catch(Exception e1){}
							try
				            {
								AllReservationsList.setListData(tripjourneyreservations.toArray());
				            }
				            catch(Exception e1){}
					    }
			    	}
			    }
			    catch(Exception e1)
			    {
					
			    }
			}   
		});
		TripJourneyButtonPanel.add(buttonCreateRes);
		TripJourneyButtonPanel.add(buttonSaveRes);
		reservetripjourney.add(TripJourneyButtonPanel);
		
		
		
		//RESERVATIONS FOR BUS AND CHAUFFEUr
		//// B&C reservations list
		JPanel BusChauffeurListPanel = new JPanel(new BorderLayout());
		JLabel labelbc1 = new JLabel("Bus-and-Chauffeur Reservations:");
		BusChauffeurListPanel.add(labelbc1, BorderLayout.NORTH);
		JList BusChauffeurResList = new JList(buschauffeurreservations.toArray());
		BusChauffeurResList.setVisibleRowCount(15);
		BusChauffeurResList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll66 = new JScrollPane(BusChauffeurResList);
		BusChauffeurListPanel.add(scroll66,BorderLayout.SOUTH);
		reservebuschauffeur.add(BusChauffeurListPanel);
		//// B&C form
		JPanel BusChauffeurInfoPanel = new JPanel(new GridLayout(13,2));
		JLabel infolabel421 = new JLabel("Customer Name:");
		JLabel infolabel422 = new JLabel("Address:");
		JLabel infolabel423 = new JLabel("E-Mail:");
		JLabel infolabel424 = new JLabel("Phone Number:");
		JLabel infolabel426 = new JLabel("Preferences:");
		JLabel infolabel427 = new JLabel("Departure Location:");
		JLabel infolabel428 = new JLabel("Arrival Location:");
		JLabel infolabel429 = new JLabel("Stops:");
		JLabel infolabel430 = new JLabel("Departure Date & Time:");
		JLabel infolabel431 = new JLabel("Arrival Date & Time:");
		JLabel infolabel432 = new JLabel("Bus Type:");
		JLabel infolabel433 = new JLabel("Chauffeur 1:");
		JLabel infolabel434 = new JLabel("Chauffeur 2:");
		JTextField fields421 = new JTextField(10);
		JTextField fields422 = new JTextField(10);
		JTextField fields423 = new JTextField(10);
		JTextField fields424 = new JTextField(10);
		//JTextField fields425 = new JTextField(10);
		JTextField fields426 = new JTextField(10);
		JTextField fields427 = new JTextField(10);
		JTextField fields428 = new JTextField(10);
		JTextField fields429 = new JTextField(10);
		JTextField fields430 = new JTextField(10);
		JTextField fields431 = new JTextField(10);
		ArrayList<String> bussesTypes2 = new ArrayList<>();
		bussesTypes2.add("None");
		bussesTypes2.add("Mini");
		bussesTypes2.add("Regular");
		bussesTypes2.add("Luxury");
		bussesTypes2.add("Party");
		JComboBox<String> bussesCombo2 = new JComboBox<>();
		bussesCombo2.setModel(new DefaultComboBoxModel(bussesTypes2.toArray()));
		JComboBox<String> chauffeurCombo3 = new JComboBox<>();
		chauffeurCombo3.setModel(new DefaultComboBoxModel(availableChauffeurs.toArray()));
		JComboBox<String> chauffeurCombo4 = new JComboBox<>();
		chauffeurCombo4.setModel(new DefaultComboBoxModel(availableChauffeurs.toArray()));
		BusChauffeurInfoPanel.add(infolabel421);
		BusChauffeurInfoPanel.add(fields421);
		BusChauffeurInfoPanel.add(infolabel422);
		BusChauffeurInfoPanel.add(fields422);
		BusChauffeurInfoPanel.add(infolabel423);
		BusChauffeurInfoPanel.add(fields423);
		BusChauffeurInfoPanel.add(infolabel424);
		BusChauffeurInfoPanel.add(fields424);
		BusChauffeurInfoPanel.add(infolabel426);
		BusChauffeurInfoPanel.add(fields426);
		BusChauffeurInfoPanel.add(infolabel427);
		BusChauffeurInfoPanel.add(fields427);
		BusChauffeurInfoPanel.add(infolabel428);
		BusChauffeurInfoPanel.add(fields428);
		BusChauffeurInfoPanel.add(infolabel429);
		BusChauffeurInfoPanel.add(fields429);
		BusChauffeurInfoPanel.add(infolabel430);
		BusChauffeurInfoPanel.add(fields430);
		BusChauffeurInfoPanel.add(infolabel431);
		BusChauffeurInfoPanel.add(fields431);
		BusChauffeurInfoPanel.add(infolabel432);
		BusChauffeurInfoPanel.add(bussesCombo2);
		BusChauffeurInfoPanel.add(infolabel433);
		BusChauffeurInfoPanel.add(chauffeurCombo3);
		BusChauffeurInfoPanel.add(infolabel434);
		BusChauffeurInfoPanel.add(chauffeurCombo4);
		reservebuschauffeur.add(BusChauffeurInfoPanel);
		//// B&C buttons
		JPanel BusChauffeurButtonPanel = new JPanel();
		JButton buttonAddBusChauffeur = new JButton("Create Reservation");
		buttonAddBusChauffeur.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            		try
            		{
            			Customer customer = null;
            			int found = -1;
			    		for(int i = 0; i<customerslist.size(); i++)
			    		{
			    			if(customerslist.get(i).getName().equalsIgnoreCase(fields421.getText()))
			    			{
			    				customer = customerslist.get(i);
			    				customer.setAddress(fields422.getText());
			    				customer.setEmail(fields423.getText());
			    				customer.setPhoneNumber(fields424.getText());
			    				found = i;
			    			}
			    		}
			    		if(found == -1)
			    		{
			    			customer = new Customer(fields421.getText(), fields422.getText(), fields424.getText(),fields423.getText());
			    			customerslist.add(customer);
			    		}
			    		try
		    			{
		    				Data.SaveCustomersData(customerslist);
		    			}
		    			catch(Exception e1){}
		    			try
		    			{
		    				CustomersList.setListData(customerslist.toArray());
		    			}
		    			catch(Exception e1){}
            			
	            		String depLoc = fields427.getText();
		            	String arrLoc = fields428.getText();
		            	String[] content = fields430.getText().split("/");
		            	String[] content2 = fields431.getText().split("/");
		            	MyDate depDate = new MyDate(Integer.parseInt(content[0]),Integer.parseInt(content[1]),Integer.parseInt(content[2]),0,0);
		            	MyDate retDate = new MyDate(Integer.parseInt(content2[0]),Integer.parseInt(content2[1]),Integer.parseInt(content2[2]),0,0);
		            	int seats = 0;
		            	String stops = fields429.getText();
		            	String extras = fields426.getText();
		            	Tour ntour = new Tour(depDate, retDate, depLoc, arrLoc, stops,(String)bussesCombo2.getSelectedItem(), seats, extras,2);
		            	ArrayList<Chauffeur> nchauffeurs = new ArrayList<>();
		            	nchauffeurs.add((Chauffeur) chauffeurCombo1.getSelectedItem());
		            	nchauffeurs.add((Chauffeur) chauffeurCombo2.getSelectedItem());
		            	ntour.setChauffeurs(nchauffeurs);
		            	Reservation res = new Reservation(customer,null,ntour,666999,0,fields426.getText(),2);
		            	tourslist.add(ntour);
		            	reservationslist.add(res);
		            	buschauffeurreservations.add(res);
			            try
			            {
			            	Data.SaveToursData(tourslist);
			            }
			            catch(Exception e1){}
			            try
			            {
			            	BusChauffeurResList.setListData(buschauffeurreservations.toArray());
			            }
			            catch(Exception e1){}
			            
			            try
			            {
			            	Data.SaveReservationsData(reservationslist);
			            }
			            catch(Exception e1){}
            		}
            		catch(Exception e1)
            		{
            			System.out.println("Add tour");
            		}
            	
            }  
        });
		BusChauffeurButtonPanel.add(buttonAddBusChauffeur);
		reservebuschauffeur.add(BusChauffeurButtonPanel);
		
		
		
		JPanel customerpanel = new JPanel();
		tabbed.add("Customers",customerpanel);
		// CUSTOMERS LIST
		JPanel CustomersListPanel = new JPanel(new BorderLayout());
		JLabel labelcust = new JLabel("Customers List:");
		CustomersListPanel.add(labelcust, BorderLayout.NORTH);
		//JList CustomersList = new JList(customerslist.toArray());
		CustomersList.setVisibleRowCount(15);
		CustomersList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll33 = new JScrollPane(CustomersList);
		CustomersListPanel.add(scroll33,BorderLayout.SOUTH);
		customerpanel.add(CustomersListPanel);
		// CUSTOMERS INFO PANEL
		JPanel CustomersInfoPanel = new JPanel(new GridLayout(4,2));
		JLabel infolabelcust1 = new JLabel("Customer Name:");
		JLabel infolabelcust2 = new JLabel("Address:");
		JLabel infolabelcust3 = new JLabel("E-Mail:");
		JLabel infolabelcust4 = new JLabel("Phone Number:");
		JTextField fieldscust1 = new JTextField(10);
		JTextField fieldscust2 = new JTextField(10);
		JTextField fieldscust3 = new JTextField(10);
		JTextField fieldscust4 = new JTextField(10);
		CustomersInfoPanel.add(infolabelcust1);
		CustomersInfoPanel.add(fieldscust1);
		CustomersInfoPanel.add(infolabelcust2);
		CustomersInfoPanel.add(fieldscust2);
		CustomersInfoPanel.add(infolabelcust3);
		CustomersInfoPanel.add(fieldscust3);
		CustomersInfoPanel.add(infolabelcust4);
		CustomersInfoPanel.add(fieldscust4);
		customerpanel.add(CustomersInfoPanel);
		//CUSTOMERS BUTTONS
		JPanel CustomersButtonPanel = new JPanel(new GridLayout(3,1));
		JButton buttonAdd4 = new JButton("Add");
		buttonAdd4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(fieldscust1.getText().isEmpty() || fieldscust2.getText().isEmpty() || fieldscust4.getText().isEmpty())
			    {
			          System.out.println("Some fields are empty");
			    }
			    else
			    {
			    	try
			    	{
					    String name = fieldscust1.getText();
					    String address = fieldscust2.getText();
					    String email = fieldscust3.getText();
						String phone = fieldscust4.getText();
						Customer cust = new Customer(name, address, phone, email);
						customerslist.add(cust);
						try
			            {
			            	CustomersList.setListData(customerslist.toArray());
			            }
			            catch(Exception e1){}
			            try
			            {
			            	Data.SaveCustomersData(customerslist);
			            }
			            catch(Exception e1){}
			    	}
					catch(Exception e1)
				    {
						System.out.println("Added");
				    }
			    }
			}  
		});
		JButton buttonSave4 = new JButton("Save");
		buttonSave4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				if(fieldscust1.getText().isEmpty() || fieldscust2.getText().isEmpty() || fieldscust4.getText().isEmpty())
		        {
					System.out.println("Some fields are empty");
		        }
		        else
		        {
		        	try
		        	{
			        	Customer selected = customerslist.get(CustomersList.getSelectedIndex());
			            selected.setName(fieldscust1.getText());
			            selected.setAddress(fieldscust2.getText());
			            selected.setEmail(fieldscust3.getText());
			            selected.setPhoneNumber(fieldscust4.getText());
			            try
			            {
			            	CustomersList.setListData(customerslist.toArray());
			            }
			            catch(Exception e1){}
			            try
			            {
			            	Data.SaveCustomersData(customerslist);
			            }
			            catch(Exception e1){}
		        	}
		        	catch(Exception e1)
		        	{
		        		System.out.println("Saved");
		        	}
		        }
		    }   
		});
		JButton buttonRemove4 = new JButton("Remove");
		buttonRemove4.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
		    {
				if(!CustomersList.isSelectionEmpty())
		        {
					try
					{
						customerslist.remove(CustomersList.getSelectedIndex());
						try
			            {
			            	CustomersList.setListData(customerslist.toArray());
			            }
			            catch(Exception e1){}
			            try
			            {
			            	Data.SaveCustomersData(customerslist);
			            }
			            catch(Exception e1){}
					}
					catch(Exception e1)
					{
						System.out.println("Removed");
					}
		        }
		        else System.out.println("No field selected");
		    }   
		});
		CustomersButtonPanel.add(buttonAdd4);
		CustomersButtonPanel.add(buttonSave4);
		CustomersButtonPanel.add(buttonRemove4);
		CustomersList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				fieldscust1.setText(customerslist.get(CustomersList.getSelectedIndex()).getName());
				fieldscust2.setText(customerslist.get(CustomersList.getSelectedIndex()).getAddress());
				fieldscust3.setText(customerslist.get(CustomersList.getSelectedIndex()).getEmail());
				fieldscust4.setText(customerslist.get(CustomersList.getSelectedIndex()).getPhoneNumber());
			}
			});
		customerpanel.add(CustomersButtonPanel);
		
		
		
		//BUSSES TAB
		JPanel bussespanel = new JPanel();
		tabbed.add("Busses",bussespanel);
		//BUSSES FORM
		JPanel BussesInfoPanel = new JPanel(new GridLayout(4,3));
		JLabel infolabelbus1 = new JLabel("Mini Busses:");
		JLabel infolabelbus2 = new JLabel("Regular Busses:");
		JLabel infolabelbus3 = new JLabel("Luxury Busses:");
		JLabel infolabelbus4 = new JLabel("Party Busses:");
		JTextField fieldsbus1 = new JTextField(10);
		JTextField fieldsbus2 = new JTextField(10);
		JTextField fieldsbus3 = new JTextField(10);
		JTextField fieldsbus4 = new JTextField(10);
		JButton busSave1 = new JButton("Save");
		busSave1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				if(fieldsbus1.getText().isEmpty())
		        {
					System.out.println("Some fields are empty");
		        }
		        else
		        {
		        	try
		        	{
			        	int stock = Integer.parseInt(fieldsbus1.getText());
			            try
			            {
			            	allbusses.setMiniBusses(stock);
			            }
			            catch(Exception e1){}
			            try
			            {
			            	allbusses.SaveData();
			            }
			            catch(Exception e1){}
		        	}
		        	catch(Exception e1)
		        	{
		        		System.out.println("Saved");
		        	}
		        }
		    }   
		});
		JButton busSave2 = new JButton("Save");
		busSave2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				if(fieldsbus2.getText().isEmpty())
		        {
					System.out.println("Some fields are empty");
		        }
		        else
		        {
		        	try
		        	{
			        	int stock = Integer.parseInt(fieldsbus2.getText());
			            try
			            {
			            	allbusses.setRegularBusses(stock);
			            }
			            catch(Exception e1){}
			            try
			            {
			            	allbusses.SaveData();
			            }
			            catch(Exception e1){}
		        	}
		        	catch(Exception e1)
		        	{
		        		System.out.println("Saved");
		        	}
		        }
		    }   
		});
		JButton busSave3 = new JButton("Save");
		busSave3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				if(fieldsbus3.getText().isEmpty())
		        {
					System.out.println("Some fields are empty");
		        }
		        else
		        {
		        	try
		        	{
			        	int stock = Integer.parseInt(fieldsbus3.getText());
			            try
			            {
			            	allbusses.setLuxuryBusses(stock);
			            }
			            catch(Exception e1){}
			            try
			            {
			            	allbusses.SaveData();
			            }
			            catch(Exception e1){}
		        	}
		        	catch(Exception e1)
		        	{
		        		System.out.println("Saved");
		        	}
		        }
		    }   
		});
		JButton busSave4 = new JButton("Save");
		busSave4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
		    {
				if(fieldsbus4.getText().isEmpty())
		        {
					System.out.println("Some fields are empty");
		        }
		        else
		        {
		        	try
		        	{
			        	int stock = Integer.parseInt(fieldsbus4.getText());
			            try
			            {
			            	allbusses.setPartyBusses(stock);
			            }
			            catch(Exception e1){}
			            try
			            {
			            	allbusses.SaveData();
			            }
			            catch(Exception e1){}
		        	}
		        	catch(Exception e1)
		        	{
		        		System.out.println("Saved");
		        	}
		        }
		    }   
		});
		BussesInfoPanel.add(infolabelbus1);
		BussesInfoPanel.add(fieldsbus1);
		BussesInfoPanel.add(busSave1);
		BussesInfoPanel.add(infolabelbus2);
		BussesInfoPanel.add(fieldsbus2);
		BussesInfoPanel.add(busSave2);
		BussesInfoPanel.add(infolabelbus3);
		BussesInfoPanel.add(fieldsbus3);
		BussesInfoPanel.add(busSave3);
		BussesInfoPanel.add(infolabelbus4);
		BussesInfoPanel.add(fieldsbus4);
		BussesInfoPanel.add(busSave4);
		bussespanel.add(BussesInfoPanel);
		fieldsbus1.setText(Integer.toString(allbusses.getMiniBusses()));
		fieldsbus2.setText(Integer.toString(allbusses.getRegularBusses()));
		fieldsbus3.setText(Integer.toString(allbusses.getLuxuryBusses()));
		fieldsbus4.setText(Integer.toString(allbusses.getPartyBusses()));
	
		
		
		
		frame.add(tabbed);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

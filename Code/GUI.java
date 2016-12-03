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
		ArrayList<Tour> tourslist = TourList.LoadData();
		ArrayList<Chauffeur> chauffeurslist = ChauffeursList.LoadData();
		ArrayList<Chauffeur> availableChauffeurs = new ArrayList<>();
		boolean departuredate = false;
		
		
		JFrame frame = new JFrame("VIA BUS");
		// TOURS TAB
		JTabbedPane tabbed = new JTabbedPane();
		JPanel ToursTab = new JPanel();
		tabbed.addTab("Tours",ToursTab);
		//CREATING TOURS LIST
		JPanel ToursListPanel = new JPanel(new BorderLayout());
		JLabel label1 = new JLabel("Tours List:");
		ToursListPanel.add(label1, BorderLayout.NORTH);
		JList ToursList = new JList(tourslist.toArray());
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
		            					break;
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
		            	Tour ntour = new Tour(depDate, retDate, depLoc, arrLoc, stops,(String)bussesCombo.getSelectedItem(), seats, extras);
		            	ArrayList<Chauffeur> nchauffeurs = new ArrayList<>();
		            	nchauffeurs.add((Chauffeur) chauffeurCombo1.getSelectedItem());
		            	nchauffeurs.add((Chauffeur) chauffeurCombo2.getSelectedItem());
		            	ntour.setChauffeurs(nchauffeurs);
		            	tourslist.add(ntour);
		            	ToursList.setListData(tourslist.toArray());
		            	TourList.SaveData(tourslist);
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
	            		selected.setExtras(fields7.getText());
	            		selected.setBusType(bussesCombo.getSelectedItem().toString());
	            		ToursList.setListData(tourslist.toArray());
	            		TourList.SaveData(tourslist);
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
	            	tourslist.remove(ToursList.getSelectedIndex());
	            	ToursList.setListData(tourslist.toArray());
	            	TourList.SaveData(tourslist);
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
	            	fields1.setText(tourslist.get(ToursList.getSelectedIndex()).getDepartureLocation());
	            	fields2.setText(tourslist.get(ToursList.getSelectedIndex()).getArrivalLocation());
	            	fields3.setText(tourslist.get(ToursList.getSelectedIndex()).getStops());
	            	fields4.setText(tourslist.get(ToursList.getSelectedIndex()).getDepartureTime().toString());
	            	fields5.setText(tourslist.get(ToursList.getSelectedIndex()).getReturnTime().toString());
	            	fields6.setText("696969");
	            	fields7.setText(tourslist.get(ToursList.getSelectedIndex()).getExtras());
	            	if(tourslist.get(ToursList.getSelectedIndex()).getBusType().equals("Mini"))
	            	{
	            		bussesCombo.setSelectedIndex(1);
	            	}
	            	else if(tourslist.get(ToursList.getSelectedIndex()).getBusType().equals("Regular"))
	            	{
	            		bussesCombo.setSelectedIndex(2);
	            	}
	            	else if(tourslist.get(ToursList.getSelectedIndex()).getBusType().equals("Luxury"))
	            	{
	            		bussesCombo.setSelectedIndex(3);
	            	}
	            	else if(tourslist.get(ToursList.getSelectedIndex()).getBusType().equals("Party"))
	            	{
	            		bussesCombo.setSelectedIndex(4);
	            	}
	            	else if(tourslist.get(ToursList.getSelectedIndex()).getBusType().equals("None"))
	            	{
	            		bussesCombo.setSelectedIndex(0);
	            	}
	            	chauffeurCombo1.setSelectedItem(tourslist.get(ToursList.getSelectedIndex()).getChauffeurs().get(0));
	            	chauffeurCombo2.setSelectedItem(tourslist.get(ToursList.getSelectedIndex()).getChauffeurs().get(1));
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
				    ChauffeurList.setListData(chauffeurslist.toArray());
				    try {
						ChauffeursList.SaveData(chauffeurslist);
					} catch (IOException e1) {

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
		            ChauffeurList.setListData(chauffeurslist.toArray());
		            try {
						ChauffeursList.SaveData(chauffeurslist);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
					chauffeurslist.remove(ChauffeurList.getSelectedIndex());
					ChauffeurList.setListData(chauffeurslist.toArray());
					try {
						ChauffeursList.SaveData(chauffeurslist);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
		
		
		JPanel reservations = new JPanel();
		tabbed.add("Reservations",reservations);
		//RESERVATIONS FOR TRIP & JOURNEY
		//// T&J list
		//// T&J form
		//// T&J passengers form
		//// T&J buttons
		//RESERVATIONS FOR BUS AND CHAUFFEUR
		//// B&C list
		//// B&C form
		//// B&C buttons
		
		frame.add(tabbed);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

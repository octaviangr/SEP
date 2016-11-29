import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class GUI extends JFrame{

	public static void main (String[] args)
	{
		String stops1 = "Berlin";
		MyDate bday = new MyDate();
		Tour tour = new Tour(bday,bday,"Horsens","Budapest",stops1,"Regular",50,"sex slaves");
		Chauffeur cha = new Chauffeur("Trip Driver","04563666","FullTime","i like cheese",45);
		Chauffeur cha2 = new Chauffeur("Speed Driver","03657666","FullTime","i like me",46);
		
		//Lists:
		ArrayList<Tour> tourslist = new ArrayList<>();
		ArrayList<Chauffeur> chauffeurslist = new ArrayList<>();
		chauffeurslist.add(cha);
		chauffeurslist.add(cha2);
		
		
		JFrame frame = new JFrame("VIA BUS");
		// TOURS TAB
		JTabbedPane tabbed = new JTabbedPane();
		JPanel ToursTab = new JPanel();
		tabbed.addTab("Tours",ToursTab);
		//CREATING TOURS LIST
		JPanel ToursListPanel = new JPanel(new BorderLayout());
		JLabel label1 = new JLabel("Tours List:");
		ToursListPanel.add(label1, BorderLayout.NORTH);
		tourslist.add(tour);
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
		JTextField fields5 = new JTextField(10);
		JTextField fields6 = new JTextField(10);
		JTextField fields7 = new JTextField(10);
		ArrayList<String> bussesTypes = new ArrayList<>();
		bussesTypes.add("Mini");
		bussesTypes.add("Regular");
		bussesTypes.add("Luxury");
		bussesTypes.add("Party");
		JComboBox<String> bussesCombo = new JComboBox<>();
		bussesCombo.setModel(new DefaultComboBoxModel(bussesTypes.toArray()));
		JComboBox<String> chauffeurCombo1 = new JComboBox<>();
		chauffeurCombo1.setModel(new DefaultComboBoxModel(chauffeurslist.toArray()));
		JComboBox<String> chauffeurCombo2 = new JComboBox<>();
		chauffeurCombo2.setModel(new DefaultComboBoxModel(chauffeurslist.toArray()));
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
            		String depLoc = fields1.getText();
	            	String arrLoc = fields2.getText();
	            	String[] content = fields4.getText().split("/");
	            	String[] content2 = fields5.getText().split("/");
	            	MyDate depDate = new MyDate(Integer.parseInt(content[0]),Integer.parseInt(content[1]),Integer.parseInt(content[2]),0,0);
	            	MyDate retDate = new MyDate(Integer.parseInt(content2[0]),Integer.parseInt(content2[1]),Integer.parseInt(content2[2]),0,0);
	            	int seats = Integer.parseInt(fields6.getText());
	            	String stops = fields3.getText();
	            	String extras = fields7.getText();
	            	Tour ntour = new Tour(depDate, retDate, depLoc, arrLoc, stops,null, seats, extras);
	            	ArrayList<Chauffeur> nchauffeurs = new ArrayList<>();
	            	nchauffeurs.add((Chauffeur) chauffeurCombo1.getSelectedItem());
	            	nchauffeurs.add((Chauffeur) chauffeurCombo2.getSelectedItem());
	            	ntour.setChauffeurs(nchauffeurs);
	            	tourslist.add(ntour);
	            	ToursList.setListData(tourslist.toArray());
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
            	}
            }   
        });
		JButton buttonRemove = new JButton("Remove");
		buttonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	if(!ToursList.isSelectionEmpty())
            	{
            		tourslist.remove(ToursList.getSelectedIndex());
            		ToursList.setListData(tourslist.toArray());
            	}
            	else System.out.println("No field selected");
            }   
        });
		ToursList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e)
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
            		bussesCombo.setSelectedIndex(0);
            	}
            	else if(tourslist.get(ToursList.getSelectedIndex()).getBusType().equals("Regular"))
            	{
            		bussesCombo.setSelectedIndex(1);
            	}
            	else if(tourslist.get(ToursList.getSelectedIndex()).getBusType().equals("Luxury"))
            	{
            		bussesCombo.setSelectedIndex(2);
            	}
            	else if(tourslist.get(ToursList.getSelectedIndex()).getBusType().equals("Party"))
            	{
            		bussesCombo.setSelectedIndex(3);
            	}
            	chauffeurCombo1.setSelectedItem(tourslist.get(ToursList.getSelectedIndex()).getChauffeurs().get(0));
            	chauffeurCombo2.setSelectedItem(tourslist.get(ToursList.getSelectedIndex()).getChauffeurs().get(1));
            }
        });
		ToursButtonPanel.add(buttonAdd);
		ToursButtonPanel.add(buttonSave);
		ToursButtonPanel.add(buttonRemove);
		ToursTab.add(ToursButtonPanel);
		
		JPanel chauffeurs = new JPanel();
		tabbed.add("Chauffeurs",chauffeurs);
		JPanel reservations = new JPanel();
		tabbed.add("Reservations",reservations);
		
		frame.add(tabbed);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

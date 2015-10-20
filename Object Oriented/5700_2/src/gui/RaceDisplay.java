package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import email.EmailValidator;
import race.*;

public class RaceDisplay {
	Race race;
	JPanel mainDisplay;
	ArrayList<Racer> allRacers = new ArrayList<Racer>();
	ArrayList<Racer> displayed = new ArrayList<Racer>();
	ArrayList<Racer> cheaters = new ArrayList<Racer>();
	
	public RaceDisplay(Race race){
		this.race = race;
		for(RaceGroup group : race.getGroups())
			for(Racer r : group.getRacers())
				allRacers.add(r);
	}
	
	private void setDisplayedRacers(JFrame frame){
		frame.remove(mainDisplay);
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Racer> displayedRacers = new ArrayList<Racer>();
		list.add("Select A Racer");
			for(Racer r : allRacers)
				list.add(r.getFirstName() + " " + r.getLastName() + " #" + r.getBibNumber());
		
	    JPanel outside = new JPanel();
	    JPanel panel = new JPanel();
	    JPanel center = new JPanel();
	    JPanel racers = new JPanel();
	    
	    JComboBox<String> dropDown = new JComboBox<String>(list.toArray(new String[list.size()]));
	    JButton submit = new JButton("Display Selected Racers");
	    
	    outside.setLayout(new BoxLayout(outside, BoxLayout.PAGE_AXIS));
	    outside.add(Box.createRigidArea(new Dimension(10,20)));
	    outside.add(panel);
	    
	    panel.setLayout(new BorderLayout(10,20));
	    
	    center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
	    panel.add(center, BorderLayout.CENTER);
	    dropDown.setMaximumSize(new Dimension(200, 20));
	    submit.setMaximumSize(new Dimension(200, 20));
	    center.add(dropDown);
	    center.add(Box.createRigidArea(new Dimension(0,20)));
	    center.add(submit);
	    
	    racers.setLayout(new BoxLayout(racers, BoxLayout.Y_AXIS));
	    racers.add(new JLabel("Your Racers:"));
	    racers.add(Box.createRigidArea(new Dimension(0,20)));
	    panel.add(racers, BorderLayout.LINE_END);
	    
	    frame.add(outside);
        frame.pack();
        frame.setVisible(true);
        
        dropDown.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String s = (String) dropDown.getSelectedItem();
				if(!s.equals("Select A Racer")){
					int number = Integer.parseInt(s.substring(s.indexOf('#')+1));		
					for(RaceGroup group : race.getGroups())
						for(Racer r : group.getRacers())
							if(r.getBibNumber() == number)
								displayedRacers.add(r);
					
					dropDown.removeItemAt(dropDown.getSelectedIndex());
					dropDown.setSelectedIndex(0);
					JLabel temp = new JLabel(s);
					racers.add(temp);
					frame.pack();
				    frame.setVisible(true);
				}
			}
        });
        
        submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(displayedRacers.isEmpty()){
					JOptionPane.showMessageDialog(frame,
						    "Please select the desired racers.",
						    "Racers Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else{
					displayed = displayedRacers;
					frame.remove(outside);
					updateDisplay();
					frame.add(mainDisplay);
					frame.pack();
				}
			}
        });
	}
	
	private void showCheaters(){
		JFrame frame = new JFrame("Detected Cheaters");
		JPanel panel = new JPanel();
		frame.add(panel);
		if(cheaters.isEmpty())
			panel.add(new JLabel("No Detected Cheaters"));
		for(Racer r : cheaters)
			panel.add(new JLabel(r.getFirstName() + " " + r.getLastName() + " #" + r.getBibNumber()));
	}
	
	private void createSupportTeam(){
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<Racer> supportGroupRacers = new ArrayList<Racer>();
		list.add("Select A Racer");
		for(RaceGroup group : race.getGroups())
			for(Racer r : group.getRacers())
				list.add(r.getFirstName() + " " + r.getLastName() + " #" + r.getBibNumber());
		
	    JFrame frame = new JFrame("Support Team Information");
	    JPanel outside = new JPanel();
	    JPanel panel = new JPanel();
	    JPanel left = new JPanel();
	    JPanel center = new JPanel();
	    JPanel racers = new JPanel();
	    
	    JTextField email = new JTextField();
	    JComboBox<String> dropDown = new JComboBox<String>(list.toArray(new String[list.size()]));
	    JButton submit = new JButton("Create Support Team");
	    
	    outside.setLayout(new BoxLayout(outside, BoxLayout.PAGE_AXIS));
	    outside.add(Box.createRigidArea(new Dimension(10,20)));
	    outside.add(panel);
	    
	    panel.setLayout(new BorderLayout(10,20));
	    
	    email.setMaximumSize(new Dimension(300, 20));
	    left.add(new JLabel("Enter Email:"));
	    left.add(Box.createRigidArea(new Dimension(0,5)));
	    left.add(email);
	    
	    left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
	    panel.add(left, BorderLayout.LINE_START);
	    
	    center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
	    panel.add(center, BorderLayout.CENTER);
	    dropDown.setMaximumSize(new Dimension(200, 20));
	    submit.setMaximumSize(new Dimension(200, 20));
	    center.add(dropDown);
	    center.add(Box.createRigidArea(new Dimension(0,20)));
	    center.add(submit);
	    
	    racers.setLayout(new BoxLayout(racers, BoxLayout.Y_AXIS));
	    racers.add(new JLabel("Your Racers:"));
	    racers.add(Box.createRigidArea(new Dimension(0,20)));
	    panel.add(racers, BorderLayout.LINE_END);
	    
	    
	    frame.add(outside);
        frame.pack();
        frame.setVisible(true);
        
        dropDown.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String s = (String) dropDown.getSelectedItem();
				if(!s.equals("Select A Racer")){
					int number = Integer.parseInt(s.substring(s.indexOf('#')+1));		
					for(Racer r : allRacers)
						if(r.getBibNumber() == number)
							supportGroupRacers.add(r);
					
					dropDown.removeItemAt(dropDown.getSelectedIndex());
					dropDown.setSelectedIndex(0);
					JLabel temp = new JLabel(s);
					racers.add(temp);
					frame.pack();
				    frame.setVisible(true);
				}
			}
        });
        
        submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EmailValidator validator = new EmailValidator();
				if(!validator.validate(email.getText()))
					JOptionPane.showMessageDialog(frame,
						    "Email is invalid, please enter a valid email address.",
						    "Email Error",
						    JOptionPane.ERROR_MESSAGE);
				else if(supportGroupRacers.isEmpty()){
					JOptionPane.showMessageDialog(frame,
						    "Please select your racers.",
						    "Racers Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else{
					new SupportTeam(supportGroupRacers, email.getText());
				}
			}
        });
	}
	
	public void updateDisplay(){
		mainDisplay.removeAll();
		mainDisplay.setLayout(new BoxLayout(mainDisplay, BoxLayout.Y_AXIS));
		Collections.sort(displayed, new Race.racerPositionComparator());
		for(int i = 0; i < 30 && i < displayed.size(); i++){
			mainDisplay.add(new JLabel(i+1 + ". " + displayed.get(i).getFirstName() + " "
					+ displayed.get(i).getLastName() + " #" + displayed.get(i).getBibNumber()));
		}
	}
	
	public void addCheaters(Racer r1, Racer r2){
		cheaters.add(r1);
		cheaters.add(r2);
	}
	
	
	private JMenuBar buildMenuBar(JFrame frame){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		
		JMenuItem createSupportTeam = new JMenuItem("Create Support Team");
		edit.add(createSupportTeam);
		createSupportTeam.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				createSupportTeam();
			}
		});
		
		JMenuItem changeDisplayedRacers = new JMenuItem("Change Displayed Racers");
		edit.add(changeDisplayedRacers);
		changeDisplayedRacers.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setDisplayedRacers(frame);
			}
		});
		
		JMenu status = new JMenu("Status");
		menuBar.add(status);
		JMenuItem displayCheaters = new JMenuItem("Display Cheaters");
		status.add(displayCheaters);
		displayCheaters.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				showCheaters();
			}
		});
		
		return menuBar;
	}
	
    public void createAndShowGUI(){
        //Create and set up the window.
        JFrame frame = new JFrame("Best Race Program ever");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setMinimumSize(new Dimension(400,200));
        
        frame.setJMenuBar(buildMenuBar(frame));
 

		mainDisplay = new JPanel();
		displayed.addAll(allRacers.subList(0, 30));
		updateDisplay();
        frame.getContentPane().add(mainDisplay);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}

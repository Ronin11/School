package race;

import java.util.ArrayList;
import java.util.Observable;

public class Racer extends Observable{
	private String firstName;
	private String lastName;
	private String birthday = "";
	private int bibNumber;
	private String endTime = "DNF";
	private RaceGroup group;
	private ArrayList<Checkpoint> passedCheckpoints = new ArrayList<Checkpoint>();
	
	public String getFirstName(){return firstName;}
	public String getLastName(){return lastName;}
	public String getBirthday(){return birthday;}
	public int getBibNumber(){return bibNumber;}
	public String getEndTime(){return endTime;}
	public RaceGroup getGroup(){return group;}
	public String getStartTime(){return group.getGroupStatTime();}
	public ArrayList<Checkpoint> getPassedCheckpoints(){return passedCheckpoints;}
	
	public Checkpoint getLastPassedCheckpoint()
		{
		if(passedCheckpoints.size() == 0)
			return null;
		else
			return passedCheckpoints.get(passedCheckpoints.size()-1);
		}
	
	public void setRaceGroup(RaceGroup rg){group = rg;}
	public void setEndTime(String s){endTime = s;}
	public void setBirthday(String s){birthday = s;}
	public void addPassedCheckpoint(Checkpoint s){
		passedCheckpoints.add(s);
		setChanged();
		notifyObservers();
		}
	
	public Racer(String firstName, String lastName, int bibNumber, RaceGroup group){
	this.firstName = firstName;
	this.lastName = lastName;
	this.bibNumber = bibNumber;
	this.group = group;
	}
	
}

package race;

import java.util.ArrayList;

public class RaceGroup {
	private int raceGroupNumber;
	private int startBlockNumber;
	private int endBlockNumber;
	private String groupLabel;
	private String groupStartTime;
	private ArrayList<Racer> racers = new ArrayList<Racer>();
	
	public int getRaceGroupNumber(){return raceGroupNumber;}
	public int getStartBlockNumber(){return startBlockNumber;}
	public int getEndBlockNumber(){return endBlockNumber;}
	public String getGroupLabel(){return groupLabel;}
	public String getGroupStatTime(){return groupStartTime;}
	public ArrayList<Racer> getRacers(){return racers;}
	
	public boolean isNumberValid(int num){
		if(num > startBlockNumber && num < endBlockNumber)
			return true;
		else
			return false;
	}
	
	public void addRacer(Racer r){racers.add(r);}
	
	public RaceGroup(int raceGroupNumber, String groupLabel, String groupStartTime,
			int startBlockNumber, int endBlockNumber){
		this.raceGroupNumber = raceGroupNumber;
		this.groupLabel = groupLabel;
		this.groupStartTime = groupStartTime;
		this.startBlockNumber = startBlockNumber;
		this.endBlockNumber = endBlockNumber;
	}
}

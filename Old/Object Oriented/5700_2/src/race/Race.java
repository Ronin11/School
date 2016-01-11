package race;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;

public class Race extends Observable {
	ArrayList<RaceGroup> groups = new ArrayList<RaceGroup>();
	ArrayList<Sensor> sensors = new ArrayList<Sensor>();
	ArrayList<Racer> cheaters = new ArrayList<Racer>();
	
	public ArrayList<RaceGroup> getGroups(){return groups;}
	public ArrayList<Racer> getCheaters(){return cheaters;}
	public void addCheater(Racer r){cheaters.add(r);}
	public ArrayList<Sensor> getSensors(){return sensors;}
	
	public RaceGroup getGroupById(int b){
		for(RaceGroup rg : groups){
			if(rg.getRaceGroupNumber() == b)
				return rg;
		}
		System.out.println("Group Not Found");
		return null;
	}
	
	private Racer findRacerByNumber(int num){
		for (RaceGroup rg : groups){
			for(Racer r : rg.getRacers()){
				if(num == r.getBibNumber())
					return r;
			}
		}
		System.out.println("Racer Not Found");
		return null;
	}
	
	private Sensor findSensorById(int id){
		for(Sensor s : sensors){
			if(s.getId() == id)
				return s;
		}
		System.out.println("Sensor Not Found");
		return null;
	}
	
	public void updateRacer(int racerNumber, int sensorId, String time){
		Racer r = null;
		Sensor s = null;
		if(findRacerByNumber(racerNumber) != null)
			r = findRacerByNumber(racerNumber);
		if(findSensorById(sensorId) != null)
			s = findSensorById(sensorId);
		if(r != null && s != null){
			Checkpoint c = new Checkpoint(s, time);
			r.addPassedCheckpoint(c);
			setChanged();
			notifyObservers();
		}else{
			System.out.println("Something bad happened");
		}
	}
	
	public Race(){
		groups = new ArrayList<RaceGroup>();
	}
	
	public static class racerPositionComparator implements Comparator<Racer>{
		@Override
		public int compare(Racer r1, Racer r2) {
			if(r1.getLastPassedCheckpoint() == null && r2.getLastPassedCheckpoint() == null)
				return 0; //They are equal
			else if(r1.getLastPassedCheckpoint() == null)
				return 1; //Second one is ahead
			else if(r2.getPassedCheckpoints() == null)
				return -1; //First one is ahead
			else if(r1.getLastPassedCheckpoint().getSensor() == r2.getLastPassedCheckpoint().getSensor())
				return r1.getLastPassedCheckpoint().getTime().compareTo(r2.getLastPassedCheckpoint().getTime());
			else
				return ((Integer)r1.getLastPassedCheckpoint().getSensor().getId()).compareTo(
						r2.getLastPassedCheckpoint().getSensor().getId());
		}
	}
}

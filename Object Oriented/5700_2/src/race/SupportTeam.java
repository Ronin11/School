package race;

import java.util.ArrayList;

import observers.SupportObserver;

public class SupportTeam {
	String email;
	ArrayList<Racer> racers = new ArrayList<Racer>();
	SupportObserver observer;
	
	
	public SupportTeam(ArrayList<Racer> racers, String email){
		this.email = email;
		observer = new SupportObserver(this.email);
		this.racers = racers;
		for(Racer r : racers)
			r.addObserver(observer);
	}

}

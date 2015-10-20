package observers;

import java.util.Observable;
import java.util.Observer;

import gui.RaceDisplay;
import race.Race;

public class RaceObserver implements Observer{
	Race race;
	RaceDisplay display;
	
	public RaceObserver(RaceDisplay display){
		this.display = display;
	}
	@Override
	public void update(Observable o, Object arg) {
		race = (Race) o;
		display.updateDisplay();
	}

}

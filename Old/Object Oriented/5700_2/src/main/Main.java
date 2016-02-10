package main;

import gui.RaceDisplay;
import io.CSV;
import observers.*;
import race.Race;
import server.UdpClient;

public class Main {

	public static void main(String[] args) {
		Race race = new Race();
		CSV csv = new CSV();
		csv.getGroups(race);
		csv.getRacers(race);
		csv.getSensors(race);
		RaceDisplay display = new RaceDisplay(race);
		RaceObserver observer = new RaceObserver(display);
		CheaterObserver cheaterObserver = new CheaterObserver(race);
		race.addObserver(observer);
		race.addObserver(cheaterObserver);
		new UdpClient(race);
		
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                display.createAndShowGUI();
            }
        });
	}

}

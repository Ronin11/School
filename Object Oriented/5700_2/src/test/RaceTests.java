package test;


import static org.junit.Assert.*;

import org.junit.*;

import gui.RaceDisplay;
import io.CSV;
import observers.RaceObserver;
import race.*;
import server.UdpClient;



public class RaceTests{
		
	//Test Race Structure
	@Test
	public void testRaceStructure(){
		Race race = new Race();
		RaceGroup group = new RaceGroup(0, "Test", "0000", 1, 100);
		Racer racer = new Racer("First", "Last", 1, group);
		group.addRacer(racer);
		race.getGroups().add(group);
		assertTrue(race.getGroups().get(0).getRacers().contains(racer));
	}
	
	@Test
	public void testSupportObserver(){
		Race race = new Race();
		RaceDisplay display = new RaceDisplay(race);
		RaceObserver observer = new RaceObserver(display);
		race.addObserver(observer);
		assertTrue(race.countObservers() == 1);
		race.deleteObserver(observer);
		assertTrue(race.countObservers() == 0);
	}
	
	@Test
	public void testCsvFileRead(){
		Race race = new Race();
		CSV csv = new CSV();
		csv.getGroups(race);
		csv.getRacers(race);
		csv.getSensors(race);
		assertTrue(!race.getGroups().isEmpty());
		for(RaceGroup group : race.getGroups())
			assertTrue(!group.getRacers().isEmpty());
	}
}

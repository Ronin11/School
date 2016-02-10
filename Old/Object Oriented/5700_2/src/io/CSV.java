package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import race.*;

public class CSV {
	String fileLocations = "C:/Users/Ronin/Desktop/School/Object Oriented/2/SensorSimulator-Version2/SensorSimulator/bin/Debug/";
	String groupFileName = "Groups.csv";
	String racersFileName = "Racers.csv";
	String sensorsFileName = "Sensors.csv";
			
	public CSV(){}
	public void getGroups(Race r){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileLocations+groupFileName));
			String line = "";
			while((line = br.readLine()) != null ){
				String[] items = line.split(",");
				r.getGroups().add(new RaceGroup(Integer.parseInt(items[0]),items[1],items[2],
						Integer.parseInt(items[3]),Integer.parseInt(items[4])));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getRacers(Race r){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileLocations+racersFileName));
			String line = "";
			while((line = br.readLine()) != null ){
				String[] items = line.split(",");
				r.getGroupById(Integer.parseInt(items[3])).addRacer(new Racer(items[0],items[1],
						Integer.parseInt(items[2]),r.getGroupById(Integer.parseInt(items[3]))));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getSensors(Race r){
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileLocations+sensorsFileName));
			String line = "";
			while((line = br.readLine()) != null ){
				String[] items = line.split(",");
				r.getSensors().add(new Sensor(Integer.parseInt(items[0]),Integer.parseInt(items[1])));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

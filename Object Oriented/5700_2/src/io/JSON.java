package io;

import org.json.simple.*;
import org.json.simple.parser.*;

import race.Race;

public class JSON {
	JSONParser parser;
	Race race;
	
	public JSON(Race r){
		parser = new JSONParser();
		race = r;
	}
	@SuppressWarnings("unchecked")
	public void parse(String input){
		try {
			Object obj = parser.parse(input);
			JSONArray array = new JSONArray();
			array.add(obj);
			JSONObject jObj = (JSONObject) array.get(0);
			int racerNumber = (int)(long)jObj.get("RacerBibNumber");
			int sensorId = (int)(long)jObj.get("SensorId");
			String time = String.valueOf((long)jObj.get("Timestamp"));
			race.updateRacer(racerNumber, sensorId, time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
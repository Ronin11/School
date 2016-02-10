package race;

public class Checkpoint {
	private Sensor sensor;
	private String time;
	
	public Sensor getSensor(){return sensor;}
	public String getTime(){return time;}
	
	public Checkpoint(Sensor s, String time){
		this.sensor = s;
		this.time = time;
	}
}

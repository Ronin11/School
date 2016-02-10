package race;

public class Sensor {
	private int id;
	private int distance;
	
	public int getId(){return id;}
	public int getDistance(){return distance;}
	
	public Sensor(int id, int distance){
		this.id = id;
		this.distance = distance;
	}
}

package engineer;

public class Engineer {
	private int id;
	private String name;
	private float hours;
	
	//This should insure that each id is unique.
	private static int newId = 0;
	
	public Engineer(){
		id = newId++;
	}
	public int getId(){return id;}
	public float getHours(){return hours;}
	public String getName(){return name;}
	
	public void setHours(float f){hours = f;}
	public void setName(String s){name = s;}
}

package shapes;

public class Rectangle extends Square{
	double height;
	
	public Rectangle(double width, double height){
		this.width = width;
		this.height = height;
	}
	
	@Override
	public double getArea(){ 
		return width*height;
	}
	

}

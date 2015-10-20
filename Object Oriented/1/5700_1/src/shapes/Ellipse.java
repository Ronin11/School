package shapes;

public class Ellipse extends Circle {
	double otherRadius;
	
	public Ellipse(double radius, double otherRadius){
		this.radius = radius;
		this.otherRadius = otherRadius;
	}
	
	@Override
	public double getArea(){
		return java.lang.Math.PI*radius*otherRadius;
	}
}

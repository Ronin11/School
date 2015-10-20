package shapes;

public class Circle extends Shape {
	double radius;
	
	Circle(){}
	public Circle(double radius){
		this.radius = radius;
	}
	
	@Override
	public double getArea() {
		return java.lang.Math.PI*(radius*radius);
	}
	

}

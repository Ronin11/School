package shapes;

public class Triangle extends Shape {
	double width;
	double height;
	
	Triangle(){}
	
	public Triangle(double width, double height){
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() {
		return width*height/2;
	}
	
	
}

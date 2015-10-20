package shapes;

public class Square extends Shape{
	double width;
	
	Square(){}
	public Square(double width)
	{
		this.width = width;
	}

	@Override
	public double getArea() {
		return width*width;
	}
}

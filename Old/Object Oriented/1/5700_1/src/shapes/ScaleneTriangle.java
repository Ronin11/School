package shapes;

public class ScaleneTriangle extends Triangle {
	double length;
	
	public ScaleneTriangle(double width, double height, double length){
		this.width = width;
		this.height = height;
		this.length = length;
	}
	
	@Override
	public double getArea(){
		double s = (width+height+length)/2;
		double area = java.lang.Math.sqrt(s*(s-width)*(s-height)*(s-length));
		return area;
	}

}

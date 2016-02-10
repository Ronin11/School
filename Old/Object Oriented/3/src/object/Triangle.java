package object;

import java.awt.Color;
import java.awt.Polygon;

public class Triangle extends ObjectShape {
	   public Triangle(int x, int y, Color color){
		   super(x,y,color);
		   name = "Triangle";
	   }
	   public Triangle(){
		   name = "Triangle";
	   }
	   protected void buildShape(){
		   int[] xPoints = {x-(Math.round(baseSize*scale)/2), x, x+(Math.round(baseSize*scale)/2)};
		   int[] yPoints = {y+(Math.round(baseSize*scale)/2), y-(Math.round(baseSize*scale)/2), y+(Math.round(baseSize*scale)/2)};
		   shape = new Polygon(xPoints, yPoints, 3);
	   }
}

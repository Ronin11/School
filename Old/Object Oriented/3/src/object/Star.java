package object;

import java.awt.Color;
import java.awt.Polygon;

public class Star extends ObjectShape {
	   public Star(int x, int y, Color color){
		   super(x,y,color);
		   name = "Star";
	   }
	   public Star(){
		   name = "Star";
	   }
	   
	   protected void buildShape(){
		   int sides = 10;
		   final double difference = .4;
		   int[] xPoints = new int[sides];
		   int[] yPoints = new int[sides];
		   double theta = 2 * Math.PI / sides;
		   for (int i = 0; i < sides; i+=2) {
			   double offset = Math.PI / (sides/2);
		       yPoints[i] = (int) Math.round(Math.cos(theta * i + offset)*scale*baseSize)+y;
		       xPoints[i] = (int) Math.round(Math.sin(theta * i + offset)*scale*baseSize)+x;
		   }
		   for (int i = 1; i < sides; i+=2) {
			   double offset = Math.PI / (sides/2);
		       yPoints[i] = (int) Math.round(Math.cos(theta * i + offset)*scale*baseSize*difference)+y;
		       xPoints[i] = (int) Math.round(Math.sin(theta * i + offset)*scale*baseSize*difference)+x;
		   }
		   shape = new Polygon(xPoints, yPoints, sides);
	   }
}

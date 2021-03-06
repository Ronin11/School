package object;

import java.awt.Color;
import java.awt.Polygon;

public class Pentagon extends ObjectShape {
	   public Pentagon(int x, int y, Color color){
		   super(x,y,color);
		   name = "Pentagon";
	   }
	   public Pentagon(){
		   name = "Pentagon";
	   }

	   protected void buildShape(){
		   int sides = 5;
		   int[] xPoints = new int[sides];
		   int[] yPoints = new int[sides];
		   double theta = 2 * Math.PI / sides;
		   for (int i = 0; i < sides; ++i) {
			   double offset = Math.PI / sides;
		       yPoints[i] = (int) Math.round(Math.cos(theta * i + offset)*scale*baseSize)+y;
		       xPoints[i] = (int) Math.round(Math.sin(theta * i + offset)*scale*baseSize)+x;
		   }
		   shape = new Polygon(xPoints, yPoints, sides);
	   }

}

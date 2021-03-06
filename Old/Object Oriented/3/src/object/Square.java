package object;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Square extends ObjectShape {
	   
	   public Square(int x, int y, Color color){
		   super(x,y,color);
		   name = "Square";
	   }
	   public Square(){
		   name = "Square";
	   }

	   @Override
	   public void buildShape() {
		   shape = new Rectangle2D.Float(x-(Math.round(baseSize*scale)/2), y-(Math.round(baseSize*scale)/2),
		    		  Math.round(baseSize*scale), Math.round(baseSize*scale));
	   }
}

package object;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Circle extends ObjectShape {
	   
	   public Circle(int x, int y, Color color){
		   super(x,y,color);
		   this.name = "Circle";
	   }
	   public Circle(){
		   this.name = "Circle";
	   }
	   
	   protected void buildShape(){
		   shape = new Ellipse2D.Float(x-(Math.round(baseSize*scale)/2), y-(Math.round(baseSize*scale)/2),
		    		  Math.round(baseSize*scale), Math.round(baseSize*scale));
	   }
}

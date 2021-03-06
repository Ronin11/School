package object;

import java.awt.Color;
import java.awt.Polygon;

public class Octogon extends ObjectShape {
	   public Octogon(int x, int y, Color color){
		   super(x,y,color);
		   name = "Octogon";
	   }
	   public Octogon(){
		   name = "Octogon";
	   }
	   
	   protected void buildShape(){
		   int sides = 8;
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
/*
	   @Override
	   public void buildShape() {
		   g.setColor(color);
		   int constant = (int)Math.round(baseSize*scale*.8);
		   g.fillOval(x, y-constant, constant, constant);
		   g.fillOval(x-constant, y-constant, constant, constant);
		   int sides = 4;
		   int[] xPoints = new int[sides];
		   int[] yPoints = new int[sides];
		   xPoints[0] = x;
		   yPoints[0] = y-constant/2;
		   xPoints[1] = x-constant*9/10;
		   yPoints[1] = y-constant/5;
		   xPoints[2] = x;
		   yPoints[2] = y+constant;
		   xPoints[3] = x+constant*9/10;
		   yPoints[3] = yPoints[1];
		   g.fillPolygon(xPoints, yPoints, sides);
		   shape = new Ellipse2D.Float(x, y-constant, constant, constant);
	   }
	  
	   public void select(Graphics g){
		   if(color != Color.black)
			   g.setColor(Color.black);
		   else
			   g.setColor(Color.red);
		   
		   int constant = (int)Math.round(baseSize*scale*.8);
		   g.drawOval(x, y-constant, constant, constant);
		   g.drawOval(x-constant, y-constant, constant, constant);
		   int sides = 4;
		   int[] xPoints = new int[sides];
		   int[] yPoints = new int[sides];
		   xPoints[0] = x;
		   yPoints[0] = y-constant/2;
		   xPoints[1] = x-constant*9/10;
		   yPoints[1] = y-constant/5;
		   xPoints[2] = x;
		   yPoints[2] = y+constant;
		   xPoints[3] = x+constant*9/10;
		   yPoints[3] = yPoints[1];
		   g.drawPolygon(xPoints, yPoints, sides);
	   }
	   */
	}



package object;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class ObjectShape implements Cloneable{
	   protected Color color = Color.BLACK;
	   protected Shape shape;
	   protected int x = 0;
	   protected int y = 0;
	   protected float scale = 1;
	   final protected int baseSize = 25;
	   final protected int duplicateOffset = 10;
	   protected String name;
	   
	   public ObjectShape createCopy(){
		   try {
			return (ObjectShape) this.clone();
		   } catch (CloneNotSupportedException e) {
			   e.printStackTrace();
			   return null;
		   }
	   }
	   public boolean isInit(){return (shape != null);}
	   
	   public void draw(Graphics g){
		   if(shape == null)
			   buildShape();
		   Graphics2D g2 = (Graphics2D) g;
		   g2.setColor(color);
		   g2.fill(shape);
	   }
	   protected void drawEdge(Graphics g){
		   Graphics2D g2 = (Graphics2D) g;
		   if(color != Color.black)
			   g2.setColor(Color.black);
		   else
			   g2.setColor(Color.red);
		   g2.setStroke(new BasicStroke(3));
		   g2.draw(shape);
	   };
	   protected abstract void buildShape();
	   
	   public ObjectShape(int x, int y, Color color){
		   this.x = x;
		   this.y = y;
		   this.color = color;
	   }
	   public ObjectShape(){}
	   
	   public String getName(){return this.name;}
	   
	   public void setVals(int x, int y, Color color){
		   this.x = x;
		   this.y = y;
		   this.color = color;
	   }
	   
	   public void addDuplicateOffset(){
		   this.x += duplicateOffset;
		   this.y += duplicateOffset;
		   this.buildShape();
	   }
	   
	   public int getDuplicateOffset(){
		   return duplicateOffset;
	   }
	   
	   public void updatePos(int x, int y){
		   this.x = x;
		   this.y = y;
		   this.buildShape();
	   }
	   public int getX(){return x;}

	   public int getY(){return y;}
	   
	   public void setColor(Color color){this.color = color;}
	   public Color getColor(){return color;}
	   
	   public void setScale(float scale){this.scale = scale;}
	   //public int getScale(){return scale;}
	   
	   public boolean contains(int eX, int eY){
		   return (shape.contains(new Point(eX, eY)));
	   }
	   
	   public void select(Graphics g){
		   this.drawEdge(g);
	   }
	   
	   
	   public Element toXML(Document doc){
			Element shape = doc.createElement("Shape");
			
			shape.setAttribute("Name", name);

			Element red = doc.createElement("R");
			red.appendChild(doc.createTextNode(String.valueOf(this.color.getRed())));
			shape.appendChild(red);
			
			Element green = doc.createElement("G");
			green.appendChild(doc.createTextNode(String.valueOf(this.color.getGreen())));
			shape.appendChild(green);
			
			Element blue = doc.createElement("B");
			blue.appendChild(doc.createTextNode(String.valueOf(this.color.getBlue())));
			shape.appendChild(blue);

			Element xPos = doc.createElement("X");
			xPos.appendChild(doc.createTextNode(String.valueOf(x)));
			shape.appendChild(xPos);
			
			Element yPos = doc.createElement("Y");
			yPos.appendChild(doc.createTextNode(String.valueOf(y)));
			shape.appendChild(yPos);
			
			return shape;
	   }
}

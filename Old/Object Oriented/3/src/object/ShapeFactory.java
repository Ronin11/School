package object;

import java.awt.Color;
import java.util.HashMap;

public class ShapeFactory {
	//Not currently the flyweight pattern, but doesnt really add to anything

   public static ObjectShape makeCircle(int x, int y, Color color) {
      return new Circle(x,y,color);
   }
   public static ObjectShape makeCircle() {
	      return new Circle();
   }
   
   public static ObjectShape makeTriangle(int x, int y, Color color) {
	      return new Triangle(x,y,color);
   }
   public static ObjectShape makeTriangle() {
	      return new Triangle();
   }
   
   public static ObjectShape makeSquare(int x, int y, Color color) {
	      return new Square(x,y,color);
   }
   public static ObjectShape makeSquare() {
	      return new Square();
   }
   
   public static ObjectShape makePentagon(int x, int y, Color color) {
	      return new Pentagon(x,y,color);
   }
   public static ObjectShape makePentagon() {
	      return new Pentagon();
   }
   
   public static ObjectShape makeStar(int x, int y, Color color) {
	      return new Star(x,y,color);
   }
   public static ObjectShape makeStar() {
	      return new Star();
   }
   
   public static ObjectShape makeOctogon(int x, int y, Color color) {
	      return new Octogon(x,y,color);
   }
   public static ObjectShape makeOctogon() {
	      return new Octogon();
   }
   
   public static ObjectShape makeShape(String name, int x, int y, Color color){
	   switch(name){
	   		case "Circle":
	   			return new Circle(x,y,color);
	   		case "Square":
	   			return new Square(x,y,color);
	   		case "Triangle":
	   			return new Triangle(x,y,color);
	   		case "Pentagon":
	   			return new Pentagon(x,y,color);
	   		case "Octogon":
	   			return new Octogon(x,y,color);
	   		case "Star":
	   			return new Star(x,y,color);
	   		default:
	   			System.out.println("Somthing Bad!");
	   			return null;
	   }
   }
}

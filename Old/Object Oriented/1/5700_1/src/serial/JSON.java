package serial;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import shapes.*;

public class JSON {
	JSONParser parser = new JSONParser();
	
	public JSON(){}
	
	public void readObjects(ArrayList<Shape> list){
	     try{
	    	 FileReader fr = new FileReader("JSON.txt");
	    	 BufferedReader reader = new BufferedReader(fr);
	    	 while(true){
	    		String s =  reader.readLine();
	    		if(s == null)
	    			break;
	    		Object obj = parser.parse(s);
	    		
		        JSONArray array = new JSONArray();
		        array.add(obj);
		        JSONObject jObj = (JSONObject) array.get(0);
		        String name = (String)jObj.get("Name");
		        switch(name){
		        case "Circle":
		        	list.add(new Circle(((Long)jObj.get("Radius")).doubleValue()));
		        	break;
		        case "Ellipse":
		        	list.add(new Ellipse(((Long)jObj.get("Radius")).doubleValue(),
		        			((Long)jObj.get("OtherRadius")).doubleValue()));
		        	break;
		        case "Triangle":
		        	list.add(new Triangle(((Long)jObj.get("Width")).doubleValue(),
		        			((Long)jObj.get("Height")).doubleValue()));
		        	break;
		        case "ScaleneTriangle":
		        	list.add(new ScaleneTriangle(((Long)jObj.get("Width")).doubleValue(),
		        			((Long)jObj.get("Height")).doubleValue(),
		        			((Long)jObj.get("Length")).doubleValue()));
		        	break;
		        case "Square":
		        	list.add(new Square(((Long)jObj.get("Width")).doubleValue()));
		        	break;
		        case "Rectangle":
		        	list.add(new Rectangle(((Long)jObj.get("Width")).doubleValue(),
		        			((Long)jObj.get("Height")).doubleValue()));
		        	break;
		        }
	    	 }
	    	 reader.close();
	         
	      }catch(ParseException pe){
			
	         System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

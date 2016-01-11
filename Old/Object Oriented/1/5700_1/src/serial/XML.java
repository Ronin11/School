package serial;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import shapes.Circle;
import shapes.Ellipse;
import shapes.Rectangle;
import shapes.ScaleneTriangle;
import shapes.Shape;
import shapes.Square;
import shapes.Triangle;

import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


public class XML {
	
	public XML(){}
	
	public void readObjects(ArrayList<Shape> list){
		
	try {
		File fXmlFile = new File("XML.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
					
		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();
			
		NodeList nList = doc.getElementsByTagName("shape");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
						
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				
				String name = eElement.getAttribute("name");
				switch(name){
				case "Circle":
		        	list.add(new Circle(Double.parseDouble(eElement.getElementsByTagName("Radius").item(0).getTextContent())));
		        	break;
		        case "Ellipse":
		        	list.add(new Ellipse(Double.parseDouble(eElement.getElementsByTagName("Radius").item(0).getTextContent()),
		        			Double.parseDouble(eElement.getElementsByTagName("OtherRadius").item(0).getTextContent())));
		        	break;
		        case "Triangle":
		        	list.add(new Triangle(Double.parseDouble(eElement.getElementsByTagName("Width").item(0).getTextContent()),
		        			Double.parseDouble(eElement.getElementsByTagName("Height").item(0).getTextContent())));
		        	break;
		        case "ScaleneTriangle":
		        	list.add(new ScaleneTriangle(Double.parseDouble(eElement.getElementsByTagName("Width").item(0).getTextContent()),
		        			Double.parseDouble(eElement.getElementsByTagName("Height").item(0).getTextContent()),
		        			Double.parseDouble(eElement.getElementsByTagName("Length").item(0).getTextContent())));
		        	break;
		        case "Square":
		        	list.add(new Square(Double.parseDouble(eElement.getElementsByTagName("Width").item(0).getTextContent())));
		        	break;
		        case "Rectangle":
		        	list.add(new Rectangle(Double.parseDouble(eElement.getElementsByTagName("Width").item(0).getTextContent()),
		        			Double.parseDouble(eElement.getElementsByTagName("Height").item(0).getTextContent())));
		        	break;
				}
			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }
}

package io;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import object.ObjectShape;
import object.ShapeFactory;

import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class XML {
	
	public static void saveFile(File file, List<ObjectShape> shapes){
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element list = doc.createElement("Elements");
			doc.appendChild(list);
			
			for(ObjectShape shape : shapes)
				list.appendChild(shape.toXML(doc));
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result;
			if(file.getName().endsWith(".xml"))
				result = new StreamResult(file);
			else
				result = new StreamResult(new File(file.getAbsoluteFile()+".xml"));

			transformer.transform(source, result);
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  } catch (ParserConfigurationException e) {
		e.printStackTrace();
	  }
	}
	
	public static List<ObjectShape> readInObjects(File file){
		List<ObjectShape> objects = new ArrayList<ObjectShape>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
	
			doc.getDocumentElement().normalize();
				
			NodeList nList = doc.getElementsByTagName("Shape");
	
			for (int temp = 0; temp < nList.getLength(); temp++) {
	
				Node nNode = nList.item(temp);
							
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
					Element eElement = (Element) nNode;
					
					String name = eElement.getAttribute("Name");
					int x = Integer.parseInt(eElement.getElementsByTagName("X").item(0).getTextContent());
					int y = Integer.parseInt(eElement.getElementsByTagName("Y").item(0).getTextContent());
					int r = Integer.parseInt(eElement.getElementsByTagName("R").item(0).getTextContent());
					int g = Integer.parseInt(eElement.getElementsByTagName("G").item(0).getTextContent());
					int b = Integer.parseInt(eElement.getElementsByTagName("B").item(0).getTextContent());
					Color color = new Color(r,g,b);
					objects.add(ShapeFactory.makeShape(name, x, y, color));
				}
			}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
		return objects;
	  }
}

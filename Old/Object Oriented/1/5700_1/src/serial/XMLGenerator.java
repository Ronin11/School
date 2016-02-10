package serial;

import java.io.File;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLGenerator {
	public static void generate(){
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element list = doc.createElement("List");
			doc.appendChild(list);
			Random random = new Random();
			
			for(int i = -1; i < random.nextInt(10); i++){
				Element shape = doc.createElement("shape");
				list.appendChild(shape);
				shape.setAttribute("name", "Circle");
	
				Element radius = doc.createElement("Radius");
				radius.appendChild(doc.createTextNode(Integer.toString(random.nextInt(20))));
				shape.appendChild(radius);
			}
			
			for(int i = -1; i < random.nextInt(10); i++){
				Element shape = doc.createElement("shape");
				list.appendChild(shape);
				
				shape.setAttribute("name", "Ellipse");

				Element radius = doc.createElement("Radius");
				radius.appendChild(doc.createTextNode(Integer.toString(random.nextInt(20))));
				shape.appendChild(radius);
				Element otherRadius = doc.createElement("OtherRadius");
				otherRadius.appendChild(doc.createTextNode(Integer.toString(random.nextInt(20))));
				shape.appendChild(otherRadius);
			}
			
			for(int i = -1; i < random.nextInt(10); i++){
				Element shape = doc.createElement("shape");
				list.appendChild(shape);
				
				shape.setAttribute("name", "Square");

				Element width = doc.createElement("Width");
				width.appendChild(doc.createTextNode(Integer.toString(random.nextInt(20))));
				shape.appendChild(width);
			}
			
			for(int i = -1; i < random.nextInt(10); i++){
				Element shape = doc.createElement("shape");
				list.appendChild(shape);
		
				shape.setAttribute("name", "Rectangle");

				Element width = doc.createElement("Width");
				width.appendChild(doc.createTextNode(Integer.toString(random.nextInt(20))));
				shape.appendChild(width);
				
				Element height = doc.createElement("Height");
				height.appendChild(doc.createTextNode(Integer.toString(random.nextInt(20))));
				shape.appendChild(height);
			}
			
			for(int i = -1; i < random.nextInt(10); i++){
				Element shape = doc.createElement("shape");
				list.appendChild(shape);
		
				shape.setAttribute("name", "Triangle");

				Element width = doc.createElement("Width");
				width.appendChild(doc.createTextNode(Integer.toString(random.nextInt(20))));
				shape.appendChild(width);
				
				Element height = doc.createElement("Height");
				height.appendChild(doc.createTextNode(Integer.toString(random.nextInt(20))));
				shape.appendChild(height);
			}
			
			for(int i = -1; i < random.nextInt(10); i++){
				Element shape = doc.createElement("shape");
				list.appendChild(shape);
				
				shape.setAttribute("name", "ScaleneTriangle");

				Element width = doc.createElement("Width");
				width.appendChild(doc.createTextNode(Integer.toString(3)));
				shape.appendChild(width);
				
				Element height = doc.createElement("Height");
				height.appendChild(doc.createTextNode(Integer.toString(6)));
				shape.appendChild(height);
				
				Element length = doc.createElement("Length");
				length.appendChild(doc.createTextNode(Integer.toString(7)));
				shape.appendChild(length);
			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("XML.xml"));

			// Output to console for testing
			//StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
	}
}

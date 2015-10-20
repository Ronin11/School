package test;

import java.io.File;
import java.util.ArrayList;

import org.junit.*;

import serial.*;
import shapes.Shape;
import main.Main;

public class UnitTest{
		
	@Test
	public void unitTest(){
		ArrayList<Shape> list = new ArrayList<Shape>();
		
		//Test xml stuff
		XMLGenerator.generate();
		XML xml = new XML();
		xml.readObjects(list);
		File xmlfile = new File("xmlOut.csv");
		Main.zhuLiDoTheThing(xmlfile.getName(), list);
		xmlfile.delete();
		
		list.clear();
			
		//Test json stuff
		JSONGenerator.generate();
		JSON json = new JSON();
		json.readObjects(list);
		File jsonfile = new File("jsonOut.csv");
		Main.zhuLiDoTheThing(jsonfile.getName(), list);
		jsonfile.delete();	
	}
}
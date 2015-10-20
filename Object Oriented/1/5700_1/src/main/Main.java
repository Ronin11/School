package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import junit.framework.Test;
import junit.framework.TestCase;
import serial.JSON;
import serial.JSONGenerator;
import serial.XML;
import serial.XMLGenerator;
import shapes.*;

public class Main extends JPanel implements ActionListener {
	
	String loadName;
	String outName;
	
	public Main(){
		super(new BorderLayout());
		
		JButton dataIn = new JButton("Select Data Input");
		dataIn.addActionListener(this);
		add(dataIn, BorderLayout.NORTH);
		
		JButton dataOut = new JButton("Select Data Output");
		dataOut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			    //Set up the file chooser.
		        JFileChooser save = new JFileChooser();
		 
		        //Show it.
		        int returnVal = save.showSaveDialog(Main.this);
		 
		        //Process the results.
		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		            String name = save.getSelectedFile().toString();
		            if (!name.endsWith(".csv"))
		                name += ".csv";
		            outName = name;
		        }
			}		
		});
		add(dataOut, BorderLayout.CENTER);
		
		JButton run = new JButton("Run Program");
		run.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Shape> list = new ArrayList<Shape>();
				if(loadName.endsWith(".xml")){
					XML xml = new XML();
					xml.readObjects(list);
					zhuLiDoTheThing(outName, list);
				}
				else if(loadName.endsWith(".txt")){
					JSON json = new JSON();
					json.readObjects(list);
					zhuLiDoTheThing(outName,list);
				}
				else{
					System.out.println("Somthing bad happened.");
				}
			}		
		});
		add(run, BorderLayout.SOUTH);
		
	}
	
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CS5700 #1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add content to the window.
        frame.add(new Main());
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
	public static void zhuLiDoTheThing(String filename, ArrayList<Shape> list){
		double totalArea = 0;
		double totalCircleShapeArea = 0;
		double totalCircleArea = 0;
		double totalEllipseArea = 0;
		double totalConvexArea = 0;
		double totalTriangleShapeArea= 0;
		double totalTriangleArea = 0;
		double totalScaleneTriangleArea = 0;
		double totalSquareShapeArea = 0;
		double totalSquareArea = 0;
		double totalRectangleArea = 0;
		
		for(Shape s : list){
			double temp = s.getArea();
			totalArea += temp;
			if(s instanceof Circle){
				totalCircleShapeArea += temp;
				if(s instanceof Ellipse)
					totalEllipseArea += temp;
				else
					totalCircleArea += temp;
			}
			else if(s instanceof Square){
				totalSquareShapeArea += temp;
				totalConvexArea += temp;
				if(s instanceof Rectangle)
					totalRectangleArea += temp;
				else
					totalSquareArea += temp;
			}
			else if(s instanceof Triangle){
				totalConvexArea += temp;
				totalTriangleShapeArea += temp;
				if(s instanceof ScaleneTriangle)
					totalScaleneTriangleArea += temp;
				else
					totalTriangleArea += temp;
			}
		}
		
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream(filename), "utf-8"));
			
    	    writer.write("Total area of all shapes:,,,,,,,"+totalArea+'\n');
    	    writer.write(",Ellipses:,,,,,"+totalCircleShapeArea+'\n');
    	    writer.write(",,Circles:,,,"+totalCircleArea+'\n');
    	    writer.write(",,Non-circle Ellipses:,,,"+totalEllipseArea+'\n');
    	    writer.write(",Convex Polygons:,,,,,"+totalConvexArea+'\n');
    	    writer.write(",,Triangles:,,,"+totalTriangleShapeArea+'\n');
    	    writer.write(",,,Isosceles:,,"+totalTriangleArea+'\n');
    	    writer.write(",,,Scalene:,,"+totalScaleneTriangleArea+'\n');
    	    writer.write(",,Rectangles:,,,,"+totalSquareShapeArea+'\n');
    	    writer.write(",,,Squares:,,"+totalSquareArea+'\n');
    	    writer.write(",,,Rectangles:,,"+totalRectangleArea+'\n');
    	    
    	    writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	    //Set up the file chooser.
        JFileChooser load = new JFileChooser();
 
        //Add a custom file filter and disable the default
        //(Accept All) file filter.
        load.addChoosableFileFilter(new FileNameExtensionFilter("xml", "txt"));
        load.setAcceptAllFileFilterUsed(false);
 
        //Show it.
        int returnVal = load.showDialog(Main.this,
                                      "Open file for parsing");
 
        //Process the results.
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = load.getSelectedFile();
        }
        load.setSelectedFile(null);
		
	}
	
	public static void main(String[] args) {
	     SwingUtilities.invokeLater(new Runnable() {
	         public void run() {
	             UIManager.put("swing.boldMetal", Boolean.FALSE);
	             createAndShowGUI();
	         }
	     });
		
	}
}

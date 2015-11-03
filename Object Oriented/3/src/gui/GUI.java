package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import command.ChangeColorCommand;
import command.DeleteCommand;
import command.DuplicateCommand;
import io.OpenFileFilter;
import io.XML;
import object.*;

public class GUI {

	private JPanel panel;
	private DrawingCanvas canvas;	
	
	private JMenuBar buildMenuBar(JFrame frame){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		JMenu edit = new JMenu("Edit");
		JMenu help = new JMenu("Help");
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(help);
		
		JMenuItem helpMenu = new JMenuItem("Help Menu");
		help.add(helpMenu);
		
		helpMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new JPanel();
				temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
				temp.add(new JLabel("Most commands are self explanatory, and shortcuts are listed in the Edit Menu."));
				temp.add(new JLabel("To create an object, click the button on the left side, and then click where you want the object created."));
				temp.add(new JLabel("The button must be clicked every time before and object is created."));
				temp.add(new JLabel("To select an object, click an existing object."));
				temp.add(new JLabel("To deselect an object, click another existing object, or undo with the edit command or Ctrl-Z"));
				temp.add(new JLabel("To change color, select the desired color by clicking the color button at the top, and select the existing object then using the change color command."));
				temp.add(new JLabel("To move an object, select the object, then click and drag it."));
				temp.add(new JLabel("To delete an object, use the delete key or the edit menu."));
				temp.add(new JLabel("To change the background color, use the File Menu."));
				JOptionPane.showMessageDialog(frame, temp);
			}
		});
		
		JMenuItem createCanvas = new JMenuItem("Create New Canvas");
		file.add(createCanvas);
		createCanvas.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color color = null;
				if(canvas == null)
				{
				     
				     while(color == null)
				    	 color = JColorChooser.showDialog(null, "Choose desired canvas color", null);
					setup(color);
					frame.pack();
				}
				else{
				     while(color == null)
				    	 color = JColorChooser.showDialog(null, "Choose desired canvas color", null);
				     canvas.setBackground(color);
				}
					
			}
		});
		JMenuItem changeCanvasBackground = new JMenuItem("Change Canvas Background");
		file.add(changeCanvasBackground);
		changeCanvasBackground.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(canvas == null)
					return;
				Color color = null;
				color = JColorChooser.showDialog(null, "Choose desired canvas color", null);
				if(color != null)
					canvas.setBackground(color);
			}
		});
		
		JMenuItem saveAs = new JMenuItem("Save as...");
		file.add(saveAs);
		saveAs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(canvas == null)
					return;
				JFileChooser chooser = new JFileChooser();
				OpenFileFilter pngFilter = new OpenFileFilter("png","PNG - Photo in PNG format");
				OpenFileFilter xmlFilter = new OpenFileFilter("xml","XML - Re-Editable File");
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.addChoosableFileFilter(pngFilter);
				chooser.addChoosableFileFilter(xmlFilter);
				if(JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(frame)){
					File file = chooser.getSelectedFile();
					System.out.println(file.getName());
					if(chooser.getFileFilter() == pngFilter)
						printImage(file);
					else if(chooser.getFileFilter() == xmlFilter){
						XML.saveFile(file, canvas.getShapes());
					}
				}
  
			}
		});
		
		JMenuItem importFile = new JMenuItem("Import");
		file.add(importFile);
		importFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.addChoosableFileFilter(new OpenFileFilter("xml","XML - Re-Editable File") );
				if(JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(frame)){
					if(canvas == null)
					{
						Color color = null;
						while(color == null)
					    	 color = JColorChooser.showDialog(null, "Choose desired canvas color", null);
						setup(color);
						frame.pack();
					}
					File file = chooser.getSelectedFile();
					canvas.addShapes(XML.readInObjects(file));
				}
			}
		});
		
		
		JMenuItem undo = new JMenuItem("Undo - Ctrl+Z");
		edit.add(undo);
		undo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(canvas == null)
					return;
				canvas.getInvoker().undo();
			}
		});
		
		JMenuItem duplicate = new JMenuItem("Duplicate - Ctrl + D");
		edit.add(duplicate);
		duplicate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(canvas == null)
					return;
				ObjectShape shape = canvas.getCurrentShape();
				if(shape != null)
					canvas.getInvoker().addCommand(new DuplicateCommand(shape, canvas));
			}
		});
		
		JMenuItem delete = new JMenuItem("Delete - delete");
		edit.add(delete);
		delete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(canvas == null)
					return;
				ObjectShape shape = canvas.getCurrentShape();
				if(shape != null)
					canvas.getInvoker().addCommand(new DeleteCommand(shape, canvas));
			}
		});
		
		JMenuItem changeColor = new JMenuItem("Change Color - Ctrl+R");
		edit.add(changeColor);
		changeColor.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(canvas == null)
					return;
				ObjectShape shape = canvas.getCurrentShape();
				if(shape != null){
					ChangeColorCommand cmd = new ChangeColorCommand(shape, canvas);
					cmd.setColor(canvas.getCurrentColor());
					canvas.getInvoker().addCommand(cmd);
				}
			}
		});
		
		return menuBar;
	}
	
	private JButton makeButton(ObjectShape shape){
		JButton temp = new JButton(shape.getName());
		temp.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas.setCurrentShape(shape.createCopy());
				canvas.setCreate(true);
			}
		});
		return temp;
	}
	
	private void setup(Color background){
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		canvas = new DrawingCanvas(background);
		
		c.gridx = 1;
		c.gridy = 0;
		panel.add(new JLabel("Current Color: "), c);
		
		c.gridx = 2;
		c.gridy = 0;
		JButton colorButton = new JButton();
		colorButton.setBackground(canvas.getCurrentColor());
		colorButton.setPreferredSize(new Dimension(40,25));
		panel.add(colorButton, c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel.add(makeButton(new Circle()), c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel.add(makeButton(new Triangle()), c);
		
		c.gridx = 0;
		c.gridy = 3;
		panel.add(makeButton(new Square()), c);
		
		c.gridx = 0;
		c.gridy = 4;
		panel.add(makeButton(new Pentagon()), c);
		
		c.gridx = 0;
		c.gridy = 5;
		panel.add(makeButton(new Octogon()), c);
		
		c.gridx = 0;
		c.gridy = 6;
		panel.add(makeButton(new Star()), c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 7;
		c.gridwidth = 4;
		panel.add(canvas, c);
		
		colorButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			     Color color = JColorChooser.showDialog(null, "Choose desired color", null);
			     if(color != null){
			    	 colorButton.setBackground(color);
			     	canvas.setCurrentColor(color);
			     }
			}
		});
	}
	
	private void printImage(File file){
		BufferedImage image = new  BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
	    try {
	    	Graphics g = image.getGraphics();
	    	canvas.update(g);
	    	OutputStream out = new FileOutputStream(file.getAbsolutePath()+".png");
	        ImageIO.write(image, "png", out);
	        out.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}
	
	
	
    public void createAndShowGUI(){
        //Create and set up the window.
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) {
		            e.printStackTrace();
		 }
        JFrame frame = new JFrame("Best Drawing Program Ever");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(400,200));
        
        frame.setJMenuBar(buildMenuBar(frame));
 

		panel = new JPanel();
        frame.getContentPane().add(panel);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}

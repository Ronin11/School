package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import command.DeleteCommand;

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
		
		JMenuItem createCanvas = new JMenuItem("Create New Canvas");
		file.add(createCanvas);
		createCanvas.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//createCanvas();
			}
		});
		
		JMenuItem saveAs = new JMenuItem("Save as...");
		file.add(saveAs);
		saveAs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//saveAs();
			}
		});
		
		JMenuItem importFile = new JMenuItem("Import");
		file.add(importFile);
		importFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//export();
			}
		});
		
		
		JMenuItem undo = new JMenuItem("Undo - Ctrl+Z");
		edit.add(undo);
		undo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				canvas.getInvoker().undo();
			}
		});
		
		JMenuItem duplicate = new JMenuItem("Duplicate - Ctrl + D");
		edit.add(duplicate);
		duplicate.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//createCanvas();
			}
		});
		
		JMenuItem delete = new JMenuItem("Delete - delete");
		edit.add(delete);
		delete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
				//createCanvas();
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
	
	private void setup(){
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		canvas = new DrawingCanvas();
		
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
		setup();
        frame.getContentPane().add(panel);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}

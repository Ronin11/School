package gui;

import io.BoardExporter;

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

import main.SudokuSolver;
import sudoku.Board;
import sudoku.Cell;


public class GUI {

	private JPanel panel;
	private JFrame frame;
	private final Object[] solvers = { "Brute Force", "Crooks", "Stochastic"};
	private SudokuSolver solver;
	
	private JMenuBar buildMenuBar(JFrame frame){
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		menuBar.add(file);
		menuBar.add(help);
		
		JMenuItem helpMenu = new JMenuItem("Help Menu");
		help.add(helpMenu);
		
		helpMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanel temp = new JPanel();
				temp.setLayout(new BoxLayout(temp, BoxLayout.Y_AXIS));
				temp.add(new JLabel("Things should be pretty self explanatory."));
				JOptionPane.showMessageDialog(frame, temp);
			}
		});
		
		
		JMenuItem selectSolver = new JMenuItem("Select Solver");
		file.add(selectSolver);
		selectSolver.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(solver == null)
					return;
				String s = (String)JOptionPane.showInputDialog(
				                    frame,
				                    "Please select the solver you want to use.",
				                    "Select Solver",
				                    JOptionPane.PLAIN_MESSAGE,
				                    null,
				                    solvers,
				                    null);

				//If a string was returned, say so.
				if ((s != null) && (s.length() > 0)) {
					solver.setSolver(s);
					if(solver.solve()){
						clearGUI();
						setup(solver.getBoard());
						solved();
					}
					else
						notSolvable();
				}

				//If you're here, the return value was null/empty.
			}
		});
		
		JMenuItem saveAs = new JMenuItem("Save as...");
		file.add(saveAs);
		saveAs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				if(JFileChooser.APPROVE_OPTION == chooser.showSaveDialog(frame)){
					File file = chooser.getSelectedFile();
					BoardExporter.export(file, solver.getBoard());
				}
			}
 
		});
		
		JMenuItem importFile = new JMenuItem("Load");
		file.add(importFile);
		importFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clearGUI();
				frame.pack();
				JFileChooser chooser = new JFileChooser();
				if(JFileChooser.APPROVE_OPTION == chooser.showOpenDialog(frame)){
					File file = chooser.getSelectedFile();
					solver = new SudokuSolver(file);
					setup(solver.getBoard());
				}
			}
		});
		
		return menuBar;
	}
	
	private JButton makeCell(Cell cell){
		String s = "";
		if(cell.getChar() != '-')
			s += cell.getChar();
		else
			s = " ";
		JButton temp = new JButton(s);
		temp.setEnabled(false);
		return temp;
	}
	
	private void setup(Board b){
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,5,5,5);
		
		for(int i = 0; i < b.size(); i++)
			for(int j = 0; j < b.size(); j++){
				c.gridx = j;
				c.gridy = i;
				panel.add(makeCell(b.getCell(i, j)), c);	
			}
		frame.pack();
	}
	
	private void clearGUI(){
		panel.removeAll();
	}
	
	public void notSolvable(){
		JOptionPane.showMessageDialog(frame,
			    "Board is not solvable with the current solver.",
			    "Board error",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void solved(){
		JOptionPane.showMessageDialog(frame,
			    "Board is solved, displaying solution.",
			    "Solved",
			    JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
    public void createAndShowGUI(){
        //Create and set up the window.
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) {
		            e.printStackTrace();
		 }
        frame = new JFrame("Best Sudoku Program Ever");
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

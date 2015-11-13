package main;

import java.io.File;

import gui.GUI;
import io.BoardImporter;
import sudoku.Board;
import sudoku.Cell;
import solver.*;

public class Main {

	public static void main(String[] args) {
		GUI gui = new GUI();
		//Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui.createAndShowGUI();
            }
        });
		/*
		SudokuSolver solver = new SudokuSolver(new File("SamplePuzzles/Puzzle-4x4-0001.txt"));
		if(solver.solve())
			System.out.println(solver.getBoard().toString());
		else
			System.out.println("Board is unsolvable.");
			*/
	}

}

package main;

import java.io.File;

import solver.*;
import sudoku.*;
import io.*;

public class SudokuSolver {
	private BoardImporter importer;
	private Board board;
	private Solver solver;
	
	public Board getBoard(){return board;}
	public void setSolver(Solver s){solver = s;}
	
	public SudokuSolver(File file){
		importer = new BoardImporter(file);
		board = importer.getBoard();
		solver = new StochasticSolver(board);
	}
	
	public boolean solve(){
		if(solver.solve())
			return true;		
		else
			return false;
	}

}

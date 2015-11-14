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
	public void setSolver(String s){
		switch(s){
			case "Stochastic":
				solver = new StochasticSolver(board);
				break;
			case "Brute Force":
				solver = new BruteForceSolver(board);
				break;
			case "Crooks":
				solver = new CrooksSolver(board);
				break;
			default:
				System.out.println("BAD");
		}
	}
	
	public SudokuSolver(File file){
		importer = new BoardImporter(file);
		board = importer.getBoard();
		//solver = new StochasticSolver(board);
	}
	
	public boolean solve(){
		if(solver.solve()){
			board = solver.getBoard();
			return true;	
		}
		else
			return false;
	}

}

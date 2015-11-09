package main;

import java.io.File;

import io.BoardImporter;
import sudoku.Board;
import sudoku.Cell;
import solver.*;

public class Main {

	public static void main(String[] args) {
		BoardImporter importer = new BoardImporter(new File("SamplePuzzles/Puzzle-4x4-0001.txt"));
		Board b = importer.getBoard();
		Solver solver = new StochasticSolver(b);
		System.out.println(solver.solve().toString());
		
	}

}

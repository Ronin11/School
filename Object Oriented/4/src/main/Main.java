package main;

import java.io.File;
import io.Import;
import sudoku.Board;

public class Main {

	public static void main(String[] args) {
		Import importer = new Import(new File("test.txt"));
		Board b = importer.nextBoard();
		System.out.println(b.toString());
		b = importer.nextBoard();
		System.out.println(b.toString());
	}

}

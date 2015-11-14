package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import org.junit.Test;

import sudoku.Board;
import solver.*;
import io.*;

public class Tests {
	File testFile = new File("src/test/Puzzle-4x4-0001.txt");
	
	//Verify that an import then export to file produces the exact same file.
	@Test
	public void testIO(){
		
		BoardImporter importer = new BoardImporter(testFile);
		File temp = new File("src/test/test");
		BoardExporter.export(temp, importer.getBoard());
		byte[] f1 = null;
		byte[] f2 = null;
		try {
			f1 = Files.readAllBytes(testFile.toPath());
			f2 = Files.readAllBytes(temp.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		assertTrue(Arrays.equals(f1, f2));
		temp.delete();
	}
	
	//Tests basic solver functions, and BruteForceSolver.
	@Test
	public void testSolver(){
		BoardImporter importer = new BoardImporter(testFile);
		Board b = importer.getBoard();
		Solver s = new BruteForceSolver(b);
		assertTrue(s.isValid());
		assertTrue(s.solve());
	}
}

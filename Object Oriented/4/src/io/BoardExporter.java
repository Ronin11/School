package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import sudoku.Board;

public class BoardExporter {
	
	public static void export(File file, Board board){
		PrintWriter writer = null;
		try {
			 writer = new PrintWriter(file);
			 writer.write(Integer.toString(board.size()) + '\n');
			 for(Character c : board.getAvailableChars())
				 writer.write(c + " ");
			 writer.write('\n' + board.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}

}

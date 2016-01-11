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
			 writer.write(Integer.toString(board.size()) + "\r\n");
			 for(int i = 0; i < board.getAvailableChars().size(); i++){
				 	writer.write(board.getAvailableChars().get(i));
				 	if(i+1 < board.getAvailableChars().size())
				 		writer.write(" ");
			 }
			 writer.write("\r\n" + board.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}

}

package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import sudoku.Board;

public class BoardImporter {
	private File file;
	private Scanner scan;
	
	public BoardImporter(File file){
		this.file = file;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Board getBoard(){
		int size = this.boardSize();
		ArrayList<Character> chars = this.getBoardChars();
		ArrayList<String> puzzle = new ArrayList<String>();
		for(int i = 0; i < size; i++)
			puzzle.add(scan.nextLine());
		return new Board(size, chars, puzzle);
	}
	
	private int boardSize(){
		int temp = 0;
		if(scan.hasNextLine())
			temp = Integer.parseInt(scan.nextLine());
		return temp;
			
	}
	private ArrayList<Character> getBoardChars(){
		ArrayList<Character> temp = new ArrayList<Character>();
		String s = scan.nextLine();
		for(char c : s.toCharArray())
			if(c != ' ')
				temp.add(c);
		return temp;
	}
}

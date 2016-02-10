package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import sudoku.*;

public abstract class Solver {
	protected Board board;
	public abstract boolean solve();
	
	protected Solver(Board b){
		board = b;
	}
	
	protected boolean isSolved(){
		return isSolved(this.board);
	}
	
	protected boolean isSolved(Board b){
		Iterator iterator = b.getIterator();
		while(iterator.hasNext())
			if(iterator.next().getChar() == '-')
				return false;
		return this.isValid();
	}
	public boolean isValid(){
		return isValid(this.board);
	}
	
	public boolean isValid(Board b){
		if(b.unsolvable())
			return false;
		for(int i = 0; i < b.size(); i++)
			if(!(this.isRowValid(b, i) && this.isColValid(b, i) && this.isBoxValid(b, i)))
				return false;
		return true;
	}
	
	public Board getBoard(){return board;}
	
	protected boolean isInsertionValid(Cell cell){
		ArrayList<Character> rowCheck = board.getRow(cell);
		rowCheck.add(cell.getChar());
		ArrayList<Character> colCheck = board.getCol(cell);
		colCheck.add(cell.getChar());
		ArrayList<Character> boxCheck = board.getBox(cell);
		boxCheck.add(cell.getChar());
		
		return (this.isListValid(rowCheck) &&
				this.isListValid(colCheck) &&
				this.isListValid(boxCheck));
	}
	
	private boolean isListValid(ArrayList<Character> list){
		list.removeAll(Collections.singleton('-'));
		Set<Character> set = new HashSet<Character>(list);
		//If there are duplicate characters, the set will combine them into 1 element
		return !(set.size() < list.size());
	}
	/*
	protected boolean isRowValid(int row){
		return this.isListValid(board.getRow(row));
	}
	
	protected boolean isColValid(int col){
		return this.isListValid(board.getCol(col));
	}

	protected boolean isBoxValid(int box){
		return this.isListValid(board.getBox(box));
	}	
	*/
	protected boolean isRowValid(Board b,int row){
		return this.isListValid(b.getRow(row));
	}
	
	protected boolean isColValid(Board b, int col){
		return this.isListValid(b.getCol(col));
	}

	protected boolean isBoxValid(Board b, int box){
		return this.isListValid(b.getBox(box));
	}	
}

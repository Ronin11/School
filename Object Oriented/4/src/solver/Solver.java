package solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import sudoku.*;

public abstract class Solver {
	protected Board board;
	public abstract Board solve();
	
	protected Solver(Board b){
		board = b;
	}
	
	protected boolean isSolved(){
		Iterator iterator = board.getIterator();
		while(iterator.hasNext())
			if(iterator.next().getChar() == '-')
				return false;
		return this.isValid();
	}
	
	protected boolean isValid(){
		for(int i = 0; i < board.size(); i++)
			if(!(this.isRowValid(i) && this.isColValid(i) && this.isBoxValid(i)))
				return false;
		return true;
	}
	
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
	
	protected boolean isRowValid(int row){
		return this.isListValid(board.getRow(row));
	}
	
	protected boolean isColValid(int col){
		return this.isListValid(board.getCol(col));
	}

	protected boolean isBoxValid(int box){
		return this.isListValid(board.getBox(box));
	}	
}
package solver;

import java.util.ArrayList;

import sudoku.Board;
import sudoku.Cell;
import sudoku.Iterator;

public class CrooksSolver extends Solver {
	private final int limit = 1000;
	static int iterations = 0;

	public CrooksSolver(Board b) {
		super(b);
	}

	@Override
	public boolean solve() {
		while(!isSolved()){
			if(iterations++ > limit)
				return false;
			setBoardPossibilities();
			setBoardOnlyPossibilities();
		}
		return true;
	}
	
	private void setBoardPossibilities(){
		Iterator iterator = board.getIterator();
		while(iterator.hasNext()){
			Cell cell = iterator.next();
			if(cell.getChar() == '-')
				this.setPossibilities(cell);
		}
	}
	
	private void setBoardOnlyPossibilities(){
		Iterator iterator = board.getIterator();
		while(iterator.hasNext()){
			Cell cell = iterator.next();
			if(cell.getChar() == '-')
				setOnlyPossibility(cell);
		}
	}
	
	private void setPossibilities(Cell cell){
		ArrayList<Character> temp = new ArrayList<Character>();
		for(Character c : board.getAvailableChars()){
			cell.setChar(c);
			if(this.isInsertionValid(cell))
				temp.add(c);
		}
		cell.clear();
		cell.getPossibilities().addAll(temp);
	}
	
	private void setOnlyPossibility(Cell cell){
		ArrayList<Cell> list = new ArrayList<Cell>();
		list.addAll(board.getBoxCells(cell));
		list.addAll(board.getColCells(cell));
		list.addAll(board.getRowCells(cell));
		ArrayList<Character> charList = new ArrayList<Character>();
		for(Cell c : list){
			if(c.getChar() == '-')
				charList.addAll(c.getPossibilities());
			else
				charList.add(c.getChar());
		}
		
		for(Character c : cell.getPossibilities()){
			if(!charList.contains(c))
				cell.setChar(c);
		}
	}
	

}

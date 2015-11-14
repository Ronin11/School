package solver;

import sudoku.Board;
import sudoku.Cell;

public class BruteForceSolver extends Solver{

	//This Solver will solve anything, but it's definitely not the fastest, or most efficient.
	public BruteForceSolver(Board b) {
		super(b);
	}

	@Override
	public boolean solve() {
		if(!isValid())
			return false;
		board = createNext(board);
		if(board == null)
			return false;
		return true;
	}
	
	//Recursive call creates all possible boards, and quits the tree when developing an invalid board.
	private Board createNext(Board b){
		if(isSolved(b))
			return b;
		else if(!isValid(b))
			return null;
		for(int i = 0; i < b.getAvailableChars().size(); i++){	
			Board temp = createNext(insertIntoNextCell(b, b.getAvailableChars().get(i)));
			if(temp != null)
				return temp;
		}
		return null;
	}
	
	private Board insertIntoNextCell(Board b, Character character){
		if(b == null)
			return null;
		Board temp = new Board(b);
		for(Cell c : temp.getAllCells()){
			if(c.getChar() == '-'){
				c.setChar(character);
				return temp;
			}
		}
		return null;
	}
}

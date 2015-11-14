package solver;

import java.util.Random;

import sudoku.*;

public class StochasticSolver extends Solver {
	private Board startingBoard;
	private Random random;
	private final int limit = 1000000;
	private int iteration = 0;
	
	public StochasticSolver(Board b){
		super(b);
		random = new Random();
		//Create Copy
		startingBoard = new Board(board);
	}
	
	@Override
	public boolean solve() {
		if(!isValid())
			return false;
		//Create Copy
		startingBoard = new Board(board);
		while(!this.isSolved()){
			if(iteration++ > limit)
				return false;
			board = new Board(startingBoard);
			shuffle();
		}
		return true;
	}
	
	private void shuffle(){
		Iterator iterator = board.getIterator();
		while(iterator.hasNext()){
			Cell temp = iterator.next();
			if(temp.getChar() == '-')
				temp.setChar(board.getAvailableChars().get(Math.abs(random.nextInt()%board.getAvailableChars().size())));
		}
				
	}

}

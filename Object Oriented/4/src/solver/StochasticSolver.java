package solver;

import java.util.Random;

import sudoku.*;

public class StochasticSolver extends Solver {
	private Board startingBoard;
	private Random random;
	
	public StochasticSolver(Board b){
		super(b);
		random = new Random();
	}
	
	@Override
	public Board solve() {
		//Create Copy
		startingBoard = new Board(board);
		while(!this.isSolved()){
			board = new Board(startingBoard);
			shuffle();
		}
		return board;
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

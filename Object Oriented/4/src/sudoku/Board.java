package sudoku;

import java.util.ArrayList;

public class Board implements SudokuContainer{
	private ArrayList<ArrayList<Character>> board;
	private int size;
	

	public Board(int size, ArrayList<String> list){
		this.size = size;
		board = new ArrayList<ArrayList<Character>>();
		for(int i = 0; i < size; i++){
			board.add(new ArrayList<Character>());
			String[] temp = list.get(i).split(" ");
			for(int j = 0; j < size; j++){
				board.get(i).add(temp[j].charAt(0));
			}
		}
		
	}
	
	public void build(){
		
	}
	
	public String toString(){
		String temp = "";
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				temp += board.get(i).get(j);
			}
			temp += '\n';
		}
		return temp;
	}
	
	@Override
	public Iterator getIterator() {
		return new SudokuIterator();
	}
	
	private class SudokuIterator implements Iterator{
		int xIndex, yIndex;
		SudokuIterator(){
			xIndex = 0;
			yIndex = 0;
		}
		@Override
		public boolean hasNext() {
			if(xIndex < board.size())
				if(yIndex < board.get(0).size())
					return true;
			return false;
		}

		@Override
		public Character next() {
			if(this.hasNext()){
				Character temp = board.get(yIndex).get(xIndex);
				if(xIndex + 1 < board.size())
					xIndex++;
				else{
					yIndex++;
					xIndex = 0;
				}
				return temp;
			}
			return null;
		}
	}
}
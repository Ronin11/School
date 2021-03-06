package sudoku;

import java.util.ArrayList;
import java.util.Observable;

public class Board extends Observable implements SudokuContainer{
	private ArrayList<ArrayList<Cell>> board;
	private ArrayList<Character> availableChars;
	private int size;
	protected boolean unsolvable = false;
	

	public Board(int size, ArrayList<Character>chars, ArrayList<String> list){
		try{
			this.size = size;
			availableChars = chars;
			board = new ArrayList<ArrayList<Cell>>();
			for(int i = 0; i < size; i++){
				board.add(new ArrayList<Cell>());
				String[] temp = list.get(i).split(" ");
				for(int j = 0; j < size; j++){
					board.get(i).add(new Cell(temp[j].charAt(0), i, j));
				}
			}
		} catch (IndexOutOfBoundsException e){
			unsolvable = true;
		}
	}
	
	public Board(Board b){
		this.size = b.size();
		this.availableChars = b.getAvailableChars();
		board = new ArrayList<ArrayList<Cell>>();
		for(int i = 0; i < size; i++){
			board.add(new ArrayList<Cell>());
			for(int j = 0; j < size; j++){
				board.get(i).add(new Cell(b.getCell(i,j).getChar(), i, j));
			}
		}
	}
	
	public int size(){return size;}
	public boolean unsolvable(){return unsolvable;}
	public ArrayList<Character> getAvailableChars(){return availableChars;}
	public ArrayList<Cell> getAllCells(){
		ArrayList<Cell> temp = new ArrayList<Cell>();
		
		for(int i = 0; i < size; i++)
			temp.addAll(board.get(i));
		
		return temp;
	}
	
	public String toString(){
		String temp = "";
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				temp += board.get(i).get(j).getChar();
				if(j+1 < size)
					temp += " ";
			}
			temp += "\r\n";
		}
		return temp;
	}
	
	public Cell getCell(int x, int y){
		return this.board.get(x).get(y);
	}
	
	public ArrayList<Character> getRow(Cell cell){
		return this.getRow(cell.getX());
	}
	
	public ArrayList<Character> getRow(int row){
		ArrayList<Character> temp = new ArrayList<Character>();
		for(Cell c : getRowCells(row))
			temp.add(c.getChar());
		return temp;
	}
	
	public ArrayList<Cell> getRowCells(Cell cell){
		return this.getRowCells(cell.getY());
	}
	
	public ArrayList<Cell> getRowCells(int row){
		ArrayList<Cell> temp = new ArrayList<Cell>();
		for(int i = 0; i < size; i++)
			temp.add(board.get(row).get(i));
		return temp;
	}
	
	public ArrayList<Character> getCol(Cell cell){
		return this.getCol(cell.getY());
	}
	
	public ArrayList<Character> getCol(int col){
		ArrayList<Character> temp = new ArrayList<Character>();
		for(Cell c : getColCells(col))
			temp.add(c.getChar());
		return temp;
	}
	
	public ArrayList<Cell> getColCells(Cell cell){
		return this.getColCells(cell.getY());
	}
	
	public ArrayList<Cell> getColCells(int col){
		ArrayList<Cell> temp = new ArrayList<Cell>();
		for(int i = 0; i < size; i++)
			temp.add(board.get(i).get(col));
		return temp;
	}
	
	public ArrayList<Character> getBox(Cell cell){
		return this.getBox(((int)(cell.getX()*Math.sqrt(board.size())) + cell.getY())/board.size()+1);
	}
	
	public ArrayList<Character> getBox(int box){
		ArrayList<Character> temp = new ArrayList<Character>();
		for(Cell c : getBoxCells(box))
			temp.add(c.getChar());
		return temp;
	}
	
	public ArrayList<Cell> getBoxCells(Cell cell){
		return this.getBoxCells(((int)(cell.getX()*Math.sqrt(board.size())) + cell.getY())/board.size()+1);
	}
	
	public ArrayList<Cell> getBoxCells(int box){
		ArrayList<Cell> temp = new ArrayList<Cell>();
		for(int i = 0; i < Math.sqrt(size); i++)
			for(int j = 0; j < Math.sqrt(size); j++)
				temp.add(board.get((int)(i+(box/(int)Math.sqrt(size))*Math.sqrt(size)))
						.get((int)(j+(box%(int)Math.sqrt(size))*Math.sqrt(size))));
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
		public Cell next() {
			if(this.hasNext()){
				Cell temp = board.get(yIndex).get(xIndex);
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

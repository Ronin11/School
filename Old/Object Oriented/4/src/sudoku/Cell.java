package sudoku;

import java.util.ArrayList;

public class Cell {
	private Character character;
	private int x,y;
	private ArrayList<Character> possibilities = new ArrayList<Character>();
	
	public Cell(Character c, int x, int y){
		this.character = c;
		this.x = x;
		this.y = y;
	}
	
	public Character getChar(){return character;}
	public void setChar(Character c){character = c;}
	public int getX(){return x;}
	public int getY(){return y;}
	public ArrayList<Character> getPossibilities(){return possibilities;}
	public void clear(){character = '-';}
}

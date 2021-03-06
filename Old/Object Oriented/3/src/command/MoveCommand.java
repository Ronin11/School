package command;

import gui.DrawingCanvas;
import object.ObjectShape;

public class MoveCommand extends Command{
	int x, y, prevX, prevY;
	public MoveCommand(ObjectShape shape, DrawingCanvas canvas) {
		super(shape, canvas);
	}
	
	public void setPosition(int prevX, int prevY, int x, int y){
		this.prevX = prevX;
		this.prevY = prevY;
		this.x = x; 
		this.y = y;
		}

	@Override
	public void doCommand() {
		if(!canvas.updatePosition(shape, x, y))
			System.out.println("Its getting weird");
	}

	@Override
	public void undoCommand() {
		if(!canvas.updatePosition(shape, prevX, prevY))
			System.out.println("Im Mr Meeseeks");
	}

}

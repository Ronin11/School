package command;

import object.ObjectShape;
import gui.DrawingCanvas;

public abstract class Command {
	protected ObjectShape shape;
	protected DrawingCanvas canvas;
	public Command(ObjectShape shape, DrawingCanvas canvas){
		this.shape = shape;
		this.canvas = canvas;
	}
	public abstract void doCommand();
	public abstract void undoCommand();
	public ObjectShape getShape(){return shape;}
}

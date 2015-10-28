package command;

import gui.DrawingCanvas;
import object.ObjectShape;

public class SelectCommand extends Command{

	public SelectCommand(ObjectShape shape, DrawingCanvas canvas) {
		super(shape, canvas);
	}

	@Override
	public void doCommand() {
		canvas.selectShape(shape);
	}

	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		
	}

}

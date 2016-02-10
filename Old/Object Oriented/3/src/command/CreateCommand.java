package command;

import gui.DrawingCanvas;
import object.ObjectShape;

public class CreateCommand extends Command{

	public CreateCommand(ObjectShape shape, DrawingCanvas canvas) {
		super(shape, canvas);
	}

	@Override
	public void doCommand() {
		canvas.addShape(shape);
	}

	@Override
	public void undoCommand() {
		canvas.removeShape(shape);
	}

}

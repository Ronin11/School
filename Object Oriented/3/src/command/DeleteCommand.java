package command;

import gui.DrawingCanvas;
import object.ObjectShape;

public class DeleteCommand extends Command{

	public DeleteCommand(ObjectShape shape, DrawingCanvas canvas) {
		super(shape, canvas);
	}

	@Override
	public void doCommand() {
		if(!canvas.removeShape(shape))
			System.out.println("Shape not found.");
	}

	@Override
	public void undoCommand() {
		canvas.addShape(shape);
	}

}

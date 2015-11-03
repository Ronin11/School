package command;

import gui.DrawingCanvas;
import object.ObjectShape;

public class DuplicateCommand extends Command{

	public DuplicateCommand(ObjectShape shape, DrawingCanvas canvas) {
		super(shape, canvas);
	}

	@Override
	public void doCommand() {
		shape = shape.createCopy();
		shape.addDuplicateOffset();
		canvas.addShape(shape);
	}

	@Override
	public void undoCommand() {
		canvas.removeShape(shape);
	}

}

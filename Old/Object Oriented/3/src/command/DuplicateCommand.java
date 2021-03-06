package command;

import gui.DrawingCanvas;
import object.ObjectShape;

public class DuplicateCommand extends Command{
	ObjectShape newShape;
	public DuplicateCommand(ObjectShape shape, DrawingCanvas canvas) {
		super(shape, canvas);
	}

	@Override
	public void doCommand() {
		newShape = shape.createCopy();
		newShape.addDuplicateOffset();
		canvas.addShape(newShape);
		canvas.selectShape(newShape);
	}

	@Override
	public void undoCommand() {
		canvas.removeShape(newShape);
	}

}

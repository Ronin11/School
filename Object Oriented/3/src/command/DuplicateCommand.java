package command;

import gui.DrawingCanvas;
import object.ObjectShape;

public class DuplicateCommand extends Command{

	public DuplicateCommand(ObjectShape shape, DrawingCanvas canvas) {
		super(shape, canvas);
	}

	@Override
	public void doCommand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undoCommand() {
		// TODO Auto-generated method stub
		
	}

}
package command;

import java.awt.Color;

import gui.DrawingCanvas;
import object.ObjectShape;

public class ChangeColorCommand extends Command{
	Color color;
	Color prevColor;
	public ChangeColorCommand(ObjectShape shape, DrawingCanvas canvas) {
		super(shape, canvas);
	}
	public void setColor(Color color){this.color = color;}
	@Override
	public void doCommand() {
		prevColor = shape.getColor();
		canvas.updateColor(shape, color);
	}

	@Override
	public void undoCommand() {
		canvas.updateColor(shape, prevColor);
	}

}

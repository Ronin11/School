package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import org.junit.Test;

import gui.DrawingCanvas;
import io.XML;
import object.*;
import command.*;

public class Tests {

	@Test
	public void testCommands(){
		try {
			DrawingCanvas canvas = new DrawingCanvas(Color.white);
			ObjectShape circle = ShapeFactory.makeCircle();
			canvas.getInvoker().addCommand(new CreateCommand(circle, canvas));
			Thread.sleep(100);
			assertTrue(canvas.getShapes().contains(circle));
			
			MoveCommand move = new MoveCommand(circle, canvas);
			move.setPosition(0, 0, 100, 100);
			canvas.getInvoker().addCommand(move);
			Thread.sleep(100);
			assertTrue(canvas.getShapes().get(0).getX() == 100);
			assertTrue(canvas.getShapes().get(0).getY() == 100);
			canvas.getInvoker().undo();
			Thread.sleep(100);
			assertTrue(canvas.getShapes().get(0).getX() == 0);
			assertTrue(canvas.getShapes().get(0).getY() == 0);
			
			
			canvas.getInvoker().addCommand(new SelectCommand(circle, canvas));
			Thread.sleep(100);
			assertSame(canvas.getCurrentShape(), circle);
			
			canvas.getInvoker().addCommand(new DuplicateCommand(circle, canvas));
			Thread.sleep(100);
			assertNotSame(canvas.getCurrentShape(), circle);
			assertTrue(canvas.getCurrentShape().getX() == canvas.getCurrentShape().getDuplicateOffset());
			canvas.getInvoker().undo();
			Thread.sleep(100);
			assertTrue(canvas.getCurrentShape() == null);
			
			ChangeColorCommand color = new ChangeColorCommand(circle,canvas);
			color.setColor(Color.red);
			canvas.getInvoker().addCommand(color);
			Thread.sleep(100);
			assertTrue(canvas.getShapes().get(0).getColor() == Color.red);
			canvas.getInvoker().undo();
			Thread.sleep(100);
			assertTrue(canvas.getShapes().get(0).getColor() == Color.black);
			
			canvas.getInvoker().addCommand(new DeleteCommand(circle, canvas));
			Thread.sleep(100);
			assertTrue(canvas.getShapes().size() == 0);
			canvas.getInvoker().undo();
			Thread.sleep(100);
			assertTrue(canvas.getShapes().size() == 1);
			canvas.getInvoker().undo();
			Thread.sleep(100);
			canvas.getInvoker().undo();
			Thread.sleep(100);
			assertTrue(canvas.getShapes().size() == 0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testShapes(){
		assertNotNull(ShapeFactory.makeCircle());
		assertNotNull(ShapeFactory.makeTriangle());
		assertNotNull(ShapeFactory.makeSquare());
		assertNotNull(ShapeFactory.makePentagon());
		assertNotNull(ShapeFactory.makeOctogon());
		assertNotNull(ShapeFactory.makeStar());
		assertNotNull(ShapeFactory.makeCircle(0,0,Color.black));
		assertNotNull(ShapeFactory.makeTriangle(0,0,Color.black));
		assertNotNull(ShapeFactory.makeSquare(0,0,Color.black));
		assertNotNull(ShapeFactory.makePentagon(0,0,Color.black));
		assertNotNull(ShapeFactory.makeOctogon(0,0,Color.black));
		assertNotNull(ShapeFactory.makeStar(0,0,Color.black));
	}
	
	@Test
	public void testIO(){
		File file = new File("src/test/test.xml");
		List<ObjectShape> shapes = new ArrayList<ObjectShape>();
		shapes.addAll(XML.readInObjects(file));
		assertTrue(!shapes.isEmpty());
		File file2 = new File("src/test/testOut.xml");
		XML.saveFile(file2, shapes);
		assertTrue(file.getTotalSpace() == file2.getTotalSpace());
		file2.delete();
	}
}

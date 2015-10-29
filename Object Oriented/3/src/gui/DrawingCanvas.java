package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import command.*;
import object.ObjectShape;
import object.ShapeFactory;

public class DrawingCanvas extends Canvas{
	Graphics2D g2;
	ObjectShape currentShape = null;
	Color currentColor = Color.black;
	boolean create = true;
	Invoker invoker = new Invoker();
	DrawingCanvas pointer;
	List<ObjectShape> shapes = Collections.synchronizedList(new ArrayList<ObjectShape>());
	final static int canvasSize = 400;
	
	private boolean select(int x, int y){
		synchronized(shapes){
			for(ObjectShape shape : shapes)
				if(shape.contains(x, y))
				{
					currentShape = shape;
					return true;
				}
			return false;
		}
	}
	
	public DrawingCanvas(){
		invoker.start();
		pointer = this;
		setBackground(Color.white);
		setSize(canvasSize,canvasSize);
		
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				if(create && currentShape != null)
				{
					currentShape.setVals(e.getX(), e.getY(), currentColor);
					invoker.addCommand(new CreateCommand(currentShape, pointer));
					create = false;
					currentShape = null;
				}
				else if(select(e.getX(), e.getY()))
				{
					invoker.addCommand(new SelectCommand(currentShape, pointer));
				}
				else
				{
					currentShape = null;
					repaint();
				}
				
				
			}
		});
		this.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_D)
				{
					if(currentShape != null)
						invoker.addCommand(new DuplicateCommand(currentShape, pointer));
				}
				else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z){
					invoker.undo();
				}
				else if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_R){
					
				}
				else if(e.getKeyCode() == KeyEvent.VK_DELETE)
				{
					if(currentShape != null)
						invoker.addCommand(new DeleteCommand(currentShape, pointer));
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}
			
		});
	}
	public void paint (Graphics g){
		g2 = (Graphics2D) g;
		requestFocus();
	}
	public void update(Graphics g){
		g2 = (Graphics2D) g;
		g.setColor(getBackground());
		g.clearRect(0, 0, canvasSize, canvasSize);
		synchronized(shapes){
			for(ObjectShape shape : shapes)
				shape.draw(g2);
		}
		requestFocus();
	}
	public void addShape(ObjectShape shape){
		shapes.add(shape);
		//shape.draw(this.getGraphics());
		repaint();
	}
	public boolean removeShape(ObjectShape shape){
		boolean temp = shapes.remove(shape);
		repaint();
		return temp;
	}
	public void setCurrentShape(ObjectShape shape){
		currentShape = shape;
	}
	public void selectShape(ObjectShape shape){
		repaint();
		currentShape = shape;
		shape.select(this.getGraphics());
		if(shapes.contains(shape)){
			shapes.remove(shape);
			shapes.add(0, shape);
		}
	}
	public void setCreate(boolean b){create = b;}
	public Color getCurrentColor(){return currentColor;}
	public void setCurrentColor(Color color){currentColor = color;}
	public Invoker getInvoker(){return invoker;}
	public ObjectShape getCurrentShape(){return currentShape;}
}
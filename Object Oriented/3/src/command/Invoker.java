package command;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Invoker implements Runnable{
	private ArrayList<Command> done = new ArrayList<Command>();
	private ArrayList<Command> toDo = new ArrayList<Command>();
	private Thread thread;
	private boolean run = true;
	private Semaphore mutex;
	
	public Invoker(){
		mutex = new Semaphore(1);
	}
	
	public void start(){
		thread = new Thread(this);
		thread.start();
	}
	public void undo(){
		
	}
	
	public void addCommand(Command cmd){
		try{
			mutex.acquire();
			try{
				toDo.add(cmd);
			} finally {
				mutex.release();
			}
		}	catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void run() {
		while(run){
			try{
				if(toDo.size() > 0){
					mutex.acquire();
					try{
						toDo.get(0).doCommand();
						done.add(toDo.get(0));
						toDo.remove(0);
						
					} finally {
						mutex.release();
					}
				}else
					Thread.sleep(50);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}

}

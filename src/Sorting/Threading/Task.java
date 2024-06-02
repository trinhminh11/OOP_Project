package Sorting.Threading;

public class Task implements Runnable{
	Runnable target;
	private int priority;

	public Task(Runnable target, int priority){
		this.target = target;
		this.priority = priority;
	}

	public int getPriority(){
		return priority;
	}
	
	@Override
	public void run(){
		target.run();
	}
}
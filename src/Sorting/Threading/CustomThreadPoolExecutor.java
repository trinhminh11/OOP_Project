package Sorting.Threading;

import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolExecutor extends ThreadPoolExecutor{
	public CustomThreadPoolExecutor(int corePoolSize, int maximumPoolSize) {
		super(corePoolSize, maximumPoolSize, 0, TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>());
    }

    @Override
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
        return new TaskComparable<>(runnable);
    }
}

class TaskComparable<T> extends FutureTask<T> implements Comparable<TaskComparable<T>>{
	private final Task task;
    public TaskComparable(Runnable task) {

        super(task, null);

        // UpCasting Runnable to Task
        this.task = (Task) task;
    
	}

    @Override
    public int compareTo(@SuppressWarnings("rawtypes") TaskComparable that) {
        return Integer.compare(this.task.getPriority(), that.task.getPriority());
    }
}
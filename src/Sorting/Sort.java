package Sorting;

public abstract class Sort<T extends Comparable<T>> {
	// Thread thread = Thread.currentThread();
	// Thread thread = new Thread(Runnable, "test");
	protected int[][] colors;
	protected int timeStep;
	private boolean started = false;

	protected Thread main_Thread;

	public String bestComplexity;
	public String averageComplexity;
	public String worstComplexity;

	public int compared = 0;
	public int swapped = 0;

	protected CONST constant = new CONST();

	public Sort(int timeStep){
		this.timeStep = timeStep;
	}

	protected void await(){
		try {
			Thread.sleep(timeStep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setTimeStep(int x){
		timeStep = x;
	}

	protected void setup_color(int length){
		colors = new int[length][3];
		reset_color(0, length-1);
	}

	protected void set_color(int left, int right, int[] color){
		for (int i = left; i <= right; i++){
			colors[i] = color;
		}
	}

	protected void reset_color(int left, int right){
		set_color(left, right, constant.WHITE);
	}

	public int[] get_color(int idx){
		if (this.colors == null){
			return new int[]{255, 255, 255};
		}
		return colors[idx];
	}

	public String[] getTimeComplexity(){
		return new String[]{bestComplexity, averageComplexity, worstComplexity}; 
	}

	public boolean isDone(T[] arr){
		if (!started || main_Thread == null){
			for (int i = 1; i < arr.length; i++){
				if (arr[i].compareTo(arr[i-1]) < 0){
					return false;
				}
			}
			started = false;
			return true;
		}

		if (main_Thread.isAlive()){
			return false;
		}

		started = false;
		
		return true;
	}

	protected void swap(T[] arr, int i, int j){
		T temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
    }

	protected abstract void _sort(T[] arr);

	public void sort(T[] arr){
		started = true;
		compared = 0;
		swapped = 0;
		
		setup_color(arr.length);
		main_Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				_sort(arr);
			}
		});
		main_Thread.start();
	}
}

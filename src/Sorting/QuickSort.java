package Sorting;


/**
 * Quick Sort with 2 partition type: lomuto or hoare, default is lomuto
 */
public class QuickSort<T extends Comparable<T>> extends Sort<T>{
	private final int MAX_THREADS = 6;

	private CustomThreadPoolExecutor ThreadPool = new CustomThreadPoolExecutor(MAX_THREADS, MAX_THREADS);

	private String partition = "lomuto";
	private boolean multiThread = false;

	public QuickSort(int timeStep){
		super(timeStep);

		bestComplexity = "O(n*log(n))";
		averageComplexity = "O(n*log(n))";
		worstComplexity = "O(n^2)";
	}

	public QuickSort(String partition, int timeStep){
		this(timeStep);
		this.partition = partition;
	}

	public void changeMode(){
		if (partition == "lomuto"){
			partition = "hoare";
		}
		else{
			partition = "lomuto";
		}
	}

	public void setMultiThread(boolean f){
		multiThread = f;
	}

	public boolean getMultiThread(){
		return multiThread;
	}

	public void shutdown(){
		ThreadPool.shutdown();
	}

	protected void _sort(T[] arr){
		set_color(0, arr.length-1, constant.WHITE);
		for(int i = 1;i<arr.length;i++) {
			set_color(0, i, constant.BLUE);

			T key = arr[i];
			int j = i-1;
			while (j>=0&& arr[j].compareTo(key) > 0) {
				try {
					Thread.sleep(timeStep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				colors[j+1] = constant.BLUE;
				colors[j] = constant.RED;
				arr[j+1] = arr[j];
				j--;
			}

			arr[j+1]=key;
		}

		set_color(0, arr.length-1, constant.WHITE);
	}
}




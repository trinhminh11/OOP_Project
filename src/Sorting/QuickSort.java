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

	private int hoare_partition(T[] arr, int low, int high){
		T pivot = arr[high];
		
		// Index of smaller element
		int i = (low - 1);

		for (int j = low; j <= high- 1; j++){
			try {
				Thread.sleep(timeStep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (arr[j].compareTo(pivot) <= 0){
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}

	private int lomuto_partition(T[] arr, int low, int high){
		set_color(low, high, constant.BLUE);
		
		T pivot = arr[high];
		
		int i = low;

		for (int j = low; j <= high- 1; j++){
			colors[i] = constant.YELLOW;
			colors[j] = constant.YELLOW;
			try {
				Thread.sleep(timeStep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (arr[j].compareTo(pivot) <= 0){
				compared ++;
				swap(arr, i, j);
				i++;
				colors[i] = constant.WHITE;
				swapped++;
			}
			colors[j] = constant.WHITE;
		}
		
		swap(arr, i, high);
		swapped ++;
		
		return i;
	}

	private void quickSort(T[] arr, int low, int high){
		if (low < high){
			int pivot;
			if (partition == "lomuto"){
				pivot = lomuto_partition(arr, low, high);
			}
			else{
				pivot = hoare_partition(arr, low, high);
			}

			quickSort(arr, low, pivot - 1);
			quickSort(arr, pivot + 1, high);
		}
	}

	protected void _sort(T[] arr){
		quickSort(arr, 0, arr.length-1);
	}
}




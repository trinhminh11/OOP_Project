package Sorting;

import Sorting.Threading.CustomThreadPoolExecutor;
import Sorting.Threading.Task;

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

	public boolean ThreadPoolDone(){
		return ThreadPool.getActiveCount() == 0;
	}

	private int hoare_partition(T[] arr, int low, int high){
		set_color(low, high-1, constant.BLUE);
		int idx = low + (int)(Math.random() * ((high - low) + 1));

		await(idx);
		swapped++;
		swap(arr, high, idx);
		
		T pivot = arr[high];
        int i = low, j = high-1;

        while (true) {
			await(i);
			compared ++;
			while (arr[i].compareTo(pivot) < 0) {
				colors[i] = constant.WHITE;
				i++;
				colors[i] = constant.YELLOW;
				await(i);
				compared ++;
			}
            
			await(j);
			compared ++;
			while (arr[j].compareTo(pivot) > 0 && j >= 1){
				colors[j] = constant.WHITE;
				j--;
				colors[j] = constant.YELLOW;
				await(j);
				compared ++;
			}

            if (i >= j){
				await(i);
				swapped ++;
				swap(arr, i, high);
				colors[i] = constant.WHITE;
				colors[j] = constant.WHITE;
                return i;
			}
			await(j);
			swapped ++;
			swap(arr, i, j);
        }
	}

	private int lomuto_partition(T[] arr, int low, int high){
		set_color(low, high, constant.WHITE);

		int idx = low + (int)(Math.random() * ((high - low) + 1));

		await(idx);
		swapped++;
		swap(arr, high, idx);
		
		T pivot = arr[high];
		
		int i = low;

		colors[i] = constant.WHITE;

		for (int j = low; j <= high- 1; j++){
			if (j>low){
				colors[j-1] = constant.BLUE;
			}

			colors[j] = constant.YELLOW;
			await(j);
			compared ++;
			if (arr[j].compareTo(pivot) <= 0){
				await(j);
				swapped++;
				swap(arr, i, j);
				colors[i] = constant.BLUE;
				i++;
				colors[i] = constant.YELLOW;
			}
		}


		await(i);
		swapped ++;
		swap(arr, i, high);

		colors[low] = constant.WHITE;
		set_color(i, high, constant.WHITE);
		
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

			colors[pivot] = constant.RED;

			if (multiThread){
				Task t1 = new Task(() -> {
					quickSort(arr, low, pivot - 1);
				}, pivot - low);

				Task t2 = new Task(() -> {
					quickSort(arr, pivot + 1, high);
				}, high - pivot);

				ThreadPool.submit(t1);
				ThreadPool.submit(t2);
			}

			else{
				quickSort(arr, low, pivot - 1);
				colors[low-1 + (low == 0 ? 1: 0)] = constant.WHITE;
				quickSort(arr, pivot + 1, high);
			}

			colors[pivot] = constant.WHITE;
		}
	}
	protected void _sort(T[] arr){
		ThreadPool = new CustomThreadPoolExecutor(MAX_THREADS, MAX_THREADS);
		quickSort(arr, 0, arr.length-1);
	}
}




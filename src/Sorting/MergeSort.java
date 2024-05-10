package Sorting;

public class MergeSort<T extends Comparable<T>> extends Sort<T>{
	private final int MAX_THREAD = 6;
	private boolean multiThread = false;
	private int count_thread = 0;

	public MergeSort(int timeStep){
		super(timeStep);
		bestComplexity = "O(n*log(n))";
		averageComplexity = "O(n*log(n))";
		worstComplexity = "O(n*log(n))";

	}

	public void setMultiThread(boolean f){
		multiThread = f;
	}

	public boolean getMultiThread(){
		return multiThread;
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
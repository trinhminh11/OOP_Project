package Sorting;

public class InsertionSort<T extends Comparable<T>> extends Sort<T>{

	public InsertionSort(int timeStep){
		super(timeStep);
		bestComplexity = "O(n)";
		averageComplexity = "O(n^2)";
		worstComplexity = "O(n^2)";
	}

	protected void _sort(T[] arr){
		set_color(0, arr.length-1, constant.WHITE);
		for(int i = 1;i<arr.length;i++) {
			set_color(0, i, constant.BLUE);

			T key = arr[i];
			int j = i-1;
			while (j>=0&& arr[j].compareTo(key) > 0) {
				compared ++;
				try {
					Thread.sleep(timeStep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				colors[j+1] = constant.BLUE;
				colors[j] = constant.RED;
				arr[j+1] = arr[j];
				swapped ++;
				j--;
			}

			arr[j+1]=key;
			swapped ++;
		}

		set_color(0, arr.length-1, constant.WHITE);
	}
}

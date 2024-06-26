package Sorting;

public class InsertionSort<T extends Comparable<T>> extends Sort<T>{

	public InsertionSort(int timeStep){
		super(timeStep);
		bestComplexity = "O(n)";
		averageComplexity = "O(n^2)";
		worstComplexity = "O(n^2)";
	}

	protected void _sort(T[] arr){
		for(int i = 1;i<arr.length;i++) {
			set_color(0, i, constant.BLUE);

			T key = arr[i];
			int j = i;
			await(j-1);
			compared++;
			while (j>0 && arr[j-1].compareTo(key) > 0) {
				colors[j] = constant.BLUE;
				await(j);
				swapped ++;
				swap(arr, j, j-1);
				colors[j-1] = constant.RED;
				j--;

				await(j);
				compared ++;
			}

		}

		set_color(0, arr.length-1, constant.WHITE);
	}
}

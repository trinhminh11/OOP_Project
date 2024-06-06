package Sorting;

public class SelectionSort<T extends Comparable<T>> extends Sort<T>{

	public SelectionSort(int timeStep){
		super(timeStep);

		bestComplexity = "O(n^2)";
		averageComplexity = "O(n^2)";
		worstComplexity = "O(n^2)";
	}
	
	protected void _sort(T[] arr){
		int n = arr.length;

        for (int i = 0; i < n-1; i++){
            int min_idx = i;
			colors[min_idx] = constant.YELLOW;
            for (int j = i+1; j < n; j++){
				colors[j] = constant.RED;
				await(j);
				compared++;
				if (arr[j].compareTo(arr[min_idx]) < 0){
					colors[min_idx] = constant.RED;
					min_idx = j;
					colors[min_idx] = constant.YELLOW;
				}
			}

			await(i);
			swapped++;
			swap(arr, i, min_idx);
			colors[i] = constant.BLUE;

			set_color(i+1, n-1, constant.WHITE);
        }

		set_color(0, n-1, constant.WHITE);
	}
}

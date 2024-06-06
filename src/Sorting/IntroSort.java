package Sorting;

public class IntroSort<T extends Comparable<T>> extends Sort<T>{

	public IntroSort(int timeStep){
		super(timeStep);
		bestComplexity = "O(n*log(n))";
		averageComplexity = "O(n*log(n))";
		worstComplexity = "O(n*log(n))";
	}

	protected void _sort(T[] arr){
		set_color(0, arr.length-1, constant.WHITE);
		for(int i = 1;i<arr.length;i++) {
			await(i);
			set_color(0, i, constant.BLUE);

			T key = arr[i];
			int j = i;
			while (j>0 && arr[j-1].compareTo(key) > 0) {
				await(j);
				colors[j] = constant.BLUE;
				compared ++;
				swap(arr, j, j-1);
				await(j);
				swapped ++;
				colors[j-1] = constant.RED;
				j--;
			}

			// await(j+1);
			// arr[j+1]=key;
			// swapped ++;
		}

		set_color(0, arr.length-1, constant.WHITE);
	}
}

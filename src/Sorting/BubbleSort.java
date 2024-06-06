package Sorting;

public class BubbleSort<T extends Comparable<T>> extends Sort<T>{

	public BubbleSort(int timeStep){
		super(timeStep);
		bestComplexity = "O(n)";
		averageComplexity = "O(n^2)";
		worstComplexity = "O(n^2)";
	}

	protected void _sort(T[] arr){
		int i, j;
        boolean is_swap;
        for (i = 0; i < arr.length - 1; i++) {
            is_swap = false;
            for (j = 0; j < arr.length - i - 1; j++) {
				await(j);
				compared ++;
				colors[j] = constant.WHITE;
				if (arr[j].compareTo(arr[j+1])>0){
					await(j);
					swapped ++;
					swap(arr, j, j+1);
                    is_swap = true;
                }
				colors[j+1] = constant.RED;
            }

			colors[j] = constant.BLUE;

            // If no two elements were
            // swapped by inner loop, then break
            if (is_swap == false)
                break;
        }

		set_color(0, arr.length-1, constant.WHITE);
	}
}

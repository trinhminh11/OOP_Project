package Sorting;

public class HeapSort<T extends Comparable<T>> extends Sort<T>{

	private int depth = 0;

	private int[][] treecolor;

	public HeapSort(int timeStep){
		super(timeStep);
		bestComplexity = "O(n*log(n))";
		averageComplexity = "O(n*log(n))";
		worstComplexity = "O(n*log(n))";
		
	}

    void heapify(T[] arr, int N, int i){
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2


		depth += 1;



		colors[i] = treecolor[i];

        // If left child is larger than root

		if (l < N){
			colors[l] = treecolor[l];
			await(l);
			compared++;
			if (arr[l].compareTo(arr[largest]) > 0){
				largest = l;
			}
		}
        

        // If right child is larger than largest so far
		if (r < N){
			colors[r] = treecolor[r];
			await(r);
			compared++;
			if (arr[r].compareTo(arr[largest]) > 0){
				largest = r;
			}
		}

		if (largest == r && l < N){
			colors[l] = constant.WHITE;
		} 

		if (largest == l && r < N){
			colors[r] = constant.WHITE;
		}


        // If largest is not root
        if (largest != i) {
			await(i);
			swapped++;
			swap(arr, i, largest);

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }

		colors[i] = constant.WHITE;
		
		if (r < N){
			colors[r] = constant.WHITE;
		}

		if (l < N){
			colors[l] = constant.WHITE;
		}

		

    }


	protected void _sort(T[] arr){
        int N = arr.length;

		treecolor = new int[N][3];

		int n_depth = (int) Math.ceil((Math.log(N) / Math.log(2)));

		double increament = 360.0 / n_depth;



		for (int i = 0; i < n_depth; i++){
			int left = (int) Math.pow(2, i) - 1;
			int right = (int) Math.pow(2, i+1) - 1;


			for (int j = left; j < Math.min(right, N); j++){
				int c = (int) (i*increament);
				treecolor[j] = constant.hueToRGB(c);
			}
		}

		// for (int i = 0; i < N; i++){
		// 	int c = (int) (i*(360/N));
		// 	treecolor[i] = constant.hueToRGB(c);
		// }

		

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--){
			depth = 0;
            heapify(arr, N, i);
		}

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
			await(i);
			swapped++;
			swap(arr, 0, i);
			colors[i] = treecolor[i];

            // call max heapify on the reduced heap
			depth = 0;
            heapify(arr, i, 0);
        }

		colors[0] = treecolor[0];
    }
}
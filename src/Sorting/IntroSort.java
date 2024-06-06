package Sorting;

public class IntroSort<T extends Comparable<T>> extends Sort<T>{

	public IntroSort(int timeStep){
		super(timeStep);
		bestComplexity = "O(n*log(n))";
		averageComplexity = "O(n*log(n))";
		worstComplexity = "O(n*log(n))";
	}


	// To maxHeap a subtree rooted with node i which is
	// an index in a[]. heapN is size of heap
	private void maxHeap(T[] arr, int i, int heapN, int begin){
		T temp = arr[begin + i - 1];
		int child;

		while (i <= heapN / 2) {
			child = 2 * i;

			if (child < heapN){
				await(begin + child - 1);
				compared++;
				if (arr[begin + child - 1].compareTo(arr[begin+child]) < 0){
					child++;
				}
			}
			
			await(begin + child - 1);
			compared++;
			if (temp.compareTo(arr[begin+child-1]) >= 0){
				break;
			}
			await(begin+i-1);
			swapped++;
			arr[begin + i - 1] = arr[begin + child - 1];
			i = child;
		}

		await(begin+i-1);
		swapped++;
		arr[begin + i - 1] = temp;
	}

	// Function to build the heap (rearranging the array)
	private void heapify(T[] arr, int begin, int end, int heapN){
		for (int i = (heapN) / 2; i >= 1; i--){
			maxHeap(arr, i, heapN, begin);

		}
	}

	// main function to do heapsort
	private void heapSort(T[] arr, int begin, int end){
		int heapN = end - begin;

		this.heapify(arr, begin, end, heapN);

		// One by one extract an element from heap
		for (int i = heapN; i >= 1; i--) {
			
			
			await(i);
			swapped++;
			// Move current root to end
			swap(arr, begin, begin + i);

			colors[i] = constant.BLUE;

			// call maxHeap() on the reduced heap
			maxHeap(arr, 1, i, begin);
		}

		set_color(begin, end, constant.WHITE);
	}

	private void insertionSort(T[] arr, int left, int right){

		set_color(left, right, constant.WHITE);
		for(int i = left+1; i<=right; i++) {
			set_color(left, i, constant.BLUE);

			T key = arr[i];
			int j = i;
			await(j-1);
			compared++;
			while (j>left && arr[j-1].compareTo(key) > 0) {
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

		set_color(left, right, constant.WHITE);
	}

	private int partition(T[] arr, int low, int high){

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

	private void sortDataUtil(T[] arr, int begin, int end, int depthLimit){
		if (end - begin > 16) {
			if (depthLimit == 0) {

				// if the recursion limit is
				// occurred call heap sort
				this.heapSort(arr, begin, end);
				return;
			}

			depthLimit = depthLimit - 1;
			int idx = begin + (int)(Math.random() * ((end - begin) + 1));
			
			// T pivot = findPivot(arr, begin,
			// 	begin + ((end - begin) / 2) + 1,
			// 							end);
			swap(arr, idx, end);

			// p is partitioning index,
			// arr[p] is now at right place
			int p = partition(arr, begin, end);

			// Separately sort elements before
			// partition and after partition
			sortDataUtil(arr, begin, p - 1, depthLimit);
			sortDataUtil(arr, p + 1, end, depthLimit);
		}

		else {
			// if the data set is small,
			// call insertion sort
			insertionSort(arr, begin, end);
		}
	}

	// A utility function to begin the
	// Introsort module
	protected void _sort(T[] arr){

		// Initialise the depthLimit 
		// as 2*log(length(data))
		int n = arr.length;
		int depthLimit
			= (int)(2 * Math.floor(Math.log(n) /
								Math.log(2)));

		this.sortDataUtil(arr, 0, n - 1, depthLimit);

		set_color(0, arr.length-1, constant.WHITE);
	}

}


// QuickSort > introSort > TimSort > MergeSort ~ HeapSort > InsertionSort > BubbleSort > SelectionSort
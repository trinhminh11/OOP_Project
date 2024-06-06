package Sorting;

import java.lang.reflect.Array;

public class TimSort<T extends Comparable<T>> extends Sort<T>{
	static int RUN_SIZE = 8; 

	public TimSort(int timeStep){
		super(timeStep);
		bestComplexity = "O(n)";
		averageComplexity = "O(n*log(n))";
		worstComplexity = "O(n*log(n))";
	}

    public void insertionSort(T[] arr, int left, int right){ 
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
  
	public void merge(T[] arr, int left, int mid, int right){

		set_color(left, right, constant.BLUE);

		@SuppressWarnings("unchecked")
		T temp[] = (T[]) Array.newInstance(arr[0].getClass(), (right-left)+1);
		
		int i = left, j = mid+1; 
		int k = 0;

		// Add elements from first half or second half based on whichever is lower, 
		// do until one of the list is exhausted and no more direct one-to-one comparison could be made
		while(i<=mid && j<=right){
			colors[left+k] = constant.YELLOW;
			
			await(i);
			compared++;
			if (arr[i].compareTo(arr[j]) <= 0){
				await(i);
				swapped ++;
				temp[k] = arr[i];
				i+=1;
			}else{
				await(j);
				swapped ++;
				temp[k] = arr[j];
				j+=1;
			}
			k+=1;
		}

		// Add remaining elements to temp arr from first half that are left over
		while(i<=mid){
			colors[left+k] = constant.YELLOW;
			
			await(i);
			swapped ++;
			temp[k] = arr[i];
			i+=1; k+=1;
		} 
		
		// Add remaining elements to temp arr from second half that are left over
		while(j<=right){
			colors[left+k] = constant.YELLOW;

			await(j);
			swapped ++;
			temp[k] = arr[j];
			j+=1; k+=1;
		}

		for(i=left, k=0; i<=right; i++,k++){
			colors[i] = constant.BLUE;
			
			await(i);
			swapped ++;
			arr[i] = temp[k];
		}

	}
  
    // Iterative Timsort function to sort the 
    // array[0...n-1] (similar to merge sort) 
    protected void _sort(T[] arr) {
		int n = arr.length; 
  
        // Sort individual subarrays of size RUN 
        for (int i = 0; i < n; i += RUN_SIZE) { 
            insertionSort( 
                arr, i, 
                Math.min((i + RUN_SIZE - 1), (n - 1))); 
        }
  
        for (int size = RUN_SIZE; size < n; size = 2 * size) { 
            for (int left = 0; left < n; left += 2 * size) { 
  
                int mid = left + size - 1; 
                int right = Math.min((left + 2 * size - 1), 
                                     (n - 1)); 
  
                if (mid < right) 
                    merge(arr, left, mid, right); 
            } 
        } 
    }
}


// 02:41, 6532, 8079
package Sorting;

import java.lang.reflect.Array;

public class MergeSort<T extends Comparable<T>> extends Sort<T>{
	
	private boolean multiThread = false;

	public MergeSort(int timeStep){
		super(timeStep);
		bestComplexity = "O(n*log(n))";
		averageComplexity = "O(n*log(n))";
		worstComplexity = "O(n*log(n))";

	}

	// Typical recursive merge sort
	public void mergeSort(T[] array, int begin, int end){
		if (begin<end){
			int mid = (begin+end)/2;
			if (multiThread){
				Thread t1 = new Thread(()->
				{
					mergeSort(array, begin, mid);
				});

				Thread t2 = new Thread(()->
				{
					mergeSort(array, mid+1, end);
				});

				t1.start();
				t2.start();
				try {
					t1.join();
					t2.join();

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

			else{
				mergeSort(array, begin, mid);
				mergeSort(array, mid+1, end);
			}

			merge(array, begin, mid, end);
			
		}
	}
	
	//Typical 2-way merge
	public void merge(T[] array, int begin, int mid, int end){
		@SuppressWarnings("unchecked")
		T temp[] = (T[]) Array.newInstance(array[0].getClass(), (end-begin)+1);
		
		int i = begin, j = mid+1; 
		int k = 0;

		// Add elements from first half or second half based on whichever is lower, 
		// do until one of the list is exhausted and no more direct one-to-one comparison could be made
		while(i<=mid && j<=end){
			try {
				Thread.sleep(timeStep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (array[i].compareTo(array[j]) <= 0){
				temp[k] = array[i];
				i+=1;
			}else{
				temp[k] = array[j];
				j+=1;
			}
			k+=1;
		}

		// Add remaining elements to temp array from first half that are left over
		while(i<=mid){
			try {
				Thread.sleep(timeStep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			temp[k] = array[i];
			i+=1; k+=1;
		} 
		
		// Add remaining elements to temp array from second half that are left over
		while(j<=end){
			try {
				Thread.sleep(timeStep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			temp[k] = array[j];
			j+=1; k+=1;
		}

		for(i=begin, k=0; i<=end; i++,k++){
			try {
				Thread.sleep(timeStep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			array[i] = temp[k];
		}
	}

	public void setMultiThread(boolean f){
		multiThread = f;
	}

	public void sorting(T[] arr){
		setup_color(arr.length);

		new Thread(() -> {
			mergeSort(arr, 0, arr.length-1);
			set_color(0, arr.length-1, constant.WHITE);

		}).start();;
		
	}
}
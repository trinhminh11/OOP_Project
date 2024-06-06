package Sorting;

import java.lang.reflect.Array;

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

	// Typical recursive merge sort
	public void mergeSort(T[] arr, int left, int right){
		if (left<right){
			int mid = (left+right)/2;
			if (multiThread && count_thread < MAX_THREAD){
				Thread t1 = new Thread(()->
				{
					mergeSort(arr, left, mid);
				});

				Thread t2 = new Thread(()->
				{
					mergeSort(arr, mid+1, right);
				});
				count_thread += 2;

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
				mergeSort(arr, left, mid);
				mergeSort(arr, mid+1, right);
			}

			merge(arr, left, mid, right);
			
		}
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

	public void setMultiThread(boolean f){
		multiThread = f;
	}

	public boolean getMultiThread(){
		return multiThread;
	}

	public void _sort(T[] arr){
		count_thread = 0;
		mergeSort(arr, 0, arr.length-1);
		reset_color(0, arr.length-1);
	}
}
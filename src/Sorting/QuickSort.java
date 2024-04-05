package Sorting;
import java.util.Iterator;


/**
 * Quick Sort with 2 partition type: lomuto and hoare, default is lomuto
 */

public class QuickSort<T extends Comparable<T>> extends Sort<T>{
	
	private Iterator<T[]> iter;

	private int timeStep = 20;

	private String partition = "lomuto";

	private CONST constant = new CONST();

	public QuickSort(){
		super();
	}

	public QuickSort(String partition){
		this.partition = partition;
	}

	public void setTimeStep(int x){
		timeStep = x;
	}

	private int lomuto_partition(T[] arr, int left, int right){
		int[] bg = constant.BLUE;
		set_color(left, right, bg);

		int idx = left;
		T pivot = arr[right];

		// reset_color(left, right);

		// colors[right] = new int[] {0, 255, 0};
		colors[right] = constant.GREEN;
		
			
		for (int i = left; i < right; i++){
			try {
				Thread.sleep(timeStep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (arr[i].compareTo(pivot) <= 0){
				swap(arr, i, idx);
				if (idx != left){
					colors[idx] = bg;
				}
				idx++;
			}
			if (i > left){
				colors[i] = constant.RED;
				// colors[i] = new int[] {255, 0, 0};
				colors[i-1] = bg;
			}

			
		}

		swap(arr, idx, right);

		reset_color(left, right);

		return idx;
	}


	private int hoare_partition(T[] arr, int left, int right){
		int[] bg = constant.BLUE;
		set_color(left, right, bg);
		
		T pivot = arr[left]; 
        int i = left, j = right; 
  
        while (true) { 
			while (true){
				try {
					Thread.sleep(timeStep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				colors[i] = constant.WHITE;
				if (arr[i].compareTo(pivot) >= 0){
					break;
				}
				i++;
			}
  
            // Find rightmost element smaller 
            // than or equal to pivot 
			while (true){
				try {
					Thread.sleep(timeStep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				colors[j] = constant.WHITE;
				if (arr[j].compareTo(pivot) <= 0){
					break;
				}
                j--; 
			}

  
            // If two pointers met. 
            if (i >= j){
				// swap(arr, left, j);
				reset_color(left, right);
                return j;
			} 
			
			swap(arr, i, j);
        }

	}

	private void quickSort(T[] arr, int left, int right){
		if (left >= right){
			return;
		}

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				int pivot;
				if (partition == "lomuto"){
					pivot = lomuto_partition(arr, left, right);
				}
				else{
					pivot = hoare_partition(arr, left, right);
				}
				quickSort(arr, left, pivot-1);
				quickSort(arr, pivot+1, right);
			}
		});  
		t1.start();
		
	}

	public void sorting(T[] arr){
		setup_color(arr.length);
		quickSort(arr, 0, arr.length-1);
	}

	public T[] next(){
		return iter.next();
	}

	public boolean hasnext(){
		return iter.hasNext();
	}
}




package Sorting;

import java.util.Iterator;
import java.util.Random;

public class BogoSort<T> extends Sort<T>{
	private final int RANDOM_SEED = 0;

	private Iterator<T[]> iter;
	private Random rand = new Random(RANDOM_SEED);



	public void sorting(T[] arr){
		Generator<T[]> arrayStep = new Generator<T[]>() {
			public void run() throws InterruptedException{
				while (true){
					int i = rand.nextInt(arr.length);
					int j = rand.nextInt(arr.length);
					swap(arr, i, j);
					yieldd(arr);
				}
			}
		};

		iter = arrayStep.iterator();

	}

	public T[] next(){
		return iter.next();
	}

	// public void sorting(float[] arr){
	// 	infiniteGenerator = new Generator<Comparable>() {
	// 		public void run() throws InterruptedException {
	// 			while (true)
	// 				swap(arr, 0, 1);
	// 				yield(arr);
	// 		}
	// 	};
	// }
}

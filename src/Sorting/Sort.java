package Sorting;


public abstract class Sort<T> {
    protected void swap(T[] arr, int i, int j)
    {
		T temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
		// arr[i] = arr[i] + arr[j];
		// arr[j] = arr[i] - arr[j];
		// arr[i] = arr[i] - arr[j];
    }
}

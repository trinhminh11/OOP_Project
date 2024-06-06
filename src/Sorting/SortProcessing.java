package Sorting;

public class SortProcessing<T extends Comparable<T>>{
	private String mode = "Quick";

	// lomuto or hoare
	QuickSort<T> quick;
	MergeSort<T> merge;
	HeapSort<T> heap;
	BubbleSort<T> bubble;
	SelectionSort<T> selection;
	InsertionSort<T>  insertion;
	TimSort<T> tim;
	IntroSort<T> intro;

	Sort<T> sort;

	public SortProcessing(int timeStep){
		quick = new QuickSort<T>(timeStep);
		merge = new MergeSort<>(timeStep);
		heap = new HeapSort<>(timeStep);
		bubble = new BubbleSort<T>(timeStep);
		selection = new SelectionSort<>(timeStep);
		insertion = new InsertionSort<>(timeStep);
		tim = new TimSort<>(timeStep);
		intro = new IntroSort<>(timeStep);

		sort = new QuickSort<>(timeStep);

	}

	public boolean check(T[] arr){
		boolean res = sort.isDone(arr);

		if (mode == "Quick" && res){
			if (quick.ThreadPoolDone()){
				if (quick.getMultiThread()){
					quick.shutdown();
				}
			}
			else{
				return false;
			}
		}


		return res;
	}

	public void changeQuickSortMode(){
		quick.changeMode();
		sort = quick;
	}

	public void setTimeStep(int t){
		sort.setTimeStep(t);
	}

	public int[] get_color(int i){
		return sort.get_color(i);
	}

	public void set_color(int l, int r, int[] color){
		sort.set_color(l, r, color);
	}
	
	public void setMode(String m){
		mode = m;
		switch (mode) {
			case "Tim":
				sort = tim;
				break;
			
			case "Intro":
				sort = intro;
				break;

			case "Quick":
				sort = quick;
				break;
			
			case "Merge":
				sort = merge;
				break;
			
			case "Heap":
				sort = heap;
				break;
			
			case "Bubble":
				sort = bubble;
				break;

			case "Selection":
				sort = selection;
				break;
			
			case "Insertion":
				sort = insertion;
				break;
		
			default:
				sort = quick;
				break;
		}

	}

	public String getMode(){
		return mode;
	}

	public void setMultiThread(boolean f){
		quick.setMultiThread(f);
		merge.setMultiThread(f);
	}

	public int getCompare(){
		return sort.compared;
	}

	public int getSwap(){
		return sort.swapped;
	}

	public void sort(T[] arr){
		sort.sort(arr);
	}

	public String[] getTimeComplexity(){
		return sort.getTimeComplexity();
	}
}
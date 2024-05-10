/* autogenerated by Processing revision 1293 on 2024-04-04 */
import processing.core.*;

import java.util.Random;

import Sorting.*;

public class Project extends PApplet {

	private final int RANDOM_SEED = 0;
	private final int FPS = 60;

	private final int MIN_TIME = 1;
	private final int MAX_TIME = 100;

	private final int MIN_LENGTH = 2;
	private final int MAX_LENGTH = 500;

	private CONST constant = new CONST();
	private Random rand = new Random(RANDOM_SEED);
	private int visual_height = 600;
	private int ui_distance = 75;
	private int ui_height = 275;
	private int num_array = 100;
	private float w = 0;
	private long curTime = 0, start_time = 0, end_time = 0;
	private boolean called_time = false;

	private boolean sorted = false;
	private Double[] arr;

	
	private int[] sortButtonColor = constant.GRAY[51];
	

	private boolean done = true;
	private boolean reseted = true;

	public class SortProcessing<T extends Comparable<T>>{
		private String mode = "Quick";
		private int timeStep = 10;

		// lomuto or hoare
		QuickSort<T> quick = new QuickSort<T>(timeStep);
		MergeSort<T> merge = new MergeSort<>(timeStep);
		HeapSort<T> heap = new HeapSort<>(timeStep);
		BubbleSort<T> bubble = new BubbleSort<T>(timeStep);
		SelectionSort<T> selection = new SelectionSort<>(timeStep);
		InsertionSort<T>  insertion = new InsertionSort<>(timeStep);


		public boolean check(T[] arr){
			for (int i = 1; i < arr.length; i++){
				if (arr[i].compareTo(arr[i-1]) < 0){
					return false;
				}
			}

			switch (mode) {
				case "Quick":
					if (quick.getMultiThread()){
						quick.shutdown();
					}
					break;
			
				default:
					break;
			}

			return true;
		}

		public void setTimeStep(int t){
			this.timeStep = t;
			quick.setTimeStep(t);
			merge.setTimeStep(t);
			heap.setTimeStep(t);
			bubble.setTimeStep(t);
			selection.setTimeStep(t);
			insertion.setTimeStep(t);
		}

		public int[] get_color(int i){
			switch (mode) {
				case "Quick":
					return quick.get_color(i);
				
				case "Merge":
					return merge.get_color(i);
				
				case "Heap":
					return heap.get_color(i);
				
				case "Bubble":
					return bubble.get_color(i);

				case "Selection":
					return selection.get_color(i);
				
				case "Insertion":
					return insertion.get_color(i);
			
				default:
					return quick.get_color(i);
			}
		}
		
		public void setMode(String m){
			mode = m;
		}

		public String getMode(){
			return mode;
		}

		public void setMultiThread(boolean f){
			quick.setMultiThread(f);
			merge.setMultiThread(f);
		}

		public int getCompare(){
			switch (mode) {
				case "Quick":
					return quick.compared;
				
				case "Merge":
					return merge.compared;
				
				case "Heap":
					return heap.compared;
				
				case "Bubble":
					return bubble.compared;

				case "Selection":
					return selection.compared;
				
				case "Insertion":
					return insertion.compared;
			
				default:
					return quick.compared;
			}
		}

		public int getSwap(){
			switch (mode) {
				case "Quick":
					return quick.swapped;
				
				case "Merge":
					return merge.swapped;
				
				case "Heap":
					return heap.swapped;
				
				case "Bubble":
					return bubble.swapped;

				case "Selection":
					return selection.swapped;
				
				case "Insertion":
					return insertion.swapped;
			
				default:
					return quick.swapped;
			}
		}

		public void sort(T[] arr){
			switch (mode) {
				case "Quick":
					quick.sort(arr);
					break;
				
				case "Merge":
					merge.sort(arr);
					break;

				case "Heap":
					heap.sort(arr);
					break;
				
				case "Bubble":
					bubble.sort(arr);
					break;
				
				case "Selection":
					selection.sort(arr);
					break;
				
				case "Insertion":
					insertion.sort(arr);
					break;
			
				default:
					quick.sort(arr);
					break;
			}
		}

		public String[] getTimeComplexity(){
			switch (mode) {
				case "Quick":
					return quick.getTimeComplexity();
				
				case "Merge":
					return merge.getTimeComplexity();
				
				case "Heap":
					return heap.getTimeComplexity();
				
				case "Bubble":
					return bubble.getTimeComplexity();

				case "Selection":
					return selection.getTimeComplexity();
				
				case "Insertion":
					return insertion.getTimeComplexity();
			
				default:
					return quick.getTimeComplexity();
			}
		}
	}

	SortProcessing<Double> sort = new SortProcessing<>();


	public class Button{
		private PVector Pos = new PVector(0, 0);
		private float Width = 0;
		private float Height = 0;
		private int[] c;
		private String Text;
		private int tsize;
		private int[] tc;
		private Boolean Pressed = false;
		private Boolean Clicked = false;
		

		Button(int x, int y, int w, int h, String t, int tsize, int[] c, int[] tc){
			Pos.x = x;
			Pos.y = y;
			Width = w;
			Height = h;
			Text = t;
			this.tsize = tsize;
			this.c = c;
			this.tc = tc;
		}

		void update(){
			Clicked = false;
			if (mousePressed == true && mouseButton == LEFT && Pressed == false){
				Pressed = true;
				if (Pos.x <= mouseX && mouseX <= Pos.x + Width && Pos.y <= mouseY && mouseY <= Pos.y + Height){
					Clicked = true;
				}
				else{
					Clicked = false;
				}
			}
			
			if (mousePressed == false){
				Pressed = false;
			}
		}

		public void render(){
			// stroke(0);
			fill(c[0], c[1], c[2]);
			rect(Pos.x, Pos.y, Width, Height);
			fill(tc[0], tc[1], tc[2]);
			textAlign(CENTER, CENTER);
			textSize(tsize);
			text(Text, Pos.x + (Width/2), Pos.y + (Height/2));
		}

		public void set_color(int[] c){
			this.c = c;
		}

		public int[] get_color(){
			return c;
		}

		public boolean isClicked(){
			return Clicked;
		}
	}

	public class Slider{
		private PVector Pos = new PVector(0, 0);
		private PVector Dir = new PVector(0, 0);
		private float Radius = 0;
		private boolean Clicked = false;
		private boolean Pressed = false;
		private int value = 20;
		private float length;

		Slider(int x, int y, int dir_x, int dir_y, int value, float R){
			Pos.x = x;
			Pos.y = y;
			Dir.x = dir_x;
			Dir.y = dir_y;
			Radius = R;
			length = dir_x;
			this.value = value;
		}

		public boolean isClicked(){
			return Clicked;
		}

		public boolean isPressed(){
			return Pressed;
		}

		public int getValue(){
			return value;
		}

		public void update(){
			Clicked = false;
			if (mousePressed == true && mouseButton == LEFT && Pressed == false){
				if (dist(mouseX, mouseY, Pos.x+value, Pos.y) <= Radius){
					Pressed = true;
					Clicked = true;
				}
				else{
					Clicked = false;
				}
			}
			
			if (mousePressed == false){
				Pressed = false;
			}

			if (Pressed){
				int diff = (int)(mouseX - Pos.x);

				value = min(max(diff, 0), (int)length);
			}
		}

		public void render(){
			fill(255);
			rect(Pos.x, Pos.y, Dir.x, Dir.y+4);
			fill(51);
			circle(Pos.x + value, Pos.y+2, Radius);
		}
	}

	public class Panel{
		private int[] c = constant.GRAY[105];
		void render(){
			stroke(0);
			fill(c[0], c[1], c[2]);
			rect(0, 0, width, ui_height);

			fill(0);
			line(width-200, 0, width-200, ui_height);

			line(300, 0, 300, ui_height);

			line(0, 125, 300, 125);

			line(0, 160, 300, 160);


			fill(255);
			textAlign(CENTER);
			textSize(25);
			text("Time Complexity", 150, 150);

			text("Best", 75, 185);
			text("Average", 75, 225);
			text("Worst", 75, 265);

			fill(0);
			line(150, 160, 150, ui_height);


			fill(255);
			String[] timeComplexity = sort.getTimeComplexity();
			text(timeComplexity[0], 225, 185);
			text(timeComplexity[1], 225, 225);
			text(timeComplexity[2], 225, 265);

			textAlign(LEFT);
			String temp = "";
			long mili = curTime%1000;
			int s = (int)(curTime/1000);

			int m = s/60;
			s = s - m*60;

			if (m < 10){
				temp += "0";
				
			}
			
			temp += m;

			temp += ":";
			if (s < 10){
				temp += "0";
			}
			temp += s;

			temp += ".";
			if (mili < 10){
				temp += "00";
			}
			else if(mili <100){
				temp += "0";
			}

			temp += mili;
			

			text("Visual Time: " + temp + " | compared: " + sort.getCompare() + " | swapped: " + sort.getSwap(), 5, ui_height + 25);

		}
	}
	// lomuto or hoare
	
	Panel panel = new Panel();

	Slider TimeStepSlider, numArraySlider;

	Button startButton, resetButton, skipButton, quickSortButton, hoareButton, multiThreadButton, mergeSortButton, heapSortButton, bubbleSortButton, selectionSortButton, insertionSortButton;

	public void settings(){
		width = 1200;
		height = visual_height + ui_height + ui_distance;
	}

	public void random_arr(){
		arr = new Double[num_array];
		w = (float)width/(float)num_array;
		float step = ((float)visual_height-1)/((float)arr.length-1);
		arr[0] = 5.0;

		for (int i = 1; i < arr.length; i++){
			arr[i] = arr[i-1] + step;
			// arr[i] = rand.nextDouble(visual_height);
		}


		for (int i = 0; i < arr.length; i++){
			int idx = rand.nextInt(arr.length);

			Double temp = arr[i];
			arr[i] = arr[idx];
			arr[idx] = temp;
		}


	}

	public void setup(){
		
		random_arr();
		frameRate(FPS);

		startButton = new Button(width-175, 10, 150, 75, "Start", 50, sortButtonColor, constant.WHITE);
		resetButton = new Button(width-175, 95, 150, 75, "Reset", 50, sortButtonColor, constant.WHITE);
		skipButton = new Button(width-175, 180, 150, 75, "Skip", 50, sortButtonColor, constant.WHITE);

		multiThreadButton = new Button(width - 100, ui_height, 100, 50, "MThread", 25, sortButtonColor, constant.GREEN);

		quickSortButton = new Button(width - 875, 25, 200, 100, "Quick", 50, sortButtonColor, constant.WHITE);
		hoareButton = new Button(width - 200, ui_height, 100, 50, "Hoare", 25, sortButtonColor, constant.GREEN);
		mergeSortButton = new Button(width-650, 25, 200, 100, "Merge", 50, sortButtonColor, constant.WHITE);
		heapSortButton = new Button(width-425, 25, 200, 100, "Heap", 50, sortButtonColor, constant.WHITE);


		bubbleSortButton = new Button(width - 875, 150, 200, 100, "Bubble", 50, sortButtonColor, constant.WHITE);
		selectionSortButton = new Button(width-650, 150, 200, 100, "Selection", 50, sortButtonColor, constant.WHITE);
		insertionSortButton = new Button(width-425, 150, 200, 100, "Insertion", 50, sortButtonColor, constant.WHITE);

		quickSortButton.set_color(constant.BLACK);

		TimeStepSlider = new Slider(25, 25, 250, 0, round(map(20, MIN_TIME, MAX_TIME, 0, 250)), 20);
		numArraySlider = new Slider(25, 75, 250, 0, round(map(100, MIN_LENGTH, MAX_LENGTH, 0, 250)), 20);
	}

	private void ButtonReset(){
		quickSortButton.set_color(sortButtonColor);
		mergeSortButton.set_color(sortButtonColor);
		heapSortButton.set_color(sortButtonColor);
		bubbleSortButton.set_color(sortButtonColor);
		selectionSortButton.set_color(sortButtonColor);
		insertionSortButton.set_color(sortButtonColor);
	}
	
	public void ButtonProcessing(){
		if (startButton.isClicked() && done){
			if (reseted){
				sort.sort(arr);
				reseted = false;
				done = false;
			}
		}
		
		startButton.update();
		startButton.render();

		if (resetButton.isClicked()){
			if (done == true){
				random_arr();
				reseted = true;
			}
		}

		resetButton.update();
		resetButton.render();

		if (skipButton.isClicked()){
			sort.setTimeStep(0);
		}

		skipButton.update();
		skipButton.render();

		if (done){
			SortButtonProcessing();
		}
		quickSortButton.render();
		if (sort.getMode()=="Quick"){
			hoareButton.render();
		}
		multiThreadButton.render();
		mergeSortButton.render();
		heapSortButton.render();

		bubbleSortButton.render();
		selectionSortButton.render();
		insertionSortButton.render();
	}

	public void SortButtonProcessing(){
		if (quickSortButton.isClicked()){
			sort.setMode("Quick");
			ButtonReset();
			quickSortButton.set_color(constant.BLACK);
		}

		quickSortButton.update();

		if (hoareButton.isClicked()){
			sort.quick.changeMode();
			int[] hoare_c = hoareButton.get_color();
			if (hoare_c == constant.BLACK){
				hoareButton.set_color(constant.GRAY[51]);
			}
			else{
				hoareButton.set_color(constant.BLACK);
			}
		}

		hoareButton.update();

		if (multiThreadButton.isClicked()){
			int[] multiThread_c = multiThreadButton.get_color();
			if (multiThread_c == constant.BLACK){
				multiThreadButton.set_color(constant.GRAY[51]);
				sort.setMultiThread(false);
			}
			else{
				multiThreadButton.set_color(constant.BLACK);
				sort.setMultiThread(true);
			}
		}

		multiThreadButton.update();

		if (bubbleSortButton.isClicked()){
			sort.setMode("Bubble");
			ButtonReset();
			bubbleSortButton.set_color(constant.BLACK);
		}

		if (mergeSortButton.isClicked()){
			sort.setMode("Merge");
			ButtonReset();
			mergeSortButton.set_color(constant.BLACK);
		}

		mergeSortButton.update();

		if (heapSortButton.isClicked()){
			sort.setMode("Heap");
			ButtonReset();
			heapSortButton.set_color(constant.BLACK);
		}

		heapSortButton.update();



		bubbleSortButton.update();

		if (selectionSortButton.isClicked()){
			sort.setMode("Selection");
			ButtonReset();
			selectionSortButton.set_color(constant.BLACK);
		}

		selectionSortButton.update();

		if (insertionSortButton.isClicked()){
			sort.setMode("Insertion");
			ButtonReset();
			insertionSortButton.set_color(constant.BLACK);
		}

		insertionSortButton.update();
	}

	public void SliderProcessing(){
		TimeStepSlider.update();
		TimeStepSlider.render();

		// draw Time step
		textAlign(CENTER, CENTER);
		fill(255);
		textSize(20);
		int tStep = round(map(TimeStepSlider.getValue(), 0, TimeStepSlider.length, MIN_TIME, MAX_TIME));
		text("Time Step: " + tStep + " ms", TimeStepSlider.Pos.x + TimeStepSlider.length/2, 50);

		if (frameCount % 20 == 0){
			sort.setTimeStep(tStep);
		}

		numArraySlider.update();
		numArraySlider.render();

		// draw number of elements
		textAlign(CENTER, CENTER);
		fill(255);
		textSize(20);
		num_array = round(map(numArraySlider.getValue(), 0, numArraySlider.length, MIN_LENGTH, MAX_LENGTH));
		text("Number of Elements: " + num_array, numArraySlider.Pos.x + numArraySlider.length/2, 100);

	}

	public void draw(){
		background(51);

		if (sorted && !called_time){
			end_time = System.currentTimeMillis();
			called_time = true;
		}

		if (done && !sorted){
			start_time = System.currentTimeMillis();
		}

		if (!sorted){
			curTime = System.currentTimeMillis() - start_time;
		}

		else{
			curTime = end_time - start_time;
		}

		panel.render();

		SliderProcessing();

		// Set time step every 20 frame
		if (frameCount % 20 == 0){
			int tStep = (int)map(TimeStepSlider.getValue(), 0, TimeStepSlider.length, 0, 100);
			sort.setTimeStep(tStep);
		}


		// check if array is sorted (Done)
		if (sort.check(arr)){
			sorted = true;
			done = true;
			sort.setTimeStep(TimeStepSlider.getValue());
		}
		else{
			sorted = false;
			called_time = false;
		}

		
		// draw array
		for(int i = 0; i < arr.length; i++){
			// stroke(255);
			try {
				int[] c = sort.get_color(i);
				fill(c[0], c[1], c[2]);
			} catch (ArrayIndexOutOfBoundsException e) {
				fill(255);
			}
			rect(i*w, (float)height - arr[i].floatValue(), w, arr[i].floatValue());
		}

		ButtonProcessing();
	}

	static public void main(String[] passedArgs) {
		String[] appletArgs = new String[] { "Project" };
		if (passedArgs != null) {
			PApplet.main(concat(appletArgs, passedArgs));
		} else {
			PApplet.main(appletArgs);
		}
	}
}

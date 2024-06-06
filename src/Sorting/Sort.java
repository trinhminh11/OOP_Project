package Sorting;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public abstract class Sort<T extends Comparable<T>> {
	// Thread thread = Thread.currentThread();
	// Thread thread = new Thread(Runnable, "test");
	protected int[][] colors;
	protected int timeStep;
	private boolean started = false;

	protected Thread main_Thread;

	public String bestComplexity;
	public String averageComplexity;
	public String worstComplexity;

	public int compared = 0;
	public int swapped = 0;

	protected CONST constant = new CONST();


	private static MidiChannel[] channels;
	private static int INSTRUMENT = 6; // 0 is a piano, 9 is percussion, other channels are for other instruments
	private static int VOLUME = 80;

	public Sort(int timeStep){
		this.timeStep = timeStep;
		Synthesizer synth;
		try {
			synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();


		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void await(int i){
		int note_min = 80;
		int note_max = 100;

		float note = (float)i / (float)colors.length;
		note = note*(note_max-note_min) + note_min;


		try {
			play((int) note, timeStep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void await(int i, int time){
		int note_min = 80;
		int note_max = 100;

		float note = (float)i / (float)colors.length;
		note = note*(note_max-note_min) + note_min;


		try {
			play((int) note, time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setTimeStep(int x){
		timeStep = x;
	}

	private static void play(int i, int duration) throws InterruptedException{
		// * start playing a note
		// channels[INSTRUMENT].noteOn(id(note), VOLUME );
		channels[INSTRUMENT].noteOn(i, VOLUME );

		// * wait
		Thread.sleep( duration );
		// * stop playing a note
		channels[INSTRUMENT].noteOff(i);
		// channels[INSTRUMENT].noteOff(id(note));
	}

	protected void setup_color(int length){
		colors = new int[length][3];
		reset_color(0, length-1);
	}

	protected void set_color(int left, int right, int[] color){
		try {
			for (int i = left; i <= right; i++){
				colors[i] = color;
			}
		} catch (Exception e) {
		}
		
	}

	protected void reset_color(int left, int right){
		set_color(left, right, constant.WHITE);
	}

	public int[] get_color(int idx){
		if (this.colors == null){
			return new int[]{255, 255, 255};
		}
		return colors[idx];
	}

	public String[] getTimeComplexity(){
		return new String[]{bestComplexity, averageComplexity, worstComplexity}; 
	}

	public boolean isDone(T[] arr){
		if (!started || main_Thread == null){
			for (int i = 1; i < arr.length; i++){
				if (arr[i].compareTo(arr[i-1]) < 0){
					return false;
				}
			}
			started = false;
			return true;
		}

		if (main_Thread.isAlive()){
			return false;
		}

		started = false;
		
		return true;
	}

	protected void swap(T[] arr, int i, int j){
		T temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
    }

	protected abstract void _sort(T[] arr);

	public void sort(T[] arr){
		started = true;
		compared = 0;
		swapped = 0;
		
		setup_color(arr.length);
		main_Thread = new Thread(new Runnable() {
			@Override
			public void run() {
				_sort(arr);

				for (int i = 0; i < arr.length; i++){
					await(i);
					int c = (int) (i*(360/arr.length));
					colors[i] = constant.hueToRGB(c);
				}
			}
		});
		main_Thread.start();
	}
	

	
}

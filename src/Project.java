/* autogenerated by Processing revision 1293 on 2024-04-04 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import java.util.Random;
// import java.util.HashMap;
// import java.util.ArrayList;
// import java.io.File;
// import java.io.BufferedReader;
// import java.io.PrintWriter;
// import java.io.InputStream;
// import java.io.OutputStream;
// import java.io.IOException;

import Sorting.*;

public class Project extends PApplet {
	private final int RANDOM_SEED = 0;
	private CONST constant = new CONST();
	private Random rand = new Random(RANDOM_SEED);
	private int visual_height = 540;
	private int ui_distance = 50;
	private int ui_height = 250;
	private int w = 10;
	private Double[] arr;

	private boolean done = true;
	private boolean reseted = true;

	public class Button{
		PVector Pos = new PVector(0, 0);
		float Width = 0;
		float Height = 0;
		int[] c;
		String Text;
		int tsize;
		int[] tc;
		Boolean Pressed = false;
		Boolean Clicked = false;

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

		void render(){
			// stroke(0);
			fill(c[0], c[1], c[2]);
			rect(Pos.x, Pos.y, Width, Height);
			fill(tc[0], tc[1], tc[2]);
			textAlign(CENTER, CENTER);
			textSize(tsize);
			text(Text, Pos.x + (Width/2), Pos.y + (Height/2));
		}

		boolean isClicked(){
			return Clicked;
		}
	}

	public class Panel{
		private int[] c = constant.GRAY[105];
		void render(){
			stroke(0);
			fill(c[0], c[1], c[2]);
			rect(0, 0, width, ui_height);
		}
	}

	QuickSort<Double> quick = new QuickSort<Double>("hoare");
	
	Panel panel = new Panel();

	Button startbutton, resetbutton;

	public void settings(){
		width = 1200;
		height = visual_height + ui_height + ui_distance;
	}

	public void random_arr(){
		arr = new Double[width/w];
		for (int i = 0; i < arr.length; i++){
			arr[i] = rand.nextDouble(visual_height);
		}
	}

	public void setup(){
		random_arr();

		frameRate(60);
		startbutton = new Button(width-250, 50, 200, 100, "Start", 50, constant.GRAY[51], constant.WHITE);

		resetbutton = new Button(width-250-250, 50, 200, 100, "Reset", 50, constant.GRAY[51], constant.WHITE);

	}

	public void draw(){
		background(51);

		panel.render();

		if (startbutton.isClicked()){
			if (reseted){
				quick.sorting(arr);
				reseted = false;
				done = false;
			}
		}

		if (resetbutton.isClicked()){
			if (done == true){
				random_arr();
				reseted = true;
			}
		}

		if (quick.check(arr)){
			done = true;
		}

		for(int i = 0; i < arr.length; i++){
			stroke(0);
			int[] c = quick.get_color(i);
			fill(c[0], c[1], c[2]);
			rect(i*w, height - arr[i].floatValue(), w, arr[i].floatValue());
		}

		startbutton.update();
		startbutton.render();

		resetbutton.update();
		resetbutton.render();
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

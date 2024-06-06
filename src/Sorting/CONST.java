package Sorting;


public class CONST {
	public int[] WHITE = new int[]{255, 255, 255};
	public int[] RED = new int[]{255, 0, 0};
	public int[] GREEN = new int[]{0, 255, 0};
	public int[] BLUE = new int[]{0, 0, 255};
	public int[] BLACK = new int[]{0, 0, 0};
	public int[] YELLOW = new int[]{255, 255, 0};
	public int[][] GRAY = new int[255][3];

	public CONST(){
		for (int i = 0; i < 255; i++){
			GRAY[i] = new int[]{i, i, i};
		}
	}

	private int _hueToRGB(int n, int H){
		double k = (n + (double)H/60) % 6;

		double fn = 1 - Math.max(0, Math.min(k, Math.min(4-k, 1)));

		return (int) (fn*255);
	}

	int[] hueToRGB(int H){
	
		return new int[]{_hueToRGB(5, H), _hueToRGB(3, H), _hueToRGB(1, H)};
	}

	int[] huetoRGB(double H){
		H *= 360;
		int _H = (int)H;
		return new int[]{_hueToRGB(5, _H), _hueToRGB(3, _H), _hueToRGB(1, _H)};
	}
	
}

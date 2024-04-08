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
}

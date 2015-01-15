public class Board {

	public int width;		// The width of the board	
	public int height;		// The height of the board
	public int length;		// The length of the board

	public Board(int w, int h, int l) {
		width = w;
		height = h;
		length = l;
	}
				
	public int getBoardSize() {
		return width*height*length;
	}
}

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Board extends JComponent {

	public int width;		// The width of the board	
	public int height;		// The height of the board
	public int length;		// The length of the board
	public int blockSize;	// The size of each block
	
	public int[][][] values; // the array of values of the blocks
	
	// the colors of the blocks, so that we can see if it works
	private Color[] colors = new Color[] {	new Color(255,255,255,200),
													new Color(255,0,0),
													new Color(0,255,0),
													new Color(0,0,255),
													new Color(255,255,0),
													new Color(0,255,255),
													new Color(255,0,255),
													new Color(100,100,100),
													new Color(20,20,20),
													new Color(200,0,100),
													new Color(200,100,0),
													new Color(100,0,200),
								 					new Color(0,200,100)};
	

	public Board(int w, int h, int l, int b) {
		width = w;
		height = h;
		length = l;
		
		blockSize = b;
		
		// setting the values to 0 (aka creating a sparce matrix)
		values = new int[width][height][length];
		
		// initalize the frame and component
		init();
	}
				
	public int getBoardSize() {
		return width*height*length;
	}
	
	private void init() {
		// some drawing stuff
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setBounds(200, 200, (width + 1) * blockSize, (height + 1) * blockSize);
		
		window.getContentPane().add(this);
		window.setVisible(true);
	}
	
	// the actual drawing of the graphics
	public void paint(Graphics g) {
		drawGrid(g);
	}
	
	// drawing the grid using the graphics object
	public void drawGrid(Graphics g) {
		for (int z = 0; z < length; z++)
			for (int y = 0; y < height; y++)
				for (int x = 0; x < width; x++) {
					
					g.setColor(colors[ values[x][y][z] ]);
					g.fillRect (x * blockSize, y * blockSize, blockSize, blockSize);
					g.setColor(new Color(0,0,0,75));
					g.drawRect (x * blockSize, y * blockSize, blockSize, blockSize);
				}
	}
	
	// get the list of values aka sparce matrix
	public int[][][] getValues() {
		return values;
	}
}

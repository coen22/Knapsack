import java.sql.Time;
import java.util.ArrayList;

public class Main {
	
	public static int width = 33;		// The width of the board	
	public static int height = 5;		// The height of the board
	public static int length = 8;		// The length of the board
	
	public static int packageTypes[][] = {
		// A
		{2, 2, 4},
		// B
		{2, 3, 4},
		// C
		{3, 3, 3}
	};
	
	// main method, it all starts here
	public static void main(String[] s) {
		long startTime = System.currentTimeMillis();
		
		// this is going to build the sparace matrix, which we're going to use to find all solutions
		SparceMatrix sparceMatrix = new SparceMatrix();

		// convert the 2D arrayList into an array
		int[][] matrix = new int [sparceMatrix.matrix.size()][sparceMatrix.matrix.get(0).size()];
		
		int x = 0;
		for (ArrayList<Integer> r : sparceMatrix.matrix) {
			int y = 0;
			for (Integer i : r) {
				matrix[x][y] = (int) i;
				y++;
			}
			x++;
		}
		
		System.out.println("");
		Search search = new Search(matrix, 0.03);
		
		ArrayList<ArrayList<Integer>> solutions = search.search();

		if (solutions.isEmpty()) {
			System.out.println("No Solution!");
			System.exit(0);
		}
		
		System.out.println("");
		
		int[] finalSolution = new int[matrix.length];
		
		int value = 0;
		
		for (Integer y : solutions.get(solutions.size() - 1)) {
			for (x = 0; x < matrix[0].length; x++) {
				if (matrix[y][x] == 1)
					value += 3;
				
				if (matrix[y][x] == 2)
					value += 4;
				
				if (matrix[y][x] == 3)
					value += 5;
				
				if (matrix[y][x] != 0)
					break;
			}
			
			for (x = 0; x < matrix[0].length; x++)
				finalSolution[x] += matrix[y][x];
		}
		int[][][] solutionBox = new int[width][height][length];
		
		int index = 0;
		
		for (int z = 0; z < length; z++) {
			for (int y = 0; y < height; y++) {
				for (x = 0; x < width; x++) {
					solutionBox[x][y][z] = finalSolution[index];
					index ++;
				}
			}
		}
		
		System.out.println("Packages: " + solutions.get(solutions.size() - 1).size());
		System.out.println("Value: " + value);
		System.out.println("Time: " + (double) (System.currentTimeMillis() - startTime)/1000 + "s");
		System.out.println("Done");
		
		GUI gui = new GUI();
		gui.packages = solutionBox;
		gui.draw();
	}
}

import java.util.ArrayList;

/**
 * The Main Class
 */
public class Main {
	
	/** The width of the truck */
	public static int width = 33;
	
	/** The height of the truck */
	public static int height = 5;
	
	/** The length of the truck */
	public static int length = 8;
	
	/** The package types (parcels) */
	public static int packageTypes[][] = {
		// A
		{2, 2, 4},
		// B
		{2, 3, 4},
		// C
		{3, 3, 3}
	};
	
	/**
	 * The main method which is only there to test the program
	 *
	 * @param args the required arguments
	 */
	public static void main(String[] args) {
		
		// ************************
		//  Generate Sparse Matrix
		// ************************
		
		SparceMatrix sparceMatrix = new SparceMatrix();

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
		
		// ************************
		// 	Search for a Solution
		// ************************
		
		System.out.println("Start!");
		long startTime = System.currentTimeMillis();
		
		Search search = new Search(matrix, 0.03);
		
		// ************************
		// 	  Show the solution
		// ************************
		
		// Duration
		System.out.println("");
		System.out.println("Time: " + (double) (System.currentTimeMillis() - startTime)/1000 + "s");
		
		ArrayList<ArrayList<Integer>> solutions = search.search();

		if (solutions.isEmpty()) {
			System.out.println("No Solution!");
			System.exit(0);
		}
		
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
			
			for (x = 0; x < matrix[0].length; x++) {
				finalSolution[x] += matrix[y][x];
			}
		}
		

		double filled = 0;
		
		for (x = 0; x < matrix[0].length; x++)
			if (finalSolution[x] != 0)
				filled ++;
		
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
		
		System.out.println("Percentage Filled: " + filled / matrix[0].length * 100 + "%");
		System.out.println("Packages: " + solutions.get(solutions.size() - 1).size());
		System.out.println("Value: " + value);
		System.out.println("Done");
		
		GUI gui = new GUI();
		gui.packages = solutionBox;
		gui.draw();
	}
}

import java.util.ArrayList;


public class Main {
	
	// main method, it all starts here
	public static void main(String[] s) {
		// this makes the board so that thing get drawn
		Board board = new Board(33, 5, 8);
		
		// this is going to build the sparace matrix, which we're going to use to find all solutions
		SparceMatrix sparceMatrix = new SparceMatrix(board);

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
		Search search = new Search(matrix, 0.5);
		
		ArrayList<Integer> solution = search.search();
		
		if (!solution.isEmpty())
			System.out.println("Solution: " + solution);
		else
			System.out.println("No Solution");

		int value = 0;
		
		if (!solution.isEmpty()) {
			for (Integer i : solution)
				inner: for (Integer j : sparceMatrix.matrix.get(i)) {
					if (j == 1)
						value += 3;
					
					if (j == 2)
						value += 4;
					
					if (j == 3)
						value += 5;
					
					if (j != 0)
						break inner;
				}
		}
		
		System.out.println("Value: " + value);
		System.out.println("Done");
	}
}

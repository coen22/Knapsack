import java.util.ArrayList;


public class Main {
	
	// main method, it all starts here
	public static void main(String[] s) {
		// this makes the board so that thing get drawn
		Board board = new Board(33, 6, 8);
		
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
		Search search = new Search(matrix, 0.01);
		
		ArrayList<Integer> solution = search.search();
		
		if (!solution.isEmpty())
			System.out.println("Solution: " + solution);
		else
			System.out.println("No Solution");

		if (!solution.isEmpty()) {
			for (Integer i : solution) {
				System.out.println("");
				for (Integer j : sparceMatrix.matrix.get(i))
					System.out.print(j + " ");
			}
		}
		
		System.out.println("");
		System.out.println("Done");
		
	}
}

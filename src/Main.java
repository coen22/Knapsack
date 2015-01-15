import java.util.ArrayList;


public class Main {
	
	// main method, it all starts here
	public static void main(String[] s) {
		// this makes the board so that thing get drawn
		Board board = new Board(4, 4, 4);
		
		//	Grid grid = new Grid(board);
		
		// Search search = new Search();
		// search.search(grid);
		
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
		
		
		// making an instance of the solver
		Solver solver = new Solver();
		
		System.out.println("");
		solver.search(matrix, sparceMatrix.rows, sparceMatrix.columns, sparceMatrix.results, board);
		System.out.println("");
		System.out.println("Done");
		System.out.println("");
	}
}

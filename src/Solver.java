import java.util.ArrayList;
public class Solver{
	public ArrayList<Integer> search(int[][] matrix, ArrayList<Integer> rows, ArrayList<Integer> cols, ArrayList<Integer> res, Board board) {
	       if (cols.isEmpty() && rows.isEmpty()) {                        //if matrix is empty print and return solution
	           System.out.println(res);
               return res;
	       }
	    	   
	       // stopping conditions
	       if (prune(matrix, rows, cols, res))
		       return res;
	       
	       int col = cols.get(0);
	       
	       for (int r = 0; r < matrix.length; r++) {
	           if (rows.contains(r) && matrix[r][col] == 1) {
	        	   		// add this move to the solution
	        	   		res.add(r);
	               
	               // we're going to remember these in case we don't find a solution
	               ArrayList<Integer> tempCol = new ArrayList<Integer>();
	               tempCol.addAll(cols);
	               
	               ArrayList<Integer> tempRow = new ArrayList<Integer>();    
	               tempRow.addAll(rows);
	               
	               for (int y = 0; y < matrix[r].length; y++) {
	                   if (cols.contains(y) && matrix[r][y] == 1) {
	                       for(int x = 0; x < matrix.length; x++) {
	                           if (rows.contains(x) && matrix[x][y] == 1 && x != r) {	                        	   
	            	               // we're gonna ignore row r
	                        	   	rows.remove(new Integer(x));
	                           }
	                       }
	                       
	                       // delete column c from the columns
	                       cols.remove(new Integer(y));
	                   }
	               }
	               
	               // we're gonna ignore row r
	               rows.remove(new Integer(r));

	               // for debugging draw the board
	               drawCurrentState(matrix, res, board);
	               
	               // search the next one
	               search(matrix, rows, cols, res, board);
	               
	               // we're gonna step back, because we don't find a solution
	               cols.clear();
	               cols.addAll(tempCol);
	               
	               rows.clear();                                            
	               rows.addAll(tempRow);
	               
	               res.remove(new Integer(r));
	           }
	       }
	       
	       return res;
	}
	
	private boolean prune(int[][] matrix, ArrayList<Integer> rows, ArrayList<Integer> cols, ArrayList<Integer> res) {
		int[] finished = new int[matrix[0].length];
		
		// create the array of the finished results
		for(Integer i : res) {
			int[] row = matrix[(int) i];
			
			for(int j = 0; j < 72; j++) {
				
			}
		}
		
		return false;
	}
	
	private void drawCurrentState(int[][] matrix, ArrayList<Integer> res, Board board) {
		cleanBoard(board);
		
		for(Integer i : res) {
			drawLine(matrix[(int) i], board);
		}
	}

	private void cleanBoard(Board board) {
		
		board.values = new int[board.width][board.height][board.length];
	}
	
	private void drawLine(int[] row, Board board) {
		// first we're gonna look what color to use
		int color = 1;
		
		// we're gonna step through the blocks
		for (int z = 0; z < board.length; z++) {
			for (int y = 0; y < board.height; y++) {
				for (int x = 0; x < board.width; x++) {
					
					if (row[x + (y + z * board.length) * board.height] == 1)
						board.values[x][y][z] = color;
					
				}
			}
		}
		
		board.repaint();
	}
}
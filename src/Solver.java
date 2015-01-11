import java.util.ArrayList;
import java.util.Random;

public class Solver{
	public ArrayList<Integer> search(int[][] matrix, ArrayList<Integer> rows, ArrayList<Integer> cols, ArrayList<Integer> res, Board board) {
			//if matrix is empty print and return solution
			if (cols.isEmpty() && rows.isEmpty()) {
				System.out.println(res);
				return res;
			}
			
	       //stopping conditions
	       // if (prune(matrix, rows, cols, res))
		   //    return res;
	       
	       int col = cols.get(0);
	       
	       for (int r = 0; r < matrix.length; r++) {
	           if (rows.contains(r) && matrix[r][col] > 0) {
	        	   		// add this move to the solution
	        	   		res.add(r);
	               
	               // we're going to remember these in case we don't find a solution
	               ArrayList<Integer> tempCol = new ArrayList<Integer>();
	               tempCol.addAll(cols);
	               
	               ArrayList<Integer> tempRow = new ArrayList<Integer>();    
	               tempRow.addAll(rows);
	               
	               for (int y = 0; y < matrix[r].length; y++) {
	                   if (cols.contains(y) && matrix[r][y] > 0) {
	                       for(int x = 0; x < matrix.length; x++) {
	                           if (rows.contains(x) && matrix[x][y] > 0 && x != r) {	                        	   
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
	       
	       printCurrentSolution(res);
	       
	       return res;
	}
	
	private void printCurrentSolution(ArrayList<Integer> res) {		
		System.out.println("");
		for(Integer i : res) {
			System.out.print(i + ", ");
		}
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
		// WIP
	}
}
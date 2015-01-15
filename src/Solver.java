import java.util.ArrayList;

public class Solver{
	public ArrayList<ArrayList<Integer>> solutions = new ArrayList<ArrayList<Integer>>();
	
	private int thresholt = 0;
	final private double percentage = 0.1;
	
	public ArrayList<Integer> search(int[][] matrix, ArrayList<Integer> rows, ArrayList<Integer> cols, ArrayList<Integer> res, Board board) {
	
		if (thresholt == 0) {
			thresholt = (int) (cols.size() * percentage); 
			System.out.println("Columns Threshholt " + thresholt);
		}
		
		if (cols.size() < thresholt) {
			System.out.println("");
			System.out.println("Open Spaces: " + cols.size());
			for (Integer i : res) {
				for (int j = 0; j < matrix[0].length; j++)
					System.out.print(matrix[i][j] + " ");
				System.out.print(": " + i);
				System.out.println("");
			}
			
			solutions.add(res);
			return res;
		}
		
		int col = cols.get(0);
       
		for (Integer row : rows) {
           if (matrix[row][col] > 0) {
        	   		// add this move to the solution
        	   		res.add(row);
               
               // we're going to remember these in case we don't find a solution
               ArrayList<Integer> tempCol = new ArrayList<Integer>();
               tempCol.addAll(cols);
               
               ArrayList<Integer> tempRow = new ArrayList<Integer>();    
               tempRow.addAll(rows);
               
               for (int y = 0; y < matrix[row].length; y++) {
                   if (cols.contains(y) && matrix[row][y] > 0) {
                       for(int x = 0; x < matrix.length; x++) {
                           if (rows.contains(x) && matrix[x][y] > 0 && x != row) {	                        	   
            	               // we're gonna ignore row r
                        	   	tempRow.remove(new Integer(x));
                           }
                       }
                       
                       // delete column c from the columns
                       tempCol.remove(new Integer(y));
                   }
               }
               
               // we're gonna ignore row r
               tempRow.remove(new Integer(row));

               // search the next one
               search(matrix, tempRow, tempCol, res, board);

               res.remove(new Integer(row));
           	}
		}
		
		return res;
	}
}
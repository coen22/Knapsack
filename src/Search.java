import java.util.ArrayList;


public class Search {
	public ArrayList<Integer> res = new ArrayList<Integer>();
	
	public ArrayList<Integer> search(Grid grid) {
		ArrayList<Integer> rows = new ArrayList<Integer>();
		
		for (int i = 0; i < grid.matrixGrid.size(); i++)
			rows.add(rows.size());
		
		return search(grid.matrixGrid, rows);
	}
	
	private ArrayList<Integer> search(ArrayList<ArrayList<Node>> grid, ArrayList<Integer> rows) {
		if (rows.isEmpty()) {
			System.out.println(res);
			return res;
		}
		
		for (Integer row : rows) {
			res.add(row);
			
			ArrayList<Integer> tRows = new ArrayList<Integer>();
			tRows.addAll(rows);
			
			for (Node node : grid.get(row)) {
				for (Integer r : node.cover()) {
					if (tRows.contains(r))
							tRows.remove(r);
				}
			}
			
			search(grid, tRows);
			
			res.remove(row);
		}
		
		return res;
	}
}

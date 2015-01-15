import java.util.ArrayList;

public class Node {
	public Node header;
	public Node left;
	public Node right;
	public Node up;
	public Node down;
	
	public int x;
	public int y;
	public int z;
	
	public int row;
	
	public Node(Node up, Node down, int x, int y, int z, int row) {
		this.up = up;
		this.down = down;
		this.x = x;
		this.y = y;
		this.z = z;
		this.row = row;
	}
	
	public ArrayList<Integer> cover() {
		ArrayList<Integer> rows = new ArrayList<Integer>();
		
		if (up != null) {
			up.down = down;
			rows.addAll(up.cover());
		}
		
		if (down != null) {
			down.up = up;
			rows.addAll(down.cover());
		}
		
		up = null;
		down = null;
		
		rows.add(row);

		return rows;
	}
}

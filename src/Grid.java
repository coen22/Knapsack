import java.util.ArrayList;


public class Grid {
	// the grid
	public ArrayList<ArrayList<Node>> matrixGrid = new ArrayList<ArrayList<Node>>();
	
	// This class contains all the pentomino data
	private Board board;
	
	public Grid(Board board) {
		this.board = board;
		
		System.out.println(board.getBoardSize());
		
		makeMatrix();
	}

	public void makeMatrix() {
		for (int i = 0; i < PackageInfo.packageTypes.length; i++) 
			makePossibleRotations(i);
	}
	
	// for every possible rotation we need to make a couple of rows
	public void makePossibleRotations(int piece) {
		int[] size = PackageInfo.packageTypes[piece].clone();
		makePossibleTranslations(piece, size);
		
		size[0] = PackageInfo.packageTypes[piece][2];
		size[2] = PackageInfo.packageTypes[piece][0];
		if (size[0] != size[2])
			makePossibleTranslations(piece, size);
		
		size[0] = PackageInfo.packageTypes[piece][0];
		size[1] = PackageInfo.packageTypes[piece][2];
		size[2] = PackageInfo.packageTypes[piece][1];
		if (size[1] != size[2])
			makePossibleTranslations(piece, size);
		
		size[0] = PackageInfo.packageTypes[piece][0];
		size[2] = PackageInfo.packageTypes[piece][1];
		if (size[0] != size[2])
			makePossibleTranslations(piece, size);
		
		size[0] = PackageInfo.packageTypes[piece][1];
		size[1] = PackageInfo.packageTypes[piece][0];
		size[2] = PackageInfo.packageTypes[piece][2];
		if (size[0] != size[1])
			makePossibleTranslations(piece, size);
		
		size[0] = PackageInfo.packageTypes[piece][2];
		size[2] = PackageInfo.packageTypes[piece][1];
		if (size[0] != size[2])
			makePossibleTranslations(piece, size);
	}
	
	// for every single piece orientation we need to look for all possible translations
	public void makePossibleTranslations(int piece, int[] size) {
		if (isIllegal(size))
			return;
		
		for (int z = 0; z <= board.length - size[2]; z++) 
			for (int y = 0; y <= board.height - size[1] ; y++) 
				for (int x = 0; x <= board.width - size[0]; x++) 
					makeRow(piece, size, x, y, z);
	}
	
	public void makeRow(int piece, int[] size, int x, int y, int z) {
		ArrayList<Node> row = new ArrayList<Node>();
		
		// to increase performance variables are pre-declared
		Node currentNode = null;
		Node upNode = null;
		
		for (int iz = 0; iz < size[2]; iz++) 
			for (int iy = 0; iy < size[1]; iy++) 
				for (int ix = 0; ix < size[0]; ix++) {
					upNode = getNodeAbove(matrixGrid.size() - 1, ix, iy, iz);
					
					currentNode = new Node(upNode, null, ix, iy, iz, matrixGrid.size());
					
					row.add(currentNode);
					
					if (upNode != null)
						upNode.down = currentNode;
				}
		
		matrixGrid.add(row);
	}
	
	private boolean isIllegal(int[] size) {
		if (size[0] > board.width ||
			size[1] > board.height ||
			size[2] > board.length)
			return true;
		
		return false;
	}
	
	private Node getNodeAbove(int row, int x, int y, int z) {
		if (row < 0)
			return null;
		
		for (Node up : matrixGrid.get(row))
			if (up.x == x && up.y == y && up.z == z)
				return up;
		
		Node up = getNodeAbove(row - 1, x, y, z);
		
		return up;
	}
}

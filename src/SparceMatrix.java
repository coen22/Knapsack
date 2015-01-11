import java.util.ArrayList;

public class SparceMatrix {
	public ArrayList<Integer> results = new ArrayList<Integer>(); // the solutions
	
	 // Temporarily for creating rows
	public ArrayList<Integer> row = new ArrayList<Integer>();
	
	public ArrayList<Integer> columns = new ArrayList<Integer>(); // The Header of every column
	public ArrayList<Integer> rows = new ArrayList<Integer>(); // The Header of every row
	
	// The matrix which contains all the possible placements of every pentomino
	public ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
	
	// This class contains all the pentomino data
	private Board board;
	
	public SparceMatrix(Board board) {
		this.board = board;
		
		System.out.println(board.getBoardSize());
		
		makeColumns();
		makeMatrix();
	}
	
	public void makeColumns() {
		for(int y = 0; y < board.getBoardSize(); y++) {
			columns.add(y);
		}
	}

	public void makeMatrix() {
		for (int i = 0; i < PackageInfo.packageTypes.length; i++) {
			makePossibleRotations(i);
		}
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
		for (int z = 0; z <= board.length - size[2]; z++) {
			for (int y = 0; y <= board.height - size[1] ; y++) {
				for (int x = 0; x <= board.width - size[0]; x++) {
					makeRow(board.getBoardSize(), piece + 1, getBox(piece, x, y, z));
				}
			}
		}
	}
	
	public ArrayList<Integer> getBox(int piece, int x, int y, int z) {
		ArrayList<Integer> num = new ArrayList<Integer>();
		
		// System.out.println("");
		
		for (int iz = z; iz < PackageInfo.packageTypes[piece][2] + z; iz++) {
			for (int iy = y; iy < PackageInfo.packageTypes[piece][1] + y; iy++) {
				for (int ix = x; ix < PackageInfo.packageTypes[piece][0] + x; ix++) {
					num.add(ix + (iy + iz * board.length) * board.height);
					// System.out.println("xyz = (" + ix + ", " + iy + ", " + iz + ")");
				}
			}
		}
		
		return num;
	}
	
	public void makeRow(int boardsize,int color, ArrayList<Integer> num) {
		if (num == null)
			return;
		
		// Reset the row variable
		row = new ArrayList<Integer>();
		
		// add zero values
		for (int i = 0; i < boardsize; i++) {
				row.add(0);
		}
		
		for (int n : num) {
			row.set(n, color);
		}
		
		rows.add(rows.size());
		matrix.add(row);
	}
	
	public void drawMatrix() {
		for (int i : columns) {
			System.out.print(columns.get(i) + " ");
		}
		
		System.out.println("");

		for (ArrayList<Integer> arrayList : matrix) {
			for (int i : arrayList) {
				System.out.print(i + " ");
			}
			System.out.println("");
		}
	}
}

package Project2;

public class Grid {
	private boolean [][] bombGrid;
	private int [][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	public Grid() {
		bombGrid = new boolean[10][10];
		countGrid = new int [10][10];
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		createBombGrid();
		createCountGrid();
	}
	
	public Grid(int rows, int columns) {
		if(rows >= 0)
			numRows = rows;
		else
			numRows = 10;
		if(columns >= 0)
			numColumns = columns;
		else
			numColumns = 10;
		bombGrid = new boolean[numRows][numColumns];
		numBombs = 25;
		countGrid = new int [numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}
	
	public Grid(int rows, int columns, int numBombs) {
		if(rows >= 0)
			numRows = rows;
		else
			numRows = 10;
		if(columns >= 0)
			numColumns = columns;
		else
			numColumns = 10;
		
		countGrid = new int [numRows][numColumns];
		bombGrid = new boolean [numRows][numColumns];
		
		if(numBombs <= numRows * numColumns)
			this.numBombs = numBombs;
		else {
			double realNumBombs;
			realNumBombs = 0.5 * numRows * numColumns;
			
			this.numBombs = (int) realNumBombs;
		}
		createBombGrid();
		createCountGrid();
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public int getNumBombs() {
		return numBombs;
	}
	
	public boolean [][] getBombGrid(){
		boolean [][] difBombGrid = new boolean [numRows][numColumns];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				difBombGrid[i][j] = bombGrid[i][j];
			}
		}
		return difBombGrid;
	}
	
	public int[][] getCountGrid(){
		int [][] difCountGrid = new int [numRows][numColumns];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				difCountGrid[i][j] = countGrid[i][j];
			}
		}
		return difCountGrid;
	}
	
	public boolean isBombAtLocation(int row, int column) {
		return bombGrid[row][column];
	}
	
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}
	
	private void createBombGrid() {
		for(int i = 1;i <= numBombs; i++) {
			int rowNum = (int) ((Math.random() * (numRows)));
			int columnNum = (int) ((Math.random() * (numColumns)));
			while(bombGrid[rowNum][columnNum] == true) {
				rowNum = (int) ((Math.random() * (numRows)));
				columnNum = (int) ((Math.random() * (numColumns)));
			}
			bombGrid[rowNum][columnNum]= true;
		}
	}
	
	
	private void createCountGrid() {
	    for (int i = 0; i < numRows; i++) {
	        for (int j = 0; j < numColumns; j++) {
	            int counter = 0;
	            // Iterate through the neighbors of the current cell
	            for (int sideRow = -1; sideRow <= 1; sideRow++) {
	                for (int sideColumn = -1; sideColumn <= 1; sideColumn++) {
	                    // Calculate neighbor coordinates
	                    int neighborRow = i + sideRow;
	                    int neighborCol = j + sideColumn;
	                    // Check if neighbor is within the grid bounds and is a bomb
	                    if (neighborRow >= 0 && neighborRow < numRows &&
	                        neighborCol >= 0 && neighborCol < numColumns &&
	                        bombGrid[neighborRow][neighborCol]) {
	                        counter++;
	                    }
	                }
	            }
	            // Assign bomb count to the current cell
	            countGrid[i][j] = counter;
	        }
	    }
	}
}


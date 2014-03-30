// Copyright Rachael Colley 2013.

package net.playcabbage.model;



public class GridCellBean {
	
	private int gridcellmasterID;
	private int z;
	private int y;
	private int startx;
	private int endx;
	private int cellcategoryID;
	
	
	public GridCellBean() {
		
	}
	
	public GridCellBean (int gridcellmasterID, int z, int y, int startx, int endx, int cellcategoryID) {
		setGridcellmasterID(gridcellmasterID);
		setZ(z);
		setY(y);
		setStartx(startx);
		setEndx(endx);
		setCellcategoryID(cellcategoryID);
	}

	public int getGridcellmasterID() {
		return gridcellmasterID;
	}

	public void setGridcellmasterID(int gridcellmasterID) {
		this.gridcellmasterID = gridcellmasterID;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getStartx() {
		return startx;
	}

	public void setStartx(int startx) {
		this.startx = startx;
	}

	public int getEndx() {
		return endx;
	}

	public void setEndx(int endx) {
		this.endx = endx;
	}

	public int getCellcategoryID() {
		return cellcategoryID;
	}

	public void setCellcategoryID(int cellcategoryID) {
		this.cellcategoryID = cellcategoryID;
	}
	
	
	
}
	
//	public boolean equals(Object aThat){
//		if ( this == aThat ) return true; // Compares the memory location - does 'this' have the same memory location as 'that'?.
//		if ( !(aThat instanceof Location) ) return false; // Checks the type of object - instanceof compares an object to a specified type
//		GridCellBean that = (GridCellBean)aThat; // Cast to InventoryItem type so that we can call it's methods
//		return // Returns true or false based on each of the conditions.
//				this.getCellCategory() == that.getCellCategory() &&
//				this.getLocation() == that.getLocation();
//	}
//	
//	public String toString() {
//		return "GridCell - cellCategory: " + this.getCellCategory() 
//				+ " location: " + this.getLocation().toString();
//	}

	
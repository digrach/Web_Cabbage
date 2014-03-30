// Copyright Rachael Colley 2013.

package net.playcabbage.model;

public class WorldParameters {

	private int minGridX, maxGridX, minGridY, maxGridY, minGridZ, maxGridZ;

	public WorldParameters(int minXCoordinate, int maxXCoordinate, int minYCoordinate, 
			int maxYCoordinate, int minZCoordinate, int maxZCoordinate) {

		this.setMinGridX(minXCoordinate);
		this.setMaxGridX(maxXCoordinate);
		this.setMinGridY(minYCoordinate);
		this.setMaxGridY(maxYCoordinate);
		this.setMinGridZ(minZCoordinate);
		this.setMaxGridZ(maxZCoordinate);
	}
	
	public WorldParameters() {

		this.setMinGridX(1);
		this.setMaxGridX(153);
		this.setMinGridY(1);
		this.setMaxGridY(88);
		this.setMinGridZ(-4);
		this.setMaxGridZ(4);
	}

	public int getMinGridX() {
		return minGridX;
	}

	public void setMinGridX(int minGridX) {
		this.minGridX = minGridX;
	}

	public int getMaxGridX() {
		return maxGridX;
	}

	public void setMaxGridX(int maxGridX) {
		this.maxGridX = maxGridX;
	}

	public int getMinGridY() {
		return minGridY;
	}

	public void setMinGridY(int minGridY) {
		this.minGridY = minGridY;
	}

	public int getMaxGridY() {
		return maxGridY;
	}

	public void setMaxGridY(int maxGridY) {
		this.maxGridY = maxGridY;
	}

	public int getMinGridZ() {
		return minGridZ;
	}

	public void setMinGridZ(int minGridZ) {
		this.minGridZ = minGridZ;
	}

	public int getMaxGridZ() {
		return maxGridZ;
	}

	public void setMaxGridZ(int maxGridZ) {
		this.maxGridZ = maxGridZ;
	}


}

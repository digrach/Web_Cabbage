// Copyright Rachael Colley 2013.

package net.playcabbage.model;

public class Location {

	private int xCoord;
	private int yCoord;
	private int zCoord;
	private double latitudeCoord;
	private double longitudeCoord;

	public Location(int xCoordinate, int yCoordinate, int zCoordinate) {
		setXCoordinate(xCoordinate);
		setYCoordinate(yCoordinate);
		setZCoordinate(zCoordinate);
		this.setLatitudeCoordinate(0);
		this.setLongitudeCoordinate(0);
	}

	public Location(int xCoordinate, int yCoordinate, int zCoordinate, double latitudeCoordinate, double longitudeCoordinate) {
		setXCoordinate(xCoordinate);
		setYCoordinate(yCoordinate);
		setZCoordinate(zCoordinate);
		this.setLatitudeCoordinate(latitudeCoordinate);
		this.setLongitudeCoordinate(longitudeCoordinate);
	}

	public boolean equals(Object aThat){
		if ( this == aThat ) return true; // Compares the memory location - does 'this' have the same memory location as 'that'?.
		if ( !(aThat instanceof Location) ) return false; // Checks the type of object - instanceof compares an object to a specified type
		Location that = (Location)aThat; // Cast to InventoryItem type so that we can call it's methods
		return // Returns true or false based on each of the conditions.
				this.getXCoordinate() == that.getXCoordinate() &&
				this.getYCoordinate() == that.getYCoordinate() &&
				this.getZCoordinate() == that.getZCoordinate() &&
				this.getLatitudeCoordinate() == that.getLatitudeCoordinate() &&
				this.getLongitudeCoordinate() == that.getLongitudeCoordinate();
	}

	public String toString() {
		return "Location - x: " 
				+ this.getXCoordinate() 
				+ " y: " + this.getYCoordinate() 
				+ " z: " + this.getZCoordinate()
				+ " lat: " + this.getLatitudeCoordinate()
				+ " long: " + this.getLongitudeCoordinate();
	}

	public int getXCoordinate() {
		return xCoord;
	}

	public void setXCoordinate(int xCoordinate) {
		xCoord = xCoordinate;
	}

	public int getYCoordinate() {
		return yCoord;
	}

	public void setYCoordinate(int yCoordinate) {
		yCoord = yCoordinate;
	}

	public int getZCoordinate() {
		return zCoord;
	}

	public void setZCoordinate(int zCoord) {
		this.zCoord = zCoord;
	}

	public double getLongitudeCoordinate() {
		return longitudeCoord;
	}

	public void setLongitudeCoordinate(double longitudeCoordinate) {
		this.longitudeCoord = longitudeCoordinate;
	}

	public double getLatitudeCoordinate() {
		return latitudeCoord;
	}

	public void setLatitudeCoordinate(double latitudeCoordinate) {
		this.latitudeCoord = latitudeCoordinate;
	}



}

// Copyright Rachael Colley 2013.

package net.playcabbage.model;

import java.util.ArrayList;
import java.util.List;

import net.playcabbage.collectibles.*;



public class GridCell {
	
	private Location location;
	private String cellCategory;
	private List<ICollectible> collectibles;
	
	public GridCell() {
		
	}
	
	public GridCell (String cellCategory, Location location) {
		setCellCategory(cellCategory);
		setLocation(location);
		collectibles = new ArrayList<ICollectible>();
	}
	
	public GridCell (String cellCategory, int z, int y, int x) {
		setCellCategory(cellCategory);
		Location l = new Location(x,y,z);
		setLocation(l);
		collectibles = new ArrayList<ICollectible>();
	}
	
	public boolean equals(Object aThat){
		if ( this == aThat ) return true; // Compares the memory location - does 'this' have the same memory location as 'that'?.
		if ( !(aThat instanceof Location) ) return false; // Checks the type of object - instanceof compares an object to a specified type
		GridCell that = (GridCell)aThat; // Cast to InventoryItem type so that we can call it's methods
		return // Returns true or false based on each of the conditions.
				this.getCellCategory() == that.getCellCategory() &&
				this.getLocation() == that.getLocation();
	}
	
	public String toString() {
		return "GridCell - cellCategory: " + this.getCellCategory() 
				+ " location: " + this.getLocation().toString();
	}

	public Location getLocation() {
		return location;
	}

	public String getCellCategory() {
		return cellCategory;
	}

	public void setCellCategory(String cellCategory) {
		this.cellCategory = cellCategory;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	public List<ICollectible> getCollectibles() {
		return collectibles;
	}

	public void setCollectibles(List<ICollectible> collectibles) {
		this.collectibles = collectibles;
	}
	
	public void addCollectible(ICollectible collectible) {
		this.collectibles.add(collectible);
	}
	
	public void removeCollectible(ICollectible collectible) {
		this.collectibles.remove(collectible);
	}
	public void removeAllCollectibles() {
		this.collectibles.clear();
	}

}

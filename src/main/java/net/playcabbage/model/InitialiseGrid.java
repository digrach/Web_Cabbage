// Copyright Rachael Colley 2013.

package net.playcabbage.model;


import java.io.FileNotFoundException;
import java.io.InputStream;

public class InitialiseGrid {
	
	private BuildGridFromFile buildGrid;
	private WorldGrid grid;
	private WorldGridManager worldGridManager;
	
	public InitialiseGrid(InputStream[] filePaths, WorldParameters worldparameters) throws FileNotFoundException {
			buildGrid = new BuildGridFromFile(filePaths, worldparameters);
			grid = new WorldGrid(worldparameters);
			grid.setzMap(buildGrid.getWorldGrid()); 
			worldGridManager = new WorldGridManager(grid);
	}
	
//	public void reloadGrid(InputStream[] filePaths, WorldParameters worldparameters) throws FileNotFoundException {
//		buildGrid = new BuildGridFromFile(filePaths, worldparameters);
//		grid.setzMap(buildGrid.getWorldGrid()); 
//	}

//	public WorldGrid getGrid() {
//		return grid;
//	}

	public WorldGridManager getWorldGridManager() {
		return worldGridManager;
	}
	
	

}

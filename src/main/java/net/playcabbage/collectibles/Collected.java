package net.playcabbage.collectibles;

import java.util.ArrayList;

public class Collected {
	
	private ArrayList<ICollectible> collectedItems;
	private PointsTable pointsTable;
	
	public Collected() {
		pointsTable = new PointsTable();
		collectedItems = new ArrayList<ICollectible>();
	}

}

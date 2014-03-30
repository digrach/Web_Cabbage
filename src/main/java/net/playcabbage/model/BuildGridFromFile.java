// Copyright Rachael Colley 2013.

package net.playcabbage.model;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

public class BuildGridFromFile {

	private InputStream[] filePaths;
	private Map<Integer,Map<Integer,Map<Integer,GridCell>>> zMap;
	private WorldParameters wp;

	public BuildGridFromFile(InputStream[] filePaths, WorldParameters worldparameters) throws FileNotFoundException {

		if (checkObjectNotNull(filePaths) && checkObjectNotNull(worldparameters) && checkFilePathLength(filePaths)) {
			this.filePaths = filePaths;
			this.wp = worldparameters;
			zMap = new TreeMap<Integer,Map<Integer,Map<Integer,GridCell>>>();
			startReading(filePaths);
		} else {
			// Throw ////////////////
		}

	}

	private void startReading(InputStream[] fileName) {
		for (int x = 0; x < filePaths.length; x++){
			readLevelData(filePaths[x]);
		}
	}

	private void readLevelData(InputStream fileName) {

		int countFileTokens = 0;

		Scanner sc = null;
		sc = new Scanner(fileName);

		while (sc.hasNext()) {

			String tokenString = sc.next();
			String splitArray[] = tokenString.split(",");
			countFileTokens ++;
			try {

				if (splitArray.length >= 5) {

					int z = Integer.parseInt(splitArray[0]);
					int y = Integer.parseInt(splitArray[1]);
					int startX = Integer.parseInt(splitArray[2]);
					int endX = Integer.parseInt(splitArray[3]);
					String cellCategory = splitArray[4];

					if (z>0) {
						System.out.println("z: " + z);
					}
					
					if (checkTokens(z,y,startX,endX,cellCategory)) {

						for (int xCounter = startX; xCounter < endX + 1; xCounter ++ ) {

							if (addEntry(z, y, xCounter, cellCategory)) {

							} else {
								String msg = "Line: " + countFileTokens + " - DUPLICATE coordinate - the following token will be discarded: " + tokenString;
								System.out.println(msg);
								//writeToLog(msg);
							}
						}
					}

					else {
						String msg = "Line: " + countFileTokens + " - UNEXPECTED token(s) - the following value(s) in the split was not expected for this grid: " + tokenString;
						//writeToLog(msg);
						System.out.println(msg);
					}

				}

			}

			catch (NumberFormatException ex) {
				String msg = "Line: " + countFileTokens + " - PROBLEM with number format - could not convert the following token(s) to int in the split: " + tokenString;
				//writeToLog(msg);
				System.out.println(msg);
				continue;

			}

		}

	}

	private boolean addEntry(int z, int y, int x, String cellCategory) {

		if (! zMap.containsKey(z)) {
			zMap.put(z, new TreeMap<Integer,Map<Integer,GridCell>>());
			zMap.get(z).put(y,new TreeMap<Integer,GridCell>());
			zMap.get(z).get(y).put(x,new GridCell(cellCategory,new Location(x,y,z)));
			return true;
		} else {
			if (! zMap.get(z).containsKey(y)) {
				zMap.get(z).put(y,new TreeMap<Integer,GridCell>());
				zMap.get(z).get(y).put(x,new GridCell(cellCategory,new Location(x,y,z)));
				return true;
			} else {
				if (! zMap.get(z).get(y).containsKey(x)) {
					zMap.get(z).get(y).put(x,new GridCell(cellCategory,new Location(x,y,z)));
					return true;
				} 
			}
		}

		return false;
	}

	private boolean checkTokens(int z, int y, int startX, int endX, String cellCategory) {
		if (! checkZ(z)) return false;
		if (! checkY(y)) return false;
		if (! checkX(startX,endX)) return false;
		if (! checkCategory(cellCategory))return false;
		return true;
	}

	private boolean checkX(int startX, int endX) {
		if (startX > endX) return false;
		if (startX < wp.getMinGridX()) return false;
		if (startX > wp.getMaxGridX()) return false;
		return true;
	}

	private boolean checkY(int y) {
		if (y < wp.getMinGridY()) return false;
		if (y > wp.getMaxGridY()) return false;
		return true;
	}

	private boolean checkZ(int z) {
		if (z < wp.getMinGridZ()) return false;
		if (z > wp.getMaxGridZ()) return false;
		return true;
	}

	private boolean checkCategory(String spaceCat) {
		GridCellTypeEnum.GridCellCategory[] cellCategories = GridCellTypeEnum.GridCellCategory.values();
		for (int x = 0; x < cellCategories.length; x++) {
			if (cellCategories[x].toString().equals(spaceCat)) {
				return true;
			}
		}
		return false;
	}

	private boolean checkObjectNotNull(Object object) {
		if (object != null) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkFilePathLength(InputStream[] filePaths) {
		if (filePaths != null && filePaths.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void getZMapStats(int z) {
		NavigableMap<Integer,Map<Integer,Map<Integer,GridCell>>> zNMap = (NavigableMap<Integer,Map<Integer,Map<Integer,GridCell>>>)zMap;
		System.out.println("z: " + z + ", first key is: " + zNMap.firstKey() + ", last key is: " + zNMap.lastKey());
	}

	public void getYMapStats(int z, int y) {
		NavigableMap<Integer,Map<Integer,GridCell>> yMap = (NavigableMap<Integer,Map<Integer,GridCell>>)zMap.get(z);
		System.out.println("y: " + y + ", first key is: " + yMap.firstKey() + ", last key is: " + yMap.lastKey());
	}

	public void getXMapStats(int z, int y, int x) {
		NavigableMap<Integer,GridCell> xMap = (NavigableMap<Integer,GridCell>)zMap.get(z).get(y);
		System.out.println("x: " + x + ", first key is: " + xMap.firstKey() + ", last key is: " + xMap.lastKey());
	}

	public void printGridStats() {

		int countZ = 0;
		int countY = 0;
		int countX = 0;

		for (Map.Entry<Integer, Map<Integer, Map<Integer, GridCell>>> zEntry : zMap.entrySet()) {
			int zKey = zEntry.getKey();
			Map<Integer, Map<Integer, GridCell>> zValue = zEntry.getValue();
			countZ ++;

			for (Map.Entry<Integer, Map<Integer, GridCell>> yEntry : zValue.entrySet()) {
				int yKey = yEntry.getKey();
				Map<Integer,GridCell> xValue = yEntry.getValue();
				countY ++;

				for (Map.Entry<Integer, GridCell> xEntry : xValue.entrySet()) {
					int xKey = xEntry.getKey();
					GridCell gridcell = xEntry.getValue();
					countX ++;
				}
			}

		}

		System.out.println("Number of Z Entries: " + countZ);
		System.out.println("Number of Y Entries: " + countY);
		System.out.println("Number of X Entries: " + countX);

	}
	
	public boolean checkZMapKeyExists(int zKey) {
		if (! zMap.containsKey(zKey)) return false;
		return true;
	}

	public boolean checkYMapKeyExists(int zKey, int yKey) {
		if (checkZMapKeyExists(zKey)) {
			if (zMap.get(zKey).containsKey(yKey)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean checkXMapKeyExists(int zKey, int yKey, int xKey) {
		if (checkYMapKeyExists(zKey,yKey)) {
			if (zMap.get(zKey).get(yKey).containsKey(xKey)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public WorldParameters getWorldparameters() {
		return wp;
	}
	
	public Map<Integer, Map<Integer, Map<Integer, GridCell>>> getWorldGrid() {
		return zMap;
	}



}

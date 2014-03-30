// Copyright Rachael Colley 2013.

package net.playcabbage.model;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;


public class WorldGrid {

	private Map<Integer,Map<Integer,Map<Integer,GridCell>>> zMap;
	WorldParameters wp;

	public WorldGrid(WorldParameters worldparameters) {
		this.wp = worldparameters;
	}

	public void setzMap(Map<Integer, Map<Integer, Map<Integer, GridCell>>> zMap) {
		this.zMap = zMap;
	}

	public Map<Integer,Map<Integer,GridCell>> getYMapFromZKey(int zKey) {
		if (zMap.containsKey(zKey)) {
			return zMap.get(zKey);
		}
		return null;
	}

	public int[] getLevelExtents(int zKey) {
		int[] levelAreaSize = null;
		if (zMap.containsKey(zKey)) {
			// Get the yMap at the zKey
			NavigableMap<Integer,Map<Integer,GridCell>> yMap = (NavigableMap<Integer,Map<Integer,GridCell>>)zMap.get(zKey);
			// Get the first key of y
			int firstYKey = yMap.firstKey();
			// Get the last key of y
			int lastYKey = yMap.lastKey();

			int firstXKey = 153; // Should be world max
			int lastXKey = 1; // Should be world min
			// Loop through each xMap in yMap.
			for (Map.Entry<Integer,Map<Integer,GridCell>> yEntry : yMap.entrySet()) {
				// Get the current xMap.
				NavigableMap<Integer,GridCell> xMap = (NavigableMap<Integer, GridCell>)yEntry.getValue();
				// Get the firstKey
				if (xMap.firstKey() < firstXKey) {
					firstXKey = xMap.firstKey();
				}
				// Get the lastKey.
				if (xMap.lastKey() > lastXKey) {
					lastXKey = xMap.lastKey();
				}

			}

			int yHeight = lastYKey - firstYKey + 1;
			int xWidth = lastXKey - firstXKey + 1;

			levelAreaSize = new int[6];
			levelAreaSize[0] = xWidth;
			levelAreaSize[1] = yHeight;
			levelAreaSize[2] = firstXKey;
			levelAreaSize[3] = lastXKey;
			levelAreaSize[4] = firstYKey;
			levelAreaSize[5] = lastYKey;

		}
		return levelAreaSize;
	}

	public int[] getAllZKeys() {

		int zKeyArray[] = new int[zMap.size()];
		int count = 0;
		for (Map.Entry<Integer, Map<Integer, Map<Integer, GridCell>>> zEntry : zMap.entrySet()) {
			int zKey = zEntry.getKey();
			zKeyArray[count] = zKey;
			count ++;
		}
		return zKeyArray;
	}

	public Map<Integer, Map<Integer, Map<Integer, GridCell>>> getzMap() {
		return zMap;
	}


}

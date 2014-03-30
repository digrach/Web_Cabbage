// Copyright Rachael Colley 2013.

package net.playcabbage.model;

import java.util.Map;

public class MakePixelArray {

	private WorldGrid wg;
	private Map<Integer,Map<Integer,GridCell>> yMap;
	private int pixelList[];

	public MakePixelArray(int zLevel,WorldGrid worldGrid) {
		this.wg = worldGrid;
		this.yMap = wg.getYMapFromZKey(zLevel);
		buildPixelArray(zLevel);
	}

	private int[] buildPixelArray(int zLevel) {

		int[] extents = wg.getLevelExtents(zLevel);
		int widthX = extents[0];
		int heightY = extents[1];
		int firstXKey = extents[2];
		int lastXKey = extents[3];
		int firstYKey = extents[4];
		int lastYKey = extents[5];

		pixelList = new int[88 * 153];

		int zKeys[] = wg.getAllZKeys();

		GridCellTypeEnum.GridCellCategory[] cellCategories = GridCellTypeEnum.GridCellCategory.values();
		int bitmapArray[][] = new int[88][153];
		for (Map.Entry<Integer, Map<Integer,GridCell>> yEntry : yMap.entrySet()) {
			int yKey = yEntry.getKey();
			Map<Integer,GridCell> yValue = yEntry.getValue();
			for (Map.Entry<Integer,GridCell> xEntry : yValue.entrySet()) {
				int xKey = xEntry.getKey();
				GridCell gc = xEntry.getValue();
				for (int k = 0; k < cellCategories.length; k++) {
					if (gc.getCellCategory().equals(cellCategories[k].toString())) {
						pixelList[((yKey-1)*widthX) + (xKey-1)] = (Integer)GridCellTypeEnum.CategoryColors[k];
					}
				}
			}
		}

		return pixelList;
	}

	public int[] getPixelList() {
		return pixelList;
	}

}

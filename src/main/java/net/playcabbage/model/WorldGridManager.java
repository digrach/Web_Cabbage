package net.playcabbage.model;

import java.util.Map;
import java.util.TreeMap;

import net.playcabbage.collectibles.*;

public class WorldGridManager {

	private WorldGrid wg;

	public WorldGridManager(WorldGrid worldGrid) {
		this.wg = worldGrid;
	}

	public WorldGrid getGrid() {
		return wg;
	}

	public GridCell getGridCell(int z, int y, int x) {

		GridCell gc = null;

		if (wg.getzMap().get(z) != null) {
			if (wg.getzMap().get(z).get(y) != null) {
				if (wg.getzMap().get(z).get(y).get(x) != null) {
					gc = wg.getzMap().get(z).get(y).get(x);
				}
			}
		}

		return gc;
	}

	public boolean addGridCell(int z, int y, int x, GridCell gridCell) {

		if (! wg.getzMap().containsKey(z)) {
			wg.getzMap().put(z, new TreeMap<Integer,Map<Integer,GridCell>>());
			wg.getzMap().get(z).put(y,new TreeMap<Integer,GridCell>());
			wg.getzMap().get(z).get(y).put(x,gridCell);
			return true;
		} else {
			if (! wg.getzMap().get(z).containsKey(y)) {
				wg.getzMap().get(z).put(y,new TreeMap<Integer,GridCell>());
				wg.getzMap().get(z).get(y).put(x,gridCell);
				return true;
			} else {
				if (! wg.getzMap().get(z).get(y).containsKey(x)) {
					wg.getzMap().get(z).get(y).put(x,gridCell);
					return true;
				} 
			}
		}

		return false;
	}

	public boolean addCollectible(ICollectible collectible, 
			int z, int y, int x) {

		if (wg.getzMap().containsKey(z)) {

			if (wg.getzMap().get(z).containsKey(y)) {

				if (wg.getzMap().get(z).get(y).containsKey(x)) {

					GridCell gc = wg.getzMap().get(z).get(y).get(x);
					gc.addCollectible(collectible);
					return true;

				} 
			}
		}

		return false;
	}

	public boolean removeCollectible(ICollectible collectible, 
			int z, int y, int x) {

		return false;
	}

	//	public boolean removeAllCollectibles(int z, int y, int x) {
	//	
	//		return false;
	//	}



}

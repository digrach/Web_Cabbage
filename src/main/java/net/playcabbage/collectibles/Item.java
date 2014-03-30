package net.playcabbage.collectibles;

import java.awt.Image;

public abstract class Item extends Collectible {

	public Item(int id, String name, int pointsBonus, Image imageIcon, String fileName) {
		super(id,name,pointsBonus,imageIcon,fileName);
	}
}
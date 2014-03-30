package net.playcabbage.collectibles;

import java.awt.Image;

public interface ICollectible {
	
	public int getId();
	public String getName();
	public int getPointsBonus();
	public Image getImageIcon();
	public String getFileName();

}

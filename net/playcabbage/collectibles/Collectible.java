package net.playcabbage.collectibles;

import java.awt.Image;

public abstract class Collectible implements ICollectible{

	private int id;
	private String name;
	private int pointsBonus;
	private Image imageIcon;
	private String fileName;
	
	public Collectible(int id, String name, int pointsBonus, Image imageIcon, String fileName) {
		this.setName(name);
		this.setPointsBonus(pointsBonus);
		this.setImageIcon(imageIcon);
		this.setFileName(fileName);
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPointsBonus() {
		return pointsBonus;
	}

	public void setPointsBonus(int pointsBonus) {
		this.pointsBonus = pointsBonus;
	}

	public Image getImageIcon() {
		if (imageIcon == null) {
			EIcon ei = new EIcon();
			return ei.makePIcon();
		} else {
			return imageIcon;	
		}
	}

	public void setImageIcon(Image imageIcon) {
		this.imageIcon = imageIcon;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}

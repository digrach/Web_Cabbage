package net.playcabbage.collectibles;

import java.awt.Image;

public class Player {

	private String name;
	private Image avatarImages[];
	private Collected itemCollection;

	public Player(String name) {
		this.setName(name);
	}

	public void addItem(Item item) {

	}

	public void removeItem(Item item) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image[] getAvatarImages() {
		return avatarImages;
	}

	public void setAvatarImages(Image[] avatarImages) {
		this.avatarImages = avatarImages;
	}



}

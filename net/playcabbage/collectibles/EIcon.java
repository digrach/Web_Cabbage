package net.playcabbage.collectibles;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class EIcon {

	public EIcon() {

	}

	public Image makePIcon() {
		int width =3;
		int height=3;
		int iArray[] = new int[width*height];
		for (int x = 0; x < iArray.length; x++) {
			if(x%2==0){
				iArray[x] = 0xFFB0171F;
			} else {
				iArray[x] = 0xFFFF3E96;
			}
		}

		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
		bi.setRGB(0,0, width, height, iArray, 0, width);
		ImageIcon ii = new ImageIcon(bi);
		Image i = bi.getScaledInstance(width*10, height*10, 0);

		return i;

	}

	public ImageIcon makeIcon(int icolor, int width, int height) {
		ImageIcon ii = new ImageIcon();
		int iArray[] = new int[width*height];
		for (int x = 0; x < iArray.length; x++) {
			iArray[x] = icolor;
		}
		return ii;

	}

}

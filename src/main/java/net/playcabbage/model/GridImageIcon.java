package net.playcabbage.model;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class GridImageIcon {
	// The coordinate to draw from inside the bitmap image (always zero).
	private int rgbStartX = 0;
	private int rgbStartY = 0;
	// The index of the array to begin reading from.
	private int theOffset;
	// Dimensions that the image will be scaled to.
	private double scaledImageWidth;
	private double scaledImageHeight;
	// How many times the scaled image has been *
	private double widthMultiplier;;
	private double heightMultiplier;
	// The original width to scan.
	int theScanSize;

	// The original array of pixel colors.
	private int pixelArray[];
	// The original width and height of the pixel array.
	private double unscaledPixelWidth;
	private double unscaledPixelHeight;
	// The size of the pixels to render.
	private int bRequiredPixelWidth;
	private int bRequiredPixelHeight;
	// The containers dimensions.
	private double containerWidth;
	private double containerHeight;

	private int startRow = 0;
	private int startCol = 0;

	public GridImageIcon(int pixelArray[], double unscaledPixelWidth, double unscaledPixelHeight, 
			int requiredPixelWidth, int requiredPixelHeight, 
			double containerWidth, double containerHeight) {

		// Stuff that gets done ONCE.
		this.pixelArray = pixelArray;
		this.unscaledPixelWidth = unscaledPixelWidth;
		this.unscaledPixelHeight = unscaledPixelHeight;
		this.bRequiredPixelWidth = requiredPixelWidth;
		this.bRequiredPixelHeight = requiredPixelHeight;
		this.containerWidth = containerWidth;
		this.containerHeight = containerHeight;

		// The original width to scan.
		theScanSize = (int)unscaledPixelWidth;
		calculateScaledWidthHeight();
	}

	private void calculateScaledWidthHeight() {
		double divisor = 0;
		widthMultiplier = 0;
		while(divisor < (containerWidth - bRequiredPixelWidth)) {
			divisor += bRequiredPixelWidth;
			widthMultiplier ++;
		}
		heightMultiplier = widthMultiplier;
		scaledImageWidth = bRequiredPixelWidth*widthMultiplier;
		scaledImageHeight = bRequiredPixelHeight*heightMultiplier;
	}

	private void checkBounds() {
		if(startRow > (this.unscaledPixelHeight - this.bRequiredPixelHeight) + 1) {
			//			startRow = (int)this.unscaledPixelHeight - this.bRequiredPixelHeight;
			startRow = ((int)this.unscaledPixelHeight - this.bRequiredPixelHeight) + 1;
		}
		if(startRow < (0 + this.bRequiredPixelHeight)) {
			startRow = 0;
		}
		if(startCol > (this.unscaledPixelWidth - this.bRequiredPixelWidth) + 1) {
			//			startCol = (int)this.unscaledPixelWidth - this.bRequiredPixelWidth;
			startCol = ((int)this.unscaledPixelWidth - this.bRequiredPixelWidth) + 1;
		}
		if(startCol < (0 + this.bRequiredPixelWidth)) {
			startCol = 0;
		}
	}

	public ImageIcon drawGridBitmap(int startRow, int startCol) {
		// Check that start coordinates will not breach bounds.
		this.startRow = startRow;
		this.startCol = startCol;
		checkBounds();
		return drawGridBitmap();
	}

	public ImageIcon drawGridBitmap() {

		// Make zero relative.
		if (startRow > 0) {
			startRow -=1;
		}
		if (startCol > 0) {
			startCol -=1;
		}

		// Calculate the offSet.
		theOffset = (int)(unscaledPixelWidth * startRow) + startCol;

		// Build the image and return.
		BufferedImage bi = new BufferedImage(bRequiredPixelWidth,bRequiredPixelHeight,BufferedImage.TYPE_4BYTE_ABGR);
		bi.setRGB(rgbStartX,rgbStartY, bRequiredPixelWidth, bRequiredPixelHeight, pixelArray, theOffset, theScanSize);
		Image i = bi.getScaledInstance((int)scaledImageWidth,(int)scaledImageHeight, 0);
		ImageIcon ii = new ImageIcon(i);

		return ii;
	}

	public int getStartRow() {
		return startRow;
	}

	public int getStartCol() {
		return startCol;
	}





}

// Copyright Rachael Colley 2013.

package net.playcabbage.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class writeLogs {
	
	private static PrintWriter pw; 
	
	
	public static void writeLine(String lineToWrite) {
		pw.println(lineToWrite);
	}
	
	public static void closeWriter() {
		pw.close();
	}

	public static void openWriter(String fileLocation) throws FileNotFoundException {
			writeLogs.pw = new PrintWriter(fileLocation);
	}
	

}

package net.playcabbage.collectibles;

import java.util.HashMap;

public class PointsTable {

	private HashMap<String,Integer> facultyPoints;
	private HashMap<String,Integer> skillPoints;
	private HashMap<String,Integer> itemPoints;
	private int participationPoints;

	public PointsTable() {
		facultyPoints = new HashMap<String,Integer>();
		skillPoints = new HashMap<String,Integer>();
		itemPoints = new HashMap<String,Integer>();
		participationPoints = 0;
	}

	private int updateFacultyPoints(String facultyName, int value) {

		return 0;
	}

	private int updateSkillPoints(String skillName, int value) {

		return 0;
	}

	private int updateItemPoints(String itemName, int value) {

		return 0;
	}

	private int updateParticipationPoints(int value) {
		participationPoints += value;
		return 0;
	}
	
	private int getFacultyPoints() {

		return 0;
	}
	
	private int getSkillPoints() {

		return 0;
	}
	
	private int getItemPoints() {

		return 0;
	}
	
	private int getParticipationPoints() {

		return 0;
	}
	
	private int calculateTotalScore() {
		
		return 0;
	}
	
	private String makePointsTable() {
		return "make points table";
	}
	
	public String toString() {
		return "A points table";
	}




}

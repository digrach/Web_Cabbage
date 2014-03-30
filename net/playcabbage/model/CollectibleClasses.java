package net.playcabbage.model;

import java.util.ArrayList;
import java.util.List;

import net.playcabbage.collectibles.*;

public class CollectibleClasses {

	private List<Class> collectibleClassesList;

	public CollectibleClasses() throws ClassNotFoundException {
		makeCollectibleClasses();
	}

	private void makeCollectibleClasses() throws ClassNotFoundException {
		collectibleClassesList = new ArrayList<Class>();

		collectibleClassesList.add(Class.forName("frontEntities.NoveltyItem"));
		collectibleClassesList.add(Class.forName("frontEntities.VocItem"));
		collectibleClassesList.add(Class.forName("frontEntities.NoveltySkill"));
		collectibleClassesList.add(Class.forName("frontEntities.VocSkill"));
		collectibleClassesList.add(Class.forName("frontEntities.NoveltyFaculty"));
		collectibleClassesList.add(Class.forName("frontEntities.VocFaculty"));
	}

	public ICollectible makeObjectFromClass(Class aClass) {

		ICollectible ic = null;

		if (aClass.getName().equals("frontEntities.NoveltyItem")) {
			ic = new NoveltyItem(0,null, 0, null,null);
			return ic;
		}

		if (aClass.getName().equals("frontEntities.VocItem")) {
			ic = new VocItem(0,null, 0, null,null);
			return ic;
		}

		if (aClass.getName().equals("frontEntities.NoveltySkill")) {
			ic = new NoveltySkill(0,null, 0, null,null);
			return ic;
		}

		if (aClass.getName().equals("frontEntities.VocSkill")) {
			ic = new VocSkill(0,null, 0, null,null);
			return ic;
		}

		if (aClass.getName().equals("frontEntities.NoveltyFaculty")) {
			ic = new NoveltyFaculty(0,null, 0, null,null);
			return ic;
		}

		if (aClass.getName().equals("frontEntities.VocFaculty")) {
			ic = new VocFaculty(0,null, 0, null,null);
			return ic;
		}

		//		for (int x = 0; x < collectibleClassesList.size(); x++ ) {
		//			if (aClass.getClass().equals(collectibleClassesList.get(x))) {
		//				
		//			}
		//		}

		return null;
	}

	public List<Class> getCollectibleClassesList() {
		return collectibleClassesList;
	}





}

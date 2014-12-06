package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.levels.CavesBossLevel;
import com.dit599.customPD.levels.*;

public abstract class LevelMapping {
	private static List<Class<? extends Level>> themeclasslist = null;
	private static List<String> themenamelist = null;

	public static void LevelMappingInit() {
		themeclasslist = new ArrayList();
		themeclasslist.add(CavesBossLevel.class);
		themeclasslist.add(CavesLevel.class);
		themeclasslist.add(CityBossLevel.class);
		themeclasslist.add(CityLevel.class);
		//themeclasslist.add(DeadEndLevel.class);
		themeclasslist.add(HallsBossLevel.class);
		themeclasslist.add(HallsLevel.class);
		//themeclasslist.add(LastLevel.class);
		//themeclasslist.add(LastShopLevel.class);
		themeclasslist.add(PrisonBossLevel.class);
		themeclasslist.add(PrisonLevel.class);
		themeclasslist.add(RegularLevel.class);
		themeclasslist.add(SewerBossLevel.class);
		themeclasslist.add(SewerLevel.class);
		//themeclasslist.add(TutorialBossLevel.class);
		//themeclasslist.add(TutorialLevel.class);

		themenamelist = new ArrayList();
		themenamelist.add("CavesBossLevel");
		themenamelist.add("CavesLevel");
		themenamelist.add("CityBossLevel");
		themenamelist.add("CityLevel");
		//themenamelist.add("DeadEndLevel");
		themenamelist.add("HallsBossLevel");
		themenamelist.add("HallsLevel");
		//themenamelist.add("LastLevel");
		//themenamelist.add("LastShopLevel");
		themenamelist.add("PrisonBossLevel");
		themenamelist.add("PrisonLevel");
		themenamelist.add("RegularLevel");
		themenamelist.add("SewerBossLevel");
		themenamelist.add("SewerLevel");
		//themenamelist.add("TutorialBossLevel");
		//themenamelist.add("TutorialLevel");

	}

	public static String getThemeName(Class<? extends Level> theme) {
		for(int i=0;i<themeclasslist.size();i++)
		 {
			if(themeclasslist.get(i).getName().equals(theme.getName()))
			{
				return themenamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Level> getThemeClass(String themename) {
		for(int i=0;i<themenamelist.size();i++)
		 {
			if(themenamelist.get(i).equals(themename))
			{
				return themeclasslist.get(i);
			}
		 }
		return null;
	}
}

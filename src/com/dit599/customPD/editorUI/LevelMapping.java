package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.levels.*;

public abstract class LevelMapping {
	private static List<Class<? extends Level>> themeclasslist = null;
	private static List<String> themenamelist = null;

	public static void LevelMappingInit() {
		themeclasslist = new ArrayList<Class<? extends Level>>();
		//themeclasslist.add(DeadEndLevel.class);
		themeclasslist.add(SewerLevel.class);
		themeclasslist.add(SewerBossLevel.class);
		themeclasslist.add(PrisonLevel.class);
		themeclasslist.add(PrisonBossLevel.class);
		themeclasslist.add(CavesLevel.class);
		themeclasslist.add(CavesBossLevel.class);
		themeclasslist.add(CityLevel.class);
		themeclasslist.add(CityBossLevel.class);
		themeclasslist.add(HallsLevel.class);
		themeclasslist.add(HallsBossLevel.class);
		//themeclasslist.add(LastLevel.class);
		//themeclasslist.add(LastShopLevel.class);
		//themeclasslist.add(RegularLevel.class);
		//themeclasslist.add(TutorialBossLevel.class);
		//themeclasslist.add(TutorialLevel.class);

		themenamelist = new ArrayList<String>();
		//themenamelist.add("DeadEndLevel");
		themenamelist.add("SewerLevel");
		themenamelist.add("SewerBossLevel");
		themenamelist.add("PrisonLevel");
		themenamelist.add("PrisonBossLevel");
		themenamelist.add("CavesLevel");
		themenamelist.add("CavesBossLevel");
		themenamelist.add("CityLevel");
		themenamelist.add("CityBossLevel");
		themenamelist.add("HallsLevel");
		themenamelist.add("HallsBossLevel");
		//themenamelist.add("LastLevel");
		//themenamelist.add("LastShopLevel");
		//themenamelist.add("RegularLevel");
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

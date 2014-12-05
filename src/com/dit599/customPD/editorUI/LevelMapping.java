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
		themeclasslist.add(0, CavesBossLevel.class);
		themeclasslist.add(1, CavesLevel.class);
		themeclasslist.add(2, CityBossLevel.class);
		themeclasslist.add(3, CityLevel.class);
		themeclasslist.add(4, DeadEndLevel.class);
		themeclasslist.add(5, HallsBossLevel.class);
		themeclasslist.add(6, HallsLevel.class);
		themeclasslist.add(7, LastLevel.class);
		themeclasslist.add(8, LastShopLevel.class);
		themeclasslist.add(9, PrisonBossLevel.class);
		themeclasslist.add(10, PrisonLevel.class);
		themeclasslist.add(11, RegularLevel.class);
		themeclasslist.add(12, SewerBossLevel.class);
		themeclasslist.add(13, SewerLevel.class);
		themeclasslist.add(14, TutorialBossLevel.class);
		themeclasslist.add(15, TutorialLevel.class);

		themenamelist = new ArrayList();
		themenamelist.add(0, "CavesBossLevel");
		themenamelist.add(1, "CavesLevel");
		themenamelist.add(2, "CityBossLevel");
		themenamelist.add(3, "CityLevel");
		themenamelist.add(4, "DeadEndLevel");
		themenamelist.add(5, "HallsBossLevel");
		themenamelist.add(6, "HallsLevel");
		themenamelist.add(7, "LastLevel");
		themenamelist.add(8, "LastShopLevel");
		themenamelist.add(9, "PrisonBossLevel");
		themenamelist.add(10, "PrisonLevel");
		themenamelist.add(11, "RegularLevel");
		themenamelist.add(12, "SewerBossLevel");
		themenamelist.add(13, "SewerLevel");
		themenamelist.add(14, "TutorialBossLevel");
		themenamelist.add(15, "TutorialLevel");

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

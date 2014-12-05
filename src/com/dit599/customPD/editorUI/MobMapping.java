package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.actors.mobs.*;
import com.dit599.customPD.levels.*;

public abstract class MobMapping {
	private static List<Class<? extends Mob>> mobsclasslist = null;
	private static List<String> mobsnamelist = null;

	public static void LevelMappingInit() {
		mobsclasslist = new ArrayList();
		mobsclasslist.add(0, Acidic.class);
		mobsclasslist.add(1, Albino.class);
		mobsclasslist.add(2, Bandit.class);
		mobsclasslist.add(3, Bat.class);
		mobsclasslist.add(4, Brute.class);
		mobsclasslist.add(5, Crab.class);
		mobsclasslist.add(6, DM300.class);
		mobsclasslist.add(7, Elemental.class);
		mobsclasslist.add(8, Eye.class);
		mobsclasslist.add(9, Gnoll.class);
		mobsclasslist.add(10, Golem.class);
		mobsclasslist.add(11, Goo.class);
		mobsclasslist.add(12, King.class);
		mobsclasslist.add(13, Monk.class);
		mobsclasslist.add(14, Piranha.class);
		mobsclasslist.add(15, Rat.class);
		mobsclasslist.add(16, Scorpio.class);
		mobsclasslist.add(17, Senior.class);
		mobsclasslist.add(18, Shaman.class);
		mobsclasslist.add(19, Shielded.class);
		mobsclasslist.add(20, Skeleton.class);
		mobsclasslist.add(21, Spinner.class);
		mobsclasslist.add(22, Statue.class);
		mobsclasslist.add(23, Succubus.class);
		mobsclasslist.add(24, Swarm.class);
		mobsclasslist.add(25, Tengu.class);
		mobsclasslist.add(26, Thief.class);
		mobsclasslist.add(27, Warlock.class);
		mobsclasslist.add(28, Wraith.class);
		mobsclasslist.add(29, Yog.class);

		mobsnamelist = new ArrayList();
		mobsnamelist.add(0, "Acidic");
		mobsnamelist.add(1, "Albino");
		mobsnamelist.add(2, "Bandit");
		mobsnamelist.add(3, "Bat");
		mobsnamelist.add(4, "Brute");
		mobsnamelist.add(5, "Crab");
		mobsnamelist.add(6, "DM300");
		mobsnamelist.add(7, "Elemental");
		mobsnamelist.add(8, "Eye");
		mobsnamelist.add(9, "Gnoll");
		mobsnamelist.add(10, "Golem");
		mobsnamelist.add(11, "Goo");
		mobsnamelist.add(12, "King");
		mobsnamelist.add(13, "Monk");
		mobsnamelist.add(14, "Piranha");
		mobsnamelist.add(15, "Rat");
		mobsnamelist.add(16, "Scorpio");
		mobsnamelist.add(17, "Senior");
		mobsnamelist.add(18, "Shaman");
		mobsnamelist.add(19, "Shielded");
		mobsnamelist.add(20, "Skeleton");
		mobsnamelist.add(21, "Spinner");
		mobsnamelist.add(22, "Statue");
		mobsnamelist.add(23, "Succubus");
		mobsnamelist.add(24, "Swarm");
		mobsnamelist.add(25, "Tengu");
		mobsnamelist.add(26, "Thief");
		mobsnamelist.add(27, "Warlock");
		mobsnamelist.add(28, "Wraith");
		mobsnamelist.add(29, "Yog");
		
		

	}

	public static String getMobName(Class<? extends Level> mob) {
		for(int i=0;i<mobsclasslist.size();i++)
		 {
			if(mobsclasslist.get(i).getName().equals(mob.getName()))
			{
				return mobsnamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Mob> getMobClass(String mobname) {
		for(int i=0;i<mobsnamelist.size();i++)
		 {
			if(mobsnamelist.get(i).equals(mobname))
			{
				return mobsclasslist.get(i);
			}
		 }
		return null;
	}
}

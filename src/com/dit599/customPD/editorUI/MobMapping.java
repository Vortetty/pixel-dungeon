package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.actors.mobs.*;
import com.dit599.customPD.levels.*;

public abstract class MobMapping {
	private static List<Class<? extends Mob>> mobsclasslist = null;
	private static List<String> mobsnamelist = null;

	public static void mobMappingInit() {
		mobsclasslist = new ArrayList();
		mobsclasslist.add(Acidic.class);
		mobsclasslist.add(Albino.class);
		mobsclasslist.add(Bandit.class);
		mobsclasslist.add(Bat.class);
		mobsclasslist.add(Brute.class);
		mobsclasslist.add(Crab.class);
		//mobsclasslist.add(DM300.class);
		mobsclasslist.add(Elemental.class);
		mobsclasslist.add(Eye.class);
		mobsclasslist.add(Gnoll.class);
		mobsclasslist.add(Golem.class);
		//mobsclasslist.add(Goo.class);
		//mobsclasslist.add(King.class);
		mobsclasslist.add(Monk.class);
		mobsclasslist.add(Piranha.class);
		mobsclasslist.add(Rat.class);
		mobsclasslist.add(Scorpio.class);
		mobsclasslist.add(Senior.class);
		mobsclasslist.add(Shaman.class);
		mobsclasslist.add(Shielded.class);
		mobsclasslist.add(Skeleton.class);
		mobsclasslist.add(Spinner.class);
		mobsclasslist.add(Statue.class);
		mobsclasslist.add(Succubus.class);
		mobsclasslist.add(Swarm.class);
		//mobsclasslist.add(Tengu.class);
		mobsclasslist.add(Thief.class);
		mobsclasslist.add(Warlock.class);
		mobsclasslist.add(Wraith.class);
		//mobsclasslist.add(Yog.class);

		mobsnamelist = new ArrayList();
		mobsnamelist.add("Acidic");
		mobsnamelist.add("Albino");
		mobsnamelist.add("Bandit");
		mobsnamelist.add("Bat");
		mobsnamelist.add("Brute");
		mobsnamelist.add("Crab");
		//mobsnamelist.add("DM300");
		mobsnamelist.add("Elemental");
		mobsnamelist.add("Eye");
		mobsnamelist.add("Gnoll");
		mobsnamelist.add("Golem");
		//mobsnamelist.add("Goo");
		//mobsnamelist.add("King");
		mobsnamelist.add("Monk");
		mobsnamelist.add("Piranha");
		mobsnamelist.add("Rat");
		mobsnamelist.add("Scorpio");
		mobsnamelist.add("Senior");
		mobsnamelist.add("Shaman");
		mobsnamelist.add("Shielded");
		mobsnamelist.add("Skeleton");
		mobsnamelist.add("Spinner");
		mobsnamelist.add("Statue");
		mobsnamelist.add("Succubus");
		mobsnamelist.add("Swarm");
		//mobsnamelist.add("Tengu");
		mobsnamelist.add("Thief");
		mobsnamelist.add("Warlock");
		mobsnamelist.add("Wraith");
		//mobsnamelist.add("Yog");
		
		

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

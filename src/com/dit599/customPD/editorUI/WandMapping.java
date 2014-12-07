package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.wands.*;

public abstract class WandMapping {
	private static List<Class<? extends Item>> wandclasslist = null;
	public static List<String> wandnamelist = null;

	public static void wandMappingInit() {
		wandclasslist = new ArrayList<Class<? extends Item>>();
		wandclasslist.add(WandOfAmok.class);
		wandclasslist.add(WandOfAvalanche.class);
		wandclasslist.add(WandOfBlink.class);
		wandclasslist.add(WandOfDisintegration.class);
		wandclasslist.add(WandOfFirebolt.class);
		wandclasslist.add(WandOfFlock.class);
		wandclasslist.add(WandOfLightning.class);
		wandclasslist.add(WandOfMagicMissile.class);
		wandclasslist.add(WandOfPoison.class);
		wandclasslist.add(WandOfRegrowth.class);
		wandclasslist.add(WandOfSlowness.class);
		wandclasslist.add(WandOfTeleportation.class);
		wandclasslist.add(WandOfTelekinesis.class);

		wandnamelist = new ArrayList<String>();
		wandnamelist.add("Wand of Amok");
		wandnamelist.add("Wand of Avalanche");
		wandnamelist.add("Wand of Blink");
		wandnamelist.add("Wand of Disintegration");
		wandnamelist.add("Wand of Firebolt");
		wandnamelist.add("Wand of Flock");
		wandnamelist.add("Wand of Lightning");
		wandnamelist.add("Wand of Magic Missile");
		wandnamelist.add("Wand of Poison");
		wandnamelist.add("Wand of Regrowth");
		wandnamelist.add("Wand of Slowness");
		wandnamelist.add("Wand of Teleportation");
		wandnamelist.add("Wand of Telekinesis");
	}

	public static String getWandName(Class<? extends Item> wand) {
		for(int i=0;i<wandclasslist.size();i++)
		 {
			if(wandclasslist.get(i).getName().equals(wand.getName()))
			{
				return wandnamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Item> getWandClass(String wandname) {
		for(int i=0;i<wandnamelist.size();i++)
		 {
			if(wandnamelist.get(i).equals(wandname))
			{
				return wandclasslist.get(i);
			}
		 }
		return null;
	}
}

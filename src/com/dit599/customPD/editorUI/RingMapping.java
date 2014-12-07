package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.rings.RingOfAccuracy;
import com.dit599.customPD.items.rings.RingOfDetection;
import com.dit599.customPD.items.rings.RingOfElements;
import com.dit599.customPD.items.rings.RingOfEvasion;
import com.dit599.customPD.items.rings.RingOfHaggler;
import com.dit599.customPD.items.rings.RingOfHaste;
import com.dit599.customPD.items.rings.RingOfHerbalism;
import com.dit599.customPD.items.rings.RingOfMending;
import com.dit599.customPD.items.rings.RingOfPower;
import com.dit599.customPD.items.rings.RingOfSatiety;
import com.dit599.customPD.items.rings.RingOfShadows;
import com.dit599.customPD.items.rings.RingOfThorns;
import com.dit599.customPD.items.wands.*;

public abstract class RingMapping {
	private static List<Class<? extends Item>> ringclasslist = null;
	private static List<String> ringnamelist = null;

	public static void ringMappingInit() {
		ringclasslist = new ArrayList<Class<? extends Item>>();
		ringclasslist.add(RingOfAccuracy.class);
		ringclasslist.add(RingOfDetection.class);
		ringclasslist.add(RingOfElements.class);
		ringclasslist.add(RingOfEvasion.class);
		ringclasslist.add(RingOfHaggler.class);
		ringclasslist.add(RingOfHaste.class);
		ringclasslist.add(RingOfHerbalism.class);
		ringclasslist.add(RingOfMending.class);
		ringclasslist.add(RingOfPower.class);
		ringclasslist.add(RingOfSatiety.class);
		ringclasslist.add(RingOfShadows.class);
		ringclasslist.add(RingOfThorns.class);

		ringnamelist = new ArrayList<String>();
		ringnamelist.add("Ring of Accuracy");
		ringnamelist.add("Ring of Detection");
		ringnamelist.add("Ring of Elements");
		ringnamelist.add("Ring of Evasion");
		ringnamelist.add("Ring of Haggler");
		ringnamelist.add("Ring of Haste");
		ringnamelist.add("Ring of Herbalism");
		ringnamelist.add("Ring of Mending");
		ringnamelist.add("Ring of Power");
		ringnamelist.add("Ring of Satiety");
		ringnamelist.add("Ring of Shadows");
		ringnamelist.add("Ring of Thorns");
	}

	public static String getRingName(Class<? extends Item> ring) {
		for(int i=0;i<ringclasslist.size();i++)
		 {
			if(ringclasslist.get(i).getName().equals(ring.getName()))
			{
				return ringnamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Item> getRingClass(String ringname) {
		for(int i=0;i<ringnamelist.size();i++)
		 {
			if(ringnamelist.get(i).equals(ringname))
			{
				return ringclasslist.get(i);
			}
		 }
		return null;
	}
}

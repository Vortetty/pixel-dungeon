package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.items.*;
import com.dit599.customPD.items.bags.*;
import com.dit599.customPD.items.food.*;
import com.dit599.customPD.items.weapon.missiles.*;
import com.dit599.customPD.plants.*;


public abstract class ConsumableMapping {
	private static List<Class<? extends Item>> consumableclasslist = null;
	private static List<String> consumablenamelist = null;

	public static void consumableMappingInit() {
		consumableclasslist = new ArrayList<Class<? extends Item>>();
		consumableclasslist.add(Ankh.class);
		consumableclasslist.add(DewVial.class);
		consumableclasslist.add(Gold.class);
		consumableclasslist.add(Food.class);
		consumableclasslist.add(MysteryMeat.class);
		consumableclasslist.add(ScrollHolder.class);
		consumableclasslist.add(SeedPouch.class);
		consumableclasslist.add(WandHolster.class);
		consumableclasslist.add(ArmorKit.class);
		consumableclasslist.add(Weightstone.class);
		consumableclasslist.add(LloydsBeacon.class);
		consumableclasslist.add(Stylus.class);
		consumableclasslist.add(TomeOfMastery.class);
		consumableclasslist.add(Torch.class);
		consumableclasslist.add(Dart.class);
		consumableclasslist.add(IncendiaryDart.class);
		consumableclasslist.add(CurareDart.class);
		consumableclasslist.add(Shuriken.class);
		consumableclasslist.add(Javelin.class);
		consumableclasslist.add(Tamahawk.class);

		consumablenamelist = new ArrayList<String>();
		consumablenamelist.add("Ankh");
		consumablenamelist.add("Dew Vial");
		consumablenamelist.add("Gold");
		consumablenamelist.add("Food");
		consumablenamelist.add("Mystery Meat");
		consumablenamelist.add("Scroll Holder");
		consumablenamelist.add("Seed Pouch");
		consumablenamelist.add("Wand Holster");
		consumablenamelist.add("Armor Kit");
		consumablenamelist.add("Weightstone");
		consumablenamelist.add("Lloyd's Beacon");
		consumablenamelist.add("Arcane Stylus");
		consumablenamelist.add("Tome of Mastery");
		consumablenamelist.add("Torch");
		consumablenamelist.add("Dart");
		consumablenamelist.add("Incendiary Dart");
		consumablenamelist.add("Curare Dart");
		consumablenamelist.add("Shuriken");
		consumablenamelist.add("Javelin");
		consumablenamelist.add("Tomahawk");
	}

	public static String getConsumableName(Class<? extends Item> consumable) {
		for(int i=0;i<consumableclasslist.size();i++)
		 {
			if(consumableclasslist.get(i).getName().equals(consumable.getName()))
			{
				return consumablenamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Item> getConsumableClass(String consumablename) {
		for(int i=0;i<consumablenamelist.size();i++)
		 {
			if(consumablenamelist.get(i).equals(consumablename))
			{
				return consumableclasslist.get(i);
			}
		 }
		return null;
	}
	public static List<String> getAllNames()
	{
		return consumablenamelist;
		
	}
}

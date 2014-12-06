package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.weapon.melee.*;
import com.dit599.customPD.items.weapon.missiles.Boomerang;

public abstract class WeaponMapping {
	private static List<Class<? extends Item>> weaponclasslist = null;
	private static List<String> weaponnamelist = null;

	public static void armorMappingInit() {
		weaponclasslist = new ArrayList<Class<? extends Item>>();
		weaponclasslist.add(ShortSword.class);
		weaponclasslist.add(Dagger.class);
		weaponclasslist.add(Knuckles.class);
		weaponclasslist.add(Boomerang.class);
		weaponclasslist.add(Quarterstaff.class);
		weaponclasslist.add(Spear.class);
		weaponclasslist.add(Sword.class);
		weaponclasslist.add(Mace.class);
		weaponclasslist.add(Longsword.class);
		weaponclasslist.add(BattleAxe.class);
		weaponclasslist.add(Glaive.class);
		weaponclasslist.add(WarHammer.class);

		weaponnamelist = new ArrayList<String>();
		weaponnamelist.add("Shortsword");
		weaponnamelist.add("Dagger");
		weaponnamelist.add("Knuckleduster");
		weaponnamelist.add("Boomerang");
		weaponnamelist.add("Quarterstaff");
		weaponnamelist.add("Spear");
		weaponnamelist.add("Sword");
		weaponnamelist.add("Mace");
		weaponnamelist.add("Longsword");
		weaponnamelist.add("Battle Axe");
		weaponnamelist.add("Glaive");
		weaponnamelist.add("War Hammer");
	}

	public static String getWeaponName(Class<? extends Item> weapon) {
		for(int i=0;i<weaponclasslist.size();i++)
		 {
			if(weaponclasslist.get(i).getName().equals(weapon.getName()))
			{
				return weaponnamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Item> getWeaponClass(String weaponname) {
		for(int i=0;i<weaponnamelist.size();i++)
		 {
			if(weaponnamelist.get(i).equals(weaponname))
			{
				return weaponclasslist.get(i);
			}
		 }
		return null;
	}
}

package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.items.weapon.Weapon.Enchantment;
import com.dit599.customPD.items.weapon.enchantments.*;

public abstract class EnchantmentsMapping {
	private static List<Class<? extends Enchantment>> enchantmentsclasslist = null;
	private static List<String> enchantmentsnamelist = null;

	
	public static void EnchantmentsMappingInit() {
		enchantmentsclasslist = new ArrayList<Class<? extends Enchantment>>();
		enchantmentsclasslist.add(0, Death.class);
		enchantmentsclasslist.add(1, Fire.class);
		enchantmentsclasslist.add(2, Horror.class);
		enchantmentsclasslist.add(3, Instability.class);
		enchantmentsclasslist.add(4, Leech.class);
		enchantmentsclasslist.add(5, Luck.class);
		enchantmentsclasslist.add(6, Paralysis.class);
		enchantmentsclasslist.add(7, Piercing.class);
		enchantmentsclasslist.add(8, Poison.class);
		enchantmentsclasslist.add(9, Slow.class);
		enchantmentsclasslist.add(10, Swing.class);

		enchantmentsnamelist = new ArrayList<String>();
		enchantmentsnamelist.add(0, "Grim");
		enchantmentsnamelist.add(1, "Blazing");
		enchantmentsnamelist.add(2, "Eldritch");
		enchantmentsnamelist.add(3, "Unstable");
		enchantmentsnamelist.add(4, "Vampiric");
		enchantmentsnamelist.add(5, "Lucky");
		enchantmentsnamelist.add(6, "Stunning");
		enchantmentsnamelist.add(7, "Piercing");
		enchantmentsnamelist.add(8, "Venomous");
		enchantmentsnamelist.add(9, "Chilling");
		enchantmentsnamelist.add(10, "Wild");

	}

	public static String getEnchantmentName(Class<? extends Enchantment> enchantment) {
		for(int i=0;i<enchantmentsclasslist.size();i++)
		 {
			if(enchantmentsclasslist.get(i).getName().equals(enchantment.getName()))
			{
				return enchantmentsnamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Enchantment> getEnchantmentClass(String enchantmentname) {
		for(int i=0;i<enchantmentsnamelist.size();i++)
		 {
			if(enchantmentsnamelist.get(i).equals(enchantmentname))
			{
				return enchantmentsclasslist.get(i);
			}
		 }
		return null;
	}
	public static List<String> getAllNames()
	{
		return enchantmentsnamelist;
		
	}
}

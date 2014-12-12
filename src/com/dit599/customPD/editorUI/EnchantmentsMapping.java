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
		enchantmentsclasslist.add(null);
		enchantmentsclasslist.add(Death.class);
		enchantmentsclasslist.add(Fire.class);
		enchantmentsclasslist.add(Horror.class);
		enchantmentsclasslist.add(Instability.class);
		enchantmentsclasslist.add(Leech.class);
		enchantmentsclasslist.add(Luck.class);
		enchantmentsclasslist.add(Paralysis.class);
		enchantmentsclasslist.add(Piercing.class);
		enchantmentsclasslist.add(Poison.class);
		enchantmentsclasslist.add(Slow.class);
		enchantmentsclasslist.add(Swing.class);

		enchantmentsnamelist = new ArrayList<String>();
		enchantmentsnamelist.add("None");
		enchantmentsnamelist.add("Grim");
		enchantmentsnamelist.add("Blazing");
		enchantmentsnamelist.add("Eldritch");
		enchantmentsnamelist.add("Unstable");
		enchantmentsnamelist.add("Vampiric");
		enchantmentsnamelist.add("Lucky");
		enchantmentsnamelist.add("Stunning");
		enchantmentsnamelist.add("Piercing");
		enchantmentsnamelist.add("Venomous");
		enchantmentsnamelist.add("Chilling");
		enchantmentsnamelist.add("Wild");

	}

	public static String getEnchantmentName(Class<? extends Enchantment> enchantment) {
		
		for(int i=0;i<enchantmentsclasslist.size();i++)
		 {
			if(enchantmentsclasslist.get(i) != null && enchantmentsclasslist.get(i).getName().equals(enchantment.getName()))
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

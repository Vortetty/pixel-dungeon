package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.armor.*;

public abstract class ArmorMapping {
	private static List<Class<? extends Item>> armorclasslist = null;
	private static List<String> armornamelist = null;

	public static void armorMappingInit() {
		armorclasslist = new ArrayList<Class<? extends Item>>();
		armorclasslist.add(ClothArmor.class);
		armorclasslist.add(LeatherArmor.class);
		armorclasslist.add(MailArmor.class);
		armorclasslist.add(ScaleArmor.class);
		armorclasslist.add(PlateArmor.class);

		armornamelist = new ArrayList<String>();
		armornamelist.add("Cloth Armor");
		armornamelist.add("Leather Armor");
		armornamelist.add("Mail Armor");
		armornamelist.add("Scale Armor");
		armornamelist.add("Plate Armor");
	}

	public static String getArmorName(Class<? extends Item> armor) {
		for(int i=0;i<armorclasslist.size();i++)
		 {
			if(armorclasslist.get(i).getName().equals(armor.getName()))
			{
				return armornamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Item> getArmorClass(String armorname) {
		for(int i=0;i<armornamelist.size();i++)
		 {
			if(armornamelist.get(i).equals(armorname))
			{
				return armorclasslist.get(i);
			}
		 }
		return null;
	}
}

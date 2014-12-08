package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.items.*;
import com.dit599.customPD.plants.*;


public abstract class SeedMapping {
	private static List<Class<? extends Item>> seedclasslist = null;
	private static List<String> seednamelist = null;

	public static void consumableMappingInit() {
		seedclasslist = new ArrayList<Class<? extends Item>>();
		seedclasslist.add(Dreamweed.Seed.class);
		seedclasslist.add(Earthroot.Seed.class);
		seedclasslist.add(Fadeleaf.Seed.class);
		seedclasslist.add(Firebloom.Seed.class);
		seedclasslist.add(Icecap.Seed.class);
		seedclasslist.add(Sorrowmoss.Seed.class);
		seedclasslist.add(Sungrass.Seed.class);

		seednamelist = new ArrayList<String>();
		seednamelist.add("Dreamweed Seed");
		seednamelist.add("Earthroot Seed");
		seednamelist.add("Fadeleaf Seed");
		seednamelist.add("Firebloom Seed");
		seednamelist.add("Icecap Seed");
		seednamelist.add("Sorrowmoss Seed");
		seednamelist.add("Sungrass Seed");
	}

	public static String getSeedName(Class<? extends Item> seed) {
		for(int i=0;i<seedclasslist.size();i++)
		 {
			if(seedclasslist.get(i).getName().equals(seed.getName()))
			{
				return seednamelist.get(i);
			}
		 }
		return null;
	}
	public static Class<? extends Item> getSeedClass(String consumablename) {
		for(int i=0;i<seednamelist.size();i++)
		 {
			if(seednamelist.get(i).equals(consumablename))
			{
				return seedclasslist.get(i);
			}
		 }
		return null;
	}
	public static List<String> getAllNames()
	{
		return seednamelist;
		
	}
}

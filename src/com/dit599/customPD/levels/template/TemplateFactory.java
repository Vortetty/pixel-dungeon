package com.dit599.customPD.levels.template;

import java.util.List;

import android.util.Log;

import com.dit599.customPD.actors.mobs.Gnoll;
import com.dit599.customPD.actors.mobs.Mob;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.armor.Armor;
import com.dit599.customPD.items.food.Pasty;
import com.dit599.customPD.items.potions.Potion;
import com.dit599.customPD.items.potions.PotionOfMight;
import com.dit599.customPD.items.rings.RingOfEvasion;
import com.dit599.customPD.items.scrolls.Scroll;
import com.dit599.customPD.items.weapon.Weapon;
import com.dit599.customPD.items.weapon.melee.ShortSword;
import com.dit599.customPD.levels.LastLevel;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Room.Type;
import com.dit599.customPD.levels.SewerLevel;

public class TemplateFactory {

	public static DungeonTemplate createSimpleDungeon(String name) {
		DungeonTemplate template = new DungeonTemplate();
		boolean found = false;
		try{
			template.load(name);
			if(template.levelTemplates.get(0).specialRooms.size() > 0){
				found = true;
				Log.d("SUCCESS", "Loaded .map");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		template.name = name;
		if(!found){
			template.reset();
			template.levelTemplates.set(0, createSimpleLevel());
			template.levelTemplates.set(1, createSimpleLevel());
			template.levelTemplates.get(1).theme = SewerLevel.class;
			template.levelTemplates.set(2, createSimpleLevel());
			template.levelTemplates.set(3, createSimpleLevel());
			template.levelTemplates.get(3).theme = LastLevel.class;
			template.save(name);
			Log.d("NEW", "Created .map");
		}
		return template;
	}

	public static LevelTemplate createSimpleLevel() {
		LevelTemplate level = new LevelTemplate();
		level.theme = SewerLevel.class;
		level.maxMobs = 5;
		level.timeToRespawn = 40;
		level.specialRooms.add(Type.SHOP);
		level.specialRooms.add(Type.ARMORY);
		level.mobs.get(1).mobClass = Gnoll.class;
		level.mobs.get(1).weight = 2;
		level.weapons.add(new ShortSword());
		level.weapons.add(new ShortSword());
		level.consumables.add(new Pasty());
		level.rings.add(RingOfEvasion.class);
		level.potions.add(PotionOfMight.class);

		return level;
	}
}

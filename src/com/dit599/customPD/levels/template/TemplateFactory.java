package com.dit599.customPD.levels.template;

import com.dit599.customPD.actors.mobs.Gnoll;
import com.dit599.customPD.items.food.Pasty;
import com.dit599.customPD.items.potions.PotionOfMight;
import com.dit599.customPD.items.rings.RingOfEvasion;
import com.dit599.customPD.items.weapon.melee.ShortSword;
import com.dit599.customPD.levels.LastLevel;
import com.dit599.customPD.levels.Room.Type;
import com.dit599.customPD.levels.SewerLevel;

public class TemplateFactory {

    public static DungeonTemplate createSimpleDungeon() {
        DungeonTemplate template = new DungeonTemplate();
        template.levelTemplates[0] = createSimpleLevel();
        template.levelTemplates[1] = createSimpleLevel();
        template.levelTemplates[1].theme = SewerLevel.class;
        template.levelTemplates[2] = createSimpleLevel();
        template.levelTemplates[3] = createSimpleLevel();
        template.levelTemplates[3].theme = LastLevel.class;
        template.name = "simple";

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
    
    public static CustomTemplate createCustomMp(){
    	CustomTemplate customtemp = new CustomTemplate();
		return  customtemp ;
    	
    }
    
    public static void onPause(){
    	
    }
     public static void onResume(){
    	
    }
    
    
}

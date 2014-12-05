package com.dit599.customPD.levels.template;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.Dungeon;
import com.dit599.customPD.actors.Char;
import com.dit599.customPD.actors.mobs.Mob;
import com.dit599.customPD.actors.mobs.Rat;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.potions.Potion;
import com.dit599.customPD.items.rings.Ring;
import com.dit599.customPD.items.scrolls.Scroll;
import com.dit599.customPD.items.wands.Wand;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Level.Feeling;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.SewerLevel;
import com.watabou.noosa.Game;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

/**
 * Data class defining everything customizable in a custom level.
 */
public class LevelTemplate implements Bundlable{
	/** Usually limited to 5-7. */
    public int maxMobs = 4;
	/** Default 50 */
    public float timeToRespawn = 50;

	public Class<? extends Level> theme = SewerLevel.class;
	public Feeling feeling = Feeling.NONE;
	
	public List<Item> weapons = new ArrayList<Item>();
	public List<Item> armor = new ArrayList<Item>();
    public List<Item> consumables = new ArrayList<Item>();
    public List<Class<? extends Potion>> potions = new ArrayList<Class<? extends Potion>>();
    public List<Class<? extends Scroll>> scrolls = new ArrayList<Class<? extends Scroll>>();
    public List<Class<? extends Wand>> wands = new ArrayList<Class<? extends Wand>>();
    public List<Class<? extends Ring>> rings = new ArrayList<Class<? extends Ring>>();

    public List<Room.Type> specialRooms = new ArrayList<Room.Type>();

    public List<MobProbability> mobs = new ArrayList<MobProbability>(4);
    public String filename = "";

	public LevelTemplate() {
        mobs.add(new MobProbability(Rat.class, 3));
        mobs.add(new MobProbability(Rat.class, 0));
        mobs.add(new MobProbability(Rat.class, 0));
        mobs.add(new MobProbability(Rat.class, 0));
	}

    /**
     * Gives you the current level based on Dungeon.depth.
     * 
     * @return
     */
	public static LevelTemplate currentLevelTemplate() {
		if (Dungeon.template == null) {
			return null;
		}
        // Depth-1 because levelTemplates are 0-indexed
        return Dungeon.template.levelTemplates[Dungeon.depth - 1];
	}

    /**
     * Returns the class of one of the predefined mobs for this level.
     */
	public Class<? extends Mob> getRandomMob() {
		int totalWeight = 0;
		for (MobProbability prob : mobs) {
			totalWeight += prob.weight;
		}
		int rnd = Random.Int(totalWeight);
		int index = 0;
		while (rnd >= mobs.get(index).weight) {
			rnd -= mobs.get(index).weight;
			index++;
		}
		return mobs.get(index).mobClass;
	}

    /**
     * Container class for a single mobtype present in a level.
     */
	public class MobProbability {
		public Class<? extends Mob> mobClass;
		public int weight;

		private MobProbability(Class<? extends Mob> mobClass, int weight) {
			this.mobClass = mobClass;
			this.weight = weight;
		}
	}
	

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        
        items.addAll(weapons);
        items.addAll(armor);
        items.addAll(consumables);

        try {
            for (Class<? extends Item> c : potions) {
                items.add(c.newInstance());
            }
            for (Class<? extends Item> c : scrolls) {
                items.add(c.newInstance());
            }
            for (Class<? extends Item> c : wands) {
                items.add(c.newInstance());
            }
            for (Class<? extends Item> c : rings) {
                items.add(c.newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return items;
    }
	
    public void save() {
		Bundle bundle = new Bundle();
		saveMobList(bundle);
		bundle.put("filename", this.filename);
		bundle.put("maxMobs", this.maxMobs);
		bundle.put("timeToRespawn", this.timeToRespawn);
		bundle.put("theme", this.theme.getName());
		
		try {
			OutputStream output;
			output = Game.instance.openFileOutput( filename, Game.MODE_PRIVATE );
			Bundle.write( bundle, output );
			output.close();
		} catch (Exception e) {
		}
	}
    private void saveMobList(Bundle bundle){
    	for (int i = 0; i < mobs.size(); i++){
    		bundle.put("mobClass" + i, mobs.get(i).mobClass.getName());
    		bundle.put("weight" + i, mobs.get(i).weight);
    	}
    }
    public void load() {
		Bundle bundle = new Bundle();
		loadMobList(bundle);
		
		this.filename = bundle.getString("filename");
		this.maxMobs = bundle.getInt("maxMobs");
		this.timeToRespawn = bundle.getFloat("timeToRespawn");
		try {
			this.theme = (Class<? extends Level>) Class.forName(bundle.getString("theme"));
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			output = Game.instance.openFileOutput( filename, Game.MODE_PRIVATE );
			Bundle.write( bundle, output );
			output.close();
		} catch (Exception e) {
		}
	}
    private void loadMobList(Bundle bundle){
    	mobs = new ArrayList<MobProbability>(4);
    	for (int i = 0; i < mobs.size(); i++){
    		String s = bundle.getString("mobClass" +1);
    		int j = bundle.getInt("weight" + i);
    		if(s == null){
    			break;
    		}
    		else{
    			try {
					mobs.add(new MobProbability((Class<? extends Mob>)Class.forName(s), j));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
    
    
	@Override
	public void restoreFromBundle(Bundle bundle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeInBundle(Bundle bundle) {
		
	}}


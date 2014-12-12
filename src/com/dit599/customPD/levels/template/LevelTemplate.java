package com.dit599.customPD.levels.template;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.util.Log;

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
	public List<Item> seeds = new ArrayList<Item>();
	public List<Class<? extends Potion>> potions = new ArrayList<Class<? extends Potion>>();
	public List<Class<? extends Scroll>> scrolls = new ArrayList<Class<? extends Scroll>>();
	public List<MagicItem> wands = new ArrayList<MagicItem>();
	public List<MagicItem> rings = new ArrayList<MagicItem>();

	public List<Room.Type> specialRooms = new ArrayList<Room.Type>();

	public List<MobProbability> mobs = new ArrayList<MobProbability>(4);

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
		return Dungeon.template.levelTemplates.get(Dungeon.depth - 1);
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

		public MobProbability(Class<? extends Mob> mobClass, int weight) {
			this.mobClass = mobClass;
			this.weight = weight;
		}
	}
	public static class MagicItem {
		public Class<? extends Item> itemClass;
		public int level;
		public boolean cursed;

		public MagicItem(Class<? extends Item> itemClass, int level, boolean cursed) {
			this.itemClass = itemClass;
			this.level = level;
			this.cursed = cursed;
		}
	}

	public ArrayList<Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.addAll(weapons);
		items.addAll(armor);
		items.addAll(consumables);
		items.addAll(seeds);
		
		try {
			for (Class<? extends Item> c : potions) {
				items.add(c.newInstance());
			}
			for (Class<? extends Item> c : scrolls) {
				items.add(c.newInstance());
			}
			for (MagicItem m : wands) {
				Wand w = (Wand) m.itemClass.newInstance();
				w.cursed = m.cursed;
				w.level = m.level;
				items.add(w);
			}
			for (MagicItem m : rings) {
				Ring r = (Ring) m.itemClass.newInstance();
				r.cursed = m.cursed;
				r.level = m.level;
				items.add(r);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return items;
	}

	private void saveMobList(Bundle bundle){
		for (int i = 0; i < mobs.size(); i++){
			bundle.put("mobClass" + i, mobs.get(i).mobClass.getName());
			bundle.put("weight" + i, mobs.get(i).weight);
		}
	}
	private void loadMobList(Bundle bundle, ClassLoader cl){
		mobs = new ArrayList<MobProbability>(4);
		int i = 0;
		while (true){
			String s = bundle.getString("mobClass" +i);
			int j = bundle.getInt("weight" + i);
			if(s == ""){
				break;
			}
			else{
				try {
					mobs.add(new MobProbability((Class<? extends Mob>) cl.loadClass(s), j));
					i++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void saveRoomList(Bundle bundle) {
		for (int i = 0; i < specialRooms.size(); i++){
			bundle.put("specialRooms" + i, specialRooms.get(i));
		}
	}
	private void loadRoomList(Bundle bundle, ClassLoader cl){
		specialRooms = new ArrayList<Room.Type>();
		int i = 0;
		while (true){
			Room.Type t = bundle.getEnum("specialRooms" +i, Room.Type.class);
			if(t == Room.Type.NULL){
				break;
			}
			else{
				specialRooms.add(t);
				i++;
			}
		}
	}
	
	private void saveScrollList(Bundle bundle) {
		for (int i = 0; i < scrolls.size(); i++){
			bundle.put("scrolls" + i, scrolls.get(i).getName());
		}
	}
	private void loadScrollList(Bundle bundle, ClassLoader cl){
		scrolls = new ArrayList<Class<? extends Scroll>>();
		int i = 0;
		while (true){
			String s = bundle.getString("scrolls" +i);
			if(s == ""){
				break;
			}
			else{
				try {
					scrolls.add((Class<? extends Scroll>) cl.loadClass(s));
					i++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void savePotionList(Bundle bundle) {
		for (int i = 0; i < potions.size(); i++){
			bundle.put("potions" + i, potions.get(i).getName());
		}
	}
	private void loadPotionList(Bundle bundle, ClassLoader cl){
		potions = new ArrayList<Class<? extends Potion>>();
		int i = 0;
		while (true){
			String s = bundle.getString("potions" +i);
			if(s == ""){
				break;
			}
			else{
				try {
					potions.add((Class<? extends Potion>) cl.loadClass(s));
					i++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	private void saveRingList(Bundle bundle) {
		for (int i = 0; i < rings.size(); i++){
			bundle.put("ringItemClass" + i, rings.get(i).itemClass.getName());
			bundle.put("ringLevel" + i, rings.get(i).level);
			bundle.put("ringCursed" + i, rings.get(i).cursed);
			
			
		}
	}
	private void loadRingList(Bundle bundle, ClassLoader cl){
		rings = new ArrayList<MagicItem>();
		int i = 0;
		while (true){
			String s = bundle.getString("ringItemClass" +i);
			int l = bundle.getInt("ringLevel" +i);
			boolean b = bundle.getBoolean("ringCursed" +i);
			if(s == ""){
				break;
			}
			else{
				try {
					rings.add(new MagicItem((Class<? extends Ring>) cl.loadClass(s), l, b));
					i++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void saveWandList(Bundle bundle) {
		for (int i = 0; i < wands.size(); i++){
			bundle.put("wandItemClass" + i, wands.get(i).itemClass.getName());
			bundle.put("wandLevel" + i, wands.get(i).level);
			bundle.put("wandCursed" + i, wands.get(i).cursed);
		}
	}
	private void loadWandList(Bundle bundle, ClassLoader cl){
		wands = new ArrayList<MagicItem>();
		int i = 0;
		while (true){
			String s = bundle.getString("wandItemClass" +i);
			int l = bundle.getInt("wandLevel" +i);
			boolean b = bundle.getBoolean("wandCursed" +i);
			if(s == ""){
				break;
			}
			else{
				try {
					wands.add(new MagicItem((Class<? extends Wand>) cl.loadClass(s), l, b));
					i++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void restoreFromBundle(Bundle bundle) {
		weapons = new ArrayList<Item>();
		armor = new ArrayList<Item>();
		consumables = new ArrayList<Item>();
		seeds = new ArrayList<Item>();
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		loadMobList(bundle, cl);
		loadRingList(bundle, cl);
		loadWandList(bundle, cl);
		loadPotionList(bundle, cl);
		loadScrollList(bundle, cl);
		loadRoomList(bundle, cl);
		this.maxMobs = bundle.getInt("maxMobs");
		this.timeToRespawn = bundle.getFloat("timeToRespawn");
		try {
			this.armor.addAll((Collection<? extends Item>) bundle.getCollection("armor"));
			this.weapons.addAll((Collection<? extends Item>) bundle.getCollection("weapons"));
			this.consumables.addAll((Collection<? extends Item>) bundle.getCollection("consumables"));
			this.seeds.addAll((Collection<? extends Item>) bundle.getCollection("seeds"));
			this.theme = (Class<? extends Level>) cl.loadClass(bundle.getString("theme"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void storeInBundle(Bundle bundle) {
		saveMobList(bundle);
		saveRingList(bundle);
		saveWandList(bundle);
		savePotionList(bundle);
		saveScrollList(bundle);
		saveRoomList(bundle);
		bundle.put("armor", this.armor);
		bundle.put("weapons", this.weapons);
		bundle.put("consumables", this.consumables);
		bundle.put("seeds", this.seeds);
		bundle.put("maxMobs", this.maxMobs);
		bundle.put("timeToRespawn", this.timeToRespawn);
		bundle.put("theme", this.theme.getName());
	}
}
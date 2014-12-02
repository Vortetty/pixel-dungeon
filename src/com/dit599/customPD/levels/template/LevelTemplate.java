package com.dit599.customPD.levels.template;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.Dungeon;
import com.dit599.customPD.actors.mobs.Mob;
import com.dit599.customPD.actors.mobs.Rat;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Level.Feeling;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.SewerLevel;
import com.watabou.utils.Random;

/**
 * Data class defining everything customizable in a custom level.
 */
public class LevelTemplate {
	/** Usually limited to 5-7. */
    public int maxMobs = 4;
	/** Default 50 */
    public float timeToRespawn = 50;

	public Class<? extends Level> theme = SewerLevel.class;
	public Feeling feeling = Feeling.NONE;
	
	public List<Item> items = new ArrayList<Item>();

    public ArrayList<Room.Type> specialRooms = new ArrayList<Room.Type>();

    public ArrayList<MobProbability> mobs = new ArrayList<MobProbability>(4);

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
}


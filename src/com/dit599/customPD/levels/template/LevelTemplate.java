package com.dit599.customPD.levels.template;

import java.util.ArrayList;

import com.dit599.customPD.Dungeon;
import com.dit599.customPD.actors.mobs.Bandit;
import com.dit599.customPD.actors.mobs.Gnoll;
import com.dit599.customPD.actors.mobs.Mob;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Level.Feeling;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Room.Type;
import com.dit599.customPD.levels.SewerLevel;
import com.watabou.utils.Random;

public class LevelTemplate {
	public Class<? extends Level> levelClass = SewerLevel.class;

	/** Usually limited to 5-7. */
	public int maxMobs = 2;
	/** Default 50 */
	public float timeToRespawn = 100;

	public Class<? extends Level> theme = SewerLevel.class;
	public Feeling feeling = Feeling.NONE;

	public Room.Type requiredSpecialRoom = Type.MAGIC_WELL;

	public ArrayList<MobProbability> mobs = new ArrayList<MobProbability>();

	public LevelTemplate() {
		mobs.add(new MobProbability(Gnoll.class, 3));
		mobs.add(new MobProbability(Bandit.class, 1));
	}

	public static LevelTemplate currentLevelTemplate() {
		if (Dungeon.template == null) {
			return null;
		}
		return Dungeon.template.levelTemplates[Dungeon.depth];
	}

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

	public class MobProbability {
		public Class<? extends Mob> mobClass;
		public int weight;

		private MobProbability(Class<? extends Mob> mobClass, int weight) {
			this.mobClass = mobClass;
			this.weight = weight;
		}
	}
}


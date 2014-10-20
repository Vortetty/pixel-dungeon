package com.dit599.customPD.levels.template;

import com.dit599.customPD.Dungeon;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Room.Type;
import com.dit599.customPD.levels.SewerLevel;

public class LevelTemplate {
	public Class<? extends Level> levelClass = SewerLevel.class;

	/** Usually limited to 5-7. */
	public int maxMobs = 2;
	/** Default 50 */
	public float timeToRespawn = 100;

	public Room.Type requiredSpecialRoom = Type.MAGIC_WELL;

	// TODO define mob classes


	public static LevelTemplate currentLevelTemplate() {
		if (Dungeon.template == null) {
			return null;
		}
		return Dungeon.template.levelTemplates[Dungeon.depth];
	}
}


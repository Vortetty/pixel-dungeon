/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.dit599.customPD.levels.painters;

import com.dit599.customPD.Dungeon;
import com.dit599.customPD.actors.blobs.WaterOfAwareness;
import com.dit599.customPD.actors.blobs.WaterOfHealth;
import com.dit599.customPD.actors.blobs.WaterOfTransmutation;
import com.dit599.customPD.actors.blobs.WellWater;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Terrain;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

public class IdentifyWellPainter extends Painter {
	
	@SuppressWarnings("unchecked")
	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY );
		
		Point c = room.center();
		set( level, c.x, c.y, Terrain.WELL );
		//set( level, c.x, c.y-1, Terrain.SIGN );
		
		
		Class<? extends WellWater> waterClass = WaterOfAwareness.class;
		WellWater water = (WellWater)level.blobs.get( waterClass);
		if (water == null) {
			try {
				water = waterClass.newInstance();
			} catch (Exception e) {
				water = null;
			}
		}
		water.seed( c.x + Level.WIDTH * c.y, 1 );
		level.blobs.put( waterClass, water );
		
		int pos;
		do {
			pos = room.random();
		} while (
				!Level.passable[pos] && !Level.avoid[pos]);
		set(level, pos, Terrain.SIGN);
		room.entrance().set( Room.Door.Type.HIDDEN );
	}
	public static String tip() {
		return "This is a well of identification, drink it to identify equipped items and to find out which inventory items are cursed! " +
				"Drinking will also reveal the current floor's map. You can also instead throw in a single item (including scrolls and " +
				"potions), to identify. This can be used to avoid the risk of equipping a cursed item just to identify it.";
	}
	public static String prompt() {
		return "Equipment Identification Room";
	}
}
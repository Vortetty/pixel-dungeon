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

import com.dit599.customPD.items.Generator;
import com.dit599.customPD.items.Gold;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.Heap.Type;
import com.dit599.customPD.items.keys.IronKey;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Terrain;
import com.watabou.utils.Point;

public class HardToHitRoomPainter extends Painter {

	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY );

		Point c = room.center();
		int cx = c.x;
		int cy = c.y;
		
		Room.Door entrance = room.entrance();
		
		entrance.set( Room.Door.Type.LOCKED );
		level.addItemToSpawn( new IronKey() );
		
		if (entrance.x == room.left) {
			level.drop( new Gold(50), room.right-1 + room.top+1  * Level.WIDTH ).type = Type.TOMB;
			level.drop( new Gold(50), room.right-1 + room.bottom-1  * Level.WIDTH ).type = Type.TOMB;
			cx = room.right - 2;
		} else if (entrance.x == room.right) {
			level.drop( new Gold(50), room.left+1 + room.top+1  * Level.WIDTH ).type = Type.TOMB;
			level.drop( new Gold(50), room.left+1 + room.bottom-1  * Level.WIDTH ).type = Type.TOMB;
			cx = room.left + 2;
		} else if (entrance.y == room.top) {
			level.drop( new Gold(50), room.left+1 + room.bottom-1 * Level.WIDTH ).type = Type.TOMB;
			level.drop( new Gold(50), room.right-1 + room.bottom-1 * Level.WIDTH ).type = Type.TOMB;
			cy = room.bottom - 2;
		} else if (entrance.y == room.bottom) {
			level.drop( new Gold(50), room.left+1 + room.top+1 * Level.WIDTH ).type = Type.TOMB;
			level.drop( new Gold(50), room.right-1 + room.top+1 * Level.WIDTH ).type = Type.TOMB;
			cy = room.top + 2;
		}
		
		set(level, new Point(cx, cy), Terrain.SIGN);
	}
	public static String tip() {
		return "Tombstones spawn ghosts in a '+' pattern in epmpty squares around the player. " +
				"Position yourself accordingly before activating them. Ghosts are easiest to kill " +
				"by exploiting the 1 guaranteed hit when the enemy enters a doorway. This guaranteed" +
				"chance of 1 hit works even for weapons that are too heavy.";
	}
	public static String prompt() {
		return "Hard-To-Hit\n\n" +
				"Try and get the enemy into a dorway, so that your next attack is guaranteed to hit!";
	}
}
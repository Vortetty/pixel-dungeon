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

import com.dit599.customPD.actors.Actor;
import com.dit599.customPD.actors.mobs.Brute;
import com.dit599.customPD.actors.mobs.Piranha;
import com.dit599.customPD.actors.mobs.Skeleton;
import com.dit599.customPD.items.Generator;
import com.dit599.customPD.items.Heap;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.potions.PotionOfInvisibility;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Terrain;
import com.watabou.utils.Random;

public class HardHittingRoomPainter extends Painter {

	private static final int NMOBS	= 3;
	
	public static void paint( Level level, Room room ) {
		
		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.WATER );
		
		Room.Door door = room.entrance(); 
		door.set( Room.Door.Type.REGULAR );
		
		int x = -1;
		int y = -1;
		if (door.x == room.left) {
			
			x = room.right - 1;
			y = room.top + room.height() / 2;
			
		} else if (door.x == room.right) {
			
			x = room.left + 1;
			y = room.top + room.height() / 2;
			
		} else if (door.y == room.top) {
			
			x = room.left + room.width() / 2;
			y = room.bottom - 1;
			
		} else if (door.y == room.bottom) {
			
			x = room.left + room.width() / 2;
			y = room.top + 1;
			
		}
		
		int pos = x + y * Level.WIDTH;
		set( level, pos, Terrain.SIGN );
		
		for (int i=0; i < NMOBS; i++) {
			Skeleton skeleton = new Skeleton();
			do {
				skeleton.pos = room.random();
			} while (level.map[skeleton.pos] != Terrain.WATER|| Actor.findChar( skeleton.pos ) != null);
			level.mobs.add( skeleton );
			Actor.occupyCell( skeleton );
		}
	}
	public static String tip() {
		return "The best way to take reduced damage is to wear good armor and to stand in a position were as few" +
				"enemies as possible can attack you at the same time. The penalty for wearing too heavy armor (getting " +
				"hungry faster and taking more turns for every movement) are only activated when moving, so while standing still " +
				"there is no downside. However, armor does nothing against magical damage.";
	}
	public static String prompt() {
		return "Hard-Hitting Enemies\n\n" +
				"Equip your heaviest armor to survive!";
	}
}

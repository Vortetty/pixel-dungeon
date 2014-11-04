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

import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Terrain;

public class TutorialExitPainter extends Painter {

	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY );
		
		for (Room.Door door : room.connected.values()) {
			door.set( Room.Door.Type.REGULAR );
		}
		
		level.exit = room.random( 1 );
		set( level, level.exit, Terrain.EXIT );
		
		int pos;
		do {
			pos = room.random();
		} while (
				!Level.passable[pos] && !Level.avoid[pos]);
		set(level, pos, Terrain.SIGN);
	}
	public static String tip() {
		return "It is recommended to explore each floor fully before going to the next one. " +
				"Tip: zoom out to check if the map is fully explored.";
	}
	public static String prompt() {
		return "Stairs To Next Floor";
	}
}

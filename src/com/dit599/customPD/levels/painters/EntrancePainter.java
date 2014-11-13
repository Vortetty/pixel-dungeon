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
import com.dit599.customPD.items.potions.PotionOfExperience;
import com.dit599.customPD.items.potions.PotionOfHealing;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Terrain;

public class EntrancePainter extends Painter {

	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY );

		for (Room.Door door : room.connected.values()) {
			if(Dungeon.isTutorial && Dungeon.depth == 2){
				door.set( Room.Door.Type.HIDDEN );
			}
			else{
				door.set( Room.Door.Type.REGULAR );
			}
		}
		do{
			level.entrance = room.random( 1 );
		}while (level.map[level.entrance] == Terrain.SIGN);
		set( level, level.entrance, Terrain.ENTRANCE );

		if(Dungeon.isTutorial){
			int pos;
			do{
				pos = room.random(0);
			}while (level.map[pos] != Terrain.EMPTY || level.heaps.get( pos ) != null);
			if(Dungeon.depth == 3){
			level.drop(new PotionOfHealing(), pos );
			}
			else if (Dungeon.depth == 4){
				level.drop(new PotionOfExperience(), pos );
			}
		}
	}

}

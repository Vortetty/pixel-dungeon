/*
 * YourPD
 * Copyright (C) 2014 YourPD team
 * This is a modification of source code from: 
 * Pixel Dungeon
 * Copyright (C) 2012-2014 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.levels.painters;

import java.util.Enumeration;

import com.watabou.pixeldungeon.CustomPD;
import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.items.Gold;
import com.watabou.pixeldungeon.items.Heap;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.bags.SeedPouch;
import com.watabou.pixeldungeon.items.keys.IronKey;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.levels.Room;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.plants.Earthroot;
import com.watabou.pixeldungeon.plants.Icecap;
import com.watabou.pixeldungeon.plants.Plant;
import com.watabou.pixeldungeon.plants.Sungrass;
import com.watabou.pixeldungeon.plants.Plant.Seed;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.utils.Random;
import com.watabou.utils.SparseArray;

import dalvik.system.DexFile;

/**
 * Paints a room that contains earthroot, sungrass and icecap seeds.
 */
public class SeedRoomPainter extends Painter {

	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY );

		set( level, room.center(), Terrain.SIGN );
//		if (Dungeon.template == null){
			int pos;
			Seed [] seeds = {
					new Earthroot.Seed(), new Earthroot.Seed(), new Sungrass.Seed(), new Icecap.Seed()
			};
			do {
				pos = room.random();
			} while (level.map[pos] != Terrain.EMPTY || level.heaps.get( pos ) != null);
			placeHeap(seeds, pos, level, Heap.Type.HEAP);
//		}
		room.entrance().set( Room.Door.Type.REGULAR );
	}
	/**
	 * Returns the string to display on a sign found in this room type.
	 */
	public static String tip() {
		return "Something has to step on the plant (or an item must be thrown ontop) in order to activate it. " +
				"For some plants you have to stay standing ontop to receive the effect!";
	}
	/**
	 * Returns the string to display on the prompt that appears when entering this room.
	 */
	public static String prompt() {
		return "Seed Room\n\n" +
				"This room contains a few copies of the most useful seeds in the game! press them in your inventory to learn what effect they have. " +
				"Seeds can be planted where you stand, or throw-planted on another square.";
	}
}
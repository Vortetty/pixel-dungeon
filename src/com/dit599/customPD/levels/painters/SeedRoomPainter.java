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

import java.util.Enumeration;

import com.dit599.customPD.PixelDungeon;
import com.dit599.customPD.items.Gold;
import com.dit599.customPD.items.Heap;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.bags.SeedPouch;
import com.dit599.customPD.items.keys.IronKey;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Terrain;
import com.dit599.customPD.plants.Firebloom.Seed;
import com.dit599.customPD.plants.Plant;
import com.dit599.customPD.scenes.GameScene;
import com.watabou.utils.Random;
import com.watabou.utils.SparseArray;

import dalvik.system.DexFile;

public class SeedRoomPainter extends Painter {

	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY );
		
		set( level, room.center(), Terrain.SIGN );
		
		int pos;
		do {
			pos = room.random();
		} while (level.map[pos] != Terrain.EMPTY || level.heaps.get( pos ) != null);
		dropAll(level.heaps, pos);

		room.entrance().set( Room.Door.Type.LOCKED );
		level.addItemToSpawn( new IronKey() );
	}
	private static void dropAll(SparseArray<Heap> heaps, int cell){
		Heap heap = heaps.get( cell );
		if (heap == null) {

			heap = new Heap();
			heap.pos = cell;
			heaps.put( cell, heap );
			GameScene.add( heap );			
		} else if (heap.type == Heap.Type.LOCKED_CHEST || heap.type == Heap.Type.CRYSTAL_CHEST) {
			int n;
			do {
				n = cell + Level.NEIGHBOURS8[Random.Int( 8 )];
			} while (!Level.passable[n] && !Level.avoid[n]);
			dropAll( heaps, n );
			return;
		}

		try{
			String dir = "com.dit599.customPD.plants.Plant";
			DexFile dex = new DexFile(PixelDungeon.self.getApplicationContext().getApplicationInfo().sourceDir);
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			Enumeration<String> entries = dex.entries();
			while (entries.hasMoreElements()) {
				String entry = entries.nextElement();
				if (entry.contains("$Seed") && !entry.contains(dir)){
					Class<?> c = classLoader.loadClass(entry);
					heap.drop((Item) c.newInstance());
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static String tip() {
		return "This room contains 1 of every seed in the game! Inspect them with '?' to learn what effect they have. " +
				"Seeds can be planted where you stand, or throw-planted on another square. Someone then has to step on " +
				"the plant to activate it, and for some plants you have to stay standing on that spot to recieve the effect!";
	}
	public static String prompt() {
		return "Seed Room";
	}
}
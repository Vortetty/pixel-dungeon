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
package com.dit599.customPD.levels.painters;

import java.util.Enumeration;

import com.dit599.customPD.CustomPD;
import com.dit599.customPD.items.Generator;
import com.dit599.customPD.items.Heap;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.keys.IronKey;
import com.dit599.customPD.items.scrolls.Scroll;
import com.dit599.customPD.items.scrolls.ScrollOfIdentify;
import com.dit599.customPD.items.scrolls.ScrollOfMagicMapping;
import com.dit599.customPD.items.scrolls.ScrollOfMirrorImage;
import com.dit599.customPD.items.scrolls.ScrollOfRemoveCurse;
import com.dit599.customPD.items.scrolls.ScrollOfTeleportation;
import com.dit599.customPD.items.scrolls.ScrollOfUpgrade;
import com.dit599.customPD.items.scrolls.ScrollOfWeaponUpgrade;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Terrain;
import com.dit599.customPD.scenes.GameScene;
import com.watabou.utils.Point;
import com.watabou.utils.Random;
import com.watabou.utils.SparseArray;

import dalvik.system.DexFile;

public class ScrollRoomPainter extends Painter {

	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY );

		Room.Door entrance = room.entrance();
		Point a = null;
		Point b = null;

		if (entrance.x == room.left) {
			a = new Point( room.left+1, entrance.y-1 );
			b = new Point( room.left+1, entrance.y+1 );
			fill( level, room.right - 1, room.top + 1, 1, room.height() - 1 , Terrain.BOOKSHELF );
		} else if (entrance.x == room.right) {
			a = new Point( room.right-1, entrance.y-1 );
			b = new Point( room.right-1, entrance.y+1 );
			fill( level, room.left+1, room.top + 1, 1, room.height() - 1 , Terrain.BOOKSHELF );
		} else if (entrance.y == room.top) {
			a = new Point( entrance.x+1, room.top+1 );
			b = new Point( entrance.x-1, room.top+1 );
			fill( level, room.left + 1, room.bottom-1, room.width() - 1, 1 , Terrain.BOOKSHELF );
		} else if (entrance.y == room.bottom) {
			a = new Point( entrance.x+1, room.bottom-1 );
			b = new Point( entrance.x-1, room.bottom-1 );
			fill( level, room.left + 1, room.top+1, room.width() - 1, 1 , Terrain.BOOKSHELF );
		}
		if (a != null && level.map[a.x + a.y * Level.WIDTH] == Terrain.EMPTY) {
			set( level, a, Terrain.SIGN );
		}
		else if (b != null && level.map[b.x + b.y * Level.WIDTH] == Terrain.EMPTY) {
			set( level, b, Terrain.SIGN );
		}

		int pos;
		do {
			pos = room.random();
		} while (level.map[pos] != Terrain.EMPTY || level.heaps.get( pos ) != null);
		dropAll(level.heaps, pos);

		entrance.set( Room.Door.Type.REGULAR );
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
		Scroll  [] scrolls = {
				new ScrollOfIdentify(), new ScrollOfMagicMapping(), new ScrollOfMagicMapping(),
				new ScrollOfRemoveCurse(), new ScrollOfUpgrade(), new ScrollOfWeaponUpgrade()
		};
		for(Scroll s : scrolls){
			s.setKnown();
			heap.drop(s);
		}
	}
	public static String tip() {
		return "Which scroll rune gives which effect is randomized each new game, and in " +
				"the real game these scrolls are unidentified until you use them. Beware cathing fire " +
				"while carrying scrolls, since they may be destroyed!";
	}
	public static String prompt() {
		return "Scroll Library\n\n " +
				"This room contains a few copies of the most useful scrolls in the game, press them in your inventory to learn " +
				"what they do. ";
	}
}


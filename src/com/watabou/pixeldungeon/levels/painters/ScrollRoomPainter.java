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
import com.watabou.pixeldungeon.items.Generator;
import com.watabou.pixeldungeon.items.Heap;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.keys.IronKey;
import com.watabou.pixeldungeon.items.scrolls.Scroll;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfIdentify;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfMagicMapping;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfMirrorImage;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfRemoveCurse;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfTeleportation;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfUpgrade;
import com.watabou.pixeldungeon.items.scrolls.ScrollOfWeaponUpgrade;
import com.watabou.pixeldungeon.levels.Level;
import com.watabou.pixeldungeon.levels.Room;
import com.watabou.pixeldungeon.levels.Terrain;
import com.watabou.pixeldungeon.scenes.GameScene;
import com.watabou.utils.Point;
import com.watabou.utils.Random;
import com.watabou.utils.SparseArray;

import dalvik.system.DexFile;

/**
 * Paints a room that contains scrolls of upgrade, weapon upgrade, identify, remove curse
 * and mapping.
 */
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

//		if (Dungeon.template == null){
			int pos;
			Scroll  [] scrolls = {
					new ScrollOfIdentify(), new ScrollOfMagicMapping(), new ScrollOfMagicMapping(),
					new ScrollOfRemoveCurse(), new ScrollOfUpgrade(), new ScrollOfWeaponUpgrade()
			};
			for(Scroll s : scrolls){
				s.setKnown();
			}
			do {
				pos = room.random();
			} while (level.map[pos] != Terrain.EMPTY || level.heaps.get( pos ) != null);
			placeHeap(scrolls, pos, level, Heap.Type.HEAP);
//		}
		entrance.set( Room.Door.Type.REGULAR );
	}
	/**
	 * Returns the string to display on a sign found in this room type.
	 */
	public static String tip() {
		return "Beware catching fire " +
				"while carrying scrolls, since they may be destroyed!";
	}
	/**
	 * Returns the string to display on the prompt that appears when entering this room.
	 */
	public static String prompt() {
		return "Scroll Library\n\n " +
				"This room contains a few copies of the most useful scrolls in the game, press them in your inventory to learn " +
				"what they do. Which scroll rune gives which effect is randomized each new game, and in " +
				"the real game all scroll types are unidentified until you use them.";
	}
}


/*
 * CustomPD
 * Copyright (C) 2014 CustomPD team
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

import java.util.ArrayList;

import com.dit599.customPD.Dungeon;
import com.dit599.customPD.actors.mobs.Mob;
import com.dit599.customPD.actors.mobs.npcs.ImpShopkeeper;
import com.dit599.customPD.actors.mobs.npcs.Shopkeeper;
import com.dit599.customPD.items.Ankh;
import com.dit599.customPD.items.Generator;
import com.dit599.customPD.items.Heap;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.Torch;
import com.dit599.customPD.items.Weightstone;
import com.dit599.customPD.items.armor.*;
import com.dit599.customPD.items.bags.ScrollHolder;
import com.dit599.customPD.items.bags.SeedPouch;
import com.dit599.customPD.items.bags.WandHolster;
import com.dit599.customPD.items.food.OverpricedRation;
import com.dit599.customPD.items.potions.PotionOfHealing;
import com.dit599.customPD.items.scrolls.ScrollOfIdentify;
import com.dit599.customPD.items.scrolls.ScrollOfMagicMapping;
import com.dit599.customPD.items.scrolls.ScrollOfRemoveCurse;
import com.dit599.customPD.items.weapon.melee.*;
import com.dit599.customPD.levels.LastShopLevel;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.Terrain;
import com.watabou.utils.Point;
import com.watabou.utils.Random;

public class ShopPainter extends Painter {

	private static int pasWidth;
	private static int pasHeight;
	private static final int MAX_TRIES = 10;

	public static void paint( Level level, Room room ) {

		fill( level, room, Terrain.WALL );
		fill( level, room, 1, Terrain.EMPTY_SP );

		pasWidth = room.width() - 2;
		pasHeight = room.height() - 2;
		int per = pasWidth * 2 + pasHeight * 2;

		Item[] range = range();

		placeShopkeeper( level, room );
		int pos = xy2p( room, room.entrance() ) + (per - range.length) / 2;
		for (int i=0; i < range.length; i++) {
			int tries = MAX_TRIES;
			Point xy = p2xy( room, (pos + per) % per );
			int cell = xy.x + xy.y * Level.WIDTH;

			if (level.heaps.get( cell ) != null) {
				do {
					cell = room.random();
					tries--;
				} while (level.heaps.get( cell ) != null && tries > 0);
			}

			level.drop( range[i], cell ).type = Heap.Type.FOR_SALE;

			pos++;
		}

		for (Room.Door door : room.connected.values()) {
			door.set( Room.Door.Type.REGULAR );
		}
	}

	private static Item[] range() {

		ArrayList<Item> items = new ArrayList<Item>();
		//		if (Dungeon.template == null){
		if (Dungeon.isTutorial){
			items.add( new LeatherArmor().identify() );
			items.add( new SeedPouch() );
			items.add( new ScrollHolder() );
		}
		else{
			if (Dungeon.template == null){
				switch (Dungeon.depth) {

				case 6:
					items.add( (Random.Int( 2 ) == 0 ? new Quarterstaff() : new Spear()).identify() );
					items.add( new LeatherArmor().identify() );
					items.add( new SeedPouch() );
					items.add( new Weightstone() );
					break;

				case 11:
					items.add( (Random.Int( 2 ) == 0 ? new Sword() : new Mace()).identify() );
					items.add( new MailArmor().identify() );
					items.add( new ScrollHolder() );
					items.add( new Weightstone() );
					break;

				case 16:
					items.add( (Random.Int( 2 ) == 0 ? new Longsword() : new BattleAxe()).identify() );
					items.add( new ScaleArmor().identify() );
					items.add( new WandHolster() );
					items.add( new Weightstone() );
					break;

				case 21:
					switch (Random.Int( 3 )) {
					case 0:
						items.add( new Glaive().identify() );
						break;
					case 1:
						items.add( new WarHammer().identify() );
						break;
					case 2:
						items.add( new PlateArmor().identify() );
						break;
					}
					items.add( new Torch() );
					items.add( new Torch() );
					break;
				}
			}
			else{
				items.add( new ScrollHolder() );
				items.add( new WandHolster() );
				items.add( new SeedPouch() );
				items.add( new Weightstone() );
			}
		}
		items.add( new Ankh() );
		items.add( new PotionOfHealing() );
		for (int i=0; i < 3; i++) {
			items.add( Generator.random( Generator.Category.POTION ) );
		}

		items.add( new ScrollOfIdentify() );
		items.add( new ScrollOfRemoveCurse() );
		items.add( new ScrollOfMagicMapping() );
		items.add( Generator.random( Generator.Category.SCROLL ) );

		items.add( new OverpricedRation() );
		items.add( new OverpricedRation() );
		//		}
		Item[] range =items.toArray( new Item[0] );
		Random.shuffle( range );
		return range;
	}

	private static void placeShopkeeper( Level level, Room room ) {

		int pos;
		do {
			pos = room.random();
		} while (level.heaps.get( pos ) != null);

		Mob shopkeeper = level instanceof LastShopLevel ? new ImpShopkeeper() : new Shopkeeper();
		shopkeeper.pos = pos;
		level.mobs.add( shopkeeper );

		if (level instanceof LastShopLevel) {
			for (int i=0; i < Level.NEIGHBOURS9.length; i++) {
				int p = shopkeeper.pos + Level.NEIGHBOURS9[i];
				if (level.map[p] == Terrain.EMPTY_SP) {
					level.map[p] = Terrain.WATER;
				}
			}
		}
	}

	private static int xy2p( Room room, Point xy ) {
		if (xy.y == room.top) {

			return (xy.x - room.left - 1);

		} else if (xy.x == room.right) {

			return (xy.y - room.top - 1) + pasWidth;

		} else if (xy.y == room.bottom) {

			return (room.right - xy.x - 1) + pasWidth + pasHeight;

		} else {

			if (xy.y == room.top + 1) {
				return 0;
			} else {
				return (room.bottom - xy.y - 1) + pasWidth * 2 + pasHeight;
			}

		}
	}

	private static Point p2xy( Room room, int p ) {
		if (p < pasWidth) {

			return new Point( room.left + 1 + p, room.top + 1);

		} else if (p < pasWidth + pasHeight) {

			return new Point( room.right - 1, room.top + 1 + (p - pasWidth) );

		} else if (p < pasWidth * 2 + pasHeight) {

			return new Point( room.right - 1 - (p - (pasWidth + pasHeight)), room.bottom - 1 );

		} else {

			return new Point( room.left + 1, room.bottom - 1 - (p - (pasWidth * 2 + pasHeight)) );

		}
	}
}

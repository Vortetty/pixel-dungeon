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
package com.dit599.customPD;

import java.util.HashMap;

import com.dit599.customPD.actors.hero.HeroClass;
import com.watabou.utils.Bundle;

public class GamesInProgress {

	private static HashMap<HeroClass, Info> state = new HashMap<HeroClass, Info>();
	private static HashMap<HeroClass, Info> t_state = new HashMap<HeroClass, Info>();
	
	public static Info check( HeroClass cl ) {
		
		if(Dungeon.isTutorial && t_state.containsKey( cl )){
			return t_state.get( cl );
		}
		else if (!Dungeon.isTutorial && state.containsKey( cl )) {
			
			return state.get( cl );
			
		} else {
			
			Info info;
			try {
				
				Bundle bundle = Dungeon.gameBundle( Dungeon.gameFile( cl ) );
				info = new Info();
				Dungeon.preview( info, bundle );

			} catch (Exception e) {
				info = null;
			}
			if(Dungeon.isTutorial){
				t_state.put( cl, info );
			}
			else{
				state.put( cl, info );
			}
			return info;
			
		}
	}
	
	public static void set( HeroClass cl, int depth, int level, int armor ) {
		Info info = new Info();
		info.depth = depth;
		info.level = level;
		info.armor = armor;
		if(Dungeon.isTutorial){
			t_state.put( cl, info );
		}
		else{
			state.put( cl, info );
		}
	}
	
	public static void setUnknown( HeroClass cl ) {
		if(Dungeon.isTutorial){
			t_state.remove(cl);
		}
		else{
			state.remove(cl);
		}
	}
	
	public static void delete( HeroClass cl ) {
		if(Dungeon.isTutorial){
			t_state.put( cl, null );
		}
		else{
			state.put( cl, null );
		}
	}
	
	public static class Info {
		public int depth;
		public int level;
		public int armor;
	}
}

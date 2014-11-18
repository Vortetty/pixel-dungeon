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
package com.dit599.customPD.actors.mobs;

import java.util.HashSet;

import com.dit599.customPD.Badges;
import com.dit599.customPD.Challenges;
import com.dit599.customPD.Dungeon;
import com.dit599.customPD.Statistics;
import com.dit599.customPD.actors.Char;
import com.dit599.customPD.actors.buffs.Amok;
import com.dit599.customPD.actors.buffs.Buff;
import com.dit599.customPD.actors.buffs.Sleep;
import com.dit599.customPD.actors.buffs.Terror;
import com.dit599.customPD.actors.hero.Hero;
import com.dit599.customPD.actors.hero.HeroSubClass;
import com.dit599.customPD.effects.Flare;
import com.dit599.customPD.effects.Wound;
import com.dit599.customPD.items.Generator;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.scenes.GameScene;
import com.dit599.customPD.sprites.CharSprite;
import com.dit599.customPD.utils.GLog;
import com.dit599.customPD.windows.WndOptions;
import com.dit599.customPD.windows.WndStory;
import com.watabou.utils.Bundle;
import com.watabou.utils.Random;

public abstract class Mob extends Char {
	
	private static final String	TXT_DIED	= "You hear something died in the distance";
	
	protected static final String TXT_NOTICE1	= "?!";
	protected static final String TXT_RAGE		= "#$%^";
	protected static final String TXT_EXP		= "%+dEXP";
	
	public AiState SLEEPEING	= new Sleeping();
	public AiState HUNTING		= new Hunting();
	public AiState WANDERING	= new Wandering();
	public AiState FLEEING		= new Fleeing();
	public AiState PASSIVE		= new Passive();
	public AiState state = SLEEPEING;
	
	public Class<? extends CharSprite> spriteClass;
	
	protected int target = -1;
	
	protected int defenseSkill = 0;
	
	protected int EXP = 1;
	protected int maxLvl = 30;
	
	protected Char enemy;
	protected boolean enemySeen;
	protected boolean alerted = false;

	protected static final float TIME_TO_WAKE_UP = 1f;
	
	public boolean hostile = true;
	
	// Unreachable target
	public static final Mob DUMMY = new Mob() {
		{
			pos = -1;
		}
	};
	
	private static final String STATE	= "state";
	private static final String TARGET	= "target";
	
	@Override
	public void storeInBundle( Bundle bundle ) {
		
		super.storeInBundle( bundle );
		
		if (state == SLEEPEING) {
			bundle.put( STATE, Sleeping.TAG );
		} else if (state == WANDERING) {
			bundle.put( STATE, Wandering.TAG );
		} else if (state == HUNTING) {
			bundle.put( STATE, Hunting.TAG );
		} else if (state == FLEEING) {
			bundle.put( STATE, Fleeing.TAG );
		} else if (state == PASSIVE) {
			bundle.put( STATE, Passive.TAG );
		}
		bundle.put( TARGET, target );
	}
	
	@Override
	public void restoreFromBundle( Bundle bundle ) {
		
		super.restoreFromBundle( bundle );
		
		String state = bundle.getString( STATE );
		if (state.equals( Sleeping.TAG )) {
			this.state = SLEEPEING;
		} else if (state.equals( Wandering.TAG )) {
			this.state = WANDERING;
		} else if (state.equals( Hunting.TAG )) {
			this.state = HUNTING;
		} else if (state.equals( Fleeing.TAG )) {
			this.state = FLEEING;
		} else if (state.equals( Passive.TAG )) {
			this.state = PASSIVE;
		}

		target = bundle.getInt( TARGET );
	}
	
	public CharSprite sprite() {
		CharSprite sprite = null;
		try {
			sprite = spriteClass.newInstance();
		} catch (Exception e) {
		}
		return sprite;
	}
	
	@Override
	protected boolean act() {
		
		super.act();
		
		boolean justAlerted = alerted;
		alerted = false;
		
		sprite.hideAlert();
		
		if (paralysed) {
			enemySeen = false;
			spend( TICK );
			return true;
		}
		
		enemy = chooseEnemy();
		
		boolean enemyInFOV = enemy.isAlive() && Level.fieldOfView[enemy.pos] && enemy.invisible <= 0;
		
		return state.act( enemyInFOV, justAlerted );
	}
	
	protected Char chooseEnemy() {
		
		if (buff( Amok.class ) != null) {
			if (enemy == Dungeon.hero || enemy == null) {
				
				HashSet<Mob> enemies = new HashSet<Mob>();
				for (Mob mob:Dungeon.level.mobs) {
					if (mob != this && Level.fieldOfView[mob.pos]) {
						enemies.add( mob );
					}
				}
				if (enemies.size() > 0) {
					return Random.element( enemies );
				}
				
			} else {
				return enemy;
			}
		}
		
		Terror terror = (Terror)buff( Terror.class );
		if (terror != null) {
			return terror.source;
		}
		
		return Dungeon.hero;
	}
	
	protected boolean moveSprite( int from, int to ) {

		if (sprite.isVisible() && (Dungeon.visible[from] || Dungeon.visible[to])) {
			sprite.move( from, to );
			return true;
		} else {
			sprite.place( to );
			return true;
		}
	}
	
	@Override
	public void add( Buff buff ) {
		super.add( buff );
		if (buff instanceof Amok) {
			if (sprite != null) {
				sprite.showStatus( CharSprite.NEGATIVE, TXT_RAGE );
			}
			state = HUNTING;
		} else if (buff instanceof Terror) {
			state = FLEEING;
		} else if (buff instanceof Sleep) {
			if (sprite != null) {
				new Flare( 4, 32 ).color( 0x44ffff, true ).show( sprite, 2f ) ;
			}
			state = SLEEPEING;
			postpone( Sleep.SWS );
		}
	}
	
	@Override
	public void remove( Buff buff ) {
		super.remove( buff );
		if (buff instanceof Terror) {
			sprite.showStatus( CharSprite.NEGATIVE, TXT_RAGE );
			state = HUNTING;
		}
	}
	
	protected boolean canAttack( Char enemy ) {
		return Level.adjacent( pos, enemy.pos ) && !pacified;
	}
	
	protected boolean getCloser( int target ) {
		
		if (rooted) {
			return false;
		}
		
		int step = Dungeon.findPath( this, pos, target, 
			Level.passable, 
			Level.fieldOfView );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean getFurther( int target ) {
		int step = Dungeon.flee( this, pos, target, 
			Level.passable, 
			Level.fieldOfView );
		if (step != -1) {
			move( step );
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void move( int step ) {
		super.move( step );
		
		if (!flying) {
			Dungeon.level.mobPress( this );
		}
	}
	
	protected float attackDelay() {
		return 1f;
	}
	
	protected boolean doAttack( Char enemy ) {
		
		boolean visible = Dungeon.visible[pos];
		
		if (visible) {
			sprite.attack( enemy.pos );
		} else {
			attack( enemy );
		}
		if(Dungeon.isTutorial && !Dungeon.encounteredMob){
			Dungeon.encounteredMob = true;
			WndStory.showChapter("Attack the monster by pressing it (or on the red icon in the lower right)!");
		}
		spend( attackDelay() );
		
		return !visible;
	}
	
	@Override
	public void onAttackComplete() {
		attack( enemy );
		super.onAttackComplete();
	}
	
	@Override
	public int defenseSkill( Char enemy ) {
		return enemySeen && !paralysed ? defenseSkill : 0;
	}
	
	@Override
	public int defenseProc( Char enemy, int damage ) {
		if (!enemySeen && enemy == Dungeon.hero && ((Hero)enemy).subClass == HeroSubClass.ASSASSIN) {
			damage += Random.Int( 1, damage );
			Wound.hit( this );
		}
		return damage;
	}
	
	@Override
	public void damage( int dmg, Object src ) {

		Terror.recover( this );
		
		if (state == SLEEPEING) {
			state = WANDERING;
		}
		alerted = true;
		
		super.damage( dmg, src );
	}
	
	
	@Override
	public void destroy() {
		
		super.destroy();
		
		Dungeon.level.mobs.remove( this );
		
		if (Dungeon.hero.isAlive()) {
			
			if (hostile) {
				Statistics.enemiesSlain++;
				Badges.validateMonstersSlain();
				Statistics.qualifiedForNoKilling = false;
				
				if (Dungeon.nightMode) {
					Statistics.nightHunt++;
				} else {
					Statistics.nightHunt = 0;
				}
				Badges.validateNightHunter();
			}
			
			if (Dungeon.hero.lvl <= maxLvl && EXP > 0) {
				Dungeon.hero.sprite.showStatus( CharSprite.POSITIVE, TXT_EXP, EXP );
				Dungeon.hero.earnExp( EXP );
			}
		}
	}
	
	@Override
	public void die( Object cause ) {
		
		super.die( cause );
		
		if (Dungeon.hero.lvl <= maxLvl + 2) {
			dropLoot();
		}
		
		if (Dungeon.hero.isAlive() && !Dungeon.visible[pos]) {	
			GLog.i( TXT_DIED );
		}
	}
	
	protected Object loot = null;
	protected float lootChance = 0;
	
	@SuppressWarnings("unchecked")
	protected void dropLoot() {
		if (loot != null && Random.Float() < lootChance) {
			Item item = null;
			if (loot instanceof Generator.Category) {
				
				item = Generator.random( (Generator.Category)loot );
				
			} else if (loot instanceof Class<?>) {
				
				item = Generator.random( (Class<? extends Item>)loot );
				
			} else {
				
				item = (Item)loot;
				
			}
			Dungeon.level.drop( item, pos ).sprite.drop();
		}
	}
	
	public boolean reset() {
		return false;
	}
	
	public void beckon( int cell ) {
		
		notice();
		
		if (state != HUNTING) {
			state = WANDERING;
		}
		target = cell;
	}
	
	public String description() {
		return "Real description is coming soon!";
	}
	
	public void notice() {
		sprite.showAlert();
	}
	
	public void yell( String str ) {
		GLog.n( "%s: \"%s\" ", name, str );
	}
	
	public interface AiState {
		public boolean act( boolean enemyInFOV, boolean justAlerted );
		public String status();
	}
	
	private class Sleeping implements AiState {
		
		public static final String TAG	= "SLEEPING";
		
		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			if (enemyInFOV && Random.Int( distance( enemy ) + enemy.stealth() + (enemy.flying ? 2 : 0) ) == 0) {
					
				enemySeen = true;

				notice();
				state = HUNTING;
				target = enemy.pos;
				
				if (Dungeon.isChallenged( Challenges.SWARM_INTELLIGENCE )) {
					for (Mob mob : Dungeon.level.mobs) {
						if (mob != Mob.this) {
							mob.beckon( target );
						}
					}
				}
				
				spend( TIME_TO_WAKE_UP );
				
			} else {
				
				enemySeen = false;
				
				spend( TICK );
				
			}
			return true;
		}
		
		@Override
		public String status() {
			return String.format( "This %s is sleeping", name );
		}
	}
	
	private class Wandering implements AiState {
		
		public static final String TAG	= "WANDERING";
		
		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			if (enemyInFOV && (justAlerted || Random.Int( distance( enemy ) / 2 + enemy.stealth() ) == 0)) {
				
				enemySeen = true;
				
				notice();
				state = HUNTING;
				target = enemy.pos;
				
			} else {
				
				enemySeen = false;
				
				int oldPos = pos;
				if (target != -1 && getCloser( target )) {
					spend( 1 / speed() );
					return moveSprite( oldPos, pos );
				} else {
					target = Dungeon.level.randomDestination();
					spend( TICK );
				}
				
			}
			return true;
		}
		
		@Override
		public String status() {
			return String.format( "This %s is wandering", name );
		}
	}
	
	private class Hunting implements AiState {
		
		public static final String TAG	= "HUNTING";
		
		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			enemySeen = enemyInFOV;
			if (enemyInFOV && canAttack( enemy )) {
				
				return doAttack( enemy );
				
			} else {

				if (enemyInFOV) {
					target = enemy.pos;
				}

				int oldPos = pos;
				if (target != -1 && getCloser( target )) {
					
					spend( 1 / speed() );
					return moveSprite( oldPos,  pos );
					
				} else {
					
					spend( TICK );
					state = WANDERING;
					target = Dungeon.level.randomDestination();
					return true;
				}
			}
		}
		
		@Override
		public String status() {
			return String.format( "This %s is hunting", name );
		}
	}
	
	protected class Fleeing implements AiState {
		
		public static final String TAG	= "FLEEING";
		
		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			enemySeen = enemyInFOV;
			if (enemyInFOV) {
				target = enemy.pos;
			}
			
			int oldPos = pos;
			if (target != -1 && getFurther( target )) {
				
				spend( 1 / speed() );
				return moveSprite( oldPos, pos );
				
			} else {
				
				spend( TICK );
				nowhereToRun();
				
				return true;
			}
		}
		
		protected void nowhereToRun() {
		}
		
		@Override
		public String status() {
			return String.format( "This %s is fleeing", name );
		}
	}
	
	private class Passive implements AiState {
		
		public static final String TAG	= "PASSIVE";
		
		@Override
		public boolean act( boolean enemyInFOV, boolean justAlerted ) {
			enemySeen = false;
			spend( TICK );
			return true;
		}
		
		@Override
		public String status() {
			return String.format( "This %s is passive", name );
		}
	}
}

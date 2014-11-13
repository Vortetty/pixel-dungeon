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
package com.dit599.customPD.actors.mobs;

import java.util.HashSet;

import com.dit599.customPD.Badges;
import com.dit599.customPD.Dungeon;
import com.dit599.customPD.actors.Char;
import com.dit599.customPD.actors.blobs.ToxicGas;
import com.dit599.customPD.actors.buffs.Buff;
import com.dit599.customPD.actors.buffs.Ooze;
import com.dit599.customPD.effects.Speck;
import com.dit599.customPD.items.LloydsBeacon;
import com.dit599.customPD.items.keys.SkeletonKey;
import com.dit599.customPD.items.scrolls.ScrollOfPsionicBlast;
import com.dit599.customPD.items.weapon.enchantments.Death;
import com.dit599.customPD.levels.Level;
import com.dit599.customPD.levels.SewerBossLevel;
import com.dit599.customPD.levels.TutorialBossLevel;
import com.dit599.customPD.scenes.GameScene;
import com.dit599.customPD.sprites.CharSprite;
import com.dit599.customPD.sprites.GooSprite;
import com.dit599.customPD.utils.GLog;
import com.watabou.noosa.Camera;
import com.watabou.utils.Random;

public class Goo extends Mob {

	private static final float PUMP_UP_DELAY	= 2f;
	
	{
		name = "Goo";
		HP = HT = 80;
		EXP = 10;
		defenseSkill = 12;
		spriteClass = GooSprite.class;
		
		loot = new LloydsBeacon();
		lootChance = 0.333f;
	}
	
	private boolean pumpedUp = false;
	
	@Override
	public int damageRoll() {
		if (pumpedUp) {
			return Random.NormalIntRange( 5, 30 );
		} else {
			return Random.NormalIntRange( 2, 12 );
		}
	}
	
	@Override
	public int attackSkill( Char target ) {
		return pumpedUp ? 30 : 15;
	}
	
	@Override
	public int dr() {
		return 2;
	}
	
	@Override
	public boolean act() {
		
		if (Level.water[pos] && HP < HT) {
			sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
			HP++;
		}
		
		return super.act();
	}
	
	@Override
	protected boolean canAttack( Char enemy ) {
		return pumpedUp ? distance( enemy ) <= 2 : super.canAttack(enemy);
	}
	
	@Override
	public int attackProc( Char enemy, int damage ) {
		if (Random.Int( 3 ) == 0) {
			Buff.affect( enemy, Ooze.class );
			enemy.sprite.burst( 0x000000, 5 );
		}
		
		if (pumpedUp) {
			Camera.main.shake( 3, 0.2f );
		}
		
		return damage;
	}
	
	@Override
	protected boolean doAttack( Char enemy ) {		
		if (pumpedUp || Random.Int( 3 ) > 0) {
		
			return super.doAttack( enemy );

		} else {
			
			pumpedUp = true;
			spend( PUMP_UP_DELAY );
			
			((GooSprite)sprite).pumpUp();
			
			if (Dungeon.visible[pos]) {
				sprite.showStatus( CharSprite.NEGATIVE, "!!!" );
				GLog.n( "Goo is pumping itself up!" );
			}
				
			return true;
		}
	}
	
	@Override
	public boolean attack( Char enemy ) {
		boolean result = super.attack( enemy );
		pumpedUp = false;
		return result;
	}
	
	@Override
	protected boolean getCloser( int target ) {
		pumpedUp = false;
		return super.getCloser( target );
	}
	
	@Override
	public void move( int step ) {
		try{
		((SewerBossLevel)Dungeon.level).seal();
		}
		catch(Exception e){
			((TutorialBossLevel)Dungeon.level).seal();
		}
		super.move( step );
	}
	
	@Override
	public void die( Object cause ) {
		
		super.die( cause );
		try{
		((SewerBossLevel)Dungeon.level).unseal();
		}
		catch(Exception e){
			((TutorialBossLevel)Dungeon.level).unseal();
		}
		
		GameScene.bossSlain();
		Dungeon.level.drop( new SkeletonKey(), pos ).sprite.drop();
		
		Badges.validateBossSlain();
		
		yell( "glurp... glurp..." );
	}
	
	@Override
	public void notice() {
		super.notice();
		yell( "GLURP-GLURP!" );
	}
	
	@Override
	public String description() {
		return
			"Little known about The Goo. It's quite possible that it is not even a creature, but rather a " +
			"conglomerate of substances from the sewers that gained rudiments of free will.";
	}
	
	private static final HashSet<Class<?>> RESISTANCES = new HashSet<Class<?>>();
	static {
		RESISTANCES.add( ToxicGas.class );
		RESISTANCES.add( Death.class );
		RESISTANCES.add( ScrollOfPsionicBlast.class );
	}
	
	@Override
	public HashSet<Class<?>> resistances() {
		return RESISTANCES;
	}
}

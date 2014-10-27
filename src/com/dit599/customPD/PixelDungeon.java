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

import javax.microedition.khronos.opengles.GL10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.style.SuperscriptSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.dit599.customPD.scenes.GameScene;
import com.dit599.customPD.scenes.PixelScene;
import com.dit599.customPD.scenes.TitleScene;
import com.watabou.noosa.Game;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;

public class PixelDungeon extends Game {
	public static PixelDungeon self;
	
	public PixelDungeon() {
		super( TitleScene.class );
		
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.items.scrolls.ScrollOfUpgrade.class, 
			"com.dit599.customPD.items.scrolls.ScrollOfEnhancement" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.actors.blobs.WaterOfHealth.class, 
			"com.dit599.customPD.actors.blobs.Light" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.items.rings.RingOfMending.class, 
			"com.dit599.customPD.items.rings.RingOfRejuvenation" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.items.wands.WandOfTelekinesis.class, 
			"com.dit599.customPD.items.wands.WandOfTelekenesis" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.actors.blobs.Foliage.class, 
			"com.dit599.customPD.actors.blobs.Blooming" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.actors.buffs.Shadows.class, 
			"com.dit599.customPD.actors.buffs.Rejuvenation" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.items.scrolls.ScrollOfPsionicBlast.class, 
			"com.dit599.customPD.items.scrolls.ScrollOfNuclearBlast" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.actors.hero.Hero.class, 
			"com.dit599.customPD.actors.Hero" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.actors.mobs.npcs.Shopkeeper.class,
			"com.dit599.customPD.actors.mobs.Shopkeeper" );
		// 1.6.1
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.items.quest.DriedRose.class,
			"com.dit599.customPD.items.DriedRose" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.actors.mobs.npcs.MirrorImage.class,
			"com.dit599.customPD.items.scrolls.ScrollOfMirrorImage.MirrorImage" );
		// 1.6.4
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.items.rings.RingOfElements.class,
			"com.dit599.customPD.items.rings.RingOfCleansing" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.items.rings.RingOfElements.class,
			"com.dit599.customPD.items.rings.RingOfResistance" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.items.weapon.missiles.Boomerang.class,
			"com.dit599.customPD.items.weapon.missiles.RangersBoomerang" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.items.rings.RingOfPower.class,
			"com.dit599.customPD.items.rings.RingOfEnergy" );
		// 1.7.2
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.plants.Dreamweed.class,
			"com.dit599.customPD.plants.Blindweed" );
		com.watabou.utils.Bundle.addAlias( 
			com.dit599.customPD.plants.Dreamweed.Seed.class,
			"com.dit599.customPD.plants.Blindweed$Seed" );
		self = this;
	}
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		
		updateImmersiveMode();
		
		DisplayMetrics metrics = new DisplayMetrics();
		instance.getWindowManager().getDefaultDisplay().getMetrics( metrics );
		boolean landscape = metrics.widthPixels > metrics.heightPixels;
		
		if (Preferences.INSTANCE.getBoolean( Preferences.KEY_LANDSCAPE, false ) != landscape) {
			landscape( !landscape );
		}
		
		Music.INSTANCE.enable( music() );
		Sample.INSTANCE.enable( soundFx() );
	}
	
	@Override
	public void onWindowFocusChanged( boolean hasFocus ) {
		
		super.onWindowFocusChanged( hasFocus );
		
		if (hasFocus) {
			updateImmersiveMode();
		}
	}
	
	public static void switchNoFade( Class<? extends PixelScene> c ) {
		PixelScene.noFade = true;
		switchScene( c );
	}
	
	/*
	 * ---> Prefernces
	 */
	
	public static void landscape( boolean value ) {
		Game.instance.setRequestedOrientation( value ?
			ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
			ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );
		Preferences.INSTANCE.put( Preferences.KEY_LANDSCAPE, value );
	}
	
	public static boolean landscape() {
		return width > height;
	}
	
	// *** IMMERSIVE MODE ****
	
	private static boolean immersiveModeChanged = false;
	
	@SuppressLint("NewApi")
	public static void immerse( boolean value ) {
		Preferences.INSTANCE.put( Preferences.KEY_IMMERSIVE, value );
		
		instance.runOnUiThread( new Runnable() {
			@Override
			public void run() {
				updateImmersiveMode();
				immersiveModeChanged = true;
			}
		} );
	}
	
	@Override
	public void onSurfaceChanged( GL10 gl, int width, int height ) {
		super.onSurfaceChanged( gl, width, height );
		
		if (immersiveModeChanged) {
			requestedReset = true;
			immersiveModeChanged = false;
		}
	}
	
	@SuppressLint("NewApi")
	public static void updateImmersiveMode() {
		if (android.os.Build.VERSION.SDK_INT >= 19) {
			instance.getWindow().getDecorView().setSystemUiVisibility( 
				immersed() ?
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE | 
				View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | 
				View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | 
				View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | 
				View.SYSTEM_UI_FLAG_FULLSCREEN | 
				View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY 
				:
				0 );
		}
	}
	
	public static boolean immersed() {
		return Preferences.INSTANCE.getBoolean( Preferences.KEY_IMMERSIVE, false );
	}
	
	// *****************************
	
	public static void scaleUp( boolean value ) {
		Preferences.INSTANCE.put( Preferences.KEY_SCALE_UP, value );
		switchScene( TitleScene.class );
	}
	
	public static boolean scaleUp() {
		return Preferences.INSTANCE.getBoolean( Preferences.KEY_SCALE_UP, true );
	}

	public static void zoom( int value ) {
		Preferences.INSTANCE.put( Preferences.KEY_ZOOM, value );
	}
	
	public static int zoom() {
		return Preferences.INSTANCE.getInt( Preferences.KEY_ZOOM, 0 );
	}
	
	public static void music( boolean value ) {
		Music.INSTANCE.enable( value );
		Preferences.INSTANCE.put( Preferences.KEY_MUSIC, value );
	}
	
	public static boolean music() {
		return Preferences.INSTANCE.getBoolean( Preferences.KEY_MUSIC, true );
	}
	
	public static void soundFx( boolean value ) {
		Sample.INSTANCE.enable( value );
		Preferences.INSTANCE.put( Preferences.KEY_SOUND_FX, value );
	}
	
	public static boolean soundFx() {
		return Preferences.INSTANCE.getBoolean( Preferences.KEY_SOUND_FX, true );
	}
	
	public static void brightness( boolean value ) {
		Preferences.INSTANCE.put( Preferences.KEY_BRIGHTNESS, value );
		if (scene() instanceof GameScene) {
			((GameScene)scene()).brightness( value );
		}
	}
	
	public static boolean brightness() {
		return Preferences.INSTANCE.getBoolean( Preferences.KEY_BRIGHTNESS, false );
	}
	
	public static void donated( String value ) {
		Preferences.INSTANCE.put( Preferences.KEY_DONATED, value );
	}
	
	public static String donated() {
		return Preferences.INSTANCE.getString( Preferences.KEY_DONATED, "" );
	}
	
	public static void lastClass( int value ) {
		if(Dungeon.isTutorial){
			Preferences.INSTANCE.put( Preferences.KEY_T_LAST_CLASS, value );
		}
		else{
			Preferences.INSTANCE.put( Preferences.KEY_LAST_CLASS, value );
		}
	}
	
	public static int lastClass() {
		if(Dungeon.isTutorial){
			return Preferences.INSTANCE.getInt( Preferences.KEY_T_LAST_CLASS, 0 );
		}
		else{
			return Preferences.INSTANCE.getInt( Preferences.KEY_LAST_CLASS, 0 );
		}
	}
	
	public static void challenges( int value ) {
		Preferences.INSTANCE.put( Preferences.KEY_CHALLENGES, value );
	}
	
	public static int challenges() {
		return Preferences.INSTANCE.getInt( Preferences.KEY_CHALLENGES, 0 );
	}
	
	public static void intro( boolean value ) {
		Preferences.INSTANCE.put( Preferences.KEY_INTRO, value );
	}
	
	public static boolean intro() {
		return Preferences.INSTANCE.getBoolean( Preferences.KEY_INTRO, true );
	}
	
	/*
	 * <--- Preferences
	 */
	
	public static void reportException( Exception e ) {
		Log.e( "PD", Log.getStackTraceString( e ) ); 
	}

	public void editMaps() {
		startActivity(new Intent ("com.dit599.editorUI.FloorsListActivity"));
	}
	


}
package com.dit599.customPD.scenes;

import com.dit599.customPD.Assets;
import com.dit599.customPD.effects.BannerSprites;
import com.dit599.customPD.effects.Fireball;
import com.dit599.customPD.ui.Archs;
import com.dit599.customPD.ui.PrefsButton;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Camera;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.audio.Music;
import com.watabou.noosa.audio.Sample;
import com.watabou.noosa.ui.Button;

public class GameModeScene extends PixelScene {

	private static final String TXT_STANDARD		= "Standard";
	private static final String TXT_CUSTOM	= "Custom";
	private static final String TXT_MAP_EDITOR		= "Map Editor";
	private static final String TXT_TUTORIAL		= "Tutorial";
	
	@Override
	public void create() {
		
		super.create();
		
		Music.INSTANCE.play( Assets.THEME, true );
		Music.INSTANCE.volume( 1f );
		
		uiCamera.visible = false;
		
		int w = Camera.main.width;
		int h = Camera.main.height;
		
		float height = 180;
		
		Archs archs = new Archs();
		archs.setSize( w, h );
		add( archs );
		
		Image title = BannerSprites.get( BannerSprites.Type.PIXEL_DUNGEON );
		add( title );
		
		title.x = (w - title.width()) / 2;
		title.y = (h - height) / 2;
		
		placeTorch( title.x + 18, title.y + 20 );
		placeTorch( title.x + title.width - 18, title.y + 20 );
		
		DashboardItem btnCustom = new DashboardItem( TXT_CUSTOM, 0 ) {
			@Override
			protected void onClick() {
				Game.switchScene( StartScene.class );
			}
		};
		btnCustom.setPos( w / 2 - btnCustom.width(), (h + height) / 2 - DashboardItem.SIZE );
		add( btnCustom );
		
		DashboardItem btnEditor = new DashboardItem( TXT_MAP_EDITOR, 0 ) {
			@Override
			protected void onClick() {
				Game.switchScene( StartScene.class );
			}
		};
		btnEditor.setPos( w / 2, (h + height) / 2 - DashboardItem.SIZE );
		add( btnEditor );
		
		DashboardItem btnStandard = new DashboardItem( TXT_STANDARD, 0 ) {
			@Override
			protected void onClick() {
				Game.switchScene( StartScene.class );
			}
		};
		btnStandard.setPos( w / 2 - btnStandard.width(), btnEditor.top() - DashboardItem.SIZE );
		add( btnStandard );
		
		DashboardItem btnTutorial = new DashboardItem( TXT_TUTORIAL, 0 ) {
			@Override
			protected void onClick() {
				Game.switchScene( StartScene.class );
			}
		};
		btnTutorial.setPos( w / 2, btnStandard.top() );
		add( btnTutorial );
		
		BitmapText version = new BitmapText( "v " + Game.version, font1x );
		version.measure();
		version.hardlight( 0x888888 );
		version.x = w - version.width();
		version.y = h - version.height();
		add( version );
		
		PrefsButton btnPrefs = new PrefsButton();
		btnPrefs.setPos( w - btnPrefs.width() - 1, 1 );
		add( btnPrefs );
		
		fadeIn();
	}
	
	private void placeTorch( float x, float y ) {
		Fireball fb = new Fireball();
		fb.setPos( x, y );
		add( fb );
	}
	
	private static class DashboardItem extends Button {
		
		public static final float SIZE	= 48;
		
		private static final int IMAGE_SIZE	= 32;
		
		private Image image;
		private BitmapText label;
		
		public DashboardItem( String text, int index ) {
			super();
			
			image.frame( image.texture.uvRect( index * IMAGE_SIZE, 0, (index + 1) * IMAGE_SIZE, IMAGE_SIZE ) );
			this.label.text( text );
			this.label.measure();
			
			setSize( SIZE, SIZE );
		}
		
		@Override
		protected void createChildren() {
			super.createChildren();
			
			image = new Image( Assets.DASHBOARD );
			add( image );
			
			label = createText( 9 );
			add( label );
		}
		
		@Override
		protected void layout() {
			super.layout();
			
			image.x = align( x + (width - image.width()) / 2 );
			image.y = align( y );
			
			label.x = align( x + (width - label.width()) / 2 );
			label.y = align( image.y + image.height() +2 );
		}
		
		@Override
		protected void onTouchDown() {
			image.brightness( 1.5f );
			Sample.INSTANCE.play( Assets.SND_CLICK, 1, 1, 0.8f );
		}
		
		@Override
		protected void onTouchUp() {
			image.resetColor();
		}
	}
	@Override
	protected void onBackPressed() {
		Game.switchScene( TitleScene.class );
	}
}

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
package com.dit599.customPD.editorUI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.dit599.customPD.R;
import com.dit599.customPD.levels.template.LevelTemplate;
import com.watabou.noosa.Game;


public class MapEditActivity extends FragmentActivity{
    
    public static final String EXTRA_FILENAME = "filename";

    public static final String TAB_ADD_FLOOR = "add_floor";

    public TemplateHandler templateHandler;
	
	private String mapName;
	private FragmentTabHost mTabHost;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor_ui);
		
        mapName = getIntent().getStringExtra(EXTRA_FILENAME);
        if (mapName == null) {
            throw new NullPointerException("Intent parameter \"" + EXTRA_FILENAME + "\" missing");
        }
        setTitle(mapName);

        templateHandler = TemplateHandler.getInstance(mapName, this);

        if (templateHandler == null || templateHandler.getDungeon() == null) {
            throw new NullPointerException();
        }
		
        initMappings();

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec(TAB_ADD_FLOOR).setIndicator("Add floor", null),
                Fragment.class, null);
        int depth = 1;
        for (LevelTemplate level : templateHandler.getDungeon().levelTemplates) {
            mTabHost.addTab(
                    mTabHost.newTabSpec(String.valueOf(depth)).setIndicator("Floor " + depth, null),
                FloorFragment.class, null);
            depth++;
        }
        
        
        mTabHost.setOnTabChangedListener(new OnTabChangeListener(){
			@Override
			public void onTabChanged(String tabSpec) {
                if (tabSpec.equals(TAB_ADD_FLOOR)) {
			    	int tabCount = mTabHost.getTabWidget().getTabCount();
                    String tab_name = "Floor " + tabCount;
                    templateHandler = TemplateHandler.getInstance(mapName, getApplicationContext());
                    templateHandler.addLevel();
			    	mTabHost.addTab(
                            mTabHost.newTabSpec(String.valueOf(tabCount)).setIndicator(tab_name,
                                    null),
			                FloorFragment.class, null);
			    	mTabHost.setCurrentTab(tabCount);	    	
			    }
		}});
        

        if (mTabHost.getTabWidget().getTabCount() > 1) {
            mTabHost.setCurrentTab(1);
        }
	}
	
    public void initMappings() {
        ArmorMapping.armorMappingInit();
        WeaponMapping.weaponMappingInit();
        WandMapping.wandMappingInit();
        RingMapping.ringMappingInit();
        PotionMapping.potionMappingInit();
        ScrollMapping.scrollMappingInit();
        ConsumableMapping.consumableMappingInit();
        LevelMapping.levelMappingInit();
        RoomMapping.roomMappingInit();
        MobMapping.mobMappingInit();
        EnchantmentsMapping.EnchantmentsMappingInit();
        GlyphsMapping.glyphMappingInit();
        SeedMapping.seedMappingInit();
    }

	public void onBackPressed() {
		templateHandler = TemplateHandler.getInstance(mapName, this);
		templateHandler.save(this);
		super.onBackPressed();
	}

	@Override
	public void onPause(){
		super.onPause();
		templateHandler = TemplateHandler.getInstance(mapName,this);
		templateHandler.save(this);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		if(this == null){
			finish();
		}
        if (mapName == null) {
            mapName = getIntent().getStringExtra(EXTRA_FILENAME);
        }
        setTitle(mapName);
        if (templateHandler == null || templateHandler.getDungeon() == null) {
        	 templateHandler = TemplateHandler.getInstance(mapName, this);
        }
		if(MobMapping.getAllNames() == null){
	        initMappings();
		}
	}

//	@Override
//	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//		Log.d("TEST", "In item click");
//		Intent intent = new Intent();
//		intent.setClass(MapEditActivity.this,
//		EnchantableItemsActivity.class);
//		startActivity(intent);
//		
//	}
//	
//	public LevelTemplate currentLevel(){
//		return template.levelTemplates.get(depth);
//	}
}

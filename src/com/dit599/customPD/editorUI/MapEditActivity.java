package com.dit599.customPD.editorUI;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost.OnTabChangeListener;

import com.dit599.customPD.R;


public class MapEditActivity extends FragmentActivity{
    
    public static final String EXTRA_FILENAME = "filename";

	
	//TODO Left over code... REMOVE later!
//	public DungeonTemplate template;
//	public int depth;
	
	
	private String mapName;
	private FragmentTabHost mTabHost;
	
	// Tab titles
	private String[] tabs = { " Add Floor ", "  Floor 1  " };

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor_ui);
		
        mapName = getIntent().getStringExtra(EXTRA_FILENAME);
        if (mapName == null) {
            throw new NullPointerException("Intent parameter \"" + EXTRA_FILENAME + "\" missing");
        }
        setTitle(mapName);
		
        initMappings();

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        for (String tab_name : tabs) {
			mTabHost.addTab(mTabHost.newTabSpec(tab_name).setIndicator(tab_name, null),
	                FloorFragment.class, null);
		}
        
        mTabHost.setCurrentTab(1);
        
        mTabHost.setOnTabChangedListener(new OnTabChangeListener(){
			@Override
			public void onTabChanged(String tabSpec) {
			    if(tabSpec.equals(tabs[0])) {
			    	int tabCount = mTabHost.getTabWidget().getTabCount();
			    	String tab_name = "  Floor " + tabCount + "  ";
			    	mTabHost.addTab(
			                mTabHost.newTabSpec(tab_name).setIndicator(tab_name, null),
			                FloorFragment.class, null);
			    	mTabHost.setCurrentTab(tabCount);
			    }
		}});
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
    }

	public void onBackPressed() {
		super.onBackPressed();
	}

	
	
	//TODO Left over code... REMOVE later!
//	@Override
//	public void onPause(){
//		super.onPause();
//		// TODO save template
//	}
//	
	@Override
	public void onResume(){
		super.onResume();

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

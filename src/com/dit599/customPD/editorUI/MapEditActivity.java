package com.dit599.customPD.editorUI;

import com.dit599.customPD.R;
import com.dit599.customPD.levels.template.DungeonTemplate;
import com.dit599.customPD.levels.template.LevelTemplate;
import com.dit599.customPD.levels.template.TemplateFactory;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost.OnTabChangeListener;


public class MapEditActivity extends FragmentActivity{

	
	//TODO Left over code... REMOVE later!
//	public DungeonTemplate template;
//	public int depth;
	
	
	
	private FragmentTabHost mTabHost;
	
	// Tab titles
	private String[] tabs = { " Add Floor ", "  Floor 1  " };

	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editor_ui);
		
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        for (String tab_name : tabs) {
			mTabHost.addTab(mTabHost.newTabSpec(tab_name).setIndicator(tab_name, null),
	                TabFragment.class, null);
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
			                TabFragment.class, null);
			    	mTabHost.setCurrentTab(tabCount);
			    }
		}});
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

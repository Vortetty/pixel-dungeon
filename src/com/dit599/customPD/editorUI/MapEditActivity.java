package com.dit599.customPD.editorUI;

import com.dit599.customPD.R;
import com.dit599.customPD.levels.template.DungeonTemplate;
import com.dit599.customPD.levels.template.LevelTemplate;
import com.dit599.customPD.levels.template.TemplateFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MapEditActivity extends Activity implements AdapterView.OnItemClickListener{

	TabHost tabHost;
	public int numoffloortab = 1;
	TabSpec parentSpec, subSpec;
	public Spinner themeSpn, mobFrequencySpn, mobLimitSpn, bossspin = null;
	public ArrayAdapter<CharSequence> themeAdapter, frequencyAdapter, mobLimitAdapter,
			adapter4 = null;
	public ImageButton mobbut1, mobbut2, mobbut3, mobbut4 = null;
	public ListView downlistview = null;
	public LinearLayout layout = null;
	public MapSelectItemAdapter downlistadapter = null;
	
	public DungeonTemplate template;
	public int depth;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_edit_activity);

		tabHost = (TabHost) findViewById(R.id.mapselecttabs);
		mobbut1 = (ImageButton) this.findViewById(R.id.mobsbutton1);
		mobbut2 = (ImageButton) this.findViewById(R.id.mobsbutton2);
		mobbut3 = (ImageButton) this.findViewById(R.id.mobsbutton3);
		mobbut4 = (ImageButton) this.findViewById(R.id.mobsbutton4);
		downlistview = (ListView) this
				.findViewById(R.id.enchantable_list_downview);
		downlistadapter = new MapSelectItemAdapter(this);



		

		downlistview.setAdapter(downlistadapter);
		downlistview.setOnItemClickListener(this);

		this.mobbut1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MapEditActivity.this,
						MapMobItemActivity.class);
				startActivity(intent);
			}
		});
		this.mobbut2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MapEditActivity.this,
						MapMobItemActivity.class);
				startActivity(intent);
			}
		});
		this.mobbut3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MapEditActivity.this,
						MapMobItemActivity.class);
				startActivity(intent);
			}
		});
		this.mobbut4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MapEditActivity.this,
						MapMobItemActivity.class);
				startActivity(intent);
			}
		});

		themeSpn = (Spinner) findViewById(R.id.theme_spinner);
		themeAdapter = ArrayAdapter.createFromResource(this, R.array.themes,
				android.R.layout.simple_spinner_item);
		themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		themeSpn.setAdapter(themeAdapter);

		mobFrequencySpn = (Spinner) findViewById(R.id.frequency_spinner);
		frequencyAdapter = ArrayAdapter.createFromResource(this, R.array.frequence,
				android.R.layout.simple_spinner_item);
		frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mobFrequencySpn.setAdapter(frequencyAdapter);

		mobLimitSpn = (Spinner) findViewById(R.id.mob_limit_spinner);
		mobLimitAdapter = ArrayAdapter.createFromResource(this, R.array.mob_limits,
				android.R.layout.simple_spinner_item);
		mobLimitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mobLimitSpn.setAdapter(mobLimitAdapter);

		bossspin = (Spinner) findViewById(R.id.mapbossspinner);
		adapter4 = ArrayAdapter.createFromResource(this, R.array.bosschoose,
				android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bossspin.setAdapter(adapter4);
		bossspin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position == 1) {
					themeSpn.setEnabled(false);
					mobFrequencySpn.setEnabled(false);
					mobLimitSpn.setEnabled(false);
					mobbut1.setEnabled(false);
					mobbut2.setEnabled(false);
					mobbut3.setEnabled(false);
					mobbut4.setEnabled(false);
					downlistview.setEnabled(false);

					themeSpn.setVisibility(0);
					mobFrequencySpn.setVisibility(0);
					mobLimitSpn.setVisibility(0);
					mobbut1.setVisibility(0);
					mobbut2.setVisibility(0);
					mobbut3.setVisibility(0);
					mobbut4.setVisibility(0);
					downlistview.setVisibility(0);

				} else {
					themeSpn.setEnabled(true);
					mobFrequencySpn.setEnabled(true);
					mobLimitSpn.setEnabled(true);
					mobbut1.setEnabled(true);
					mobbut2.setEnabled(true);
					mobbut3.setEnabled(true);
					mobbut4.setEnabled(true);
					downlistview.setEnabled(true);

					themeSpn.setVisibility(1);
					mobFrequencySpn.setVisibility(1);
					mobLimitSpn.setVisibility(1);
					mobbut1.setVisibility(1);
					mobbut2.setVisibility(1);
					mobbut3.setVisibility(1);
					mobbut4.setVisibility(1);
					downlistview.setVisibility(1);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		tabHost.setup();
		parentSpec = tabHost.newTabSpec("Floor" + numoffloortab);
		parentSpec.setIndicator("Floor" + numoffloortab, this.getResources()
				.getDrawable(R.drawable.icon));
		parentSpec.setContent(R.id.mapselectfloortab_1);

		subSpec = tabHost.newTabSpec("flooradd");
		subSpec.setIndicator("   +   ",
				this.getResources().getDrawable(R.drawable.icon));
		subSpec.setContent(R.id.mapselectfloortab_2);

		tabHost.addTab(parentSpec);
		tabHost.addTab(subSpec);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				if (tabId == "flooradd") {
					numoffloortab++;
					TabSpec flooraddSpec = tabHost.newTabSpec("Floor"
							+ numoffloortab);
					flooraddSpec.setIndicator("Floor" + numoffloortab,
							MapEditActivity.this.getResources()
									.getDrawable(R.drawable.icon));
					flooraddSpec.setContent(R.id.mapselectfloortab_1);
					tabHost.addTab(flooraddSpec);

				}
			}

		});

	}
	
	@Override
	public void onPause(){
		super.onPause();
		// TODO save template
	}
	
	@Override
	public void onResume(){
		super.onResume();
		template = TemplateFactory.createSimpleDungeon();
		// TODO lead template
		
		// TODO set info of all fields based on template
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.d("TEST", "In item click");
		Intent intent = new Intent();
		intent.setClass(MapEditActivity.this,
		EnchantableItemsActivity.class);
		startActivity(intent);
		
	}
	
	public LevelTemplate currentLevel(){
		return template.levelTemplates.get(depth);
	}
}

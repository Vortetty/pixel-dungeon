package com.dit599.customPD.editorUI;

import com.dit599.customPD.R;
import com.dit599.customPD.levels.template.DungeonTemplate;
import com.dit599.customPD.levels.template.LevelTemplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MapMobItemActivity extends Activity {
	private Spinner mobtypespin,mobfrespin=null;
	private Button mobconfimbt,mobcancelbt=null;
	private ArrayAdapter<CharSequence> freqAdapter = null;
	private ArrayAdapter<String> typeAdapter=null;
	private static String mobtypeinfo=null;
	private static int moblevelinfo=0;
	LevelTemplate level;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		level = (TemplateHandler.getInstance(getIntent().getStringExtra("mapName"))).getCurrentLevel();
		super.setContentView(R.layout.mobitem); 
		this.mobconfimbt=(Button) this.findViewById(R.id.mobconfirmbutton);
		this.mobcancelbt=(Button) this.findViewById(R.id.mobcancelbutton);

		this.mobconfimbt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				level.mobs.set(getIntent().getIntExtra("mobIndex", 0), 
				               level.new MobProbability(MobMapping.getMobClass(mobtypeinfo), moblevelinfo));
				finish();
			}});

		this.mobcancelbt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				onBackPressed();
			}});
		this.mobtypespin=(Spinner)this.findViewById(R.id.mobtypespinner);
		this.mobfrespin=(Spinner)this.findViewById(R.id.mobfrespinner);

		MobMapping.mobMappingInit();

		typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MobMapping.getAllNames());
		typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		mobtypespin.setAdapter(typeAdapter); 
		mobtypespin.setSelection(MobMapping.getAllNames().indexOf(MobMapping.getMobName(level
				                 .mobs.get(getIntent().getIntExtra("mobIndex", 0)).mobClass)));
		mobtypeinfo = mobtypespin.getSelectedItem().toString();
		mobtypespin.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mobtypeinfo = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}}); 

		freqAdapter = ArrayAdapter.createFromResource(this, 
				R.array.mobfrequ, android.R.layout.simple_spinner_item); 
		freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
		mobfrespin.setAdapter(freqAdapter); 
		mobfrespin.setSelection(level.mobs.get(getIntent().getIntExtra("mobIndex", 0)).weight);
		moblevelinfo = mobfrespin.getSelectedItemPosition();
		mobfrespin.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				moblevelinfo = position;

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}}); 

	}	
	@Override
	public void onStart(){
		super.onStart();
		if (level == null){
			level = (TemplateHandler.getInstance(getIntent().getStringExtra("mapName"))).getCurrentLevel();
		}
		if(MobMapping.getAllNames() == null){
			MobMapping.mobMappingInit();
		}
	}

}

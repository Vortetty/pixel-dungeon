package com.dit599.customPD.editorUI;

import java.util.List;

import com.dit599.customPD.R;
import com.dit599.customPD.levels.template.DungeonTemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost.TabSpec;


public class TabFragment extends Fragment {

	public int numoffloortab = 1;
	TabSpec parentSpec, subSpec;
	public Spinner themeSpn, mobFrequencySpn, mobLimitSpn, bossspin = null;
	public ArrayAdapter<String> themeAdapter = null;
	public ArrayAdapter<CharSequence>frequencyAdapter, mobLimitAdapter, adapter4 = null;
	public ImageButton mobbut1, mobbut2, mobbut3, mobbut4 = null;
	public ListView downlistview = null;
	public LinearLayout layout = null;
	public MapSelectItemAdapter downlistadapter = null;
	
	public DungeonTemplate template;
	public int depth;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    	
        View v = inflater.inflate(R.layout.fragment_tab, container, false);
        
		mobbut1 = (ImageButton) v.findViewById(R.id.mobsbutton1);
		mobbut2 = (ImageButton) v.findViewById(R.id.mobsbutton2);
		mobbut3 = (ImageButton) v.findViewById(R.id.mobsbutton3);
		mobbut4 = (ImageButton) v.findViewById(R.id.mobsbutton4);

		this.mobbut1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(v.getContext(),
						MapMobItemActivity.class);
				startActivity(intent);
			}
		});
		this.mobbut2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(v.getContext(),
						MapMobItemActivity.class);
				startActivity(intent);
			}
		});
		this.mobbut3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(v.getContext(),
						MapMobItemActivity.class);
				startActivity(intent);
			}
		});
		this.mobbut4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(v.getContext(),
						MapMobItemActivity.class);
				startActivity(intent);
			}
		});

		themeSpn = (Spinner) v.findViewById(R.id.theme_spinner);
		themeAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, 
				                                      LevelMapping.themenamelist);
		themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		themeSpn.setAdapter(themeAdapter);

		mobFrequencySpn = (Spinner) v.findViewById(R.id.frequency_spinner);
		frequencyAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.frequence,
				android.R.layout.simple_spinner_item);
		frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mobFrequencySpn.setAdapter(frequencyAdapter);

		mobLimitSpn = (Spinner) v.findViewById(R.id.mob_limit_spinner);
		mobLimitAdapter = ArrayAdapter.createFromResource(v.getContext(), R.array.mob_limits,
				android.R.layout.simple_spinner_item);
		mobLimitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mobLimitSpn.setAdapter(mobLimitAdapter);

		bossspin = (Spinner) v.findViewById(R.id.mapbossspinner);
		adapter4 = ArrayAdapter.createFromResource(v.getContext(), R.array.bosschoose,
				android.R.layout.simple_spinner_item);
		adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bossspin.setAdapter(adapter4);
		
		
		//TODO This part doesn't work, fix it!
		
//		bossspin.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				if (position == 1) {
//					themeSpn.setEnabled(false);
//					mobFrequencySpn.setEnabled(false);
//					mobLimitSpn.setEnabled(false);
//					mobbut1.setEnabled(false);
//					mobbut2.setEnabled(false);
//					mobbut3.setEnabled(false);
//					mobbut4.setEnabled(false);
//					downlistview.setEnabled(false);
//
//					themeSpn.setVisibility(0);
//					mobFrequencySpn.setVisibility(0);
//					mobLimitSpn.setVisibility(0);
//					mobbut1.setVisibility(0);
//					mobbut2.setVisibility(0);
//					mobbut3.setVisibility(0);
//					mobbut4.setVisibility(0);
//					downlistview.setVisibility(0);
//
//				} else {
//					themeSpn.setEnabled(true);
//					mobFrequencySpn.setEnabled(true);
//					mobLimitSpn.setEnabled(true);
//					mobbut1.setEnabled(true);
//					mobbut2.setEnabled(true);
//					mobbut3.setEnabled(true);
//					mobbut4.setEnabled(true);
//					downlistview.setEnabled(true);
//
//					themeSpn.setVisibility(1);
//					mobFrequencySpn.setVisibility(1);
//					mobLimitSpn.setVisibility(1);
//					mobbut1.setVisibility(1);
//					mobbut2.setVisibility(1);
//					mobbut3.setVisibility(1);
//					mobbut4.setVisibility(1);
//					downlistview.setVisibility(1);
//				}
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				// TODO Auto-generated method stub
//
//			}
//		});		
		
        return v;
    }
}

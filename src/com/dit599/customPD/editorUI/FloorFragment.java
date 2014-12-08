package com.dit599.customPD.editorUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost.TabSpec;

import com.dit599.customPD.R;
import com.dit599.customPD.levels.template.DungeonTemplate;


public class FloorFragment extends Fragment {

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
    	
        View root = inflater.inflate(R.layout.fragment_tab, container, false);
        
        depth = Integer.valueOf(getTag());
        Log.d("FloorFragment", "Depth is " + depth);

		mobbut1 = (ImageButton) root.findViewById(R.id.mobsbutton1);
		mobbut2 = (ImageButton) root.findViewById(R.id.mobsbutton2);
		mobbut3 = (ImageButton) root.findViewById(R.id.mobsbutton3);
		mobbut4 = (ImageButton) root.findViewById(R.id.mobsbutton4);

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

		themeSpn = (Spinner) root.findViewById(R.id.theme_spinner);
		themeAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, 
				                                      LevelMapping.getAllNames());
		themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		themeSpn.setAdapter(themeAdapter);

		mobFrequencySpn = (Spinner) root.findViewById(R.id.frequency_spinner);
		frequencyAdapter = ArrayAdapter.createFromResource(root.getContext(), R.array.frequence,
				android.R.layout.simple_spinner_item);
		frequencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mobFrequencySpn.setAdapter(frequencyAdapter);

		mobLimitSpn = (Spinner) root.findViewById(R.id.mob_limit_spinner);
		mobLimitAdapter = ArrayAdapter.createFromResource(root.getContext(), R.array.mob_limits,
				android.R.layout.simple_spinner_item);
		mobLimitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mobLimitSpn.setAdapter(mobLimitAdapter);

				
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
		
        initItemCategoryButtons(root);

        return root;
    }

    private void initItemCategoryButtons(View rootView) {
        ItemCategoryClickListener clickListener = new ItemCategoryClickListener();
        Button weaponBtn = (Button) rootView.findViewById(R.id.weaponbutton);
        weaponBtn.setOnClickListener(clickListener);
        Button armorBtn = (Button) rootView.findViewById(R.id.armorbutton);
        armorBtn.setOnClickListener(clickListener);
        Button potionBtn = (Button) rootView.findViewById(R.id.potionbutton);
        potionBtn.setOnClickListener(clickListener);
    }

    private class ItemCategoryClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
            case R.id.weaponbutton:
                startActivity(new Intent(getActivity(), EnchantableItemsActivity.class));
                break;
            case R.id.armorbutton:
                startActivity(new Intent(getActivity(), EnchantableItemsActivity.class));
                break;
            case R.id.potionbutton:
                startActivity(new Intent(getActivity(), EnchantableItemsActivity.class));
                break;
            }
        }

    }
}

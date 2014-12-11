package com.dit599.customPD.editorUI;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TabHost.TabSpec;

import com.dit599.customPD.R;
import com.dit599.customPD.levels.template.DungeonTemplate;


public class FloorFragment extends Fragment {

	TabSpec parentSpec, subSpec;
	private Spinner themeSpn, mobFrequencySpn, mobLimitSpn;
	private ArrayAdapter<String> themeAdapter = null;
	private ArrayAdapter<CharSequence>frequencyAdapter, mobLimitAdapter;
	private ImageButton mobbut1, mobbut2, mobbut3, mobbut4 = null;
	private Button roomButton = null;
	public static String chooseItemType=null;
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
		
		final MapEditActivity activity = (MapEditActivity) getActivity();

		depth = Integer.valueOf(getTag());
		Log.d("FloorFragment", "Depth is " + depth);

		mobbut1 = (ImageButton) root.findViewById(R.id.mobsbutton1);
		mobbut2 = (ImageButton) root.findViewById(R.id.mobsbutton2);
		mobbut3 = (ImageButton) root.findViewById(R.id.mobsbutton3);
		mobbut4 = (ImageButton) root.findViewById(R.id.mobsbutton4);
		roomButton = (Button) root.findViewById(R.id.roomsbutton);

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
        List<String> themeItems = LevelMapping.getAllNames();
		themeAdapter = new ArrayAdapter<String>(root.getContext(), android.R.layout.simple_spinner_item, 
				themeItems);
		themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		themeSpn.setAdapter(themeAdapter);
		themeSpn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {	
				if(((String)themeSpn.getSelectedItem()).contains("Boss")){
					mobbut1.setEnabled(false);
					mobbut2.setEnabled(false);
					mobbut3.setEnabled(false);
					mobbut4.setEnabled(false);
					roomButton.setEnabled(false);
				}
				else{
					mobbut1.setEnabled(true);
					mobbut2.setEnabled(true);
					mobbut3.setEnabled(true);
					mobbut4.setEnabled(true);
					roomButton.setEnabled(true);
				}
				activity.templateHandler.getLevel(depth).theme = 
						LevelMapping.getThemeClass((String)themeSpn.getSelectedItem());
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});		

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


		initItemCategoryButtons(root);

        loadTemplateToUI();

		return root;
	}

    /**
     * Takes all data of the template and populates the views with that data
     */

    private void loadTemplateToUI() {
        final MapEditActivity activity = (MapEditActivity) getActivity();

        List<String> themeItems = LevelMapping.getAllNames();
        String currentTheme = LevelMapping
                .getThemeName(activity.templateHandler.getLevel(depth).theme);
        themeSpn.setSelection(themeItems.indexOf(currentTheme));
    }

	private void initItemCategoryButtons(View rootView) {
		ItemCategoryClickListener clickListener = new ItemCategoryClickListener();
		Button weaponBtn = (Button) rootView.findViewById(R.id.weaponbutton);
		weaponBtn.setOnClickListener(clickListener);
		Button armorBtn = (Button) rootView.findViewById(R.id.armorbutton);
		armorBtn.setOnClickListener(clickListener);
		Button potionBtn = (Button) rootView.findViewById(R.id.potionbutton);
		potionBtn.setOnClickListener(clickListener);
		
		Button scrollBtn = (Button) rootView.findViewById(R.id.scrollbutton);
		scrollBtn.setOnClickListener(clickListener);
		Button roomsBtn = (Button) rootView.findViewById(R.id.roomsbutton);
		roomsBtn.setOnClickListener(clickListener);
		Button seedBtn = (Button) rootView.findViewById(R.id.seedbutton);
		seedBtn.setOnClickListener(clickListener);
		Button wandBtn = (Button) rootView.findViewById(R.id.wandbutton);
		wandBtn.setOnClickListener(clickListener);
		Button ringsBtn = (Button) rootView.findViewById(R.id.ringbutton);
		ringsBtn.setOnClickListener(clickListener);
		Button cosumablesBtn = (Button) rootView.findViewById(R.id.consumablesbutton);
		cosumablesBtn.setOnClickListener(clickListener);
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
				chooseItemType="Potions";
				startActivity(new Intent(getActivity(), ItemsActivity.class));
				break;
			case R.id.scrollbutton:
				chooseItemType="Scrolls";
				startActivity(new Intent(getActivity(), ItemsActivity.class));
				break;
			case R.id.roomsbutton:
				chooseItemType="Rooms";
				startActivity(new Intent(getActivity(), ItemsActivity.class));
				break;
			case R.id.seedbutton:
				chooseItemType="Seeds";
				startActivity(new Intent(getActivity(), ItemsActivity.class));
				break;
			case R.id.consumablesbutton:
				chooseItemType="Other";
				startActivity(new Intent(getActivity(), ItemsActivity.class));
				break;
			case R.id.wandbutton:
				chooseItemType="Wands";
				startActivity(new Intent(getActivity(), EnchantableItemsActivity.class));
				break;
			case R.id.ringbutton:
				chooseItemType="Rings";
				startActivity(new Intent(getActivity(), EnchantableItemsActivity.class));
				break;
			}
		}

	}
}

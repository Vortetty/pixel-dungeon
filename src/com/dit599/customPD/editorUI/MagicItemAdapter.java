package com.dit599.customPD.editorUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dit599.customPD.R;
import com.dit599.customPD.items.Ankh;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.potions.Potion;
import com.dit599.customPD.items.potions.PotionOfHealing;
import com.dit599.customPD.items.rings.*;
import com.dit599.customPD.items.scrolls.Scroll;
import com.dit599.customPD.items.scrolls.ScrollOfIdentify;
import com.dit599.customPD.items.wands.Wand;
import com.dit599.customPD.items.wands.WandOfAmok;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.template.LevelTemplate;
import com.dit599.customPD.levels.template.LevelTemplate.MagicItem;
import com.dit599.customPD.plants.Dreamweed;
import com.dit599.customPD.plants.Plant;

import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class MagicItemAdapter extends BaseAdapter {
	public ArrayAdapter<String> adapter,leveladapter;
	private int mItemCount = 0;
	private LevelTemplate level;
	private MagicItemsActivity activity;
	public static final String[] MAGICITEM_LEVEL = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "10", "-1", "-2", "-3", "-4", "-5" };
	Context context;

	public MagicItemAdapter(Context context, LevelTemplate l, MagicItemsActivity a) {
		this.context = context;
		this.level = l;
		this.activity = a;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItemCount;
	}

	public void addItem(boolean inLists) {
		if(inLists){
			if(FloorFragment.chooseItemType.equals("Wands")){
				MagicItem wand =new MagicItem(WandOfAmok.class,0,false);
				level.wands.add(wand);
			   
			}
			else if(FloorFragment.chooseItemType.equals("Rings")){
				MagicItem ring=new MagicItem(RingOfAccuracy.class,0,false);	
				level.rings.add(ring);
			}
			
			
		}
		mItemCount++;
		notifyDataSetChanged();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub    	
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.customizable_item3, null);
		Spinner typespin=(Spinner)view.findViewById(R.id.wand_ring_typespinner);
		Spinner levelspin=(Spinner)view.findViewById(R.id.wand_ring_level_spinner);
		Button delete = (Button) view.findViewById(R.id.wand_ring_deletbt); 
		delete.setOnClickListener(deleteItem(position));
		ItemTypesFilter(typespin, levelspin,position);
		return view;
	}
	public void ItemTypesFilter(Spinner spin,Spinner levelspin, final int pos)
	{
		if(FloorFragment.chooseItemType.equals("Wands")){
			adapter= new ArrayAdapter<String> (context, android.R.layout.simple_spinner_item, WandMapping.getAllNames());    	
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			leveladapter= new ArrayAdapter<String> (context, android.R.layout.simple_spinner_item, MAGICITEM_LEVEL);    	
			leveladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


		}else if(FloorFragment.chooseItemType.equals("Rings")){
			adapter= new ArrayAdapter<String> (context, android.R.layout.simple_spinner_item, RingMapping.getAllNames());    	   	
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			leveladapter= new ArrayAdapter<String> (context, android.R.layout.simple_spinner_item, MAGICITEM_LEVEL);    	
			leveladapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);



		}else {

		}
		spin.setAdapter(adapter);
		spin.setSelection(getType(pos));
		spin.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String type =parent.getItemAtPosition(position).toString();
				setType(pos, type);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}}); 
		
		levelspin.setAdapter(leveladapter);
		levelspin.setSelection(getLevel(pos));
		levelspin.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				String choice =parent.getItemAtPosition(position).toString();
				setLevel(pos, choice);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}}); 


	}
	private int getType(int position){
		if(FloorFragment.chooseItemType.equals("Wands")){
			return WandMapping.getAllNames().indexOf(
					WandMapping.getWandName(level.wands.get(position).itemClass));			
		}
		else if(FloorFragment.chooseItemType.equals("Rings")){
			return RingMapping.getAllNames().indexOf(
					RingMapping.getRingName(level.rings.get(position).itemClass));	
		}
		
		return 0;
	}
	private void setType(int position, String type){
		try{
			if(FloorFragment.chooseItemType.equals("Wands")){
				MagicItem wand=new MagicItem(WandMapping.getWandClass(type),0,false);
				level.wands.set(position, wand);			
			}
			else if(FloorFragment.chooseItemType.equals("Rings")){
				MagicItem ring=new MagicItem(RingMapping.getRingClass(type),0,false);
				level.rings.set(position, ring);		
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	private int getLevel(int position){
		if(FloorFragment.chooseItemType.equals("Wands")){
			return level.wands.get(position).level;			
		}
		else if(FloorFragment.chooseItemType.equals("Rings")){
			return level.rings.get(position).level;
		}		
		return 0;
	}
	private void setLevel(int position, String choice){
		try{
			if(FloorFragment.chooseItemType.equals("Wands")){
				level.wands.get(position).level = Integer.parseInt(choice);		
			}
			else if(FloorFragment.chooseItemType.equals("Rings")){
				level.rings.get(position).level = Integer.parseInt(choice);			
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	private OnClickListener deleteItem(final int pos){
		return new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(FloorFragment.chooseItemType.equals("Wands")){
					level.wands.remove(pos);
				}
				else if(FloorFragment.chooseItemType.equals("Rings")){
					level.rings.remove(pos);	
				}
				
				mItemCount--;
				notifyDataSetChanged();
			}
		};
	}
}

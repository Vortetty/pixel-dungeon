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
import com.dit599.customPD.items.Gold;
import com.dit599.customPD.items.Item;
import com.dit599.customPD.items.potions.Potion;
import com.dit599.customPD.items.potions.PotionOfHealing;
import com.dit599.customPD.items.scrolls.Scroll;
import com.dit599.customPD.items.scrolls.ScrollOfIdentify;
import com.dit599.customPD.levels.Room;
import com.dit599.customPD.levels.template.LevelTemplate;
import com.dit599.customPD.plants.Dreamweed;
import com.dit599.customPD.plants.Plant;

import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ItemAdapter extends BaseAdapter {
	public ArrayAdapter<String> adapter;
	private int mItemCount = 0;
	private LevelTemplate level;
	private ItemsActivity activity;
	private static final int MAX_SPECIALS = 4;

	Context context;

	public ItemAdapter(Context context, LevelTemplate l, ItemsActivity a) {
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
			if(FloorFragment.chooseItemType.equals("Potions")){
				level.potions.add(PotionOfHealing.class);
			}
			else if(FloorFragment.chooseItemType.equals("Scrolls")){
				level.scrolls.add(ScrollOfIdentify.class);		
			}
			else if(FloorFragment.chooseItemType.equals("Rooms")){
				level.specialRooms.add(Room.Type.ARMORY);		
			}
			else if(FloorFragment.chooseItemType.equals("Seeds")){
				level.seeds.add(new Dreamweed.Seed());		
			}
			else if(FloorFragment.chooseItemType.equals("Other")){
				level.consumables.add(new Ankh());		
			}
		}
		mItemCount++;
		//Max specialrooms reached
		if(FloorFragment.chooseItemType.equals("Rooms") && mItemCount == MAX_SPECIALS){
			activity.addButton.setEnabled(false);
		}
		notifyDataSetChanged();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
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
		View view = inflater.inflate(R.layout.customizable_item2, null);
		Spinner spin=(Spinner)view.findViewById(R.id.itemtypespinner);
		Button delete = (Button) view.findViewById(R.id.itemdeletbt); 
		delete.setOnClickListener(deleteItem(position));
		ItemTypesFilter(spin, position);
		return view;
	}
	public void ItemTypesFilter(Spinner spin, final int pos)
	{

		//TODO Choose between xml style and mappings class style, XML should be more efficient but we have to initialize Mappings anyway
		// in order to retrieve correct class.
		//    	if(FloorFragment.ChooseItemType.equals("Potions")){
		//    		adapter=ArrayAdapter.createFromResource(context, R.array.potiontype, android.R.layout.simple_spinner_item);    	
		// 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 		}else if(FloorFragment.ChooseItemType.equals("Scrolls")){
		// 			adapter=ArrayAdapter.createFromResource(context, R.array.scrolltype, android.R.layout.simple_spinner_item);    	
		// 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 			
		// 			
		// 		}else if(FloorFragment.ChooseItemType.equals("Rooms")){
		// 			adapter=ArrayAdapter.createFromResource(context, R.array.roomtype, android.R.layout.simple_spinner_item);    	
		// 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 			
		// 			
		// 		}else if(FloorFragment.ChooseItemType.equals("Seeds")){
		// 			adapter=ArrayAdapter.createFromResource(context, R.array.seedtype, android.R.layout.simple_spinner_item);    	
		// 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 			
		// 			
		// 		}else if(FloorFragment.ChooseItemType.equals("Other")){
		// 			adapter=ArrayAdapter.createFromResource(context, R.array.consumabltetype, android.R.layout.simple_spinner_item);    	
		// 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// 			
		if(FloorFragment.chooseItemType.equals("Potions")){
			adapter= new ArrayAdapter<String> (context, R.layout.item_spinner, PotionMapping.getAllNames());    	
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


		}else if(FloorFragment.chooseItemType.equals("Scrolls")){
			adapter= new ArrayAdapter<String> (context, R.layout.item_spinner, ScrollMapping.getAllNames());    	   	
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


		}else if(FloorFragment.chooseItemType.equals("Rooms")){
			adapter= new ArrayAdapter<String> (context, R.layout.item_spinner, RoomMapping.getAllNames());    	 	
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


		}else if(FloorFragment.chooseItemType.equals("Seeds")){
			adapter= new ArrayAdapter<String> (context, R.layout.item_spinner, SeedMapping.getAllNames());    	   	
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


		}else if(FloorFragment.chooseItemType.equals("Other")){
			adapter= new ArrayAdapter<String> (context, R.layout.item_spinner, ConsumableMapping.getAllNames());    	    	
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


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


	}
	private int getType(int position){
		if(FloorFragment.chooseItemType.equals("Potions")){
			return PotionMapping.getAllNames().indexOf(
					PotionMapping.getPotionName(level.potions.get(position)));			
		}
		else if(FloorFragment.chooseItemType.equals("Scrolls")){
			return ScrollMapping.getAllNames().indexOf(
					ScrollMapping.getScrollName(level.scrolls.get(position)));	
		}
		else if(FloorFragment.chooseItemType.equals("Rooms")){
			return RoomMapping.getAllNames().indexOf(
					RoomMapping.getRoomName(level.specialRooms.get(position)));	
		}
		else if(FloorFragment.chooseItemType.equals("Seeds")){
			return SeedMapping.getAllNames().indexOf(
					SeedMapping.getSeedName((level.seeds.get(position)).getClass()));	
		}
		else if(FloorFragment.chooseItemType.equals("Other")){
			return ConsumableMapping.getAllNames().indexOf(
					ConsumableMapping.getConsumableName((level.consumables.get(position)).getClass()));		
		}
		return 0;
	}
	private void setType(int position, String type){
		try{
			if(FloorFragment.chooseItemType.equals("Potions")){
				level.potions.set(position, (Class<? extends Potion>) PotionMapping.getPotionClass(type));			
			}
			else if(FloorFragment.chooseItemType.equals("Scrolls")){
				level.scrolls.set(position, (Class<? extends Scroll>) ScrollMapping.getScrollClass(type));		
			}
			else if(FloorFragment.chooseItemType.equals("Rooms")){
				level.specialRooms.set(position, RoomMapping.getRoomType(type));		
			}
			else if(FloorFragment.chooseItemType.equals("Seeds")){
				level.seeds.set(position, (Plant.Seed) SeedMapping.getSeedClass(type).newInstance());		
			}
			else if(FloorFragment.chooseItemType.equals("Other")){
				Item temp = (Item) ConsumableMapping.getConsumableClass(type).newInstance();
				if(temp instanceof Gold){
					temp = temp.random();
				}
				level.consumables.set(position, temp);		
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
				if(FloorFragment.chooseItemType.equals("Potions")){
					level.potions.remove(pos);
				}
				else if(FloorFragment.chooseItemType.equals("Scrolls")){
					level.scrolls.remove(pos);	
				}
				else if(FloorFragment.chooseItemType.equals("Rooms")){
					level.specialRooms.remove(pos);		
					activity.addButton.setEnabled(true);
				}
				else if(FloorFragment.chooseItemType.equals("Seeds")){
					level.seeds.remove(pos);	
				}
				else if(FloorFragment.chooseItemType.equals("Other")){
					level.consumables.remove(pos);	
				}
				mItemCount--;
				notifyDataSetChanged();
			}
		};
	}
}

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

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.R;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


public class MapSelectItemAdapter extends BaseAdapter {
    private List<String>datalist =new ArrayList();
    private List<Integer>imagelist=new ArrayList();
    private int mItemCount = 6;

    Context context;

    public MapSelectItemAdapter(Context context) {
        this.context = context;
        datalist.add("Weapons");
        datalist.add("Armors");
        datalist.add("Potions");
        datalist.add("Scrolls");
        datalist.add("Rooms");
        datalist.add("Consumables");
        
        imagelist.add(R.drawable.icon);
        imagelist.add(R.drawable.icon);
        imagelist.add(R.drawable.icon);
        imagelist.add(R.drawable.icon);
        imagelist.add(R.drawable.icon);
        imagelist.add(R.drawable.icon);
       
        
        
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mItemCount;
    }

    public void addItem() {
        mItemCount++;
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
    	LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_sublayout, null);
        ImageButton ibut=(ImageButton) layout.findViewById(R.id.item_imagebut);
        TextView tv=(TextView) layout.findViewById(R.id.item_infobut);
        return layout;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
    

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_sublayout, null);
    	if(position < datalist.size()){
        	ImageButton ibut=(ImageButton) layout.findViewById(R.id.item_imagebut);
        	ibut.setImageResource(imagelist.get(position));
        	ibut.setOnClickListener(new OnClickListener(){
        		
        		 @Override
        		 public void onClick(View v) {
        		 // TODO Auto-generated method stub
        		 Intent intent = new Intent("com.dit599.customPD.editorUI.EnchantableItemsActivity");
        		 context.startActivity(intent);
        		 }});
        	TextView tv=(TextView) layout.findViewById(R.id.item_infobut);
        	tv.setText(datalist.get(position));
    	}
//        LayoutInflater inflater = LayoutInflater.from(context);
//        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_sublayout, null);
//        ImageButton ibut=(ImageButton) layout.findViewById(R.id.item_imagebut);
//        TextView tv=(TextView) layout.findViewById(R.id.item_infobut);
        return layout;
    }
//    public void SetItem(ListView lv,String type,int position) {
//        // TODO Auto-generated method stub
//    	
////    	LinearLayout layout=(LinearLayout) lv.getItemAtPosition(position);
////        ImageButton ibut=(ImageButton) layout.findViewById(R.id.item_imagebut);
////        ibut.setImageResource(R.drawable.icon);
////        TextView tv=(TextView) layout.findViewById(R.id.item_infobut);
////        tv.setText(type);
////        notifyDataSetChanged();
//    	
//    }

}

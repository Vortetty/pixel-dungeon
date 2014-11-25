/*
 * YourPD
 * Copyright (C) 2014 YourPD team
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

import com.dit599.customPD.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class FloorsListActivity extends ListActivity {

	 private ArrayAdapter<String> adapter;
	int clickCounter=1;
	String[] Floors ={"Floor 1","Floor 2","Floor 3","Floor 4","Floor 5","Floor 6",
			  "Floor 7","Floor 8","Floor 9","Floor 10","Floor 11","Floor 12","Floor 13","Floor 14",
			  "Floor 15","Floor 16","Floor 17","Floor 18","Floor 19","Floor 20","Floor 21","Floor 22",
			  "Floor 23","Floor 24","Floor 25","Floor 26","Floor 27"	  
		  };
  final ArrayList<String> list = new ArrayList<String>();
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main2);
     
    adapter=new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1,
        list);
    setListAdapter(adapter); }
  
 
  protected void onListItemClick(ListView l, View v,final int position, long id) {
	  final String item = (String) getListAdapter().getItem(position);
	  Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	  Intent openStart = new Intent("com.dit599.customPD.editorUI.ItemTypesActivity");
	    startActivity(openStart);
	    
  }
  
  //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
  public void addItems(View v1) {
     
      list.add("Floor"+" "+ clickCounter++);
      //adapter = new ArrayAdapter<String>(this, R.layout.main,Floors);
      setListAdapter(adapter);
      adapter.notifyDataSetChanged();
     
  }
	  /*  public View getView(final int position, View convertView, ViewGroup parent) {
			final String name = item.get(position);
			final Set<String> activeAccs = new HashSet<String>();
			int i = 0;
			
		
			//If account is the first active account, it will receive the first background colour etc.
			
	    ImageButton delete = (ImageButton) findViewById(R.id.delete_button);
		/*Need to make the button unfocusable, in order to be able to click the button and
        the background of the item separately.
		 
	    
	    
		delete.setFocusableInTouchMode(false);
		delete.setFocusable(false);
		delete.setOnClickListener(
				new ImageButton.OnClickListener() {
					@Override
					public void onClick(View v) {
						list.remove(item);
					
	  }
				}
	
	);
		return delete;*/
  }



package com.dit599.editorUI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
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
    setContentView(R.layout.main);
     
    adapter=new ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_1,
        list);
    setListAdapter(adapter); }
  
 
  protected void onListItemClick(ListView l, View v,final int position, long id) {
	  final String item = (String) getListAdapter().getItem(position);
	  Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
	  Intent openStart = new Intent("com.dit599.editorUI.FloorItemsActivity");
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



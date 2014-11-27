package com.dit599.customPD.editorUI;

import com.dit599.customPD.R;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;


public class LayoutListAdapter<LinearLayout> extends ArrayAdapter<LinearLayout>{
	  private final Context context; 

	  public LayoutListAdapter(Context context) {
	    super(context, R.layout.armoritemsublayout);
	    this.context = context;
	   
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.armoritemsublayout, parent, false);	    
	    return rowView;
	  }
	 

}

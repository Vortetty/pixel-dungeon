package com.dit599.customPD.editorUI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dit599.customPD.R;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class ItemAdapter extends BaseAdapter {
	public ArrayAdapter<CharSequence> adapter;
    private int mItemCount = 3;

    Context context;

    public ItemAdapter(Context context) {
        this.context = context;
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
        ItemTypesFilter(spin);
        return view;
    }
    public void ItemTypesFilter(Spinner spin)
    {
    	if(FloorFragment.ChooseItemType.equals("Potions")){
    		adapter=ArrayAdapter.createFromResource(context, R.array.potiontype, android.R.layout.simple_spinner_item);    	
 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 		}else if(FloorFragment.ChooseItemType.equals("Scrolls")){
 			adapter=ArrayAdapter.createFromResource(context, R.array.scrolltype, android.R.layout.simple_spinner_item);    	
 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 			
 			
 		}else if(FloorFragment.ChooseItemType.equals("Rooms")){
 			adapter=ArrayAdapter.createFromResource(context, R.array.roomtype, android.R.layout.simple_spinner_item);    	
 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 			
 			
 		}else if(FloorFragment.ChooseItemType.equals("Seeds")){
 			adapter=ArrayAdapter.createFromResource(context, R.array.seedtype, android.R.layout.simple_spinner_item);    	
 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 			
 			
 		}else if(FloorFragment.ChooseItemType.equals("Other")){
 			adapter=ArrayAdapter.createFromResource(context, R.array.consumabltetype, android.R.layout.simple_spinner_item);    	
 			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 			
 			
 		}else {
 			
 		}
    	spin.setAdapter(adapter);
    	spin.setOnItemSelectedListener(new OnItemSelectedListener(){

  			@Override
  			public void onItemSelected(AdapterView<?> parent, View view,
  					int position, long id) {
  				// TODO Auto-generated method stub
  				String message =parent.getItemAtPosition(position).toString();
  		        Toast.makeText(context,message, Toast.LENGTH_LONG).show();      
  			}

  			@Override
  			public void onNothingSelected(AdapterView<?> parent) {
  				// TODO Auto-generated method stub
  				
  			}}); 
		
    	
    }

}

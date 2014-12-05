package com.dit599.customPD.editorUI;

import com.dit599.customPD.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MapMobItemActivity extends Activity {
	public Spinner mobtypespin,mobfrespin=null;
	public Button mobconfimbt,mobcancelbt=null;
	public ArrayAdapter<CharSequence> adapter1,adapter2,adapter3=null;
	public ImageView tv=null;
	public static String Mobtypeinfo=null;
	public static String Moblevelinfo=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.mobitem); 
		this.tv=(ImageView) this.findViewById(R.id.mobimageView);
		this.mobconfimbt=(Button) this.findViewById(R.id.mobconfirmbutton);
		this.mobcancelbt=(Button) this.findViewById(R.id.mobcancelbutton);
		
		this.mobconfimbt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Mob type:"+Mobtypeinfo+"Mob level:"+Moblevelinfo, Toast.LENGTH_LONG).show();
			}});
		
		this.mobcancelbt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapMobItemActivity.this, MapEditActivity.class);  
                startActivity(intent); 
			}});
		this.mobcancelbt=(Button) this.findViewById(R.id.mobcancelbutton);
		this.mobtypespin=(Spinner)this.findViewById(R.id.mobtypespinner);
	    this.mobfrespin=(Spinner)this.findViewById(R.id.mobfrespinner);
	        
	    adapter1 = ArrayAdapter.createFromResource(this, 
       		 R.array.mobtype, android.R.layout.simple_spinner_item); 
       adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
       mobtypespin.setAdapter(adapter1); 
       mobtypespin.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Mobtypeinfo =parent.getItemAtPosition(position).toString();
		                    
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}}); 
       
       adapter2 = ArrayAdapter.createFromResource(this, 
         		 R.array.mobfrequ, android.R.layout.simple_spinner_item); 
         adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
         mobfrespin.setAdapter(adapter2); 
         mobfrespin.setOnItemSelectedListener(new OnItemSelectedListener(){

  			@Override
  			public void onItemSelected(AdapterView<?> parent, View view,
  					int position, long id) {
  				// TODO Auto-generated method stub
  				Moblevelinfo =parent.getItemAtPosition(position).toString();
  		                    
  			}

  			@Override
  			public void onNothingSelected(AdapterView<?> parent) {
  				// TODO Auto-generated method stub
  				
  			}}); 
		
	}	

}

package com.dit599.customPD.editorUI;

import com.dit599.customPD.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MapItemSelectActivity extends Activity {
	
	TabHost tabHost; 
	public int Numoffloortab=1;
	TabSpec parentSpec,subSpec;
	public Spinner spin1,spin2,spin3=null;
    public ArrayAdapter<CharSequence> adapter1,adapter2,adapter3=null;
	public ImageButton mobbut1,mobbut2,mobbut3,mobbut4,weaponbut,armbut,potionbut,scrollsbut,roomsbut,consubut=null;
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.mapselectitem);  
       
        
        tabHost = (TabHost) findViewById(R.id.mapselecttabs);
        mobbut1=(ImageButton) this.findViewById(R.id.mobsbutton1);
        mobbut2=(ImageButton) this.findViewById(R.id.mobsbutton2);
        mobbut3=(ImageButton) this.findViewById(R.id.mobsbutton3);
        mobbut4=(ImageButton) this.findViewById(R.id.mobsbutton4);
        
        this.mobbut1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapMobItemActivity.class);  
                startActivity(intent); 
			}});
        this.mobbut2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapMobItemActivity.class);  
                startActivity(intent); 
			}});
        this.mobbut3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapMobItemActivity.class);  
                startActivity(intent); 
			}});
        this.mobbut4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapMobItemActivity.class);  
                startActivity(intent); 
			}});
        
        weaponbut=(ImageButton) this.findViewById(R.id.weaponsbutton);
        armbut=(ImageButton) this.findViewById(R.id.armorsbutton);
        potionbut=(ImageButton) this.findViewById(R.id.potionsbutton);
        scrollsbut=(ImageButton) this.findViewById(R.id.scrollsbutton);
        roomsbut=(ImageButton) this.findViewById(R.id.roomsbutton);
        consubut=(ImageButton) this.findViewById(R.id.consumsbutton);
        
        
        this.weaponbut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapSelectArmorActivity.class);  
                startActivity(intent); 
			}});
        this.armbut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapSelectArmorActivity.class);  
                startActivity(intent); 
			}});
        this.potionbut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapSelectArmorActivity.class);  
                startActivity(intent); 
			}});
        this.scrollsbut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapSelectArmorActivity.class);  
                startActivity(intent); 
			}});
        this.roomsbut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapSelectArmorActivity.class);  
                startActivity(intent); 
			}});
        this.consubut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapItemSelectActivity.this, MapSelectArmorActivity.class);  
                startActivity(intent); 
			}});
        
        spin1 = (Spinner) findViewById(R.id.spinner1); 
        adapter1 = ArrayAdapter.createFromResource(this, 
        		 R.array.themes, android.R.layout.simple_spinner_item); 
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spin1.setAdapter(adapter1); 
        
        spin2 = (Spinner) findViewById(R.id.spinner1); 
        adapter2 = ArrayAdapter.createFromResource(this, 
        		 R.array.frequence, android.R.layout.simple_spinner_item); 
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spin2.setAdapter(adapter2); 
        
        spin3 = (Spinner) findViewById(R.id.spinner1); 
        adapter3 = ArrayAdapter.createFromResource(this, 
        		 R.array.limits, android.R.layout.simple_spinner_item); 
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        spin3.setAdapter(adapter3); 
       
        
       
        tabHost.setup();  
        parentSpec = tabHost.newTabSpec("Floor"+Numoffloortab);  
        parentSpec.setIndicator("Floor"+Numoffloortab,  
                this.getResources().getDrawable(R.drawable.icon));  
        parentSpec.setContent(R.id.mapselectfloortab_1);  
       
  
        subSpec = tabHost.newTabSpec("flooradd");  
        subSpec.setIndicator("   +   ",  
                this.getResources().getDrawable(R.drawable.icon));  
        subSpec.setContent(R.id.mapselectfloortab_2);  
  
        tabHost.addTab(parentSpec);  
        tabHost.addTab(subSpec);  
        tabHost.setOnTabChangedListener(new OnTabChangeListener (){

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				if(tabId=="flooradd"){
					Numoffloortab++; 
					TabSpec flooraddSpec = tabHost.newTabSpec("Floor"+Numoffloortab);  
					flooraddSpec.setIndicator("Floor"+Numoffloortab,  
							MapItemSelectActivity.this.getResources().getDrawable(R.drawable.icon));  
					flooraddSpec.setContent(R.id.mapselectfloortab_1); 				
					tabHost.addTab(flooraddSpec);  
					
				 }
			}
			
			
		});
        
      
	}
}

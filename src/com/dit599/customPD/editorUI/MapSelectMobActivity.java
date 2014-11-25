package com.dit599.customPD.editorUI;

import com.dit599.customPD.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
//modified map editor version 2.1
public class MapSelectMobActivity extends Activity {
	
	public Button armpagebut,armaddbut=null;
	public Spinner armtypespin,armechspin,armlevelspin=null;
	public ArrayAdapter<CharSequence> adapter1,adapter2,adapter3=null;
	public LinearLayout layout;
	public static  String armtypeinfo=null;
	public static String armenchinfo=null;
	public static String armlevelinfo=null;
	public static int Numofarmor=0;
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.armitem);  
        layout = (LinearLayout) findViewById(R.id.maparmselectlinear);
        this.armaddbut=(Button) this.findViewById(R.id.armmorbut);
        this.armaddbut=(Button) this.findViewById(R.id.armaddbut);
        
        this.armtypespin=(Spinner)this.findViewById(R.id.armspinner1);
        this.armechspin=(Spinner)this.findViewById(R.id.armspinner2);
        this.armlevelspin=(Spinner)this.findViewById(R.id.armspinner3);
        
       
        adapter1 = ArrayAdapter.createFromResource(this, 
        		 R.array.armortype, android.R.layout.simple_spinner_item); 
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        armtypespin.setAdapter(adapter1); 
        armtypespin.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				armtypeinfo =parent.getItemAtPosition(position).toString();
		                    
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}}); 
        
         
        adapter2 = ArrayAdapter.createFromResource(this, 
        		 R.array.armorench, android.R.layout.simple_spinner_item); 
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        armechspin.setAdapter(adapter2); 
        armechspin.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				armenchinfo =parent.getItemAtPosition(position).toString();
		                    
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}}); 
        
       
        adapter3 = ArrayAdapter.createFromResource(this, 
        		 R.array.armorlevel, android.R.layout.simple_spinner_item); 
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
        armlevelspin.setAdapter(adapter3); 
        armlevelspin.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				armlevelinfo =parent.getItemAtPosition(position).toString();
		                    
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}}); 
        
        
        
        this.armpagebut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapSelectMobActivity.this, MapItemSelectActivity.class);  
                startActivity(intent); 
				
			}});
        
        this.armaddbut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Button but=new Button (MapSelectMobActivity.this);
				but.setId(Numofarmor);
				but.setText(Numofarmor+"Armor type: "+MapSelectMobActivity.armtypeinfo+" Armor ench :"+MapSelectMobActivity.armenchinfo+" Armor level:"+MapSelectMobActivity.armlevelinfo);
				Numofarmor++;
				layout.removeView(armaddbut);
			    layout.addView(but);
			    layout.addView(armaddbut);
				
				
			}});
        
        
	}
}

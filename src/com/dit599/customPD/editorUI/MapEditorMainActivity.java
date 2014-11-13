package com.dit599.customPD.editorUI;

import com.dit599.customPD.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MapEditorMainActivity extends Activity {
	private Button floorbu1,floorbu2,floorbu3,flooraddbu;
	public LinearLayout layout;
	public static int Numoffloorbut=3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main); 
		layout = (LinearLayout) findViewById(R.id.mylinear);
		this.floorbu1=(Button)this.findViewById(R.id.floor1bu);
		this.floorbu2=(Button)this.findViewById(R.id.floor2bu);
		this.floorbu3=(Button)this.findViewById(R.id.floor3bu);
		this.floorbu1.setId(1);
		this.floorbu2.setId(2);
		this.floorbu3.setId(3);
		this.flooraddbu=(Button)this.findViewById(R.id.flooraddbu);	
		this.flooraddbu.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   
			    Button but=new Button (MapEditorMainActivity.this);
			    but.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent("com.dit599.customPD.editorUI.FloorItemActivity");   
		                startActivity(intent);  
					}});
			    Numoffloorbut++;
			    but.setId(Numoffloorbut);
			    but.setText("Floor "+but.getId());
			    layout.removeView(flooraddbu);
			    layout.addView(but);
			    layout.addView(flooraddbu);
				
			}});
		
		this.floorbu1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.dit599.customPD.editorUI.FloorItemActivity");  
                startActivity(intent);  
			}});
		this.floorbu2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.dit599.customPD.editorUI.FloorItemActivity");  
                startActivity(intent);  
			}});
		this.floorbu3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("com.dit599.customPD.editorUI.FloorItemActivity");   
                startActivity(intent);  
			}});
		
	}
}
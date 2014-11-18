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
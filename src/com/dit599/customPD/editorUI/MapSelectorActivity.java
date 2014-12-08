package com.dit599.customPD.editorUI;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dit599.customPD.R;
import com.watabou.noosa.Game;

public class MapSelectorActivity extends Activity {
	public EditText edv=null;
	public Button bt=null;
	public LinearLayout mlayout=null;
	ArrayList<String> files;


	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.mapselect);  
		edv=(EditText)findViewById(R.id.mapselectet);
		bt=(Button)this.findViewById(R.id.mapselectbut);
		files = new ArrayList<String>();

		for(String f : Game.instance.fileList()){
			if(f.endsWith(".map")){
				files.add(f.substring(0, f.length()-4));
				Button but=new Button (MapSelectorActivity.this);
				but.setOnClickListener(getListener());
				but.setText(f.substring(0, f.length()-4));
				mlayout.addView(but);
			}
		}
		mlayout= (LinearLayout) findViewById(R.id.mapselectlinear);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String temp = edv.getText().toString();
				if(!temp.equals("") && !files.contains(temp)){
					Button but=new Button (MapSelectorActivity.this);
					but.setOnClickListener(getListener());
					but.setText(temp);
					mlayout.addView(but);

				}}});
	}     
	private OnClickListener getListener(){
		return new OnClickListener(){

			@Override
			public void onClick(View v) {
				String temp = ((Button)v).getText().toString();
				Intent intent = new Intent();  
				intent.setClass(MapSelectorActivity.this, MapEditActivity.class);  
				intent.putExtra(MapEditActivity.EXTRA_FILENAME, temp);
				Log.d("NAME PARAM", temp);
				startActivity(intent);  
			}};
	}
}

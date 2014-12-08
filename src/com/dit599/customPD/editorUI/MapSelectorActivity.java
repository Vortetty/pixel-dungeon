package com.dit599.customPD.editorUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dit599.customPD.R;

public class MapSelectorActivity extends Activity {
	private Button mapbu1,mapbu2,mapbu3;
	public EditText edv=null;
	public Button bt=null;
	public LinearLayout mlayout=null;
	public static String choosemap=null;
	
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.mapselect);  
        edv=(EditText)findViewById(R.id.mapselectet);
        bt=(Button)this.findViewById(R.id.mapselectbut);
        mlayout= (LinearLayout) findViewById(R.id.mapselectlinear);
        mapbu1=(Button) findViewById(R.id.mapex1bu);
        mapbu2=(Button) findViewById(R.id.mapex2bu);
        mapbu3=(Button) findViewById(R.id.mapex3bu);
		bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			   
			    Button but=new Button (MapSelectorActivity.this);
			    but.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();  
		                intent.setClass(MapSelectorActivity.this, MapEditActivity.class);  
                        intent.putExtra(MapEditActivity.EXTRA_FILENAME, "map1");
		                startActivity(intent);  
					}});
			
			    but.setText(edv.getText().toString()+".map");
			    choosemap=but.getText().toString();
			    mlayout.addView(but);
				
			}});
		mapbu1.setOnClickListener(new OnClickListener(){

		

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();  
		                intent.setClass(MapSelectorActivity.this, MapEditActivity.class);  
		                intent.putExtra(MapEditActivity.EXTRA_FILENAME, "map1");
		                startActivity(intent);  
					}});
		mapbu2.setOnClickListener(new OnClickListener(){

			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapSelectorActivity.this, MapEditActivity.class);  
                startActivity(intent);  
			}});
		mapbu3.setOnClickListener(new OnClickListener(){

			

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();  
                intent.setClass(MapSelectorActivity.this, MapEditActivity.class);  
                startActivity(intent);  
			}});
			
			   
        
	}     

}

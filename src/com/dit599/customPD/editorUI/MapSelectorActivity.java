package com.dit599.customPD.editorUI;

import com.dit599.customPD.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
//modified map editor version 2.1
public class MapSelectorActivity extends Activity {
	private Button mapbu1,mapbu2,mapbu3;
	public EditText edv=null;
	public Button bt=null;
	public LinearLayout mlayout=null;
	public static int numofMapbut=3;
	public static String choosemap=null;
	
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.mapselect);  
        edv=(EditText)findViewById(R.id.mapselectet);
        bt=(Button)findViewById(R.id.mapselectbut);
        mlayout= (LinearLayout) findViewById(R.id.mapselectlinear);
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
		                intent.setClass(MapSelectorActivity.this, FloorItemActivity.class);  
		                startActivity(intent);  
					}});
			    numofMapbut++;
			    but.setId(numofMapbut);
			    but.setText(edv.getText().toString()+".map");
			    choosemap=but.getText().toString();
			    mlayout.addView(but);
			    mlayout.addView(but);
				
			}});
        
	}     

}

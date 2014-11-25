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
	private Button Mapbu1,Mapbu2,Mapbu3;
	public EditText edv=null;
	public Button bt=null;
	public LinearLayout mlayout=null;
	public static int NumofMapbut=3;
	public static String choosemap=null;
	
	
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.mapselect);  
        this.edv=(EditText)this.findViewById(R.id.mapselectet);
        this.bt=(Button)this.findViewById(R.id.mapselectbut);
        this.mlayout= (LinearLayout) this.findViewById(R.id.mapselectlinear);
        this.Mapbu1.setId(1);
		this.Mapbu2.setId(2);
		this.Mapbu3.setId(3);
		this.bt.setOnClickListener(new OnClickListener(){

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
			    NumofMapbut++;
			    but.setId(NumofMapbut);
			    but.setText(edv.getText().toString()+".map");
			    choosemap=but.getText().toString();
			    mlayout.addView(but);
			    mlayout.addView(but);
				
			}});
        
	}     

}

package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
public class MapSelectArmorActivity extends Activity {
	
	public Button armoraddbut=null;
	public Spinner armtypespin,armechspin,armlevelspin=null;
	public ArrayAdapter<CharSequence> adapter1,adapter2,adapter3=null;
	public LinearLayout layout,sublayout;
	public static  String armtypeinfo=null;
	public static String armenchinfo=null;
	public static String armlevelinfo=null;
	public static int Numofarmor=0;
	public static LinearLayout.LayoutParams layoutParam=null;
    public ListView sublayoutListView=null;
    
    public ArrayAdapter<ListView> adapter=null;
    
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.armitem);  
        
        layout=(LinearLayout) this.findViewById(R.id.armoritemmylinear);
        
        layout.setOrientation(LinearLayout.VERTICAL);
        LayoutInflater inflater=getLayoutInflater();
        sublayout = (LinearLayout) inflater.inflate(
                R.layout.armoritemsublayout, null).findViewById(R.id.armorlinearayout); 
        
        sublayoutListView=(ListView) this.findViewById(R.id.armorlistview);
        adapter = new ArrayAdapter<ListView>(this,android.R.layout.simple_list_item_1);
        sublayoutListView.setAdapter(adapter);
        
        
        layout.addView(sublayout);
        armoraddbut=(Button) this.findViewById(R.id.armoraddbut);
        
        this.armoraddbut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
			//layout.addView(sublayout);
//				FactoryAdditionalLayout();
				Toast.makeText(getApplicationContext(),"Armor type: "+MapSelectArmorActivity.armtypeinfo+" Armor ench :"+MapSelectArmorActivity.armenchinfo+" Armor level:"+MapSelectArmorActivity.armlevelinfo , Toast.LENGTH_LONG).show();
				
				sublayoutListView.addView(sublayout);
				adapter = new ArrayAdapter<ListView>(MapSelectArmorActivity.this,android.R.layout.simple_list_item_1);
		        sublayoutListView.setAdapter(adapter);
				
				
			}});
       
        
	}
	
//   public void FactoryAdditionalLayout()
//   {
//	   armtypespin = armechspin= new Spinner(this);
//       armtypespin.setPrompt("---Choose armor type_---");
//       armechspin= new Spinner(this);
//       armechspin.setPrompt("---Choose armor ench_---");
//       armlevelspin= new Spinner(this);
//       armlevelspin.setPrompt("---Choose armor Level---");
//       
//       armoraddbut=new Button(this);
//       armoraddbut.setText("+");
//       adapter1 = ArrayAdapter.createFromResource(this, 
//       		 R.array.armortype, android.R.layout.simple_spinner_item); 
//       adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
//       armtypespin.setAdapter(adapter1); 
//       armtypespin.setOnItemSelectedListener(new OnItemSelectedListener(){
//
//    	   
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				armtypeinfo =parent.getItemAtPosition(position).toString();
//		                    
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				// TODO Auto-generated method stub
//				
//			}}); 
//       
//        
//       adapter2 = ArrayAdapter.createFromResource(this, 
//       		 R.array.armorench, android.R.layout.simple_spinner_item); 
//       adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
//       armechspin.setAdapter(adapter2); 
//       armechspin.setOnItemSelectedListener(new OnItemSelectedListener(){
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				armenchinfo =parent.getItemAtPosition(position).toString();
//		                    
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				// TODO Auto-generated method stub
//				
//			}}); 
//       
//      
//       adapter3 = ArrayAdapter.createFromResource(this, 
//       		 R.array.armorlevel, android.R.layout.simple_spinner_item); 
//       adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
//       armlevelspin.setAdapter(adapter3); 
//       armlevelspin.setOnItemSelectedListener(new OnItemSelectedListener(){
//
//			@Override
//			public void onItemSelected(AdapterView<?> parent, View view,
//					int position, long id) {
//				// TODO Auto-generated method stub
//				armlevelinfo =parent.getItemAtPosition(position).toString();
//		                    
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> parent) {
//				// TODO Auto-generated method stub
//				
//			}}); 
//       sublayout.addView(armtypespin,layoutParam);
//       sublayout.addView(armechspin,layoutParam);
//       sublayout.addView(armlevelspin,layoutParam);
//      
//       
//       linears.add(sublayout);
//       
//       ArrayAdapter<LinearLayout> arrayAdapter = new ArrayAdapter<LinearLayout>( this,android.R.layout.simple_spinner_item,linears); 
//       ListView= (ListView)findViewById(R.id.armorlistview);    
//       ListView.setAdapter(arrayAdapter);          
//      
//       
//
//   }
   OnChildClickListener stvClickEvent = new OnChildClickListener() {

 		@Override
 		public boolean onChildClick(ExpandableListView parent, View v,
 				int groupPosition, int childPosition, long id) {
 			// TODO Auto-generated method stub
 			String msg = "parent_id = " + groupPosition + " child_id = "
 					+ childPosition;
 			Toast.makeText(MapSelectArmorActivity.this, msg,
 					Toast.LENGTH_SHORT).show();
 			return false;
 		}
 	};
}

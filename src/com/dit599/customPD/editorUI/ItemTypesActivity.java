package com.dit599.customPD.editorUI;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ExpandableListView;

public class ItemTypesActivity extends Activity {
  // more efficient than HashMap for mapping integers to objects
  SparseArray<Group> groups = new SparseArray<Group>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    createData();
    ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
    ExpandableListAdapter adapter = new ExpandableListAdapter(this,
        groups);
    listView.setAdapter(adapter);
  }

  public void createData() {
	  
	  String[] floor_items={"Enemies","Weapons","Potions"};
	  String [] enemies_type={"Giant Bat","Ghost","Rat"};
	  String[] weapons_type={"Sword","Axe","Hammer"};
	  String [] potion_type={"Golden","Magenta","Charcoal"};
    for (int j = 0; j < floor_items.length; j++) {
    	
        Group group = new Group(floor_items[j]);
        if ( j<1 ){
      for (int i = 0; i <enemies_type.length; i++) {
        group.children.add(enemies_type[i]);

      }}
        
        if ( j==1&&j<2 ){
            for (int i = 0; i <weapons_type.length; i++) {
              group.children.add(weapons_type[i]);

            }}
        
        if ( j==2&&j<3 ){
            for (int i = 0; i <potion_type.length; i++) {
              group.children.add(potion_type[i]);

            }}
        
      groups.append(j,group);
    }
  }

} 
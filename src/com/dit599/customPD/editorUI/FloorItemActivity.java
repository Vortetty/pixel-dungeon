package com.dit599.customPD.editorUI;

 


import java.util.List;

import com.dit599.customPD.R;









import android.app.Activity;  
import android.os.Bundle;  
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.TabHost;  
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TabHost.TabSpec;  

public class FloorItemActivity extends Activity {  
    /** Called when the activity is first created. */  
	ExpandableListView expandableListView;
	TreeViewAdapter adapter;
	SuperTreeViewAdapter superAdapter;
	TabHost tabHost; 
    public int NumofRoomtab=1;
	public String[] supertab = { "Enemies", "Weapons" ,"......"};
	public String[][][] subtab = { { { "Type" }, { "Giant Bat", "Ghost","Rat" } },
			{ { "Level" }, { "...", "....." } } };
	TabSpec parentSpec,subSpec;
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.flooritem);  
        tabHost = (TabHost) findViewById(R.id.tabs);  
        expandableListView = (ExpandableListView) findViewById(R.id.expandablelistview);
        superAdapter = new SuperTreeViewAdapter(this, stvClickEvent);
      
		
        tabHost.setup();  
        parentSpec = tabHost.newTabSpec("Room"+NumofRoomtab);  
        parentSpec.setIndicator("Room"+NumofRoomtab,  
                this.getResources().getDrawable(R.drawable.icon));  
        parentSpec.setContent(R.id.roomtab_1);  
       
  
        subSpec = tabHost.newTabSpec("Roomadd");  
        subSpec.setIndicator("   +   ",  
                this.getResources().getDrawable(R.drawable.icon));  
        subSpec.setContent(R.id.roomaddtab);  
  
        tabHost.addTab(parentSpec);  
        tabHost.addTab(subSpec);  
        tabHost.setOnTabChangedListener(new OnTabChangeListener (){

			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				if(tabId=="Roomadd"){
					NumofRoomtab++; 
					TabSpec roomaddSpec = tabHost.newTabSpec("Room"+NumofRoomtab);  
					roomaddSpec.setIndicator("Room"+NumofRoomtab,  
			        		FloorItemActivity.this.getResources().getDrawable(R.drawable.icon));  
					roomaddSpec.setContent(R.id.roomtab_1); 				
					tabHost.addTab(roomaddSpec);  
					
				 }
			}
			
			
		});
        
        
        
        
       
       
       
        List<SuperTreeViewAdapter.SuperTreeNode> superNodeTree = superAdapter
				.GetTreeNode();
		for (int i = 0; i < supertab.length; i++) {
			SuperTreeViewAdapter.SuperTreeNode superNode = new SuperTreeViewAdapter.SuperTreeNode();
			superNode.parent = supertab[i];

			for (int j = 0; j < subtab.length; j++) {
				TreeViewAdapter.TreeNode node = new TreeViewAdapter.TreeNode();
				node.parent = subtab[j][0][0];
				for (int k = 0; k < subtab[j][1].length; k++) {
					node.childs.add(subtab[j][1][k]);
				}
				superNode.childs.add(node);
			}
			superNodeTree.add(superNode);
		}
		superAdapter.UpdateTreeNode(superNodeTree);
		expandableListView.setAdapter(superAdapter);
  
    } 
    
  
    OnChildClickListener stvClickEvent = new OnChildClickListener() {

		@Override
		public boolean onChildClick(ExpandableListView parent, View v,
				int groupPosition, int childPosition, long id) {
			// TODO Auto-generated method stub
			String msg = "parent_id = " + groupPosition + " child_id = "
					+ childPosition;
			Toast.makeText(FloorItemActivity.this, msg,
					Toast.LENGTH_SHORT).show();
			return false;
		}
	};
	
	 
}  


	

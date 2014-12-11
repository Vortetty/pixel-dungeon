package com.dit599.customPD.editorUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.dit599.customPD.R;
public class ItemsActivity extends Activity {
	
	public Spinner itemtypespin=null;
	public ArrayAdapter<CharSequence> itemadapter=null;
    public LinearLayout layout;
    public ListView mItemListView = null;        
    public ArrayAdapter<CharSequence> adapter;
    public ItemAdapter mItemAdapter;
    
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.customizable_item_activity2);  
        
        
        layout = (LinearLayout) this.findViewById(R.id.enchantable_base_layout2);
        
        mItemListView = (ListView) this.findViewById(R.id.enchantable_list_view2);
        View footer = getLayoutInflater().inflate(R.layout.item_list_footer, null);
        mItemListView.addFooterView(footer);

        mItemAdapter = new ItemAdapter(this);
        mItemListView.setAdapter(mItemAdapter);
        itemtypespin=(Spinner) this.findViewById(R.id.itemtypespinner);              
        Button addButton = (Button) footer.findViewById(R.id.add_button);

        addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mItemAdapter.addItem();
            }
        });

    }

   OnChildClickListener stvClickEvent = new OnChildClickListener() {

 		@Override
 		public boolean onChildClick(ExpandableListView parent, View v,
 				int groupPosition, int childPosition, long id) {
 			// TODO Auto-generated method stub
 			String msg = "parent_id = " + groupPosition + " child_id = "
 					+ childPosition;
 			Toast.makeText(ItemsActivity.this, msg,
 					Toast.LENGTH_SHORT).show();
 			return false;
 		}
 	};
 	
}
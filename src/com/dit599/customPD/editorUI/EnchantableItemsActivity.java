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
public class EnchantableItemsActivity extends Activity {
	
	public Spinner armtypespin,armechspin,armlevelspin=null;
	public ArrayAdapter<CharSequence> adapter1,adapter2,adapter3=null;
    public LinearLayout layout;
    public ListView mItemListView = null;
    
    public EnchantableItemAdapter mItemAdapter;
    
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.enchantable_item_activity);  
        
        layout = (LinearLayout) this.findViewById(R.id.enchantable_base_layout);
        
        mItemListView = (ListView) this.findViewById(R.id.enchantable_list_view);
        View footer = getLayoutInflater().inflate(R.layout.item_list_footer, null);
        mItemListView.addFooterView(footer);

        mItemAdapter = new EnchantableItemAdapter(this);
        mItemListView.setAdapter(mItemAdapter);
        
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
 			Toast.makeText(EnchantableItemsActivity.this, msg,
 					Toast.LENGTH_SHORT).show();
 			return false;
 		}
 	};
}

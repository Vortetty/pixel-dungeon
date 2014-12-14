/*
 * YourPD
 * Copyright (C) 2014 YourPD team
 * This is a modification of source code from: 
 * Pixel Dungeon
 * Copyright (C) 2012-2014 Oleg Dolya
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
import com.dit599.customPD.items.rings.RingOfAccuracy;
import com.dit599.customPD.items.wands.WandOfAmok;
import com.dit599.customPD.levels.template.LevelTemplate;
import com.dit599.customPD.levels.template.LevelTemplate.MagicItem;
import com.watabou.noosa.Game;
public class MagicItemsActivity extends Activity {
	
	public Spinner armtypespin,armechspin,armlevelspin=null;
	public ArrayAdapter<CharSequence> adapter1,adapter2,adapter3=null;
    public LinearLayout layout;
    public ListView mItemListView = null;
    private LevelTemplate level;
    public MagicItemAdapter mItemAdapter;
    
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.customizable_item_activity3);  
        layout = (LinearLayout) this.findViewById(R.id.enchantable_base_layout3);
		this.setTitle(FloorFragment.chooseItemType);
		level = TemplateHandler.getInstance(getIntent().getStringExtra(MapEditActivity.EXTRA_FILENAME), this)
				 .getLevel(getIntent().getIntExtra(EnchantableItemsActivity.EXTRA_DEPTH, 0));
        mItemListView = (ListView) this.findViewById(R.id.enchantable_list_view3);
        View footer = getLayoutInflater().inflate(R.layout.item_list_footer, null);
        mItemListView.addFooterView(footer);

        mItemAdapter = new MagicItemAdapter(getApplicationContext(), this.level, this);
        mItemListView.setAdapter(mItemAdapter);
        
        Button addButton = (Button) footer.findViewById(R.id.add_button);

        addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mItemAdapter.addItem(true);
            }
        });
        addButton.setEnabled(true);
    }

   OnChildClickListener stvClickEvent = new OnChildClickListener() {

 		@Override
 		public boolean onChildClick(ExpandableListView parent, View v,
 				int groupPosition, int childPosition, long id) {
 			// TODO Auto-generated method stub
 			String msg = "parent_id = " + groupPosition + " child_id = "
 					+ childPosition;
 			Toast.makeText(MagicItemsActivity.this, msg,
 					Toast.LENGTH_SHORT).show();
 			return false;
 		}
 	};
	@Override
	public void onStart(){
		super.onStart();
		if(this == null){
			finish();
		}
		if(RingMapping.getAllNames()==null||WandMapping.getAllNames() == null){
			initMappings();
		}
		if (level == null){
			level = TemplateHandler.getInstance(getIntent().getStringExtra(MapEditActivity.EXTRA_FILENAME), this)
					 .getLevel(getIntent().getIntExtra(EnchantableItemsActivity.EXTRA_DEPTH, 0));
		}
		if(mItemAdapter.getCount() == 0){
			if(FloorFragment.chooseItemType.equals("Wands")){
				initItems(level.wands.size());	
			}
			else if(FloorFragment.chooseItemType.equals("Rings")){
				initItems(level.rings.size());
			}
			
		}
	}
	private void initMappings(){
		WandMapping.wandMappingInit();
		RingMapping.ringMappingInit();
		
	}
	private void initItems(int i){
		while (i > 0){
			mItemAdapter.addItem(false);
			i--;
		}
	}
	@Override
	public void onPause(){
		super.onPause();
		TemplateHandler.getInstance(getIntent().getStringExtra(MapEditActivity.EXTRA_FILENAME), this).save(this);
	}
}

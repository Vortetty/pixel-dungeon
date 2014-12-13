package com.dit599.customPD.editorUI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dit599.customPD.R;
import com.dit599.customPD.levels.template.LevelTemplate;
import com.watabou.noosa.Game;
public class EnchantableItemsActivity extends Activity {
	
    public static final String EXTRA_DEPTH = "depth";
    public static final String EXTRA_TYPE = "type";
    public static final int WEAPON = 0;
    public static final int ARMOR = 1;
    public LinearLayout layout;
    public ListView mItemListView = null;
    
    public LevelTemplate level;

    public EnchantableItemAdapter mItemAdapter;
    
	@Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.customizable_item_activity);

        String mapName = getIntent().getStringExtra(MapEditActivity.EXTRA_FILENAME);
        int depth = getIntent().getIntExtra(EXTRA_DEPTH, 0);
        if (depth == 0) {
            throw new RuntimeException();
        }
        level = TemplateHandler.getInstance(mapName).getLevel(depth);
        
        int type = getIntent().getIntExtra(EXTRA_TYPE, 0);

        layout = (LinearLayout) this.findViewById(R.id.enchantable_base_layout);
        
        mItemListView = (ListView) this.findViewById(R.id.enchantable_list_view);
        View footer = getLayoutInflater().inflate(R.layout.item_list_footer, null);
        mItemListView.addFooterView(footer);

        mItemAdapter = new EnchantableItemAdapter(this, level, type);
        mItemListView.setAdapter(mItemAdapter);
        
        Button addButton = (Button) footer.findViewById(R.id.add_button);

        addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mItemAdapter.addItem();
            }
        });

    }
	@Override
	public void onStart(){
		super.onStart();
		if(Game.instance == null){
			finish();
		}
	}
}

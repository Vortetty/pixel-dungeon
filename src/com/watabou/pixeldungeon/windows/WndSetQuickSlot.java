package com.watabou.pixeldungeon.windows;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.items.Item;
import com.watabou.pixeldungeon.items.wands.Wand;
import com.watabou.pixeldungeon.ui.QuickSlot;

public class WndSetQuickSlot extends WndOptions{
	
	private Item item;

	public WndSetQuickSlot(String title, String message, String[] options, Item i) {
		super(title, message, options);
		item = i;
	}
	@Override
	protected void onSelect( int index ) {
		if(index == 1){
			Dungeon.qsRight = item instanceof Wand ? item : item.getClass();
			QuickSlot.refresh(true);
		}
		else{
			Dungeon.qsLeft = item instanceof Wand ? item : item.getClass();
			QuickSlot.refresh(false);
		}
	};
}

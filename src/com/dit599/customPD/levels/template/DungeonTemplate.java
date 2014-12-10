package com.dit599.customPD.levels.template;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.util.Log;

import com.watabou.noosa.Game;
import com.watabou.utils.Bundlable;
import com.watabou.utils.Bundle;

public class DungeonTemplate implements Bundlable{
    public static final int MAX_DEPTH = 30;
    public String name;

    /**
     * The levelTemplates are 0-indexed, so the level at depth 1 has index 0.
     */
    public List<LevelTemplate> levelTemplates = new ArrayList<LevelTemplate>(MAX_DEPTH);
    public DungeonTemplate() {
//    	for(int i = 0; i < MAX_DEPTH; i++){
//			levelTemplates.add(new LevelTemplate());
//    	}
    }

    public DungeonTemplate(String file) {
        load(file);
    }

    public void reset(){
    	levelTemplates = new ArrayList<LevelTemplate>(MAX_DEPTH);
    	for(int i = 0; i < MAX_DEPTH; i++){
			levelTemplates.add(new LevelTemplate());
    	}
    }
	@Override
	public void restoreFromBundle(Bundle bundle) {
		levelTemplates = new ArrayList<LevelTemplate>(MAX_DEPTH);
		levelTemplates.addAll((Collection<? extends LevelTemplate>) bundle.getCollection("levelTemplates"));
		if (name == null || name.equals("")){
			name = bundle.getString("templateName");
		}
		Log.d("DTRESTORE", levelTemplates.size() + "");
	}
	@Override
	public void storeInBundle(Bundle bundle) {
		bundle.put("levelTemplates", levelTemplates );
		bundle.put("templateName", name);
		Log.d("DTSTORE", levelTemplates.size() + "");
	}


	public void save(String file) {
		Bundle bundle = new Bundle();
		name = file;
		storeInBundle(bundle);
		try {
			OutputStream output = Game.instance.openFileOutput(name + ".map", Game.MODE_PRIVATE );
			Bundle.write( bundle, output );
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void load(String file) {
		Bundle  bundle = null;
		name = file;
		try {
			InputStream input = Game.instance.openFileInput(name + ".map");
			bundle = Bundle.read( input );
			input.close();
			if(bundle == null){
				bundle = new Bundle();
			}
			restoreFromBundle(bundle);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

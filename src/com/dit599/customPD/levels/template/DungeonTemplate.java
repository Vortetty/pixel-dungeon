package com.dit599.customPD.levels.template;

public class DungeonTemplate {
    public static final int MAX_DEPTH = 30;
    public LevelTemplate[] levelTemplates = new LevelTemplate[MAX_DEPTH];

    public DungeonTemplate() {
    	for(int i = 0; i < MAX_DEPTH; i++){
			levelTemplates[i] = new LevelTemplate();
    	}
    }

}

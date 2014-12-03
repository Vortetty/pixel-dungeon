package com.dit599.customPD.levels.template;

public class DungeonTemplate {
    public static final int MAX_DEPTH = 30;
    public String name;

    /**
     * The levelTemplates are 0-indexed, so the level at depth 1 has index 0.
     */
    public LevelTemplate[] levelTemplates = new LevelTemplate[MAX_DEPTH];
    public CustomTemplate [] customTemplates= new CustomTemplate[MAX_DEPTH]; 
    public DungeonTemplate() {
    	for(int i = 0; i < MAX_DEPTH; i++){
			levelTemplates[i] = new LevelTemplate();
    	}
    }

}

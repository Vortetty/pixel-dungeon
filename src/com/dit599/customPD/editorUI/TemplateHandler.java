package com.dit599.customPD.editorUI;

import java.io.FileNotFoundException;
import java.util.HashMap;

import com.dit599.customPD.levels.template.DungeonTemplate;
import com.dit599.customPD.levels.template.LevelTemplate;
import com.dit599.customPD.levels.template.TemplateFactory;

/**
 * 
 * @author Anton
 *
 *         Class is not designed to be thread-safe
 */
public class TemplateHandler {

    private static HashMap<String, TemplateHandler> instances = new HashMap<String, TemplateHandler>();

    private String mapName;
    private DungeonTemplate template;
    private LevelTemplate current;

    /**
     * Provedes an instance associated with the map name. Handles reading from
     * and to files.
     * 
     * @param mapName
     * @return
     * @throws FileNotFoundException
     */
    public static TemplateHandler getInstance(String mapName) {
        TemplateHandler instance = instances.get(mapName);
        if (instance == null) {
            instance = new TemplateHandler(mapName);
            instances.put(mapName, instance);
        }
        return instance;
    }

    private TemplateHandler(String mapName) {
        this.mapName = mapName;
        loadFromFile();
    }

    /**
     * Synchronously loads the specified template.
     */
    private void loadFromFile() {
        setTemplate(TemplateFactory.createSimpleDungeon(mapName));
    }

    public void save() {
    	getTemplate().save(mapName);
    }

    public DungeonTemplate getDungeon() {
        return getTemplate();
    }

    /**
     * 
     * @param depth
     *            the 1-indexed depth
     * @return
     */
    public LevelTemplate getLevel(int depth) {
        return getTemplate().levelTemplates.get(depth - 1); // handle 1-index
    }

	public DungeonTemplate getTemplate() {
		return template;
	}

	public void setTemplate(DungeonTemplate template) {
		this.template = template;
	}
	public void addLevel(){
		this.template.levelTemplates.add(TemplateFactory.createSimpleLevel());
	}

}

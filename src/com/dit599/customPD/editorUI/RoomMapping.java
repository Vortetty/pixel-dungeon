package com.dit599.customPD.editorUI;

import java.util.ArrayList;
import java.util.List;

import com.dit599.customPD.levels.*;

public abstract class RoomMapping {
	private static List<Room.Type> roomtypelist = null;
	private static List<String> roomnamelist = null;

	public static void LevelMappingInit() {
		roomtypelist = new ArrayList<Room.Type>();
		roomtypelist.add(Room.Type.ARMORY);
		roomtypelist.add(Room.Type.CRYPT);
		roomtypelist.add(Room.Type.GARDEN);
		roomtypelist.add(Room.Type.LABORATORY);
		roomtypelist.add(Room.Type.LIBRARY);
		roomtypelist.add(Room.Type.MAGIC_WELL);
		roomtypelist.add(Room.Type.POOL);
		roomtypelist.add(Room.Type.SHOP);
		roomtypelist.add(Room.Type.STATUE);
		roomtypelist.add(Room.Type.STORAGE);
		roomtypelist.add(Room.Type.TRAPS);
		roomtypelist.add(Room.Type.TREASURY);
		roomtypelist.add(Room.Type.VAULT);

		roomnamelist = new ArrayList<String>();
		roomnamelist.add("Armory");
		roomnamelist.add("Crypt");
		roomnamelist.add("Garden");
		roomnamelist.add("Laboratory");
		roomnamelist.add("Library");
		roomnamelist.add("Magic Well");
		roomnamelist.add("Piranha Room");
		roomnamelist.add("Shop");
		roomnamelist.add("Animated Statue Room");
		roomnamelist.add("Storage");
		roomnamelist.add("Trapped Room");
		roomnamelist.add("Treasury");
		roomnamelist.add("Vault");

	}

	public static String getRoomName(int index) {
		return roomnamelist.get(index);
	}
	public static Room.Type getRoomType(int index) {
		return roomtypelist.get(index);
	}
}

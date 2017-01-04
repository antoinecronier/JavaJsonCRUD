package javajsoncrud.manager;

import java.io.File;
import java.util.ArrayList;

import javajackson.json.manager.JsonManager;

public class CRUDManager {
	public static final String DB_PATH = ".\\jsonDatabase\\";
	private ArrayList<String> tables;

	private static CRUDManager INSTANCE = null;

	public static CRUDManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CRUDManager();
		}
		return INSTANCE;
	}

	private CRUDManager() {
		this.tables = this.readFolder();
	}

	private ArrayList<String> readFolder() {
		ArrayList<String> result = new ArrayList<String>();
		File folder = new File(this.DB_PATH);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				if (listOfFiles[i].getName().contains(".json")) {
					result.add(listOfFiles[i].getName().split(".json")[0]);
				}
			}
		}

		return result;
	}

	public <T> ArrayList<T> tableItems(String table, Class<?> elem){
		return JsonManager.getInstance().readFromFile(table, DB_PATH, elem);
	}

	public void printTableItems(String table, Class<?> elem){
		for (Object object : JsonManager.getInstance().readFromFile(table, DB_PATH, elem)) {
			System.out.println(object.toString());
		}
	}
}

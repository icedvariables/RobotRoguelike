package robotroguelike.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MapManager {
	public static final String SAVE_DIRECTORY = "/saves/";
	public static final String FILE_EXTENSION = ".sav";
	
	public static void saveMap(Map map, String name){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;

		try{
			File f = new File(System.getProperty("user.dir"), SAVE_DIRECTORY + name + FILE_EXTENSION);
			fout = new FileOutputStream(f);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(map);
			
			System.out.println("Saved map '" + name + "' to file: " + f + " successfully.");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				fout.close();
				oos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static Map loadMap(String name){
		FileInputStream fin = null;
		ObjectInputStream ois = null;
		Map map = null;

		try{
			File f = new File(System.getProperty("user.dir"), SAVE_DIRECTORY + name + FILE_EXTENSION);
			fin = new FileInputStream(f);
			ois = new ObjectInputStream(fin);
			map = (Map)ois.readObject();
			
			System.out.println("Loaded map '" + name + "' from file: " + f + " successfully.");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				fin.close();
				ois.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return map;
	}
}

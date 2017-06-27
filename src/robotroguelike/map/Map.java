package robotroguelike.map;

import java.util.ArrayList;

import robotroguelike.creatures.Creature;
import robotroguelike.items.Item;
import robotroguelike.tiles.Tile;
import robotroguelike.tiles.TileFloor;
import robotroguelike.tiles.TileNull;

public class Map {
	private Tile[][] tiles;
	public ArrayList<Creature> creatures = new ArrayList<Creature>();
	
	public final int width;
	public final int height;
	
	public Map(Tile[][] tiles){
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
	}
	
	public Tile tileAt(int x, int y){
		if(x >= 0 && x < width && y >= 0 && y < height){
			return tiles[x][y];
		}
		return new TileNull();
	}
	
	public Item dig(int x, int y, Item item){
		Item returnItem = null;
		
		if(tileAt(x, y).isDiggable(item.getTier())){
			returnItem = tileAt(x, y).returnOnDig();
			
			tiles[x][y] = new TileFloor();
		}
		
		return returnItem;
	}
	
	public void addCreature(Creature c){
		creatures.add(c);
		
		System.out.println(creatures);
	}
}

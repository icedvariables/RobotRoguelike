package robotroguelike.map;

import java.util.ArrayList;

import robotroguelike.creatures.Creature;
import robotroguelike.items.Item;
import robotroguelike.tiles.Tile;
import robotroguelike.tiles.TileFloor;

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
		return null;
	}
	
	public Item dig(int x, int y, Item item){
		Item returnItem = null;
		
		Tile tile = tileAt(x, y);
		
		if(item != null && tile != null && tile.isDiggableWith(item.getTier())){
			returnItem = tile.returnOnDig();
			
			tiles[x][y] = new TileFloor();
		}
		
		return returnItem;
	}
	
	public void addCreature(Creature c){
		creatures.add(c);
		
		System.out.println(creatures);
	}
}

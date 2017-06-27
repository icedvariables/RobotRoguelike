package robotroguelike.map;

import robotroguelike.tiles.Creature;
import robotroguelike.tiles.Tile;
import robotroguelike.tiles.TileNull;

public class Map {
	private Tile[][] tiles;
	public final int width;
	public final int height;
	
	public Map(Tile[][] tiles){
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
	}
	
	public Tile tileAt(int x, int y){
		if(x >= 0 || x < width || y >= 0 || y < height){
			return tiles[x][y];
		}
		return new TileNull();
	}
	
	public void dig(int x, int y, Creature creature){
		if(tileAt(x, y).isDiggable(creature.inventory.getEquippedItem())){
			
		}
	}
}

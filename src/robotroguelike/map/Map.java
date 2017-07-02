package robotroguelike.map;

import java.io.Serializable;
import java.util.ArrayList;

import robotroguelike.creatures.Creature;
import robotroguelike.items.Item;
import robotroguelike.tiles.Tile;

public class Map implements Serializable {
	private static final long serialVersionUID = -3802009101439612931L;

	public final String name = "map";

	private Tile[][] tiles;
	public ArrayList<Creature> creatures = new ArrayList<Creature>();

	public final int width;
	public final int height;

	public Map(Tile[][] tiles) {
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
	}

	public Map(Tile[][] tiles, ArrayList<Creature> creatures) {
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
		this.creatures = creatures;
	}

	public Tile tileAt(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return tiles[x][y];
		}
		return null;
	}

	public Item dig(int x, int y, Item item) {
		Item returnItem = null;

		Tile tile = tileAt(x, y);

		if (item != null && tile != null && tile.isDiggableWith(item.getTier())) {
			returnItem = tile.returnOnDig();

			tiles[x][y] = null;
		}

		return returnItem;
	}

	public void addCreature(Creature c) {
		creatures.add(c);

		System.out.println(creatures);
	}
	
	public int countAmountOfTile(Tile tile) {
		int counter = 0;

		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(tileAt(x, y).id == tile.id) {
					counter++;
				}
			}
		}
		return counter;
	}

	public Tile[][] getTiles() {
		return tiles;
	}
}

package robotroguelike.map;

import java.io.Serializable;
import java.util.ArrayList;

import robotroguelike.entities.Entity;
import robotroguelike.items.Item;
import robotroguelike.tiles.Tile;

public class Map implements Serializable {
	private static final long serialVersionUID = -3802009101439612931L;

	public final String name = "map";

	private Tile[][] tiles;
	public ArrayList<Entity> creatures = new ArrayList<Entity>();

	public final int width;
	public final int height;

	public Map(Tile[][] tiles) {
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
	}

	public Map(Tile[][] tiles, ArrayList<Entity> creatures) {
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

	public Entity entityAt(int x, int y) {
		for(int i = 0; i < creatures.size(); i++) {
			Entity e = creatures.get(i);

			if(e.getX() == x && e.getY() == y)
				return e;
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

	public boolean placeTile(int x, int y, Tile tile) {
		if(tile != null && tileAt(x, y) == null && entityAt(x, y) == null) {
			tiles[x][y] = tile;
			return true;
		}

		return false;
	}

	public boolean placeEntity(int x, int y, Entity entity) {
		if(entity != null && entityAt(x, y) == null && tileAt(x, y) == null) {
			entity.setPosition(x, y);
			creatures.add(entity);

			return true;
		}

		return false;
	}

	public void addCreature(Entity c) {
		creatures.add(c);

		System.out.println(creatures);
	}

	public int countAmountOfTile(Tile tile) {
		int counter = 0;

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (tileAt(x, y) != null && tileAt(x, y).id == tile.id) {
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

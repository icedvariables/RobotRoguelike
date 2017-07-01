package robotroguelike.map;

import robotroguelike.tiles.Tile;
import robotroguelike.tiles.TileStone;

public class SimpleMapBuilder implements MapBuilder {
	private int width, height;
	private Tile[][] tiles;

	public SimpleMapBuilder(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
	}

	private void randomise() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				double rand = Math.random();

				tiles[x][y] = rand < 0.7 ? null : new TileStone();
			}
		}
	}

	public Map build() {
		randomise();
		// smooth(1);
		return new Map(tiles);
	}
}

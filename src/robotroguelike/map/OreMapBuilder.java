package robotroguelike.map;

import robotroguelike.tiles.Tile;
import robotroguelike.tiles.TileCopperOre;
import robotroguelike.tiles.TileIronOre;
import robotroguelike.tiles.TileStone;

public class OreMapBuilder implements MapBuilder {
	private int width, height;
	private Tile[][] tiles;

	public OreMapBuilder(int width, int height) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
	}

	public OreMapBuilder(int width, int height, long seed) {
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
		rand.setSeed(seed);
	}

	private void randomize() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y ++) {
				double num = rand.nextDouble();
				try {
					if(num > 0.9995) {
						tiles[x+1][y+1] = new TileIronOre();
						tiles[x][y] = new TileIronOre();
						tiles[x-1][y-1] = new TileIronOre();
					}else if(num > 0.999) {
						tiles[x+1][x+1] = new TileCopperOre();
						tiles[x][y] = new TileCopperOre();
						tiles[x-1][y-1] = new TileCopperOre();
					}else
						tiles[x][y] = new TileStone();
				}catch(ArrayIndexOutOfBoundsException e) {} // TODO: Perform proper checks instead.
			}
		}
	}
	
	private void smooth(int amount) {
		for(int i = 0; i < amount; i++) {
			smooth();
		}
	}
	
	private void smooth() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < width; y++) {
				try {
					if(tiles[x][y] instanceof TileIronOre) {
						if(rand.nextDouble() < 0.2) {
							tiles[x+1][y] = new TileIronOre();
							tiles[x-1][y] = new TileIronOre();
							tiles[x][y+1] = new TileIronOre();
							tiles[x][y-1] = new TileIronOre();
						}
					}
					if(tiles[x][y] instanceof TileCopperOre) {
						if(rand.nextDouble() < 0.225) {
							tiles[x+1][y] = new TileCopperOre();
							tiles[x-1][y] = new TileCopperOre();
							tiles[x][y+1] = new TileCopperOre();
							tiles[x][y-1] = new TileCopperOre();
						}
					}
				}catch(ArrayIndexOutOfBoundsException e) {} // TODO: Make this actually perform checks if a
				                                            // a coordinate is out of bounds instead of just
				                                            // catching out of bounds errors.
			}
		}
	}
	
	@Override
	public Map build() {
		randomize();
		smooth(10);
		return new Map(tiles);
	}
}

package robotroguelike.map;

import robotroguelike.tiles.Tile;
import robotroguelike.tiles.TileFloor;
import robotroguelike.tiles.TileStone;

public class SimpleMapBuilder implements MapBuilder {
	private int width, height;
	private Tile[][] tiles;
	
	public SimpleMapBuilder(int width, int height){
		this.width = width;
		this.height = height;
		this.tiles = new Tile[width][height];
	}
	
	private void randomise(){
        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
            	double rand = Math.random();
            	
                tiles[x][y] = rand < 0.5 ? new TileFloor() : new TileStone();
            }
        }
    }
	
	private void smooth(int times){
		for(int i = 0; i < times; i++)
			smooth();
	}
	
	private void smooth(){
		Tile[][] newTiles = new Tile[width][height];
		
		for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int floors = 0;
                int rocks = 0;

                for (int ox = -1; ox < 2; ox++) {
                    for (int oy = -1; oy < 2; oy++) {
                        if (x + ox < 0 || x + ox >= width || y + oy < 0 ||
                            y + oy >= height)
                            continue;

                        if (tiles[x + ox][y + oy] == new TileFloor())
                            floors++;
                        else
                            rocks++;
                    }
                }
                newTiles[x][y] = floors >= rocks ? new TileFloor() : new TileStone();
            }
        }
        tiles = newTiles;
	}

	
	public Map build(){
		randomise();
		//smooth(1);
		return new Map(tiles);
	}
}

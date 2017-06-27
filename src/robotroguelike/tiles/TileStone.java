package robotroguelike.tiles;

import java.awt.Color;

public class TileStone extends Tile {
	public TileStone(){
		super((char)219, Color.GRAY);
		canWalkOver = false;
	}
}

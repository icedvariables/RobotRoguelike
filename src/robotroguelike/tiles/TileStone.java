package robotroguelike.tiles;

import java.awt.Color;

import robotroguelike.items.Item;
import robotroguelike.items.ItemStone;

public class TileStone extends Tile {
	public TileStone() {
		super((char) 219, Color.GRAY, false);
	}

	@Override
	public Item returnOnDig() {
		return new ItemStone();
	}
}

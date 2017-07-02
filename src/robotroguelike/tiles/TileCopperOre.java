package robotroguelike.tiles;

import java.awt.Color;

import robotroguelike.items.Item;
import robotroguelike.items.ItemCopperIngot;

public class TileCopperOre extends Tile {
	public TileCopperOre() {
		super((char) 177, Color.ORANGE, false);
	}

	@Override
	public Item returnOnDig() {
		return new ItemCopperIngot();
	}
}

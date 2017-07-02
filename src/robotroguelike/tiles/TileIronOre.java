package robotroguelike.tiles;

import java.awt.Color;

import robotroguelike.items.Item;
import robotroguelike.items.ItemIronIngot;

public class TileIronOre extends Tile {
	public TileIronOre() {
		super(TileId.IRON_ORE, (char) 177, Color.GRAY, false);
	}

	@Override
	public Item returnOnDig() {
		return new ItemIronIngot();
	}
}

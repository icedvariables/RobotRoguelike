package robotroguelike.items;

import robotroguelike.tiles.Tile;
import robotroguelike.tiles.TileStone;

public class ItemStone extends Item {
	public ItemStone() {
		super(ItemId.STONE, "Stone", "A piece of rock.", Tier.LOW, true, false);
	}

	@Override
	public Tile getTileToPlace() {
		return new TileStone();
	}
}

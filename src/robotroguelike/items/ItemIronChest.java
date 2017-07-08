package robotroguelike.items;

import robotroguelike.entities.Entity;
import robotroguelike.entities.IronChest;
import robotroguelike.map.Map;

public class ItemIronChest extends Item {

	public ItemIronChest() {
		super(ItemId.IRON_CHEST, "Iron Chest", "An storage chest made of iron.", Tier.LOW, true, false);
	}

	@Override
	public Entity getEntityToPlace(Map map) {
		return new IronChest(map);
	}
}

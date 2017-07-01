package robotroguelike.crafting;

import robotroguelike.items.Item;
import robotroguelike.items.ItemIds;
import robotroguelike.items.Tier;

public class ItemIronPickaxe extends Item {
	public ItemIronPickaxe() {
		super(ItemIds.IRON_PICKAXE, "Iron Pickaxe", "A tool used to mine rocks.", Tier.MEDIUM, true);
	}
}

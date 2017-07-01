package robotroguelike.crafting;

import robotroguelike.items.Item;
import robotroguelike.items.ItemStack;

public class CraftingRecipe {
	public final Item item;
	public final ItemStack[] ingredients;

	public CraftingRecipe(Item item, ItemStack[] ingredients) {
		this.item = item;
		this.ingredients = ingredients;
	}
}

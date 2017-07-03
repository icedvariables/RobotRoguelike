package robotroguelike.crafting;

import java.util.ArrayList;
import java.util.List;

import robotroguelike.items.Item;
import robotroguelike.items.ItemCopperCable;
import robotroguelike.items.ItemCopperIngot;
import robotroguelike.items.ItemElectronicCircuit;
import robotroguelike.items.ItemIronCog;
import robotroguelike.items.ItemIronIngot;
import robotroguelike.items.ItemIronPickaxe;
import robotroguelike.items.ItemLowTierEngine;
import robotroguelike.items.ItemLowTierMiningRobot;
import robotroguelike.items.ItemStack;
import robotroguelike.items.ItemStone;
import robotroguelike.items.ItemStoneHandle;

public class CraftingManager {
	private static final List<CraftingRecipe> recipes = new ArrayList<CraftingRecipe>();

	public static void addAllCraftingRecipes() {
		addCraftingRecipe(new CraftingRecipe(new ItemStoneHandle(),
				new ItemStack[] { new ItemStack(new ItemStone(), 3) }));

		addCraftingRecipe(new CraftingRecipe(new ItemIronPickaxe(),
				new ItemStack[] { new ItemStack(new ItemStoneHandle(), 1), new ItemStack(new ItemIronIngot(), 5) }));

		addCraftingRecipe(new CraftingRecipe(new ItemCopperCable(),
				new ItemStack[] { new ItemStack(new ItemCopperIngot(), 1) }));

		addCraftingRecipe(new CraftingRecipe(new ItemElectronicCircuit(),
				new ItemStack[] { new ItemStack(new ItemCopperCable(), 3), new ItemStack(new ItemIronIngot(), 1) }));

		addCraftingRecipe(new CraftingRecipe(new ItemIronCog(),
				new ItemStack[] { new ItemStack(new ItemIronIngot(), 2) }));

		addCraftingRecipe(new CraftingRecipe(new ItemLowTierEngine(),
				new ItemStack[] { new ItemStack(new ItemIronCog(), 2), new ItemStack(new ItemElectronicCircuit(), 1), new ItemStack(new ItemCopperCable(), 2), new ItemStack(new ItemIronIngot(), 4) }));

		addCraftingRecipe(new CraftingRecipe(new ItemLowTierMiningRobot(),
				new ItemStack[] { new ItemStack(new ItemLowTierEngine(), 1), new ItemStack(new ItemIronPickaxe(), 1), new ItemStack(new ItemElectronicCircuit(), 2), new ItemStack(new ItemIronIngot(), 5)}));
	}

	public static CraftingRecipe addCraftingRecipe(CraftingRecipe recipe) {
		recipes.add(recipe);
		return recipe;
	}

	public static CraftingRecipe[] getRecipes() {
		return recipes.toArray(new CraftingRecipe[recipes.size()]);
	}

	public static Item[] getItems() {
		Item[] items = new Item[recipes.size()];

		for (int i = 0; i < recipes.size(); i++) {
			items[i] = recipes.get(i).item;
		}

		return items;
	}
}
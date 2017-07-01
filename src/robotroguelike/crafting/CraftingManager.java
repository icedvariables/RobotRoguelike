package robotroguelike.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import robotroguelike.items.Item;
import robotroguelike.items.ItemIronIngot;
import robotroguelike.items.ItemStack;
import robotroguelike.items.ItemStone;
import robotroguelike.items.ItemStoneHandle;
import robotroguelike.items.ItemTest;

public class CraftingManager {
	private static final List<CraftingRecipe> recipes = new ArrayList<CraftingRecipe>();
	
	public static void addAllCraftingRecipes(){
		addCraftingRecipe(new CraftingRecipe(new ItemStoneHandle(),
				new ItemStack[]{new ItemStack(new ItemStone(), 3)}));
		addCraftingRecipe(new CraftingRecipe(new ItemIronPickaxe(),
				new ItemStack[]{new ItemStack(new ItemStoneHandle(), 1), new ItemStack(new ItemIronIngot(), 5)}));
	}

	public static CraftingRecipe addCraftingRecipe(CraftingRecipe recipe){
		recipes.add(recipe);
		return recipe;
	}
	
	public static CraftingRecipe[] getRecipes(){
		return recipes.toArray(new CraftingRecipe[recipes.size()]);
	}
	
	public static Item[] getItems(){
		Item[] items = new Item[recipes.size()];

		for(int i = 0; i < recipes.size(); i++){
			items[i] = recipes.get(i).item;
		}
		
		return items;
	}
}
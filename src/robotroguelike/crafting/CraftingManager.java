package robotroguelike.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import robotroguelike.items.Item;
import robotroguelike.items.ItemIronIngot;
import robotroguelike.items.ItemStack;
import robotroguelike.items.ItemTest;

public class CraftingManager {
	private static final List<CraftingRecipe> recipes = new ArrayList<CraftingRecipe>();
	
	public static void addAllCraftingRecipes(){
		addCraftingRecipe(new CraftingRecipe(new ItemTest(), new ItemStack[]{new ItemStack(new ItemIronIngot(), 2)}));
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
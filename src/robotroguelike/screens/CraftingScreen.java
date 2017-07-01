package robotroguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import robotroguelike.crafting.CraftingManager;
import robotroguelike.crafting.CraftingRecipe;
import robotroguelike.game.Game;
import robotroguelike.game.Inventory;
import robotroguelike.items.Item;
import robotroguelike.items.ItemStack;
import asciiPanel.AsciiPanel;

public class CraftingScreen implements Screen {
	private Screen returnScreen;
	private Inventory inventory;
	
	private int selectorIndex = 0;
	
	private final int OFFSET_X = 4, OFFSET_Y = 3, INGREDIENTS_OFFSET = 25, INVENTORY_OFFSET = Game.HEIGHT - 3;
	
	public CraftingScreen(Screen returnScreen, Inventory inventory){
		this.returnScreen = returnScreen;
		this.inventory = inventory;
	}

	public void display(AsciiPanel terminal){
		terminal.write("Crafting", OFFSET_X, OFFSET_Y);
		terminal.write("--------", OFFSET_X, OFFSET_Y + 1);
		
		displayCraftingList(terminal);
	}
	
	public void displayCraftingList(AsciiPanel terminal){
		CraftingRecipe[] recipes = CraftingManager.getRecipes();
		
		for(int i = 0; i < recipes.length; i++){
			Item item = recipes[i].item;
			ItemStack[] ingredients = recipes[i].ingredients;

			terminal.write(item.getName(), OFFSET_X + 3, OFFSET_Y + 3 + i, item.getTier().color);
			terminal.write(generateIngredientsString(ingredients), INGREDIENTS_OFFSET, OFFSET_Y + 3 + i);
		}

		// Selector:
		if(recipes.length > 0)
			terminal.write((char)223, OFFSET_X, OFFSET_Y + 3 + selectorIndex, Color.WHITE);
		
		// Mini inventory:
		terminal.write("Inventory: " + inventory.toString(), OFFSET_X, INVENTORY_OFFSET);
	}

	public boolean craftItem(int index){
		return inventory.craft(CraftingManager.getRecipes()[index]);
	}

	private String generateIngredientsString(ItemStack[] ingredients){
		String str = "";
		for(int i = 0; i < ingredients.length; i++){
			str += ingredients[i].toString();
			str += (i + 1) == ingredients.length ? "." : ", "; // Add commas to separate items in the list.
		}
		return str;
	}

	public Screen respondToInput(KeyEvent key){
		switch(key.getKeyCode()){
		case KeyEvent.VK_ESCAPE:
		case KeyEvent.VK_C: return returnScreen;
		case KeyEvent.VK_DOWN:
			if(selectorIndex + 1 < inventory.size())
				selectorIndex++;
			break;
		case KeyEvent.VK_UP:
			if(selectorIndex > 0)
				selectorIndex--;
			break;
		case KeyEvent.VK_ENTER:
			craftItem(selectorIndex);
			break;
		}

		return this;
	}
}
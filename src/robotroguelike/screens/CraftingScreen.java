package robotroguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import robotroguelike.crafting.CraftingManager;
import robotroguelike.crafting.CraftingRecipe;
import robotroguelike.game.Game;
import robotroguelike.game.GraphicsEngine;
import robotroguelike.game.Inventory;
import robotroguelike.items.Item;
import robotroguelike.items.ItemStack;

public class CraftingScreen implements Screen {
	private Screen returnScreen;
	private Inventory inventory;

	private int selectorIndex = 0;

	private final int OFFSET_X = 4, OFFSET_Y = 3, INGREDIENTS_OFFSET = 25, INVENTORY_OFFSET = Game.HEIGHT - 3;

	public CraftingScreen(Screen returnScreen, Inventory inventory) {
		this.returnScreen = returnScreen;
		this.inventory = inventory;
	}

	@Override
	public void display(GraphicsEngine graphics) {
		graphics.drawText("Crafting", OFFSET_X, OFFSET_Y);
		graphics.drawText("--------", OFFSET_X, OFFSET_Y + 1);

		displayCraftingList(graphics);
	}

	public void displayCraftingList(GraphicsEngine graphics) {
		CraftingRecipe[] recipes = CraftingManager.getRecipes();

		for (int i = 0; i < recipes.length; i++) {
			Item item = recipes[i].item;
			ItemStack[] ingredients = recipes[i].ingredients;

			graphics.drawText(item.getName(), OFFSET_X + 3, OFFSET_Y + 3 + i, item.getTier().color);
			graphics.drawText(generateIngredientsString(ingredients), OFFSET_X + 3 + INGREDIENTS_OFFSET, OFFSET_Y + 3 + i);
		}

		// Selector:
		if (recipes.length > 0)
			graphics.drawChar((char) 223, OFFSET_X, OFFSET_Y + 3 + selectorIndex, Color.WHITE);

		// Mini inventory:
		String inventoryStr = "Inventory: " + inventory.toString();
		if(inventoryStr.length() > Game.WIDTH)
			graphics.drawText(inventoryStr.substring(0, Game.WIDTH - 4 - OFFSET_X) + "...", OFFSET_X, INVENTORY_OFFSET);
		else
			graphics.drawText(inventoryStr, OFFSET_X, INVENTORY_OFFSET);
	}

	public boolean craftItem(int index) {
		return inventory.craft(CraftingManager.getRecipes()[index]);
	}

	private String generateIngredientsString(ItemStack[] ingredients) {
		String str = "";
		for (int i = 0; i < ingredients.length; i++) {
			str += ingredients[i].toString();
			str += (i + 1) == ingredients.length ? "." : ", "; // Add commas to separate items in the list.
		}
		return str;
	}

	@Override
	public Screen respondToInput(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
		case KeyEvent.VK_C:
			return returnScreen;
		case KeyEvent.VK_DOWN:
			if (selectorIndex + 1 < CraftingManager.getRecipes().length)
				selectorIndex++;
			break;
		case KeyEvent.VK_UP:
			if (selectorIndex > 0)
				selectorIndex--;
			break;
		case KeyEvent.VK_ENTER:
			craftItem(selectorIndex);
			break;
		}

		return this;
	}
}
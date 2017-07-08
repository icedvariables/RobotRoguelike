package robotroguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import robotroguelike.game.Game;
import robotroguelike.game.GraphicsEngine;
import robotroguelike.game.Inventory;
import robotroguelike.items.Item;
import robotroguelike.items.ItemStack;

public class InventoryScreen implements Screen {
	private Screen returnScreen;
	private Inventory inventory;
	private final String ownerName;

	private int selectorIndex = 0;

	private final int OFFSET_X = 4, OFFSET_Y = 3, DESCRIPTION_OFFSET = 25, EQUIPPED_OFFSET = Game.WIDTH - 13,
			SELECTED_OFFSET = Game.WIDTH - 25;

	public InventoryScreen(Screen returnScreen, Inventory inventory, String ownerName) {
		this.returnScreen = returnScreen;
		this.inventory = inventory;
		this.ownerName = ownerName;
		if(inventory.getEquippedItemStack() != null)
			selectorIndex = inventory.getEquippedItemIndex();
	}

	@Override
	public void display(GraphicsEngine graphics) {
		graphics.drawText(ownerName + "'s Inventory", OFFSET_X, OFFSET_Y);

		// This just generates a bunch '-' to create an underline for the title.
		char[] underline = new char[ownerName.length() + 12];
		Arrays.fill(underline, '-');
		graphics.drawText(new String(underline), OFFSET_X, OFFSET_Y + 1);

		displayItems(graphics);
	}

	private void displayItems(GraphicsEngine graphics) {
		ItemStack[] items = inventory.getItems();

		for (int i = 0; i < items.length; i++) {
			Item itm = items[i].getItem();

			graphics.drawText(items[i].getQuantity() + " x ", OFFSET_X + 3, OFFSET_Y + 3 + i);
			graphics.drawText(itm.getName(), OFFSET_X + 8, OFFSET_Y + 3 + i, itm.getTier().color);
			graphics.drawText(itm.getDescription(), OFFSET_X + 8 + DESCRIPTION_OFFSET, OFFSET_Y + 3 + i);

			if (items[i] == inventory.getEquippedItemStack())
				graphics.drawText("(equipped)", EQUIPPED_OFFSET, OFFSET_Y + 3 + i, Color.RED);
			if (items[i].selectedInInventory)
				graphics.drawText("(selected)", SELECTED_OFFSET, OFFSET_Y + 3 + i, Color.GREEN);
		}

		// Selector:
		if (!inventory.isEmpty())
			graphics.drawChar((char) 223, OFFSET_X, OFFSET_Y + 3 + selectorIndex, Color.WHITE);
	}

	@Override
	public Screen respondToInput(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
		case KeyEvent.VK_I:
			return returnScreen;
		case KeyEvent.VK_DOWN:
			if (selectorIndex + 1 < inventory.size())
				selectorIndex++;
			break;
		case KeyEvent.VK_UP:
			if (selectorIndex > 0)
				selectorIndex--;
			break;
		case KeyEvent.VK_E:
			inventory.equipItem(selectorIndex);
			break;
		case KeyEvent.VK_ENTER:
			inventory.toggleItemSelection(selectorIndex);
			break;
		}

		return this;
	}
}

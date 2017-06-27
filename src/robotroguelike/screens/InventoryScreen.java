package robotroguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import robotroguelike.game.Inventory;
import robotroguelike.items.Item;

public class InventoryScreen implements Screen {
	private Screen returnScreen;
	private Inventory inventory;
	private final int OFFSET_X = 2, OFFSET_Y = 3, DESCRIPTION_OFFSET = 25;

	public InventoryScreen(Screen returnScreen, Inventory inventory){
		this.returnScreen = returnScreen;
		this.inventory = inventory;
	}

	public void display(AsciiPanel terminal){
		terminal.write("Inventory", OFFSET_X, OFFSET_Y, Color.WHITE);
		terminal.write("---------", OFFSET_X, OFFSET_Y + 1, Color.WHITE);

		displayItems(terminal);
	}

	private void displayItems(AsciiPanel terminal){
		Item[] items = inventory.getItems();

		for(int i = 0; i < items.length; i++){
			terminal.write(items[i].getName(), OFFSET_X, OFFSET_Y + 3 + i, items[i].getTier().color);
			terminal.write(items[i].getDescription(), OFFSET_X + DESCRIPTION_OFFSET, OFFSET_Y + 3 + i);
		}
	}

	public Screen respondToInput(KeyEvent key){
		if(key.getKeyCode() == KeyEvent.VK_I)
			return returnScreen;
		else
			return this;
	}
}

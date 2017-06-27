package robotroguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import robotroguelike.game.Inventory;
import robotroguelike.items.Item;

public class InventoryScreen implements Screen {
	private Screen returnScreen;
	private Inventory inventory;
	
	private int selectorIndex = 0;
	
	private final int OFFSET_X = 4, OFFSET_Y = 3, DESCRIPTION_OFFSET = 25;

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
			terminal.write(items[i].getName(), OFFSET_X + 3, OFFSET_Y + 3 + i, items[i].getTier().color);
			terminal.write(items[i].getDescription(), OFFSET_X + 3 + DESCRIPTION_OFFSET, OFFSET_Y + 3 + i);
		}
		
		// Selector:
		terminal.write((char)223, OFFSET_X, OFFSET_Y + 3 + selectorIndex, Color.WHITE);
		
	}

	public Screen respondToInput(KeyEvent key){
		switch(key.getKeyCode()){
		case KeyEvent.VK_I: return returnScreen;
		case KeyEvent.VK_DOWN:
			if(selectorIndex + 1 < inventory.size())
				selectorIndex++;
			break;
		case KeyEvent.VK_UP:
			if(selectorIndex > 0)
				selectorIndex--;
			break;
		}
		
		return this;
	}
}

package robotroguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;
import robotroguelike.game.Game;
import robotroguelike.game.Inventory;
import robotroguelike.items.Item;
import robotroguelike.items.ItemStack;

public class InventoryScreen implements Screen {
	private Screen returnScreen;
	private Inventory inventory;
	
	private int selectorIndex = 0;
	
	private final int OFFSET_X = 4, OFFSET_Y = 3, DESCRIPTION_OFFSET = 25, EQUIPPED_OFFSET = Game.WIDTH - 13, SELECTED_OFFSET = Game.WIDTH - 25;

	public InventoryScreen(Screen returnScreen, Inventory inventory){
		this.returnScreen = returnScreen;
		this.inventory = inventory;
	}

	public void display(AsciiPanel terminal){
		terminal.write("Inventory", OFFSET_X, OFFSET_Y);
		terminal.write("---------", OFFSET_X, OFFSET_Y + 1);

		displayItems(terminal);
	}

	private void displayItems(AsciiPanel terminal){
		ItemStack[] items = inventory.getItems();

		for(int i = 0; i < items.length; i++){
			Item itm = items[i].getItem();

			terminal.write(items[i].getQuantity() + " x ", OFFSET_X + 3, OFFSET_Y + 3 + i);
			terminal.write(itm.getName(), OFFSET_X + 8, OFFSET_Y + 3 + i, itm.getTier().color);
			terminal.write(itm.getDescription(), OFFSET_X + 8 + DESCRIPTION_OFFSET, OFFSET_Y + 3 + i);
			
			if(items[i] == inventory.getEquippedItemStack())
				terminal.write("(equipped)", EQUIPPED_OFFSET, OFFSET_Y + 3 + i, Color.RED);
			if(items[i].selectedInInventory)
				terminal.write("(selected)", SELECTED_OFFSET, OFFSET_Y + 3 + i, Color.GREEN);
		}
		
		// Selector:
		if(!inventory.isEmpty())
			terminal.write((char)223, OFFSET_X, OFFSET_Y + 3 + selectorIndex, Color.WHITE);
	}

	public Screen respondToInput(KeyEvent key){
		switch(key.getKeyCode()){
		case KeyEvent.VK_ESCAPE:
		case KeyEvent.VK_I: return returnScreen;
		case KeyEvent.VK_DOWN:
			if(selectorIndex + 1 < inventory.size())
				selectorIndex++;
			break;
		case KeyEvent.VK_UP:
			if(selectorIndex > 0)
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

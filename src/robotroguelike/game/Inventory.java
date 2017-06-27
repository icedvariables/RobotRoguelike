package robotroguelike.game;

import java.util.ArrayList;

import robotroguelike.items.Item;

public class Inventory {
	private ArrayList<Item> items;
	private int inventoryIndex; // The index for the currently equipped item.
	
	public Inventory(){
		// TODO: Make inventory have a maximum size.
		items = new ArrayList<Item>();
	}
	
	public Item getItemAt(int index){
		return items.get(index);
	}
	
	public Item[] getItems(){
		return items.toArray(new Item[items.size()]);
	}
	
	public void giveItem(Item i){
		items.add(i);
	}
	
	public Item getEquippedItem(){
		return items.get(inventoryIndex);
	}
	
	public int getEquippedItemIndex(){
		return inventoryIndex;
	}
	
	public boolean equipItem(int i){
		if(i < items.size()){
			inventoryIndex = i;
			return true;
		}
		return false;
	}
	
	public int size(){
		return items.size();
	}
}

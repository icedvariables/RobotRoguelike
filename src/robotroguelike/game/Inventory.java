package robotroguelike.game;

import java.util.ArrayList;
import java.util.List;

import robotroguelike.items.Item;
import robotroguelike.items.ItemStack;

public class Inventory {
	public static final int NO_EQUIPPED_ITEM = -1;

	private List<ItemStack> items;
	private int equippedItemIndex = NO_EQUIPPED_ITEM; // The index for the currently equipped item.
	
	public Inventory(){
		// TODO: Make inventory have a maximum size.
		items = new ArrayList<ItemStack>();
	}
	
	public ItemStack getItemStackAt(int index){
		return items.get(index);
	}
	
	public ItemStack[] getItems(){
		return items.toArray(new ItemStack[items.size()]);
	}
	
	public void giveItem(Item itm){
		for(int i = 0; i < size(); i++){
			if(items.get(i).getItem().id == itm.id){
				items.get(i).increaseQuantityByOne();
				return;
			}
		}
		// If no existing stacks to increase the quantity of then make a new stack.
		items.add(new ItemStack(itm, 1));
	}
	
	public Item getEquippedItem(){
		if(equippedItemIndex != NO_EQUIPPED_ITEM)
			return items.get(equippedItemIndex).getItem();
		
		return null;
	}
	
	public int getEquippedItemIndex(){
		return equippedItemIndex;
	}
	
	public ItemStack[] getSelectedItems(){
		List<ItemStack> selectedItems = new ArrayList<ItemStack>();
		
		for(int i = 0; i < size(); i++){
			if(items.get(i).selectedInInventory)
				selectedItems.add(items.get(i));
		}
		
		return selectedItems.toArray(new ItemStack[selectedItems.size()]);
	}
	
	public boolean equipItem(int i){
		if(i < items.size() && i > 0 && items.get(i).getItem().isEquippable()){
			equippedItemIndex = i;
			return true;
		}
		return false;
	}
	
	public void toggleItemSelection(int i){
		items.get(i).selectedInInventory = !items.get(i).selectedInInventory;
	}
	
	public void selectItem(int i){
		items.get(i).selectedInInventory = true;
	}
	
	public void deselectItem(int i){
		items.get(i).selectedInInventory = false;
	}
	
	public int size(){
		return items.size();
	}
	
	public boolean isEmpty(){
		return items.size() == 0;
	}
}

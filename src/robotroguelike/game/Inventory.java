package robotroguelike.game;

import java.util.ArrayList;

import robotroguelike.items.Item;
import robotroguelike.items.ItemStack;

public class Inventory {
	public static final int NO_EQUIPPED_ITEM = -1;

	private ArrayList<ItemStack> items;
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
	
	public boolean equipItem(int i){
		if(i < items.size() && i > 0 && items.get(i).getItem().isEquippable()){
			equippedItemIndex = i;
			return true;
		}
		return false;
	}
	
	public int size(){
		return items.size();
	}
	
	public boolean isEmpty(){
		return items.size() == 0;
	}
}

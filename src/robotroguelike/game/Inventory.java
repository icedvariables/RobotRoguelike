package robotroguelike.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import robotroguelike.crafting.CraftingRecipe;
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
	
	public boolean craft(CraftingRecipe recipe){
		Item item = recipe.item;
		ItemStack[] ingredients = recipe.ingredients;

		boolean success = consumeItems(ingredients);

		if(success)
			giveItem(item);

		return success;
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
	
	public void giveItemStack(ItemStack stack){
		// TODO: Add new stacks to existing stacks rather than having them separate.
		items.add(stack);
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
	
	public boolean consumeItems(ItemStack[] stacks){
		if(!hasItems(stacks))
			return false;

		List<Integer> stacksToBeRemovedIndexes = new ArrayList<Integer>();

		for(int i = 0; i < stacks.length; i++){
			for(int j = 0; j < items.size(); j++){
				if(items.get(j).getItem().id == stacks[i].getItem().id){
					boolean success = items.get(j).decreaseQuantityBy(stacks[i].getQuantity());
					
					if(!success)
						stacksToBeRemovedIndexes.add(j);
				}
			}
		}

		// Remove any stacks that now have a quantity of 0.
		// Put the indexes of these stacks in reverse order so that by removing them,
		// the position of the next stack to be removed is not shifted.
		Collections.sort(stacksToBeRemovedIndexes);
		Collections.reverse(stacksToBeRemovedIndexes);

		for(int i = 0; i < stacksToBeRemovedIndexes.size(); i++){
			items.remove(stacksToBeRemovedIndexes.get(i).intValue()); // .intValue() is needed to convert from Integer to int.
		}

		return true;
	}
	
	public boolean hasItems(ItemStack[] array){
		for(int i = 0; i < array.length; i++){
			if(!hasItemStack(array[i]))
				return false;
		}
		return true;
	}
	
	public boolean hasItemStack(ItemStack stack){
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getItem().id == stack.getItem().id && items.get(i).getQuantity() >= stack.getQuantity())
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

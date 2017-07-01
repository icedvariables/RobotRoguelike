package robotroguelike.items;

import java.io.Serializable;

public class ItemStack implements Serializable {
	private static final long serialVersionUID = 5103036811801216338L;

	public boolean selectedInInventory = false;
	
	private Item item;
	private int quantity = 1;
	
	public ItemStack(Item item, int quantity){
		this.item = item;
		this.quantity = quantity;
	}
	
	public Item getItem(){
		return item;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public boolean setQuantity(int q){
		if(q > 0){
			quantity = q;
			return true;
		}
		return false; // Failed.
	}
	
	public boolean decreaseQuantityBy(int x){
		return setQuantity(quantity - x);
	}
	
	public boolean increaseQuantityByOne(){
		return setQuantity(quantity + 1);
	}
	
	@Override
	public String toString(){
		return quantity + " x " + item.getName();
	}
}
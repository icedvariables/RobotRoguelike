package robotroguelike.items;

public class ItemStack {
	public final int maxQuantity;
	public boolean selectedInInventory = false;
	
	private Item item;
	private int quantity = 1;
	
	public ItemStack(Item item, int quantity){
		this.item = item;
		this.quantity = quantity;
		this.maxQuantity = item.maxQuantity;
	}
	
	public Item getItem(){
		return item;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public boolean setQuantity(int q){
		if(q < maxQuantity && q > 0){
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
package robotroguelike.items;

public class Item {
	public final int maxQuantity;
	public final int id;

	private String name = "Item";
	private String description = "An item.";
	private Tier tier = Tier.LOW;
	private boolean equippable;
	
	// TODO: Implement durability for items.
	
	/*public Item(){
		id = 0x404;
		maxQuantity = 50;
	}*/
	
	public Item(int id, String name, String description, Tier tier, int maxQuantity, boolean equippable){
		this.id = id;
		this.name = name;
		this.description = description;
		this.tier = tier;
		this.maxQuantity = maxQuantity;
		this.equippable = equippable;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public Tier getTier(){
		return tier;
	}
	
	public boolean isEquippable(){
		return equippable;
	}
}

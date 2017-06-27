package robotroguelike.items;

public class Item {	
	public int quantity = 1;
	private String name = "Item";
	private String description = "An item.";
	private Tier tier = Tier.NORMAL;
	
	// TODO: Implement durability for items.
	
	public Item(){}
	
	public Item(String name, String description, Tier tier){
		this.name = name;
		this.description = description;
		this.tier = tier;
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
}

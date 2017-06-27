package robotroguelike.items;

public class Item {	
	private String name = "Item";
	private String description = "An item.";
	private int tier = Tier.NORMAL;
	
	// TODO: Implement durability for items.
	
	public Item(){}
	
	public Item(String name, String description){
		this.name = name;
		this.description = description;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getTier(){
		return tier;
	}
}

package robotroguelike.items;

import java.io.Serializable;

import robotroguelike.entities.Entity;
import robotroguelike.map.Map;
import robotroguelike.tiles.Tile;

public class Item implements Serializable {
	private static final long serialVersionUID = -5818639862963916760L;

	public final ItemId id;

	private String name = "Item";
	private String description = "An item.";
	private Tier tier = Tier.LOW;
	private boolean equippable;
	private boolean diggingTool = false;

	// TODO: Implement durability for items.

	public Item(ItemId id, String name, String description, Tier tier, boolean equippable, boolean diggingTool) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.tier = tier;
		this.equippable = equippable;
		this.diggingTool = diggingTool;
	}

	public Tile getTileToPlace() {
		return null;
	}

	public Entity getEntityToPlace(Map map) {
		return null;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Tier getTier() {
		return tier;
	}

	public boolean isEquippable() {
		return equippable;
	}

	public boolean isDiggingTool() {
		return diggingTool;
	}
}

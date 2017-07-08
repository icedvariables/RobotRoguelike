package robotroguelike.entities;

import robotroguelike.items.Tier;
import robotroguelike.map.Map;

public class IronChest extends Entity {
	public IronChest(Map map) {
		super("Iron Chest", 'C', Tier.LOW.color, map);
	}
}

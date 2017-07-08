package robotroguelike.entities;

import robotroguelike.items.ItemIronPickaxe;
import robotroguelike.items.Tier;
import robotroguelike.map.Map;

public class LowTierMiningRobot extends Robot {
	public LowTierMiningRobot(Map map) {
		super("Low Tier Mining Robot", 'R', Tier.LOW.color, map);
		inventory.giveItem(new ItemIronPickaxe());
		inventory.equipItem(0);
	}
}

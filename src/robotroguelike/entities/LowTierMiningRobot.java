package robotroguelike.entities;

import java.awt.event.KeyEvent;

import robotroguelike.items.ItemIronPickaxe;
import robotroguelike.items.Tier;
import robotroguelike.map.Map;

public class LowTierMiningRobot extends Robot {
	public LowTierMiningRobot(Map map) {
		super('R', Tier.LOW.color, map);
		inventory.giveItem(new ItemIronPickaxe());
		inventory.equipItem(0);
	}

	@Override
	public void update(KeyEvent key) {
		boolean success = moveBy(0, 1);
		if(!success)
			dig(x, y + 1);
	}
}

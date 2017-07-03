package robotroguelike.entities;

import robotroguelike.items.Tier;
import robotroguelike.map.Map;

public class LowTierMiningRobot extends Entity {
	public LowTierMiningRobot(Map map) {
		super('R', Tier.LOW.color, map);
	}
}

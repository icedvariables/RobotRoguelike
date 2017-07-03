package robotroguelike.creatures;

import robotroguelike.items.Tier;
import robotroguelike.map.Map;

public class LowTierMiningRobot extends Creature {
	public LowTierMiningRobot(Map map) {
		super('R', Tier.LOW.color, map);
	}
}

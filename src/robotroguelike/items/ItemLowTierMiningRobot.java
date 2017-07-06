package robotroguelike.items;

import robotroguelike.entities.Entity;
import robotroguelike.entities.LowTierMiningRobot;
import robotroguelike.map.Map;

public class ItemLowTierMiningRobot extends Item {
	public ItemLowTierMiningRobot() {
		super(ItemId.LOW_TIER_MINING_ROBOT, "Low Tier Mining Robot", "A low tier level robot designed to mine rocks.", Tier.LOW, true);
	}

	@Override
	public Entity getEntityToPlace(Map map) {
		return new LowTierMiningRobot(map);
	}
}

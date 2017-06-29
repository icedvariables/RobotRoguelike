package robotroguelike.items;

import robotroguelike.game.Game;

public class ItemIronIngot extends Item {
	public ItemIronIngot(){
		super(2, "Iron Ingot", "A lump of pure iron.", Tier.MEDIUM, Game.DEFAULT_ITEM_STACK_MAX_QUANTITY, false);
	}
}
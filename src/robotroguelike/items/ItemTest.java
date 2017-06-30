package robotroguelike.items;

import robotroguelike.game.Game;

public class ItemTest extends Item {
	public ItemTest(){
		super(100, "Test Item", "You shouldn't have this...", Tier.HIGH, Game.DEFAULT_ITEM_STACK_MAX_QUANTITY, false);
	}
}
package robotroguelike.entities;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import robotroguelike.game.GlyphColor;
import robotroguelike.game.Inventory;
import robotroguelike.items.Item;
import robotroguelike.items.ItemStack;
import robotroguelike.map.Map;
import robotroguelike.tiles.Tile;

public class Entity extends GlyphColor implements Serializable {
	private static final long serialVersionUID = 516739414373036469L;

	public Inventory inventory;
	public int[] direction = { 0, 0 };

	protected int x, y;

	private Map map;

	public Entity(char glyph, Color color, Map map) {
		super(glyph, color);
		this.map = map;
		this.inventory = new Inventory();
	}

	public void update(KeyEvent key) {}

	public Item dig(int wx, int wy) {
		Item returnItem = null;
		if (inventory.getEquippedItemStack() != null)
			returnItem = map.dig(wx, wy, inventory.getEquippedItemStack().getItem());
		if (returnItem != null)
			inventory.giveItem(returnItem);
		return returnItem;
	}

	public boolean placeEquippedItem() {
		boolean success = false;

		int placeX = getX() + direction[0];
		int placeY = getY() + direction[1];

		ItemStack equippedStack = inventory.getEquippedItemStack();
		Entity entityToPlace = equippedStack.getItem().getEntityToPlace(map);
		Tile tileToPlace = equippedStack.getItem().getTileToPlace();

		if(equippedStack.getQuantity() > 0) {
			equippedStack.decreaseQuantityBy(1);
			success = map.placeEntity(placeX, placeY, entityToPlace); // First, attempt to place the relevant entity.
			if(!success)
				success = map.placeTile(placeX, placeY, tileToPlace); // If that fails, place the item's equivalent tile.
		}

		return success;
	}

	public boolean moveBy(int mx, int my) {
		// TODO: Make this method set the creature's direction.

		Tile t = map.tileAt(x + mx, y + my);

		if (t == null || t.canCreaturesWalkOver()) {
			x += mx;
			y += my;
			return true;
		}

		return false;
	}

	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

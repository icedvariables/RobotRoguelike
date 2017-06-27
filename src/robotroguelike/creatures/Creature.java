package robotroguelike.creatures;

import java.awt.Color;

import robotroguelike.game.Inventory;
import robotroguelike.map.Map;
import robotroguelike.tiles.Tile;

public class Creature extends Tile {
	public Inventory inventory;
	
	public int x, y;
	
	private Map map;
	private CreatureAi ai;

	public Creature(char glyph, Color color, Map map){
		super(glyph, color);
		this.map = map;
		this.inventory = new Inventory();
	}
	
	public void dig(int wx, int wy){
		map.dig(wx, wy, inventory.getEquippedItem());
	}
	
	public void moveBy(int mx, int my){
		ai.onEnter(x + mx, y + my, map.tileAt(x + mx, y + my));
	}
	
	protected void setCreatureAi(CreatureAi ai){
		this.ai = ai;
	}
}

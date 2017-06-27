package robotroguelike.tiles;

import java.awt.Color;
import java.util.ArrayList;

import robotroguelike.entities.Entity;
import robotroguelike.game.Inventory;
import robotroguelike.map.Map;

public class Creature extends Tile {
	public Inventory inventory;
	
	private Map map;
	private CreatureAi ai;

	public Creature(char glyph, Color color, Map map, CreatureAi ai){
		super(glyph, color);
		this.map = map;
		this.ai = ai;
		this.inventory = new Inventory();
	}
	
	public void dig(int wx, int wy){
		map.dig(wx, wy, this);
	}
}

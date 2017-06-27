package robotroguelike.creatures;

import java.awt.Color;

import robotroguelike.game.Inventory;
import robotroguelike.map.Map;
import robotroguelike.tiles.Tile;

public class Creature extends Tile {
	public Inventory inventory;
	
	protected int x, y;
	
	private Map map;

	public Creature(char glyph, Color color, Map map){
		super(glyph, color);
		this.map = map;
		this.inventory = new Inventory();
	}
	
	public void dig(int wx, int wy){
		map.dig(wx, wy, inventory.getEquippedItem());
	}
	
	public boolean moveBy(int mx, int my){
		Tile t = map.tileAt(x + mx, y + my);
		if(t.canWalkOver){
			x += mx;
			y += my;
		}
		return t.canWalkOver;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}

package robotroguelike.creatures;

import java.awt.Color;

import robotroguelike.game.Inventory;
import robotroguelike.items.Item;
import robotroguelike.map.Map;
import robotroguelike.tiles.Tile;

public class Creature extends Tile {
	public Inventory inventory;
	public int[] direction = {0, 0};
	
	protected int x, y;
	
	private Map map;

	public Creature(char glyph, Color color, Map map){
		super(glyph, color);
		this.map = map;
		this.inventory = new Inventory();
	}
	
	public void dig(int wx, int wy){
		Item returnItem = map.dig(wx, wy, inventory.getEquippedItem());
		inventory.giveItem(returnItem);
	}
	
	public boolean moveBy(int mx, int my){
		Tile t = map.tileAt(x + mx, y + my);
		
		if(t != null && t.canWalkOver){
			x += mx;
			y += my;
			return true;
		}
		
		return false;
	}
	
	/*public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}*/
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}

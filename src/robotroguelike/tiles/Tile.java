package robotroguelike.tiles;

import java.awt.Color;

import robotroguelike.items.Item;
import robotroguelike.map.Map;

public class Tile {
	public char glyph;
	public Color color;
	
	protected Map map;
	private Item correspondingItem = new Item();
	private int tierRequiredToMine;
	
	public Tile(char glyph, Color color){
		this.glyph = glyph;
		this.color = color;
	}
	
	public boolean isDiggable(Item i){
		return i.getTier() >= tierRequiredToMine;
	}
	
	public Item returnOnDig(){
		return correspondingItem;
	}
	
	public void setCorrespondingItem(Item i){
		correspondingItem = i;
	}
}
package robotroguelike.tiles;

import java.awt.Color;

import robotroguelike.items.Item;
import robotroguelike.items.Tier;

public class Tile {
	public char glyph;
	public Color color;
	public boolean canWalkOver = true;

	private Item correspondingItem = new Item();
	private int tier = Tier.NORMAL;
	
	public Tile(char glyph, Color color){
		this.glyph = glyph;
		this.color = color;
	}
	
	public boolean isDiggable(int itemTier){
		return itemTier >= tier;
	}
	
	public Item returnOnDig(){
		return correspondingItem;
	}
	
	public void setCorrespondingItem(Item i){
		correspondingItem = i;
	}
}
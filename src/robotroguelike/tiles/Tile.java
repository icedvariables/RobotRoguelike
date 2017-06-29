package robotroguelike.tiles;

import java.awt.Color;

import robotroguelike.items.Item;
import robotroguelike.items.Tier;

public class Tile {
	public char glyph;
	public Color color;
	public boolean canWalkOver = true;

	private Item correspondingItem;
	private Tier tier = Tier.LOW;
	
	public Tile(char glyph, Color color){
		this.glyph = glyph;
		this.color = color;
	}
	
	public boolean isDiggable(Tier itemTier){
		return itemTier.number >= tier.number;
	}
	
	public Item returnOnDig(){
		return correspondingItem;
	}
	
	public void setCorrespondingItem(Item i){
		correspondingItem = i;
	}
}
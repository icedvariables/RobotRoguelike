package robotroguelike.tiles;

import java.awt.Color;
import java.io.Serializable;

import robotroguelike.items.Item;
import robotroguelike.items.Tier;

public class Tile implements Serializable {
	private static final long serialVersionUID = -268064312409560406L;

	public char glyph;
	public Color color;
	public boolean canWalkOver = true;

	private Tier tier = Tier.LOW;
	
	public Tile(char glyph, Color color){
		this.glyph = glyph;
		this.color = color;
	}
	
	public boolean isDiggableWith(Tier itemTier){
		return itemTier.number >= tier.number;
	}
	
	public Item returnOnDig(){
		return null;
	}
}
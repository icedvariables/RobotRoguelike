package robotroguelike.tiles;

import java.awt.Color;

import robotroguelike.game.GlyphColor;
import robotroguelike.items.Item;
import robotroguelike.items.Tier;

public class Tile extends GlyphColor {
	private static final long serialVersionUID = 3544605713148520921L;
	
	private Tier tier = Tier.LOW;
	private boolean walkOver = false;

	public Tile(char glyph, Color color, boolean walkOver) {
		super(glyph, color);
		this.walkOver = walkOver;
	}

	public boolean isDiggableWith(Tier itemTier) {
		return itemTier.number >= tier.number;
	}

	public Item returnOnDig() {
		return null;
	}
	
	public boolean canCreaturesWalkOver() {
		return walkOver;
	}
}

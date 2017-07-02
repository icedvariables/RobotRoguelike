package robotroguelike.game;

import java.awt.Color;
import java.io.Serializable;

public class GlyphColor implements Serializable {
	private static final long serialVersionUID = -268064312409560406L;

	public char glyph;
	public Color color;
	
	public GlyphColor(char glyph, Color color) {
		// TODO: Make selectors on menus use this class instead.
		
		this.glyph = glyph;
		this.color = color;
	}
}
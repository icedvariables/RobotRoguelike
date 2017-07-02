package robotroguelike.game;

import java.awt.Color;

public interface GraphicsEngine {
	public void clear();

	public void drawTile(GlyphColor tile, int x, int y);

	public void drawChar(char c, int x, int y, Color color);

	public void drawChar(char c, int x, int y);

	public void drawText(String text, int x, int y, Color color);

	public void drawText(String text, int x, int y);

	public void drawTextCenter(String text, int y, Color color);

	public void drawTextCenter(String text, int y);
}

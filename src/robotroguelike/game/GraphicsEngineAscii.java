package robotroguelike.game;

import java.awt.Color;

import asciiPanel.AsciiPanel;
import robotroguelike.tiles.Tile;

public class GraphicsEngineAscii implements GraphicsEngine {
	private AsciiPanel terminal;

	public GraphicsEngineAscii(AsciiPanel terminal) {
		this.terminal = terminal;
	}

	@Override
	public void clear() {
		terminal.clear();
	}

	@Override
	public void drawChar(char c, int x, int y, Color color) {
		terminal.write(c, x, y, color);
	}

	@Override
	public void drawChar(char c, int x, int y) {
		terminal.write(c, x, y);
	}

	@Override
	public void drawTile(Tile tile, int x, int y) {
		terminal.write(tile.glyph, x, y, tile.color);
	}

	@Override
	public void drawText(String text, int x, int y, Color color) {
		terminal.write(text, x, y, color);
	}

	@Override
	public void drawText(String text, int x, int y) {
		terminal.write(text, x, y);
	}

	@Override
	public void drawTextCenter(String text, int y, Color color) {
		terminal.writeCenter(text, y, color);
	}

	@Override
	public void drawTextCenter(String text, int y) {
		terminal.writeCenter(text, y);
	}
}

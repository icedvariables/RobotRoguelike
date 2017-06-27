package robotroguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import robotroguelike.game.Game;
import robotroguelike.map.Map;
import robotroguelike.map.SimpleMapBuilder;
import robotroguelike.tiles.Tile;
import asciiPanel.AsciiPanel;

public class GameScreen implements Screen {
	private Map map;
	private int centerX, centerY;
	
	public GameScreen(){
		map = new SimpleMapBuilder(200, 200).build();
	}
	
	public void display(AsciiPanel terminal){
		int left = getScrollX();
		int top = getScrollY();
		
		displayTiles(terminal, left, top);
		
		terminal.write('@', centerX - left, centerY - top, Color.YELLOW);
	}
	
	public Screen respondToInput(KeyEvent key){
		switch(key.getKeyCode()){
		case KeyEvent.VK_UP: scrollBy(0, -1); break;
		case KeyEvent.VK_DOWN: scrollBy(0, 1); break;
		case KeyEvent.VK_LEFT: scrollBy(-1, 0); break;
		case KeyEvent.VK_RIGHT: scrollBy(1, 0); break;
		}
		
		return this;
	}
	
	private void displayTiles(AsciiPanel terminal, int left, int top) {
	    for (int x = 0; x < Game.WIDTH; x++){
	        for (int y = 0; y < Game.HEIGHT; y++){
	            int wx = x + left;
	            int wy = y + top;
	            
	            Tile t = map.tileAt(wx, wy);
	            System.out.println(t.glyph);
	            terminal.write(t.glyph, x, y, t.color);
	        }
	    }
	}
	
	private void scrollBy(int mx, int my){
		centerX = Math.max(0, Math.min(centerX + mx, map.width - 1));
        centerY = Math.max(0, Math.min(centerY + my, map.height - 1));
	}
	
	public int getScrollX(){
	    return Math.max(0, Math.min(centerX - Game.WIDTH / 2, map.width - Game.WIDTH));
	}
	
	public int getScrollY(){
	    return Math.max(0, Math.min(centerY - Game.HEIGHT / 2, map.height - Game.HEIGHT));
	}
}

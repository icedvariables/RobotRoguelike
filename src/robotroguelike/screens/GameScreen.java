package robotroguelike.screens;

import java.awt.event.KeyEvent;

import robotroguelike.creatures.Creature;
import robotroguelike.creatures.Player;
import robotroguelike.game.Game;
import robotroguelike.map.Map;
import robotroguelike.map.SimpleMapBuilder;
import robotroguelike.tiles.Tile;
import asciiPanel.AsciiPanel;

public class GameScreen implements Screen {
	private Map map;
	private Player player;
	private int scrollX = 0, scrollY = 0;

	public GameScreen(){
		map = new SimpleMapBuilder(1000, 1000).build();
		player = new Player(map);
		player.x = Game.WIDTH / 2;
		player.y = Game.HEIGHT / 2;
		map.addCreature(player);
	}
	
	public void display(AsciiPanel terminal){
		displayTiles(terminal);
		displayCreatures(terminal);
	}
	
	public Screen respondToInput(KeyEvent key){
		switch(key.getKeyCode()){
		case KeyEvent.VK_UP: movePlayer(0, -1); break;
		case KeyEvent.VK_DOWN: movePlayer(0, 1); break;
		case KeyEvent.VK_LEFT: movePlayer(-1, 0); break;
		case KeyEvent.VK_RIGHT: movePlayer(1, 0); break;
		}
		
		return this;
	}
	
	private void movePlayer(int moveX, int moveY){
		if(player.x - scrollX == Game.WIDTH / 2)
			scrollBy(moveX, 0);
		if(player.y - scrollY == Game.HEIGHT / 2)
			scrollBy(0, moveY);
		player.x += moveX;
		player.y += moveY;
	}
	
	private void displayTiles(AsciiPanel terminal){
	    for (int x = 0; x < Game.WIDTH; x++){
	        for (int y = 0; y < Game.HEIGHT; y++){
	            int wx = x + scrollX;
	            int wy = y + scrollY;
	            
	            Tile t = map.tileAt(wx, wy);
	            terminal.write(t.glyph, x, y, t.color);
	        }
	    }
	}
	
	private void displayCreatures(AsciiPanel terminal){
		for(int i = 0; i < map.creatures.size(); i++){
			Creature c = map.creatures.get(i);
			terminal.write(c.glyph, c.x - scrollX, c.y - scrollY, c.color);
			System.out.println(c.x + "," + c.y);
		}
	}
	
	private void scrollBy(int mx, int my){
		scrollX = Math.max(0, Math.min(scrollX + mx, map.width - 1));
        scrollY = Math.max(0, Math.min(scrollY + my, map.height - 1));
	}
}

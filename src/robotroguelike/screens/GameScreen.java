package robotroguelike.screens;

import java.awt.event.KeyEvent;

import robotroguelike.crafting.CraftingManager;
import robotroguelike.creatures.Creature;
import robotroguelike.creatures.Player;
import robotroguelike.game.Game;
import robotroguelike.items.ItemIronIngot;
import robotroguelike.items.ItemStack;
import robotroguelike.map.Map;
import robotroguelike.map.SimpleMapBuilder;
import robotroguelike.tiles.Tile;
import asciiPanel.AsciiPanel;

public class GameScreen implements Screen {
	private Map map;
	private Player player;
	private int scrollX = 0, scrollY = 0;

	public GameScreen(){
		map = new SimpleMapBuilder(500, 500).build();
		CraftingManager.addAllCraftingRecipes();
		player = new Player(map);

		player.inventory.giveItemStack(new ItemStack(new ItemIronIngot(), 2));

		movePlayerAndScroll(map.width / 2, map.height / 2);
		map.addCreature(player);
	}
	
	public void display(AsciiPanel terminal){
		displayTiles(terminal);
		displayCreatures(terminal);
	}
	
	public Screen respondToInput(KeyEvent key){
		switch(key.getKeyCode()){
		case KeyEvent.VK_UP: movePlayerAndScroll(0, -1); break;
		case KeyEvent.VK_DOWN: movePlayerAndScroll(0, 1); break;
		case KeyEvent.VK_LEFT: movePlayerAndScroll(-1, 0); break;
		case KeyEvent.VK_RIGHT: movePlayerAndScroll(1, 0); break;
		case KeyEvent.VK_I: return new InventoryScreen(this, player.inventory);
		case KeyEvent.VK_C: return new CraftingScreen(this, player.inventory);
		}
		
		return this;
	}

	private void movePlayerAndScroll(int moveX, int moveY){
		player.moveBy(moveX, moveY);
		scrollX = player.getX() - (Game.WIDTH / 2);
		scrollY = player.getY() - (Game.HEIGHT / 2);
	}

	private void displayTiles(AsciiPanel terminal){
	    for (int x = 0; x < Game.WIDTH; x++){
	        for (int y = 0; y < Game.HEIGHT; y++){
	            int wx = x + scrollX;
	            int wy = y + scrollY;
	            
	            Tile t = map.tileAt(wx, wy);
	            if(t != null)
	            	terminal.write(t.glyph, x, y, t.color);
	        }
	    }
	}
	
	private void displayCreatures(AsciiPanel terminal){
		for(int i = 0; i < map.creatures.size(); i++){
			Creature c = map.creatures.get(i);
			
			int drawX = c.getX() - scrollX;
			int drawY = c.getY() - scrollY;
			
			if(drawX >= 0 && drawX < Game.WIDTH && drawY >= 0 && drawY < Game.HEIGHT)
				terminal.write(c.glyph, drawX, drawY, c.color);
		}
	}
}

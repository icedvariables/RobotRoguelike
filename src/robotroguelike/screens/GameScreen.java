package robotroguelike.screens;

import java.awt.event.KeyEvent;

import robotroguelike.entities.Entity;
import robotroguelike.entities.Player;
import robotroguelike.game.Game;
import robotroguelike.game.GlyphColor;
import robotroguelike.game.GraphicsEngine;
import robotroguelike.items.ItemCopperIngot;
import robotroguelike.items.ItemIronIngot;
import robotroguelike.items.ItemStack;
import robotroguelike.items.ItemStone;
import robotroguelike.map.Map;
import robotroguelike.map.MapManager;
import robotroguelike.map.OreMapBuilder;
import robotroguelike.tiles.TileCopperOre;
import robotroguelike.tiles.TileIronOre;
import robotroguelike.tiles.TileStone;

public class GameScreen implements Screen {
	public String infoString = "";

	private Map map;
	private Player player;
	private int scrollX = 0, scrollY = 0;

	public GameScreen(boolean buildMap) {
		if (buildMap) {
			buildNewMap();
		} else {
			loadExistingMap();
		}
	}

	private void buildNewMap() {
		map = new OreMapBuilder(500, 300).build();
		System.out.println("Map is: " + map.width + " x " + map.height);
		System.out.println("Stone in map: " + map.countAmountOfTile(new TileStone()));
		System.out.println("Iron ore in map: " + map.countAmountOfTile(new TileIronOre()));
		System.out.println("Copper ore in map: " + map.countAmountOfTile(new TileCopperOre()));

		player = new Player(map);

		player.inventory.giveItemStack(new ItemStack(new ItemStone(), 25));
		player.inventory.giveItemStack(new ItemStack(new ItemIronIngot(), 25));
		player.inventory.giveItemStack(new ItemStack(new ItemCopperIngot(), 25));

		player.setPosition(map.width / 2, map.height / 2);
		scroll(player.getX(), player.getY());
		map.addCreature(player);
	}

	private void loadExistingMap() {
		map = MapManager.loadMap("map");

		for (int i = 0; i < map.creatures.size(); i++) {
			if (map.creatures.get(i) instanceof Player)
				player = (Player) map.creatures.get(i);
		}
		infoString = "Loaded map from save file '" + map.name + MapManager.FILE_EXTENSION + "'.";
	}

	@Override
	public void display(GraphicsEngine graphics) {
		displayTiles(graphics);
		displayCreatures(graphics);
		graphics.drawText(infoString, 0, Game.HEIGHT - 1);
		graphics.drawText("Version: " + Game.VERSION, 0, 0);
	}

	@Override
	public Screen respondToInput(KeyEvent key) {
		for (int i = 0; i < map.creatures.size(); i++) {
			map.creatures.get(i).update(key);
		}

		scroll(player.getX(), player.getY());

		switch (key.getKeyCode()) {
		case KeyEvent.VK_I:
			return new InventoryScreen(this, player.inventory, "Player");
		case KeyEvent.VK_C:
			return new CraftingScreen(this, player.inventory);
		case KeyEvent.VK_SPACE:
			player.dig(player.getX() + player.direction[0], player.getY() + player.direction[1]);
			break;
		case KeyEvent.VK_S:
			MapManager.saveMap(map);
			infoString = "Saved map to save file '" + map.name + MapManager.FILE_EXTENSION + "'.";
			break;
		case KeyEvent.VK_P:
			player.placeEquippedItem();
			break;
		case KeyEvent.VK_E:
			Entity e = map.entityAt(player.getX() + player.direction[0], player.getY() + player.direction[1]);
			if(e != null)
				return new InventoryScreen(this, e.inventory, e.getName());
			break;
		}

		return this;
	}

	private void scroll(int x, int y) {
		scrollX = x - (Game.WIDTH / 2);
		scrollY = y - (Game.HEIGHT / 2);
	}

	private void displayTiles(GraphicsEngine graphics) {
		for (int x = 0; x < Game.WIDTH; x++) {
			for (int y = 0; y < Game.HEIGHT; y++) {
				int wx = x + scrollX;
				int wy = y + scrollY;

				GlyphColor t = map.tileAt(wx, wy);
				if (t != null)
					graphics.drawTile(t, x, y);
			}
		}
	}

	private void displayCreatures(GraphicsEngine graphics) {
		for (int i = 0; i < map.creatures.size(); i++) {
			Entity c = map.creatures.get(i);

			int drawX = c.getX() - scrollX;
			int drawY = c.getY() - scrollY;

			if (drawX >= 0 && drawX < Game.WIDTH && drawY >= 0 && drawY < Game.HEIGHT)
				graphics.drawTile(c, drawX, drawY);
		}
	}
}

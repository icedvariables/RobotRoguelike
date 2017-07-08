package robotroguelike.screens;

import java.awt.event.KeyEvent;

import robotroguelike.entities.Entity;
import robotroguelike.entities.Player;
import robotroguelike.game.Game;
import robotroguelike.game.GlyphColor;
import robotroguelike.game.GraphicsEngine;
import robotroguelike.items.ItemCopperIngot;
import robotroguelike.items.ItemIronIngot;
import robotroguelike.items.ItemLowTierMiningRobot;
import robotroguelike.items.ItemStack;
import robotroguelike.items.ItemStone;
import robotroguelike.logging.Log;
import robotroguelike.logging.Logger;
import robotroguelike.map.Map;
import robotroguelike.map.MapManager;
import robotroguelike.map.OreMapBuilder;
import robotroguelike.tiles.TileCopperOre;
import robotroguelike.tiles.TileIronOre;
import robotroguelike.tiles.TileStone;

public class GameScreen implements Screen {
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
		Logger.addLog("Built new " + map.width + "x" + map.height + " map.", Log.NORMAL);

		player = new Player(map);

		player.inventory.giveItemStack(new ItemStack(new ItemStone(), 25));
		player.inventory.giveItemStack(new ItemStack(new ItemIronIngot(), 25));
		player.inventory.giveItemStack(new ItemStack(new ItemCopperIngot(), 25));
		player.inventory.giveItemStack(new ItemStack(new ItemLowTierMiningRobot(), 5));

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
		Logger.addLog("Loaded map from save file '" + map.name + MapManager.FILE_EXTENSION + "'.", Log.NORMAL);
	}

	@Override
	public void display(GraphicsEngine graphics) {
		displayTiles(graphics);
		displayCreatures(graphics);
		Log log = Logger.getLatestLog();
		if(log != null)
			graphics.drawText(log.toString(), 0, Game.HEIGHT - 1, log.getColor());
		graphics.drawText("Version: " + Game.VERSION, 0, 0);
	}

	@Override
	public Screen respondToInput(KeyEvent key) {
		for (int i = 0; i < map.creatures.size(); i++) {
			map.creatures.get(i).update(key);
		}

		scroll(player.getX(), player.getY());

		Entity e;

		switch (key.getKeyCode()) {
		case KeyEvent.VK_I: // INVENTORY
			return new InventoryScreen(this, player.inventory, "Player");

		case KeyEvent.VK_C: // CRAFTING
			return new CraftingScreen(this, player.inventory);

		case KeyEvent.VK_D: // DEPOSIT SELECTED ITEMS
			e = map.entityAt(player.getX() + player.direction[0], player.getY() + player.direction[1]);
			if(e != null) {
				Logger.addLog("Depositing selected items into " + e.getName() + "'s inventory.", Log.NORMAL);
				player.inventory.moveSelectedItemsInto(e.inventory);
			}
			break;

		case KeyEvent.VK_W: // WITHDRAW SELECTED ITEMS
			e = map.entityAt(player.getX() + player.direction[0], player.getY() + player.direction[1]);
			if(e != null) {
				Logger.addLog("Withdrawing selected items from " + e.getName() + "'s inventory.", Log.NORMAL);
				e.inventory.moveSelectedItemsInto(player.inventory);
			}
			break;

		case KeyEvent.VK_SPACE: // DIG
			player.dig();
			break;

		case KeyEvent.VK_S: // SAVE MAP
			MapManager.saveMap(map);
			Logger.addLog("Saved map to save file '" + map.name + MapManager.FILE_EXTENSION + "'.", Log.NORMAL);
			break;

		case KeyEvent.VK_P: // PLACE EQUIPPED ITEM
			player.placeEquippedItem();
			break;

		case KeyEvent.VK_E: // OPEN ENTITY'S INVENTORY
			e = map.entityAt(player.getX() + player.direction[0], player.getY() + player.direction[1]);
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

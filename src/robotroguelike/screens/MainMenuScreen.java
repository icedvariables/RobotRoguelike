package robotroguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import robotroguelike.game.Game;
import robotroguelike.game.GraphicsEngine;

public class MainMenuScreen implements Screen {
	@Override
	public void display(GraphicsEngine graphics) {
		graphics.drawTextCenter("-- Robot Roguelike --", 5, Color.CYAN);
		graphics.drawTextCenter("---", 7);
		graphics.drawTextCenter("Press [enter] to build a new map.", 10);
		graphics.drawTextCenter("Press [space] to load an existing map.", 11);
		graphics.drawText("Version: " + Game.VERSION, 0, 0);
	}

	@Override
	public Screen respondToInput(KeyEvent key) {
		int keyCode = key.getKeyCode();

		if (keyCode == KeyEvent.VK_ENTER)
			return new GameScreen(true);
		if (keyCode == KeyEvent.VK_SPACE)
			return new GameScreen(false);
		if (keyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		return this;
	}
}

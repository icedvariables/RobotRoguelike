package robotroguelike.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

public class MainMenuScreen implements Screen {
	public void display(AsciiPanel terminal) {
		terminal.writeCenter("-- Robot Roguelike --", 5);
		terminal.writeCenter("---", 7);
		terminal.writeCenter("Press [enter] to build a new map.", 10);
		terminal.writeCenter("Press [space] to load an existing map.", 11);
	}

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

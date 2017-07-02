package robotroguelike.screens;

import java.awt.event.KeyEvent;

import robotroguelike.game.GraphicsEngine;

public interface Screen {
	public void display(GraphicsEngine graphics);

	public Screen respondToInput(KeyEvent key);
}

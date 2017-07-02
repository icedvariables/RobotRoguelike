package robotroguelike.game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import asciiPanel.AsciiPanel;
import robotroguelike.crafting.CraftingManager;
import robotroguelike.screens.MainMenuScreen;
import robotroguelike.screens.Screen;

public class Game extends JFrame implements KeyListener {
	private static final long serialVersionUID = 2801156805808640208L;

	public static final String VERSION = "0.0.1";

	public static final int WIDTH = 120;
	public static final int HEIGHT = 36;
	// public static final int DEFAULT_ITEM_STACK_MAX_QUANTITY = 50;

	private GraphicsEngine graphics;
	private Screen screen;

	public Game() {
		super();
		AsciiPanel terminal = new AsciiPanel(WIDTH, HEIGHT);
		terminal.setDefaultForegroundColor(Color.WHITE);
		add(terminal);
		graphics = new GraphicsEngineAscii(terminal);
		pack();

		CraftingManager.addAllCraftingRecipes();

		screen = new MainMenuScreen();
		addKeyListener(this);
		repaint();
	}

	@Override
	public void repaint() {
		graphics.clear();
		screen.display(graphics);
		super.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		screen = screen.respondToInput(e);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static void main(String[] args) {
		Game app = new Game();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
	}
}

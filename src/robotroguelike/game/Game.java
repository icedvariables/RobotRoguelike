package robotroguelike.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import asciiPanel.AsciiPanel;
import robotroguelike.screens.MainMenuScreen;
import robotroguelike.screens.Screen;

public class Game extends JFrame implements KeyListener {
	private static final long serialVersionUID = 2801156805808640208L;
	
	public static final int WIDTH = 120;
	public static final int HEIGHT = 36;

	private AsciiPanel terminal;
	private Screen screen;
	
	public Game(){
		super();
		terminal = new AsciiPanel(WIDTH, HEIGHT);
		terminal.write("Test", 38, 12);
		add(terminal);
		pack();
		
		screen = new MainMenuScreen();
		addKeyListener(this);
		repaint();
	}
	
	public void repaint(){
		terminal.clear();
		screen.display(terminal);
		super.repaint();
	}
	
	public void keyPressed(KeyEvent e){
        screen = screen.respondToInput(e);
        repaint();
    }
	
	public void keyReleased(KeyEvent e){}

    public void keyTyped(KeyEvent e){}
	
	public static void main(String[] args){
        Game app = new Game();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}

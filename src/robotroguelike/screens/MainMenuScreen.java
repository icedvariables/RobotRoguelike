package robotroguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;

import robotroguelike.game.Game;
import asciiPanel.AsciiPanel;

public class MainMenuScreen implements Screen {
	public void display(AsciiPanel terminal){
		terminal.writeCenter("-- Robot Roguelike --", 5);
		terminal.writeCenter("---", 7);
		terminal.writeCenter("Press [enter] to begin...", 10);
		terminal.write("[enter]", (Game.WIDTH / 2) - 7, 10, Color.RED);
	}

	public Screen respondToInput(KeyEvent key){
        int keyCode = key.getKeyCode();
        
        if(keyCode == KeyEvent.VK_ENTER){
        	return new GameScreen();
        }
        if(keyCode == KeyEvent.VK_ESCAPE){
        	System.exit(0);
        }
        
        return this;
    }
}

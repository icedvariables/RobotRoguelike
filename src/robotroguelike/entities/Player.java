package robotroguelike.entities;

import java.awt.Color;
import java.awt.event.KeyEvent;

import robotroguelike.map.Map;

public class Player extends Entity {
	public Player(Map map) {
		super("Player", '@', Color.YELLOW, map);
	}

	@Override
	public void update(KeyEvent key) {
		switch(key.getKeyCode()) {
		case KeyEvent.VK_UP:
			direction = Directions.UP;
			moveBy(0, -1);
			break;
		case KeyEvent.VK_DOWN:
			direction = Directions.DOWN;
			moveBy(0, 1);
			break;
		case KeyEvent.VK_LEFT:
			direction = Directions.LEFT;
			moveBy(-1, 0);
			break;
		case KeyEvent.VK_RIGHT:
			direction = Directions.RIGHT;
			moveBy(1, 0);
			break;
		}
	}
}

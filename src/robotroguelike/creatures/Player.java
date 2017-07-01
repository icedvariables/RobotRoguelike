package robotroguelike.creatures;

import java.awt.Color;

import robotroguelike.game.Game;
import robotroguelike.map.Map;

public class Player extends Creature {
	public Player(Map map) {
		super('@', Color.YELLOW, map);
		x = Game.WIDTH / 2;
		y = Game.HEIGHT / 2;
	}
}

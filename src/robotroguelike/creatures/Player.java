package robotroguelike.creatures;

import java.awt.Color;

import robotroguelike.map.Map;

public class Player extends Creature {
	public Player(Map map) {
		super('@', Color.YELLOW, map);
		x = map.width / 2;
		y = map.height / 2;
	}
}

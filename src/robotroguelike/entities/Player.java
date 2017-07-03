package robotroguelike.entities;

import java.awt.Color;

import robotroguelike.map.Map;

public class Player extends Entity {
	public Player(Map map) {
		super('@', Color.YELLOW, map);
		x = 0;
		y = 0;
	}
}

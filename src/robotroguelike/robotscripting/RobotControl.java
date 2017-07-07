package robotroguelike.robotscripting;

import robotroguelike.entities.Robot;
import robotroguelike.items.Item;

public class RobotControl {
	private Robot robot;

	public RobotControl(Robot robot) {
		this.robot = robot;
	}

	public Item dig(int[] direction) {
		return robot.dig(direction[0], direction[1]);
	}

	public void display(String text) {
		System.out.println("Robot: " + text);
	}

	public boolean moveUp() {
		return robot.moveBy(0, -1);
	}

	public boolean moveDown() {
		return robot.moveBy(0, 1);
	}

	public boolean moveLeft() {
		return robot.moveBy(-1, 0);
	}

	public boolean moveRight() {
		return robot.moveBy(1, 0);
	}
}

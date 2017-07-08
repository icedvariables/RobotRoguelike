package robotroguelike.robotscripting;

import robotroguelike.entities.Robot;
import robotroguelike.items.Item;

public class RobotControl {
	private Robot robot;

	public RobotControl(Robot robot) {
		this.robot = robot;
	}

	public Item dig(int[] direction) {
		return robot.dig(robot.getX() + direction[0], robot.getY() + direction[1]);
	}

	public boolean move(int[] direction) {
		return robot.moveBy(direction[0], direction[1]);
	}

	public void display(String text) {
		System.out.println(robot.getName() + ": " + text);
	}

	public String getName() {
		return robot.getName();
	}

	public int getX() {
		return robot.getX();
	}

	public int getY() {
		return robot.getY();
	}
}

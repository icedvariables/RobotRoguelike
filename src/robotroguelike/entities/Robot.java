package robotroguelike.entities;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import robotroguelike.map.Map;
import robotroguelike.robotscripting.RobotControl;

public class Robot extends Entity {
	private ScriptEngineManager engineManager;
	protected ScriptEngine engine;

	public Robot(String name, char glyph, Color color, Map map) {
		super(name, glyph, color, map);
		engineManager = new ScriptEngineManager();
		engine = engineManager.getEngineByName("JavaScript");

		engine.put("robot", new RobotControl(this));

		engine.put("UP", Directions.UP);
		engine.put("DOWN", Directions.DOWN);
		engine.put("LEFT", Directions.LEFT);
		engine.put("RIGHT", Directions.RIGHT);

		File scriptFile = new File(System.getProperty("user.dir"), "robot.js");

		try {
			engine.eval(new java.io.FileReader(scriptFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ScriptException e) {
			System.err.println("Robot script threw an error!");

			e.printStackTrace();
		}
	}

	@Override
	public void update(KeyEvent key) {
		try {
			Invocable inv = (Invocable) engine;

			inv.invokeFunction("update");
		} catch (ScriptException e) {
			System.err.println("Robot script threw an error!");

			e.printStackTrace();
		} catch(NoSuchMethodException e) {
			System.err.println("Robot script does noot have an update() function!");

			e.printStackTrace();
		}
	}
}

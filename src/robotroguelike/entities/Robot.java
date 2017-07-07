package robotroguelike.entities;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import robotroguelike.map.Map;
import robotroguelike.robotscripting.RobotControl;

public class Robot extends Entity {
	private ScriptEngineManager engineManager;
	protected ScriptEngine engine;

	public Robot(char glyph, Color color, Map map) {
		super(glyph, color, map);
		engineManager = new ScriptEngineManager();
		engine = engineManager.getEngineByName("JavaScript");

		engine.put("directions", Directions.class);
		engine.put("robot", new RobotControl(this));

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
}

package robotroguelike.entities;

import java.awt.event.KeyEvent;

import javax.script.ScriptException;

import robotroguelike.items.ItemIronPickaxe;
import robotroguelike.items.Tier;
import robotroguelike.map.Map;

public class LowTierMiningRobot extends Robot {
	public LowTierMiningRobot(Map map) {
		super("Low Tier Mining Robot", 'R', Tier.LOW.color, map);
		inventory.giveItem(new ItemIronPickaxe());
		inventory.equipItem(0);
	}

	@Override
	public void update(KeyEvent key) {
		try {
			engine.eval("update()");
		} catch (ScriptException e) {
			System.err.println("Robot script threw an error!");
			e.printStackTrace();
		}
	}
}

package robotroguelike.map;

import java.util.Random;

interface MapBuilder {
	public final Random rand = new Random();

	public Map build();
}

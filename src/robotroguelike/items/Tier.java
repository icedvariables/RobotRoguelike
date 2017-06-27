package robotroguelike.items;

import java.awt.Color;

public class Tier {
	public static Tier NORMAL = new Tier(0, Color.WHITE);
	public static Tier LOW = new Tier(1, Color.RED);
	public static Tier MEDIUM = new Tier(2, Color.GREEN);
	public static Tier HIGH = new Tier(3, Color.CYAN);
	public static Tier VERY_HIGH = new Tier(4, Color.MAGENTA);
	
	public final int number;
	public final Color color;
	
	public Tier(int number, Color color){
		this.number =  number;
		this.color = color;
	}
}

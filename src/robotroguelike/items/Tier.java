package robotroguelike.items;

import java.awt.Color;
import java.io.Serializable;

public class Tier implements Serializable {
	private static final long serialVersionUID = -410047334865331817L;

	public static Tier LOW = new Tier(0, Color.RED);
	public static Tier MEDIUM = new Tier(1, Color.GREEN);
	public static Tier HIGH = new Tier(2, Color.CYAN);
	
	public final int number;
	public final Color color;
	
	public Tier(int number, Color color){
		this.number =  number;
		this.color = color;
	}
}

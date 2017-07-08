package robotroguelike.logging;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	public static final Color NORMAL = Color.WHITE;
	public static final Color ERROR = Color.RED;
	public static final Color ROBOT_MESSAGE = Color.CYAN;
	
	private final long time;
	private final String message;
	private final Color color;
	
	public Log(String message, Color color, long time) {
		this.message = message;
		this.color = color;
		this.time = time;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Color getColor() {
		return color;
	}
	
	public long getTime() {
		return time;
	}
	
	public String getFormattedTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date(time));
	}
	
	@Override
	public String toString() {
		return getFormattedTime() + " - " + getMessage(); 
	}
}

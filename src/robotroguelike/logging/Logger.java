package robotroguelike.logging;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Logger {
	private static final List<Log> logs = new ArrayList<Log>();
	
	public static void addLog(String message, Color color) {
		logs.add(new Log(message, color, System.currentTimeMillis()));
	}
	
	public static Log[] getLogs() {
		return logs.toArray(new Log[logs.size()]);
	}
	
	public static Log getLatestLog() {
		if(logs.size() > 0)
			return logs.get(logs.size() - 1);
		return null;
	}
}

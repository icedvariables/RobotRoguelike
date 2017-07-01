package robotroguelike.game;

public class MapFileInvalidVersionException extends Exception {
	private static final long serialVersionUID = 6724162525348160941L;

	public MapFileInvalidVersionException() {
	}

	public MapFileInvalidVersionException(String msg) {
		super(msg);
	}
}

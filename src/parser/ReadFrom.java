package parser;

import java.io.InputStream;
import java.util.Map;

public interface ReadFrom {
	public Map<String, String> read(InputStream in);
}

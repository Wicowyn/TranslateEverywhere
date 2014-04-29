package parser;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ReadFrom {
	public Map<String, String> read(InputStream in);
	public List<String> readKey(InputStream in);
}

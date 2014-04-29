package parser;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import entity.Line;


public interface WriteTo {
	public void write(OutputStream out, List<Line> list) throws IOException;
}

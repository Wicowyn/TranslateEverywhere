package parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import entity.Line;

public class AndroidParser implements WriteTo {

	@Override
	public void write(OutputStream out, List<Line> list) throws IOException {
		Writer writer=new BufferedWriter(new OutputStreamWriter(out));
	    
	    writer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
	    
	    for(Line tLine : list){
	    	String line;
	    	
	    	if(tLine.toSkip()){
	    		line=new String("\n");
	    	}
	    	else{
		    	line ="\t<string name=\""
		        		+tLine.left+"\">"
		        		+tLine.right+"</string>\n";		    	
	    	}
	    	
	    	writer.append(line);
	    }
	    
	    writer.append("</resources>");
	    writer.close();
	}

}

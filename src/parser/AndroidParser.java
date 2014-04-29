package parser;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import entity.Line;

public class AndroidParser implements WriteTo, ReadFrom{

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

	@Override
	public Map<String, String> read(InputStream in) {
		Map<String, String> map=new HashMap<>();
		SAXBuilder sxb = new SAXBuilder();
		
	    try {
			Document doc=sxb.build(in);
			Element root=doc.getRootElement();
			
			for(Element elem : root.getChildren()){
				map.put(elem.getAttributeValue("name"), elem.getValue());
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return map;
	}

	@Override
	public List<String> readKey(InputStream in) {
		List<String> list=new ArrayList<>();
		SAXBuilder sxb = new SAXBuilder();
		
	    try {
			Document doc=sxb.build(in);
			Element root=doc.getRootElement();
			
			for(Element elem : root.getChildren()){
				list.add(elem.getAttribute("name").getValue());
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
		return list;
	}

}

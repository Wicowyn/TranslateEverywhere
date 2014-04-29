package parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IOSParser implements ReadFrom{

	@Override
	public Map<String, String> read(InputStream in) {
		Map<String, String> map=new HashMap<>();
	    Scanner scanner=new Scanner(in);
		
	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine();
	        
	        if (line.contains("=")) {
	        	line=line.replaceFirst("\" *= *\"", "=");
	        	line=line.substring(0, line.lastIndexOf(";"));
	        	line=line.trim();
	            line=line.replaceAll("\"", "");
	            
	            String[] parts=line.split("=");
	            
	            map.put(parts[0], parts[1]);
	        }
	    }
		
		scanner.close();
		
		return map;
	}

	@Override
	public List<String> readKey(InputStream in) {
		List<String> list=new ArrayList<>();
	    Scanner scanner=new Scanner(in);
		
	    while (scanner.hasNextLine()) {
	        String line = scanner.nextLine();
	        
	        if (line.contains("=")) {
	        	line=line.replaceFirst("\" *= *\"", "=");
	        	line=line.substring(0, line.lastIndexOf(";"));
	        	line=line.trim();
	            line=line.replaceAll("\"", "");
	            
	            String[] parts=line.split("=");
	            
	            list.add(parts[0]);
	        }
	        else{
	        	list.add(null);
	        }
	    }
		
		scanner.close();
		
		return list;
	}

}

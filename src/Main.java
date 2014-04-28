import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws IOException {
		Map<String, String> map=readTrad(args[1]);
		List<TransitiveLine> list=readTransitive(args[0]);
		
	    Writer writer=new BufferedWriter(new OutputStreamWriter(
	    		new FileOutputStream(new File(args[2]))));
	    
	    writer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<resources>\n");
	    
	    for(TransitiveLine tLine : list){
	    	String line;
	    	
	    	if(tLine.toSkip()){
	    		line=new String("\n");
	    	}
	    	else{
		    	line ="\t<string name=\""
		        		+tLine.android+"\">"
		        		+map.get(tLine.ios)+"</string>\n";		    	
	    	}
	    	
	    	writer.append(line);
	    }
	    
	    writer.append("</resources>");
	    writer.close();
	}
	
	public static Map<String, String> readTrad(String file) throws FileNotFoundException{
		Map<String, String> map=new HashMap<>();
	    Scanner scanner=new Scanner(new FileInputStream(file));
		
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
	
	public static List<TransitiveLine> readTransitive(String file) throws FileNotFoundException{
		List<TransitiveLine> list=new ArrayList<>();
	    Scanner scanner=new Scanner(new FileInputStream(file));
	    int idTransitiveLine=0;
		
		while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			
			String[] parts=line.split("=");
			if(parts.length<2){
				list.add(new TransitiveLine(idTransitiveLine, null, null));
			}
			else{
				list.add(new TransitiveLine(idTransitiveLine, parts[0], parts[1]));
			}
			
			idTransitiveLine++;
		}
		
		scanner.close();
		
		return list;
	}
	
	public static class Line{
		public String android;
		public String ios;
		
		public Line(){
			
		}
		
		public Line(String android, String ios){
			this.android=android;
			this.ios=ios;
		}
	}
	

	public static class TransitiveLine extends Line{
		public int id;
		
		public TransitiveLine(){
			
		}
		
		public TransitiveLine(int id, String android, String ios){
			super(android, ios);
			
			this.id=id;
		}
		
		public boolean toSkip(){
			return android==null || ios==null;
		}
	}
}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import parser.ReadFrom;
import parser.WriteTo;
import entity.Line;
import entity.TradFile;


public class Manager {
	private ReadFrom read;
	private WriteTo write;
	
	public Manager(ReadFrom r, WriteTo w){
		read=r;
		write=w;
	}
	
	public static final String FILE_DELIMITER="##";
	
	public static List<TradFile> readTransitive(InputStream in) throws FileNotFoundException{
		List<TradFile> list=new ArrayList<>();
	    Scanner scanner=new Scanner(in);
	    TradFile tFile=null;
		
	    
		while(scanner.hasNextLine()){
			String line=scanner.nextLine();
			
			if(line.startsWith(FILE_DELIMITER)){
				if(tFile!=null) list.add(tFile);
				
				tFile=new TradFile();
				tFile.setFile(line.substring(FILE_DELIMITER.length(), line.length()));
			}
			else if(tFile!=null){
				String[] parts=line.split("=");
				
				if(parts.length<2){
					tFile.getList().add(new Line(null, null));
				}
				else{
					String left=parts[0].trim();
					String right=parts[1].trim();
					
					switch (right) {
					case "":
						throw new RuntimeException(parts[0]+" right hand is empty");
					case "?":
						System.out.println("Ignore : "+left);
						break;
					default:
						tFile.getList().add(new Line(left, right));
						break;
					}
				}
				
			}
		}
		
		if(tFile!=null) list.add(tFile);
		
		scanner.close();
		
		return list;
	}
	
	public static List<Line> resolve(Map<String, String> map, List<Line> ll){
		List<Line> list=new ArrayList<>(ll);
		
		for(Line line : list){
			line.right=map.get(line.right);
		}
		
		return list;
	}
	
	public void process(File transitive, List<File> tSource, File destDir) throws IOException{
		List<TradFile> listTradFile=readTransitive(new FileInputStream(transitive));
		Map<String, String> map=new HashMap<>();
		
		for(File src : tSource) map.putAll(read.read(new FileInputStream(src)));
		
		for(TradFile tradFile : listTradFile){
			File dest=new File(destDir, tradFile.getFile());
			
			write.write(new FileOutputStream(dest), resolve(map, tradFile.getList()));
		}
	}
}

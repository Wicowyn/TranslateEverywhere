import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import parser.AndroidParser;
import parser.IOSParser;
import parser.ReadFrom;
import parser.WriteTo;


public class Main {

	public static void main(String[] args) throws IOException {
		if(args[0].equals("skeleton")){
			ReadFrom r;
			
			switch (args[1]) {
			case "ios":
				r=new IOSParser();
				break;
			case "android":
				r=new AndroidParser();
				break;
			default:
				throw new IllegalArgumentException("unknown : "+args[1]);
			}
			
			List<File> list=new ArrayList<>();
			for(int i=2; i<args.length; i++){
				list.add(new File(args[i]));
			}
			
			generateSkeleton(list, r, new File(args[1]+"_skeleton.tew"));
		}
		else{
			ReadFrom r;
			WriteTo w;
			
			switch (args[0].substring(0, args[0].indexOf('2'))) {
			case "i":
				r=new IOSParser();
				break;
			default:
				throw new IllegalArgumentException("unknown : "+args[0]);
			}
			
			switch (args[0].substring(args[0].indexOf('2')+1, args[0].length())) {
			case "a":
				w=new AndroidParser();
				break;
			default:
				throw new IllegalArgumentException("unknown : "+args[0]);
			}
			
			List<File> list=new ArrayList<>();
			for(int i=2; i<args.length-1; i++){
				list.add(new File(args[i]));
			}
			
			Manager manager=new Manager(r, w);
			manager.process(new File(args[1]), list, new File(args[args.length-1]));
		}
	}
	
	public static void generateSkeleton(List<File> list, ReadFrom r, File out) throws IOException{
		Writer writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out)));
		
		for(File file : list){
			writer.append(Manager.FILE_DELIMITER+file.getName()+"\n");
			
			for(String key : r.readKey(new FileInputStream(file))){
				if(key!=null){
					writer.append(key+"=\n");
				}
				else{
					writer.append("\n");
				}
			}
		}
		
		writer.close();
	}
}

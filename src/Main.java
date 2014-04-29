import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import parser.AndroidParser;
import parser.IOSParser;
import parser.ReadFrom;
import parser.WriteTo;


public class Main {

	public static void main(String[] args) throws IOException {
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

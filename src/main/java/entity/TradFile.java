package entity;
import java.util.ArrayList;
import java.util.List;



public class TradFile {
	private String file;
	private List<Line> list=new ArrayList<>();
	
	
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public List<Line> getList() {
		return list;
	}
	
	public void setList(List<Line> list) {
		this.list = list;
	}
	
}

package entity;

public class Line {
	public String left;
	public String right;
	
	public Line(){
		
	}
	
	public Line(String android, String ios){
		this.left=android;
		this.right=ios;
	}
	
	public boolean toSkip(){
		return left==null || right==null;
	}
}

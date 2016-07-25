package ooxx;

import java.io.Serializable;

public class Data implements Serializable{

	int x;
	int y;
	boolean isReceive;
	
	Data(int x , int y, boolean isReceive){
		this.x = x;
		this.y = y;
		this.isReceive = isReceive;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean isReceive(){
		return isReceive;
	}
}

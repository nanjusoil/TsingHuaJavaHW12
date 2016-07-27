package ooxx;

import java.io.Serializable;

public class Data implements Serializable{

	int x;
	int y;
	int player;
	
	Data(int x , int y, int player){
		this.x = x;
		this.y = y;
		this.player = player;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int isReceive(){
		return player;
	}
}

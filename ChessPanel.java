package ooxx;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessPanel extends JPanel{

	public int[][] chess = new int[8][8];
	@Override
	  public void paintComponent(Graphics g)
	  {
		for(int i = 1 ; i <=8 ; i++)
		  g.drawLine(0, i*100, 800, i*100);
		for(int i = 1 ; i <=8 ; i++)
		  g.drawLine(i*100, 0 , i*100, 800);
		
		for(int i = 0 ; i <8 ; i++)
			for(int j = 0 ; j <8 ; j++){
				if(chess[i][j] == 1 ){
					g.fillRect(i*100, j*100, 100, 100);
				}
			}
				
	  }
	  
}

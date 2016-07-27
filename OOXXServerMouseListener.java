package ooxx;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class OOXXServerMouseListener implements MouseListener, Runnable{

	OOXXServer chessboard;
	boolean canClick = true;
	public OOXXServerMouseListener(OOXXServer chessboard){
		this.chessboard = chessboard;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		chessboard.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.canClick){
			Data data = new Data(e.getX() , e.getY() , 0);
			try {
				chessboard.out.writeObject(data);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			chessboard.chesspanel.chess[e.getX()/100][e.getY()/100] = -1;
			chessboard.chesspanel.repaint();
			this.canClick = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}

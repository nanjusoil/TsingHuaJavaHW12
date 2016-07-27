package ooxx;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JFrame;

public class OOXXClient extends JFrame implements Runnable{
	private String address = "127.0.0.1";
	private int port = 8765;
	static boolean canClick = false;
    ObjectOutputStream out;
    ObjectInputStream in;
    ChessPanel chesspanel;
	public OOXXClient(){
		Socket client = new Socket() ;
	    InetSocketAddress isa = new InetSocketAddress(this.address,this.port);
	    try
	    {
	    	client.connect(isa,10000);
	    	out = new ObjectOutputStream(client.getOutputStream());
	    	in = new ObjectInputStream(client.getInputStream());
	    	
	    }
	    catch(java.io.IOException e)
	    {
	    	e.printStackTrace();
	    }
	}
	@Override
	public void run() {
		Data data;
		try {
			while((data=(Data)in.readObject())!=null){
				OOXXClient.canClick = true;
				System.out.println(OOXXClient.canClick);
				chesspanel.chess[data.getX()/100][data.getY()/100] = -1;
				chesspanel.repaint();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	  public static void main(String[] argc){
		  OOXXClient chessboard = new OOXXClient();
		  chessboard.setSize(800, 800);
		  
		  Thread gamethread = new Thread(chessboard);
		  gamethread.start();
		  chessboard.chesspanel = new ChessPanel();
		  chessboard.chesspanel.setBackground(Color.ORANGE);
		  
		 
		  chessboard.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(OOXXClient.canClick){
					Data data = new Data(e.getX() , e.getY() , 0);
					try {
						chessboard.out.writeObject(data);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					chessboard.chesspanel.chess[e.getX()/100][e.getY()/100] = 1;
					chessboard.chesspanel.repaint();
					OOXXClient.canClick = false;
					System.out.println(OOXXClient.canClick);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			  
		  });
		  chessboard.add(chessboard.chesspanel);
		  chessboard.setVisible(true);
	  }

}
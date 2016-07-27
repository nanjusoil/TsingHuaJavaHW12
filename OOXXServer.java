package ooxx;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;

public class OOXXServer extends JFrame implements Runnable{
	private String address = "127.0.0.1";
	private int port = 8765;
	private ServerSocket server;
    ObjectOutputStream out;
    ObjectInputStream in;
    ChessPanel chesspanel;
	boolean canClick = true;
	//OOXXServerMouseListener mouseListener = new OOXXServerMouseListener(this);
	public OOXXServer(){
		try
		{
			server = new ServerSocket(port);
			
		}
		catch(java.io.IOException e)
		{
		}
	}
	@Override
	public void run() {
		Socket socket ;
		System.out.println("Server Started!");
		Data data;
		while(true)
		{
			socket = null;
			try
			{
				socket = server.accept();
				System.out.println("Server Accepted");

				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
				
				while((data=(Data)in.readObject())!=null){
					this.canClick = true;
					chesspanel.chess[data.getX()/100][data.getY()/100] = 1;
					chesspanel.repaint();
					System.out.println("我取得的值:"+data.getX());
					System.out.println("我取得的值:"+data.getY());
				}
			}
			catch(java.io.IOException e)
			{
			}
			catch(java.lang.ClassNotFoundException e)
			{
			}
		}
	}
	  public static void main(String[] argc){
		  OOXXServer ooxxserver = new OOXXServer();
		  ooxxserver.setSize(800, 800);
		  
		  Thread gamethread = new Thread(ooxxserver);
		  gamethread.start();
		  ooxxserver.chesspanel = new ChessPanel();
		  ooxxserver.chesspanel.setBackground(Color.ORANGE);
		  
		  ooxxserver.chesspanel.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {
					if(ooxxserver.canClick){
						Data data = new Data(e.getX() , e.getY() , 0);
						try {
							ooxxserver.out.writeObject(data);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						ooxxserver.chesspanel.chess[e.getX()/100][e.getY()/100] = -1;
						ooxxserver.chesspanel.repaint();
						ooxxserver.canClick = false;
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				  
			  });
		  ooxxserver.add(ooxxserver.chesspanel);
		  ooxxserver.setVisible(true);
	  }

}
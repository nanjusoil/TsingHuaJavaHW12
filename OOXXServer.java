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
	OOXXServerMouseListener mouseListener = new OOXXServerMouseListener(this);
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
					this.mouseListener.canClick = true;
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
		  
		  ooxxserver.add(ooxxserver.chesspanel);
		  ooxxserver.setVisible(true);
	  }

}
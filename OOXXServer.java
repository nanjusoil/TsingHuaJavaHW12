package ooxx;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OOXXServer extends Thread{

	private int port = 8765;
	private ServerSocket server ;
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
	public void run(){
		Socket socket ;
		ObjectInputStream in ;
		ObjectOutputStream out ;

		System.out.println("伺服器已啟動 !"  );
		while(true)
		{
			socket = null;
			try
			{
				synchronized(server) 
				{
					socket = server.accept();
				}
				System.out.println("取得連線 : InetAddress = " + socket.getInetAddress()  );

				in = new java.io.ObjectInputStream(socket.getInputStream());
				out = new java.io.ObjectOutputStream(socket.getOutputStream());
				Data data;
				Data data2;
				while((data=(Data)in.readObject())!=null){
					data2 = new Data(data.getX() +100 , data.getY() + 100 , false);
					System.out.println("我取得的值:"+data.getX());
					System.out.println("我取得的值:"+data.getY());
					out.writeObject(data2);
				}

				in.close();
				in = null ;
				socket.close();		
			}
			catch(java.io.IOException e)
			{
			}
			catch(java.lang.ClassNotFoundException e)
			{
			}
		}
	}
	
	public static void main(String args[])
	{
		(new OOXXServer()).start();
	}
}

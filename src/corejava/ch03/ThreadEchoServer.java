package corejava.ch03;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/** 
 * @TODO
 * @author yicha
 * @date   2015年8月26日 
 */
public class ThreadEchoServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int i = 0 ;
		ServerSocket server = null ;
		try {
			server = new ServerSocket(8189);
			
			while(true){
				i ++ ;
				Socket incoming = server.accept() ;
				System.out.println("第" + i + "个客户端已经连接！");
				Runnable r = new ThreadEchoHandler(incoming) ;
				Thread h = new Thread(r) ;
				h.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				server.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class ThreadEchoHandler implements Runnable{

	private Socket incoming ;
	public ThreadEchoHandler(Socket s){
		this.incoming = s ;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		InputStream inStream;
		OutputStream outStream ;
		Scanner in = null ;
		try {
			inStream = incoming.getInputStream();
			outStream = incoming.getOutputStream() ;
			in = new Scanner(inStream) ;
			PrintWriter out = new PrintWriter(outStream, true) ;
			
			out.println("Hello,welcome !(enter 'exit' to quit)");
			
			boolean done = false ;
			
			while(!done && in.hasNextLine()){
				String line = in.nextLine() ;
				System.out.println(Thread.currentThread().getName() + " say:" + line);
				if(line.trim().equals("exit")){
					done = true ;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			in.close();
		}
		
		
	}
	
}
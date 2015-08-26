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
 * @date   2015年8月25日 
 */
public class EchoServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		try(ServerSocket ss = new ServerSocket(8189)){
			
			try(Socket incoming = ss.accept()){
				InputStream in = incoming.getInputStream() ;
				OutputStream out = incoming.getOutputStream() ;
				
				try(Scanner sc = new Scanner(in)){
					
					PrintWriter pw = new PrintWriter(out,true) ;
					pw.println("Hello! Enter 'exit' to quit!");
					
					boolean done = false ;
					
					while(!done && sc.hasNext()){
						String line = sc.nextLine() ;
						System.out.println("from client:" + line);
						pw.println("Echo:" + line) ;
						if(line.trim().equals("exit")){
							done = true ;
						}
					}
					
				}
			}
		}
	}

}

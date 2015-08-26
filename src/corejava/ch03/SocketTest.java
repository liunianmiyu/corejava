package corejava.ch03;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/** 
 * @TODO
 * @author zcc
 * @date   2015年8月25日 
 */
public class SocketTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		
		try(Socket s = new Socket("time-A.timefreq.bldrdoc.gov", 13)){
			InputStream in = s.getInputStream() ;
			Scanner sc = new Scanner(in) ;
			
			while(sc.hasNextLine()){
				String line = sc.nextLine() ;
				System.out.println(line);
			}
			sc.close();
		}
	}
}

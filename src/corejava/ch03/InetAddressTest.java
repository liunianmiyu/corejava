package corejava.ch03;

import java.net.InetAddress;
import java.net.UnknownHostException;

/** 
 * @TODO
 * @author yicha
 * @date   2015年8月25日 
 */
public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub

		boolean isLocal = false ;
		
		if(!isLocal){
			String host = "www.baidu.com" ;
			InetAddress[] addresses = InetAddress.getAllByName(host) ;
			for(InetAddress address : addresses){
				System.out.println(address);
			}
		}else{
			InetAddress localHost = InetAddress.getLocalHost() ;
			System.out.println(localHost);
		}
	}

}

package corejava.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/** 
 * @TODO
 * @author yicha
 * @date   2015年8月26日 
 */
public class URLConnectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String urlStr = "http://jsoup.org/" ;
		
		try {
			URL url = new URL(urlStr) ;
			URLConnection connection = url.openConnection() ;
			
			boolean isLogin = false ;
			
			if(isLogin){
				String username = "" ;
				String password = "" ;
				String input = username+":" + password ;
			    try {
					String encoding = encodeBase64(input.getBytes()) ;
					connection.setRequestProperty("Authorization", "Basic"+encoding);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("编码出现异常！");
					e.printStackTrace();
				}
			}
			
			connection.connect();
			
			//print header fields
			Map<String, List<String>> headers = connection.getHeaderFields() ;
			for(Map.Entry<String, List<String>> entry : headers.entrySet()){
				String key = entry.getKey() ;
				for(String value : entry.getValue()){
					System.out.println(key + ":" + value);
				}
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())) ;
			int count = 0 ;
			String line ;
			while((line = reader.readLine()) != null && count < 100){
				System.out.println(line);
				count ++ ;
			}
			reader.close();
			
			if(count >= 10) System.out.println("...");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*** 
     * encode by Base64 
     */  
    public static String encodeBase64(byte[]input) throws Exception{  
        Class<?> clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("encode", byte[].class);  
        mainMethod.setAccessible(true);  
        Object retObj=mainMethod.invoke(null, new Object[]{input});  
        return (String)retObj;  
    }
}

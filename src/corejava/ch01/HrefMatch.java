package corejava.ch01;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @TODO   根据一个url匹配出网页中所有的超链接
 * @author yicha
 * @date   2015年8月19日 
 */
public class HrefMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String urlString ;
		if(args.length > 0 ){
			urlString = args[0] ;
		}else{
			urlString = "http://java.sun.com" ;
		}
		
		try {
			//open reader for url 
			InputStreamReader in = new InputStreamReader(new URL(urlString).openStream()) ;
			//read contents to StringBuilder
			StringBuilder sb = new StringBuilder() ;
			int ch ;
			while((ch = in.read()) != -1){
				sb.append((char)ch) ;
			}
			String patternString = "<a\\s+href\\s*=(\"[^\"]*\"|[^\\s>]*)\\s>" ;
			Pattern pattern = Pattern.compile(patternString,Pattern.CASE_INSENSITIVE) ;
			Matcher matcher = pattern.matcher(sb) ;
			
			while(matcher.find()){
				int start = matcher.start(1) ;
				int end = matcher.end(1) ;
				String match = sb.substring(start, end) ;
				System.out.println(match);
			}
		} catch (MalformedURLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}

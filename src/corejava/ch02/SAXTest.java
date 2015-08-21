package corejava.ch02;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/** 
 * @TODO   利用SAX解析 XHTML ， 得到页面中所有的超链接
 * @author zcc
 * @date   2015年8月21日 
 */
public class SAXTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, MalformedURLException, IOException {
		
		String url ;
		if(args.length == 0 ){
			url = "http://www.w3c.org/MarkUp" ;
			System.out.println("Using:" + url);
		}else{
			url = args[0] ;
		}
		
		DefaultHandler handler = new DefaultHandler(){
			@Override
			public void startElement(String uri, String localName,
					String qName, Attributes attributes) throws SAXException {
				// TODO Auto-generated method stub
				if(localName.equalsIgnoreCase("a") && attributes != null){
					for(int i = 0 ; i < attributes.getLength() ; i ++){
						String name = attributes.getLocalName(i) ;
						if(name.equalsIgnoreCase("href")){
							System.out.println(attributes.getValue(i));
						}
					}
				}
			}
		};
		
		SAXParserFactory factory = SAXParserFactory.newInstance() ;
		factory.setNamespaceAware(true);
		factory.setFeature("http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd", false);
		SAXParser parser = factory.newSAXParser() ;
		InputStream in = new URL(url).openStream() ;
		parser.parse(in, handler);
	}
}

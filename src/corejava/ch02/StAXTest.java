package corejava.ch02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/** 
 * @TODO
 * @date   2015年8月21日 
 */
public class StAXTest {

	public static void main(String[] args) {
		
		try {
			InputStream in = new FileInputStream(new File("resources/xml/book.xml")) ;
			
			XMLInputFactory factory = XMLInputFactory.newInstance() ;
			XMLStreamReader parser = factory.createXMLStreamReader(in) ;
			while(parser.hasNext()){
				int event = parser.next() ;
				if(event == XMLStreamConstants.START_ELEMENT){
					if(parser.getLocalName().equals("book")){
						String id = parser.getAttributeValue(null, "id") ;
						if(id != null){
							System.out.println(id);
						}
					}
					if(parser.getLocalName().equals("name")){
						String name = parser.getElementText();
						if(name != null){
							System.out.println(name);
						}
					}
					if(parser.getLocalName().equals("price")){
						String price = parser.getElementText() ;
						if(price != null){
							System.out.println(price);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

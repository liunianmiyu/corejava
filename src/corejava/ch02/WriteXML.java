package corejava.ch02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/** 
 * @TODO
 * @author yicha
 * @date   2015年8月21日 
 */
public class WriteXML {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		// TODO Auto-generated method stub

		OutputStream out = new FileOutputStream(new File("resources/xml/out.xml"));
		XMLOutputFactory factory = XMLOutputFactory.newInstance() ;
		XMLStreamWriter writer = factory.createXMLStreamWriter(out) ;
		
		writer.writeStartDocument("utf-8", "1.0");
		
		writer.writeStartElement("books");
		for(int i = 0 ; i < 5 ; i ++){
			
			writer.writeStartElement("book");
			writer.writeAttribute("id", i+"");
			writer.writeStartElement("name");
			writer.writeCharacters("hello world");
			writer.writeEndElement();
			writer.writeEndElement();
		}
		
		writer.writeEndElement();
		
		writer.writeEndDocument();
	}

}

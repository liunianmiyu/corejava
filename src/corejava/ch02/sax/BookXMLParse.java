package corejava.ch02.sax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/** 
 * @TODO
 * @author yicha
 * @date   2015年8月21日 
 */
public class BookXMLParse extends DefaultHandler{

	private Locator locator ;
	
	private List<Book> books ;
	private Book currentBook ;
	private String preTag ;
	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub
		this.locator = locator ;
	}
	
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		books = new ArrayList<Book>() ;
		currentBook = null ;
		preTag = null ;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		if("book".equalsIgnoreCase(qName)){
			currentBook = new Book() ;
			currentBook.setId(attributes.getValue("id"));
		}
		preTag = qName ;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		if("book".equalsIgnoreCase(qName)){
			books.add(currentBook);
			currentBook = null ;
		}
		preTag = null ;
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		if(preTag != null && currentBook != null){
			String value = new String(ch, start, length) ;
			if(preTag.equals("name")){
				currentBook.setName(value);
			}else if(preTag.equals("price")){
				currentBook.setPrice(Double.parseDouble(value));
			}
		}
	}
	
	@Override
	public void warning(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Warning occurred \n");
		System.out.println("Locator info:" + locatorInfo());
		e.printStackTrace();
	}
	
	@Override
	public void error(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Error occurred \n");
		System.out.println("Locator info:" + locatorInfo());
		e.printStackTrace();
	}
	
	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("FatalError occurred \n");
		System.out.println("Locator info:" + locatorInfo());
		e.printStackTrace();
	}
	private String locatorInfo() {
		return "resource: " + locator.getSystemId() + ", Locator Info: [" +
	            locator.getLineNumber() + ", " + locator.getColumnNumber() + "]";
	}
	
	
	public static void main(String[] args) throws SAXException, IOException {
		// TODO Auto-generated method stub

		XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        BookXMLParse handler = new BookXMLParse();
        xmlReader.setContentHandler(handler);
        xmlReader.setEntityResolver(handler);
        xmlReader.setErrorHandler(handler);
        xmlReader.setDTDHandler(handler);
        xmlReader.parse("resources/xml/book.xml");
        System.out.println("Book List:");
        System.out.println(handler.books);
	}

}

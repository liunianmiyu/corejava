package corejava.ch02.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/** 
 * @TODO
 * @author yicha
 * @date   2015年8月31日 
 */
public class TestJaxb {

	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub

		/*Country country = new Country();
		country.setPopulation(1000000);
		country.setName("China");
		country.setCapital("beijing");
		country.setImportance(1);
		
		初始化jaxb编组器(marshaler)
		JAXBContext jaxbContext = JAXBContext.newInstance(Country.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		设置flag为true，表示将格式化输出
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		编组java对象到xml文件(输出到文件和标准输出)
		jaxbMarshaller.marshal(country, new File("out.xml"));
		jaxbMarshaller.marshal(country, System.out);*/
		
		
		/**
		 * 反编组
		 */
		File file = new File("out.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Country.class);
		Unmarshaller jabxUnmarshaller = jaxbContext.createUnmarshaller();
		Country country = (Country) jabxUnmarshaller.unmarshal(file);
		System.out.println(country.getName());
	}

}

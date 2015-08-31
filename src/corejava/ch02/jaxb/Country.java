package corejava.ch02.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @TODO
 * @author yicha
 * @date 2015年8月31日
 */
@XmlType(propOrder = { "name", "capital","population"})
@XmlRootElement(name = "Country")
public class Country {

	private int population;
	private String name;
	private String capital;
	private int importance;

	public int getPopulation() {
		return population;
	}

	@XmlElement(name="population")
	public void setPopulation(int population) {
		this.population = population;
	}

	public String getName() {
		return name;
	}

	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	@XmlElement(name="capital")
	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getImportance() {
		return importance;
	}

	@XmlAttribute(name="importance", required=true)
	public void setImportance(int importance) {
		this.importance = importance;
	}

}

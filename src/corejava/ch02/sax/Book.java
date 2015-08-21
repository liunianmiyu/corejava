package corejava.ch02.sax;

/**
 * @TODO
 * @date 2015年8月21日
 */
public class Book {

	private String id;
	private String name;
	private double price;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "bookId:" + id + "|bookName:" + name + "|bookPrice:" + price;
	}
}

package thingk.in.java.ch13;

import java.util.Scanner;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月19日 
 */
public class ScannerTest {

	private int id;
	private long count;
	private float price;
	private double length;
	private String name;
	
	public ScannerTest(String str) {
		// TODO Auto-generated constructor stub
		Scanner sc = new Scanner(str);
		sc.useDelimiter(",");
		id = sc.nextInt();
		count = sc.nextLong();
		price = sc.nextFloat();
		length = sc.nextDouble();
		name = sc.next();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[id:" + id + ",count:" + count + ",price:" + price + ",length:" + length + ",name:" + name;
	}
	
	public static void main(String[] args) {
		ScannerTest test = new ScannerTest("13,355555,4.50,3.333,zhang");
		System.out.println(test);
	}
}

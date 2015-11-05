package thingk.in.java.ch13;

import java.io.PrintStream;
import java.util.Formatter;
import java.util.Scanner;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月19日 
 */
public class Turtle {

	private static final int WIDTH = -15;
	private String name;
	private Formatter formatter;
	
	public Turtle(String name, Formatter formatter){
		this.name = name;
		this.formatter = formatter;
	}
	
	public void print(int x, int y){
		formatter.format("%"+WIDTH+"s the turtle in (%d,%d)\n", name, x, y);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		PrintStream outAlias = System.err;
//		Turtle tom = new Turtle("tom", new Formatter(outAlias));
//		Turtle jack = new Turtle("jack", new Formatter(System.out));
//		tom.print(0, 0);
//		jack.print(1, 3);
		System.out.println("+1234".matches("(-|\\+)?\\d+"));
		for(String regex : new String[]{"Rudolph","[r|R]udolph","[rR][aeiou][a-z]ol.*","R.*"}){
			System.out.println("Rudolph".matches(regex));
		}
	}

}

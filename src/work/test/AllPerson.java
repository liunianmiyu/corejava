package work.test;

import java.util.ArrayList;
import java.util.Iterator;

class Person{
	private String name;
	public Person(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}

class Student extends Person{
	public Student(String name){
		super(name);
	}
}
/** 
 * @TODO
 * @author yicha
 * @date   2015年9月26日 
 */
public class AllPerson {

	public static void printAll(ArrayList< ? extends Person > list){
		Iterator< ? extends Person> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	public static <T> void printAll2(ArrayList<T> list){
		Iterator<T> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Person> list1 = new ArrayList<Person>();
		ArrayList<Student> list2 = new ArrayList<Student>();
		
		Person p1 = new Person("p1");
		Person p2 = new Person("p2");
		
		Student s1 = new Student("s1");
		Student s2 = new Student("s2");
		
		list1.add(p1);
		list1.add(p2);
		
		list2.add(s1);
		list2.add(s2);
		
		printAll(list1);
		printAll(list2);
		
		printAll2(list1);
		printAll2(list2);
	}
}

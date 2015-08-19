package corejava.ch01;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/** 
 * @TODO
 * @author yicha
 * @date   2015年8月18日 
 */
public class SerialCloneTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub

		Employee2 hacker = new Employee2("Hacke", 3500, 1998, 10, 1) ;
		//clone hacker
		Employee2 hacker2 = (Employee2) hacker.clone() ;
		
		
		hacker.raiseSalary(10);
		
		System.out.println(hacker);
		System.out.println(hacker2);
	}

}
/**
 * a class whose clone methon use serializable
 * @author Zhangcc
 *
 */
class SerialCloneable implements Cloneable,Serializable{
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		try {
			//save the object to a byte array
			ByteArrayOutputStream bout = new ByteArrayOutputStream() ;
			ObjectOutputStream out = new ObjectOutputStream(bout) ;
			out.writeObject(this);
			out.close();
			
			//read a clone of the object from a byte array
			ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray()) ;
			ObjectInputStream in = new ObjectInputStream(bin) ;
			Object obj = in.readObject() ;
			in.close();
			return obj ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
class Employee2 extends SerialCloneable{
	
	private String name ;
	private double salary ;
	private Date hireDay ;
	
	public Employee2(String name,double salary,int year,int month,int day){
		this.name = name ;
		this.salary = salary ;
		GregorianCalendar calendar = new GregorianCalendar(year, month-1, day) ;
		this.hireDay = calendar.getTime() ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	
	public void raiseSalary(double raiseNum){
		this.salary += raiseNum ;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + this.name + "|" + this.salary + "|" + this.hireDay + "]";
	}
}

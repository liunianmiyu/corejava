package corejava.ch01;

import java.io.Serializable;

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4666366934924705527L;
	
	private String name;
	private double salary;
	private int year;
	private int month;
	private int day;

	public Employee(String name, double salary, int year, int month, int day) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.salary = salary;
		this.year = year;
		this.month = month;
		this.day = day;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void raiseSalary(double raiseNum){
		this.salary += raiseNum ;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name + "|" + this.salary + "|" + this.year + "-" + this.month + "-" + this.day ;
	}
}

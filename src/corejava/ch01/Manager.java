package corejava.ch01;

public class Manager extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288096131004261955L;
	
	private Employee secretary;

	public Manager(String name, double salary, int year, int month, int day) {
		super(name, salary, year, month, day);
		// TODO Auto-generated constructor stub
	}

	public Employee getSecretary() {
		return secretary;
	}

	public void setSecretary(Employee secretary) {
		this.secretary = secretary;
	}
}

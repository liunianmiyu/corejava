package work.test;
/** 
 * @TODO
 * @author yicha
 * @date   2015年11月2日 
 */
class Person1{
	private int id;
	public Person1(int id){
		this.id = id;
	}
	public void setId(int id){
		this.id = id;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:" + id;
	}
}
public class ReferenceOrValue {

	public static void tripleValue(double x){
		x = x * 3;
	}
	
	public static void triplePerson(Person1 p){
		//p = new Person1(2);
		p.setId(2);
	}
	public static void main(String[] args) {
		Person1 p1 = new Person1(1);
		triplePerson(p1);
		System.out.println(p1);
	}
}

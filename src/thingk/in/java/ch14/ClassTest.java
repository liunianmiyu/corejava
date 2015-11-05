package thingk.in.java.ch14;
/** 
 * @TODO
 * @author yicha
 * @date   2015年10月20日 
 */
interface HasBetter{}
interface WaterProof{}
interface Shoots{}

class Toy{
	Toy(){}
	Toy(int i){}
}

class ToyChild extends Toy implements HasBetter, WaterProof, Shoots{
	ToyChild(){
		super(1);
	}
}
public class ClassTest {
	
	static void printInfo(Class cc){
		System.out.println(cc.getName());
		System.out.println(cc.getSimpleName());
		System.out.println(cc.getCanonicalName());
		System.out.println(cc.isInterface());
	}

	public static void main(String[] args) {
		Class c = null;
		
		try {
			c = Class.forName("thingk.in.java.ch14.ToyChild");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("class not found.");
			System.exit(1);
		}
		
		printInfo(c);
		
		for(Class<?> face : c.getInterfaces()){
			System.out.println(face);
		}
		
		Class<?> up = c.getSuperclass();
		Object obj = null;
		
		try {
			obj = up.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			System.out.println("can not instantite");
			System.exit(1);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("can not access");
			System.exit(1);
		}
		System.out.println(obj.getClass());
		
		Class<? extends Number> c1 = int.class;
		c1 = double.class;
		System.out.println(c1.getName());
	}
}

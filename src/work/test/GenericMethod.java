package work.test;
/** 
 * @TODO
 * @author yicha
 * @date   2015年9月26日 
 */
public class GenericMethod {

	public <T> T  show(T t){
		return t;
	}
	
	public <T> void print(T t){
		System.out.println(t);
	}
	
	public static void main(String[] args) {
		GenericMethod gm = new GenericMethod();
		System.out.println(gm.show("hello").toString());
		System.out.println(gm.show(5).toString());
	}
}

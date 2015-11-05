package work.test;
/** 
 * @TODO
 * @author yicha
 * @date   2015年11月2日 
 */
public class ClassPathDemo {

	public void getPath(){
		String path = this.getClass().getResource("/").getPath();
		System.out.println(path);
	}
	public static void main(String[] args) {
		ClassPathDemo demo = new ClassPathDemo();
		demo.getPath();
	}
}

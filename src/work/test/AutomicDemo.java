package work.test;

import java.util.concurrent.atomic.AtomicLong;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月30日 
 */
public class AutomicDemo {

	private final AtomicLong count = new AtomicLong(0);
	
	public long getLong(){
		return count.incrementAndGet();
	}
	
	public static void main(String[] args) {
		AutomicDemo demo = new AutomicDemo();
		for(int i = 0; i < 100; i ++){
			System.out.println(demo.getLong());
		}
	}
}

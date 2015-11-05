package work.concurrent;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月14日 
 */
public class TestJoin implements Runnable{

	private String name;
	
	public TestJoin(String name){
		this.name = name;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("%s finished:%s\n", name, new Date());
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new TestJoin("one"));
		Thread t2 = new Thread(new TestJoin("two"));
		t1.start();
		t2.start();
		
		/**
		 * 如果没有join方法，返回的结果为：
		 * 			main thread finished
		 *		    one finished:Wed Oct 14 17:25:06 CST 2015
		 *			two finished:Wed Oct 14 17:25:06 CST 2015
		 *
		 * 有join方法后的返回结果：
		 *          one finished:Wed Oct 14 17:26:43 CST 2015
		 *			two finished:Wed Oct 14 17:26:43 CST 2015
		 *			main thread finished
		 *
		 *join waits for this thread die;
		 */
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 10; i ++){
			System.out.println(i);
		}
		
		System.out.println("main thread finished");
	}
}

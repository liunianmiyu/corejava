package work.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月14日 
 */
public class TestThreadPoll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//ExecutorService service = Executors.newCachedThreadPool();
		ExecutorService service = Executors.newFixedThreadPool(5);
		//ExecutorService service = Executors.newSingleThreadExecutor();
		
		for(int i = 0 ; i < 10 ; i ++){
			service.execute(new TestRunnable());
		}
	}

}

class TestRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + "被调用了");
	}
	
}

package work.concurrent;

import java.util.concurrent.CountDownLatch;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月15日 
 */
public class ThreadSecure2 {

	public static void test(){
		Counter counter = new Counter();
		int threadCount = 1000;
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		
		for(int i = 0; i < threadCount; i ++){
			Thread thread = new Thread(new MyRunnable3(counter, countDownLatch));
			thread.start();
		}
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(counter.getCount());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i = 0; i < 10; i ++){
			test();
		}
	}

}
class MyRunnable3 implements Runnable{

	private Counter counter;
	private CountDownLatch countDownLatch;
	
	public MyRunnable3(Counter counter, CountDownLatch countDownLatch){
		this.counter = counter;
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 100; i ++){
			counter.addCount();
		}
		countDownLatch.countDown();
	}
	
}

class Counter {
	private int count = 0;
	
	public int getCount(){
		return count;
	}
	
	public synchronized void addCount(){
		count ++;
	}
}
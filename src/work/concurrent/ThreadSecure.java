package work.concurrent;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月15日 
 */
public class ThreadSecure {

	public static void test(){
		//用来测试的list
		List<Object> list = new Vector<Object>();
		//启动的线程数量
		int threadCount = 1000;
		//用来让主线程等待threadCount个线程执行完毕
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		
		for(int i = 0; i < threadCount; i ++){
			Thread thread = new Thread(new MyRunnable2(list, countDownLatch));
			thread.start();
		}
		
		//主线程等待所有的子线程执行完毕
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(list.size());
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(int i = 0; i < 10; i ++){
			test();
		}
	}

}

class MyRunnable2 implements Runnable{

	private List<Object> list;
	private CountDownLatch countDownLatch;
	
	public MyRunnable2(List<Object> list, CountDownLatch countDownLatch){
		this.countDownLatch = countDownLatch;
		this.list = list;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 100; i ++){
			list.add(new Object());
		}
		countDownLatch.countDown();
	}
	
}

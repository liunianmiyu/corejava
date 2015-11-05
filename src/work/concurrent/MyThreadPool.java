package work.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 
 * @TODO   自定义的线程池
 * @author yicha
 * @date   2015年10月14日 
 */
public class MyThreadPool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//任务等待队列
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(20);
		//自定义线程池
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 50, TimeUnit.SECONDS, workQueue);
		
		MyRunnable r1 = new MyRunnable();
		MyRunnable r2 = new MyRunnable();
		MyRunnable r3 = new MyRunnable();
		MyRunnable r4 = new MyRunnable();
		MyRunnable r5 = new MyRunnable();
		MyRunnable r6 = new MyRunnable();
		MyRunnable r7 = new MyRunnable();
		
		pool.execute(r1);
		pool.execute(r2);
		pool.execute(r3);
		pool.execute(r4);
		pool.execute(r5);
		pool.execute(r6);
		pool.execute(r7);
		
		//关闭线程池
		pool.shutdown();
	}

}

class MyRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + "任务正在执行......");
	}
	
}
package work.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月16日 
 */
public class ReentrantLockTest extends Thread{

	private MyReentrantLock myReentrantLock;
	private int id;
	
	public ReentrantLockTest(int id, MyReentrantLock lock){
		this.id = id;
		this.myReentrantLock = lock;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		myReentrantLock.print(id);
	}
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		MyReentrantLock lock = new MyReentrantLock();
		for(int i = 0; i < 10; i ++){
			service.execute(new ReentrantLockTest(i, lock));
		}
		service.shutdown();
	}
}
class MyReentrantLock {
	private ReentrantLock reentrantLock = new ReentrantLock();
	
	public void print(int id){
		try{
			reentrantLock.lock();
			System.out.println(id + "获得锁！");
			Thread.sleep((long) (Math.random()*1000));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println(id + "释放锁！");
			reentrantLock.unlock();
		}
	}
}
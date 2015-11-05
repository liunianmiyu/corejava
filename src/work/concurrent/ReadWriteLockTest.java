package work.concurrent;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月15日 
 */
public class ReadWriteLockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Data data = new Data();
		for(int i = 0; i < 3; i ++){
			new Thread(){
				public void run() {
					data.set(new Random().nextInt(30));
				};
			}.start();
		}
		
		for(int i = 0; i < 3; i ++){
			new Thread(){
				public void run() {
					data.get();
				};
			}.start();
		}
	}

}

class Data{
	private int data;//共享的数据
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void set(int i){
		lock.writeLock().lock();
		
		try{
			System.out.println(Thread.currentThread().getName() + "开始写入数据...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.data = i;
			System.out.println(Thread.currentThread().getName() + "写入" + this.data);
		}finally{
			lock.writeLock().unlock();//释放写锁
		}
	}
	
	public void get(){
		lock.readLock().lock();
		try{
			System.out.println(Thread.currentThread().getName() + "准备读取数据...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "读取数据" + this.data);
		}finally{
			lock.readLock().unlock();
		}
	}
}
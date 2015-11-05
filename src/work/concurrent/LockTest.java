package work.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月15日 
 */
public class LockTest {

	public static void main(String[] args) {
		final Output1 output = new Output1();
		
		new Thread(){
			public void run() {
				output.output("zhangsan");
			};
		}.start();
		
		new Thread(){
			public void run() {
				output.output("lisi");
			};
		}.start();
	}
}

class Output1{
	private Lock lock = new ReentrantLock();
	
	public void output(String name){
		lock.lock();
		try{
			for(int i = 0; i < name.length(); i ++){
				System.out.print(name.charAt(i));
			}
		}finally{
			lock.unlock();
		}
	}
}
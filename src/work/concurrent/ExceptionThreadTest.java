package work.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月14日 
 */
public class ExceptionThreadTest implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Runnable接口中run方法抛出的异常！");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			ExecutorService service = Executors.newCachedThreadPool();
			service.execute(new ExceptionThreadTest());
			System.out.println("hello");
		}catch(Exception e){
			System.out.println("能否捕获到该异常！");
		}
		
	}

}

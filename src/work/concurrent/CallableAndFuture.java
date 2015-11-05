package work.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月14日 
 */
public class CallableAndFuture {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * FutureTask 实现了Runnable 和  Future 接口
		 */
//		Callable<Integer> callable = new Callable<Integer>() {
//
//			@Override
//			public Integer call() throws Exception {
//				// TODO Auto-generated method stub
//				return new Random().nextInt(100);
//			}
//		};
//		
//		FutureTask<Integer> future = new FutureTask<Integer>(callable);
//		new Thread(future).start();
//		
//		try {
//			Thread.sleep(5000);
//			int n = future.get();
//			System.out.println(n);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ExecutorService service = Executors.newCachedThreadPool();
		Future<Integer> future = service.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				return new Random().nextInt(100);
			}
		});
		
		try {
			Thread.sleep(5000);
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package work.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



/** 
 * @TODO
 * @author yicha
 * @date   2015年10月14日 
 */
public class CallableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExecutorService service = Executors.newCachedThreadPool();
	
		List<Future<String>> listFuture = new ArrayList<Future<String>>();
		
		for(int i = 0; i < 5; i ++){
			Future<String> future = service.submit(new TaskWithResult(i));
			listFuture.add(future);
		}
		
		//遍历任务结果
		for(Future<String> future : listFuture){
			try {
				while(!future.isDone());
				System.out.println(future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				service.shutdown();
			}
		}
	}

}
class TaskWithResult implements Callable<String>{

	private int id;
	
	public TaskWithResult(int id){
		this.id = id;
	}
	
	/**
	 * 任务的具体过程，一旦将任务传递给ExecutorService的submit()方法
	 * 则方法自动在一个线程上运行
	 */
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("call 方法被调用" + Thread.currentThread().getName());
		return "call方法被自动调用，返回结果" + id;
	}
	
}
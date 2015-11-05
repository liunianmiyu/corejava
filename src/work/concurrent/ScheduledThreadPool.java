package work.concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月14日 
 */
public class ScheduledThreadPool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		
		//5秒后执行任务
		service.schedule(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("boom!");
			}
		}, 5, TimeUnit.SECONDS);
		
		//5秒后执行任务，以后每个2秒执行任务
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("boom,boom,boom!");
			}
		}, 5, 2, TimeUnit.SECONDS);
	}

}

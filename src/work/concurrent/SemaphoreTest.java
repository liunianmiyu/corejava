package work.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月16日 
 */
public class SemaphoreTest extends Thread{

	private Semaphore position;
	private int id;
	
	public SemaphoreTest(int i, Semaphore s){
		this.position = s;
		this.id = i;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			if(position.availablePermits() > 0){
				System.out.println(id + "进入，有空位！");
			}else{
				System.out.println(id + "进入，没有空位，等待！");
			}
			position.acquire();
			System.out.println(id + "获得空位！");
			Thread.sleep(3000);
			position.release();
			System.out.println(id + "释放空位！");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ExecutorService service = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);
        for(int i = 0; i < 10; i ++){
        	service.execute(new SemaphoreTest(i + 1, semaphore));
        }
        service.shutdown();
        semaphore.acquireUninterruptibly(3);
        System.out.println("使用完毕！");
        semaphore.release(3);
	}

}

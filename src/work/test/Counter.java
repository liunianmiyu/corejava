package work.test;
/** 
 * @TODO
 * @author yicha
 * @date   2015年9月26日 
 */
public class Counter {

	long count;
	
	public synchronized void add(){
		count ++;
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + "-----" + count);
	}
}

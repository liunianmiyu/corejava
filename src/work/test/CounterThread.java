package work.test;
/** 
 * @TODO
 * @author yicha
 * @date   2015年9月26日 
 */
public class CounterThread extends Thread{

	protected Counter counter;
	
	public CounterThread(Counter counter){
		this.counter = counter;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 5; i ++){
			counter.add();
		}
	}
	
	public static void main(String[] args) {
		Counter counter = new Counter();
		CounterThread t1 = new CounterThread(counter);
		CounterThread t2 = new CounterThread(counter);
		t1.start();
		t2.start();
	}
}

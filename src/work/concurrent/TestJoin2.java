package work.concurrent;
/** 
 * @TODO
 * @author yicha
 * @date   2015年10月14日 
 */
public class TestJoin2 implements Runnable {

	private static int count = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 5; i ++){
			count = count + 1;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Runnable r = new TestJoin2();
		Thread t = new Thread(r);
		t.start();
		t.join();
		System.out.println(count);
	}

}

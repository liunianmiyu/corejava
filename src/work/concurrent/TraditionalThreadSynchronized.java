package work.concurrent;
/** 
 * @TODO
 * @author yicha
 * @date   2015年10月15日 
 */
public class TraditionalThreadSynchronized {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Outputter outputter = new Outputter();
		
		new Thread(){
			public void run() {
				outputter.output("zhangsan");
			};
		}.start();
		
		new Thread(){
			public void run() {
				outputter.output("lisi");
			};
		}.start();
		
	}

}

class Outputter{
	public void output(String name){
		//TODO 为了保证对name的输出不是原子性操作，逐个字符输出
		synchronized (this) {
			for(int i = 0; i < name.length(); i ++){
				System.out.print(name.charAt(i));
			}
		}
	}
}

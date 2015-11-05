package thingk.in.java.ch16;

import java.util.ArrayList;
import java.util.Random;

/** 
 * @TODO
 * @author yicha
 * @date   2015年11月2日 
 */
public class RandomList<T> {
	
	private ArrayList<T> storage = new ArrayList<T>();
	
	private Random random = new Random(47);
	
	public void add(T item){
		storage.add(item);
	}
	
	public T get(){
		return storage.get(random.nextInt(storage.size()));
	}

	public static void main(String[] args) {
//		RandomList<String> rls = new RandomList<String>();
//		
//		for(String s : "hello world ni hao".split(" ")){
//			rls.add(s);
//		}
//		
//		for(int i = 0; i < 10; i ++){
//			System.out.println(rls.get());
//		}
		
		int k = 1;
		//按位异或  0001 ^ 0011
		//      0^0=0 0^0=0 0^1=1 1^1=0   0010=2 
		k ^= 3;
		System.out.println(k);
	}
}

package thingk.in.java.ch15;

import java.util.ArrayList;
import java.util.List;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月20日 
 */
class CountedInteger{
	private static int count;
	private final int id = count ++;
	public CountedInteger(){
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Long.toString(id);
	}
}


public class FilledList<T> {

	private Class<T> type;
	
	public FilledList(Class<T> type){
		this.type = type;
	}
	
	public List<T> createList(int nElements){
		List<T> list = new ArrayList<T>();
		for(int i = 0; i < nElements; i ++){
			try {
				list.add(type.newInstance());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		FilledList<CountedInteger> list = new FilledList<CountedInteger>(CountedInteger.class);
		System.out.println(list.createList(15));
	}
}

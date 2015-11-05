package thingk.in.java.ch16;
/** 
 * @TODO   泛型  栈的实现
 * @author yicha
 * @date   2015年11月2日 
 */
public class LinkedStack<T> {

	private static class Node<U>{
		U item;
		Node<U> next;
		
		Node(){
			this.item = null;
			this.next = null;
		}
		
		Node(U item, Node<U> next){
			this.item = item;
			this.next = next;
		}
		
		boolean end(){
			return item == null && next == null;
		}
	}
	
	private Node<T> top = new Node<T>();
	
	/**
	 * 入栈
	 * @param item
	 */
	public void push(T item){
		top = new Node<T>(item, top);
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public T pop(){
		T result = top.item;
		if(! top.end()){
			top = top.next;
		}
		return result;
	}
	
	public static void main(String[] args) {
		LinkedStack<String> lss = new LinkedStack<String>();
		
		for(String s : "hello world".split(" ")){
			lss.push(s);
		}
		
		String result;
		while((result = lss.pop()) != null){
			System.out.println(result);
		}
	}
}

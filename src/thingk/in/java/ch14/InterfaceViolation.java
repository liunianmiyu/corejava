package thingk.in.java.ch14;

interface A{
	void h();
}

class B implements A{

	@Override
	public void h() {
		// TODO Auto-generated method stub
		System.out.println("class B method h()");
	}
	
	public void g(){
		System.out.println("class B method g()");
	}
	
}

class ParamClass<T>{
	private T c;
	public ParamClass(T c){
		this.c = c;
	}
	public T get(){
		return c;
	}
}
/** 
 * @TODO
 * @author yicha
 * @date   2015年10月20日 
 */
public class InterfaceViolation {

	public static void main(String[] args) {
		A a = new B();
		a.h();
		//a.g();
		if(a instanceof B){
			((B) a).g();
		}
		
		ParamClass<A> p = new ParamClass<A>(new B());
		
		B b = (B)p.get();
		b.h();
	}
}

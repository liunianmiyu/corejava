package thingk.in.java.ch14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface Interface{
	void dosomething();
	void dosomethingElse(String arg);
}

class RealObject implements Interface{

	@Override
	public void dosomething() {
		// TODO Auto-generated method stub
		System.out.println("dosomething");
	}

	@Override
	public void dosomethingElse(String arg) {
		// TODO Auto-generated method stub
		System.out.println("somethingElse" + arg);
	}
	
}

class DynamicProxyHandler implements InvocationHandler{

	private Object proxied;
	
	public DynamicProxyHandler(Object proxied){
		this.proxied = proxied;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("***proxy:" + proxy.getClass() + "***method:" + method + "***args:" + args);
		if(args != null){
			for(Object o : args){
				System.out.println(" " + o);
			}
		}
		return method.invoke(proxied, args);
	}
	
}
/** 
 * @TODO
 * @author yicha
 * @date   2015年10月20日 
 */
public class SimpleProxyDemo {

	public static void consumer(Interface iface){
		iface.dosomething();
		iface.dosomethingElse("hello");
	}
	
	public static void main(String[] args) {
		RealObject ro = new RealObject();
		consumer(ro);
		
		Interface iface = (Interface) Proxy.newProxyInstance(Interface.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(ro));
		consumer(iface);
	}
}

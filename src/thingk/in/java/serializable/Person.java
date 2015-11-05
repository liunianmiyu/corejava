package thingk.in.java.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月19日 
 */
public class Person implements Serializable {

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = -6445137710929704442L;

	private static class InstanceHolder{
		private static final Person instance = new Person("zhangcc", 25, Gender.FEMAIL);
	}
	
	private String name = null;
	transient private Integer age = null;
	private Gender gender = null;
	
	public Person(){
		
	}
	
	public Person(String name, Integer age, Gender gender){
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	/**
	 * 获取单例对象
	 * @return
	 */
	public static Person getInstance(){
		return InstanceHolder.instance;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.defaultWriteObject();
		out.writeInt(age);
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		in.defaultReadObject();
		age = in.readInt();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + name + ", " + age + ", " + gender + "]";
	}

//	@Override
//	public void writeExternal(ObjectOutput out) throws IOException {
//		// TODO Auto-generated method stub
//		out.writeObject(name);
//		out.writeInt(age);
//	}
//
//	@Override
//	public void readExternal(ObjectInput in) throws IOException,
//			ClassNotFoundException {
//		// TODO Auto-generated method stub
//		name = (String)in.readObject();
//		age = in.readInt();
//	}
	
	
	private Object readResolve(){
		return InstanceHolder.instance;
	}
}

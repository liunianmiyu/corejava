package thingk.in.java.serializable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月19日 
 */
public class SimpleSerial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File  file = new File("person.out");
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			
			//Person p = new Person("zhang", 24, Gender.MALE);
			Person p = Person.getInstance();
			out.writeObject(p);
			out.close();
			
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			try {
				Object obj = in.readObject();
				System.out.println(obj);
				System.out.println(p.equals(obj));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				in.close();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

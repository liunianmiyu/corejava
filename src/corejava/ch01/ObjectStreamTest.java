package corejava.ch01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectStreamTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		Employee harry = new Employee("zhangcc", 8000, 1991, 2, 20) ;
		Manager hacker = new Manager("Hacker", 5000, 1990, 5, 3) ;
		hacker.setSecretary(harry) ;
		Manager tony = new Manager("Tony", 9000, 1988, 2, 24) ;
		tony.setSecretary(harry);
		
		Employee[] staff = new Employee[3] ;
		staff[0] = harry ;
		staff[1] = hacker ;
		staff[2] = tony ;
		
		//将所有对象保存到文件employee.dat中
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat"))){
			out.writeObject(staff) ;
		}
		
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat"))){
			//将所有记录恢复到一个新的数组中
			Employee[] newStaff = (Employee[]) in.readObject() ;
			
			newStaff[0].raiseSalary(10) ;
			
			for(Employee e : newStaff){
				System.out.println(e.toString());
			}
		}
	}

}

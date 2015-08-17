package corejava.ch01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class StreamDemo {

	/**
	 * 1、抽象类 InputStream 和 OutputStream 构成了输入输出类层次结构的基础（面向字节的流）
	 * 2、因为面向字节的流不便于处理unicode字符，所以从抽象类Writer 和 Reader 中继承出来一个
	 *   专门处理unicode字符的类层次结构；
	 * @param args
	 */
	
	private void readNumFromFile(){
		try {
			FileInputStream fis = new FileInputStream(new File("E:\\test.txt"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String bfline = br.readLine();
			System.out.println(bfline);
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StreamDemo().readNumFromFile();
	}

}

package work.test;

import java.io.IOException;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月28日 
 */
public class ProgressBuilderDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ProcessBuilder pb = new ProcessBuilder("notepad.exe","testpb");
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

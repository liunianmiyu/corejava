package corejava.ch01;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * @TODO   输入一个正则表达式和字符串去匹配或者输入cancel终止。如果正则中包含群组，
 *         那么群组的边界也将在匹配结果中输出。
 * @author yicha
 * @date   2015年8月19日 
 */
public class RegexTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in) ;
		System.out.println("Enter pattern:");
		String patternString = sc.nextLine() ;
		
		Pattern pattern = Pattern.compile(patternString) ;
		
		while(true){
			System.out.println("Enter string to match:");
			String input = sc.nextLine() ;
			
			if(input == null || input.equals("cancel")) return ;
			
			Matcher matcher = pattern.matcher(input) ;
			
			if(matcher.matches()){
				System.out.println("Match!");
				int groupNum = matcher.groupCount() ;
				if(groupNum > 0){
					for (int i = 0; i < input.length(); i++) {
						//输出空的群组
						for(int j = 1 ; j < groupNum ; j ++ ){
							if(i == matcher.start(j) && i == matcher.end(j)){
								System.out.print("()");
							}
						}
						//输出  (
						for(int j = 1 ; j < groupNum ; j ++){
							if(i == matcher.start(j) && i != matcher.end(j)){
								System.out.print("(");
							}
						}
						//输出字符
						System.out.print(input.charAt(i));
						//输出 )
						for(int j = 1 ; j < groupNum ; j ++){
							if(i+1 != matcher.start(j) && i + 1 == matcher.end(j)){
								System.out.print(")");
							}
						}
						
					}
					System.out.println();
				}else{
					System.out.println("Not match!");
				}
			}
		}
	}

}

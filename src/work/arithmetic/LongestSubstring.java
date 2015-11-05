package work.arithmetic;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月16日 
 */
public class LongestSubstring {
	
	public static int getLongestSubstring(String str){
		int res = 0;
		int left = 0;
		int[] space = new int[300];
		
		for(int i = 0; i < 300; i ++){
			space[i] = -1;
		}
		
		for(int i = 0; i < str.length(); i ++){
			if(space[str.charAt(i)] >= left){
				left = space[str.charAt(i)] + 1;
			}
			space[str.charAt(i)] = i;
			if(res < i - left + 1){
				res = i - left + 1;
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		String str = "aabcdedsa";
		System.out.println(getLongestSubstring(str));
	}
}

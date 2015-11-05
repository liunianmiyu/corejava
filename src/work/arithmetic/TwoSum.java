package work.arithmetic;

import java.util.Arrays;

/** 
 * @TODO
 * @author yicha
 * @date   2015年10月16日 
 */
public class TwoSum {

	static class Pairs implements Comparable<Pairs>{

		private int value;
		private int index;
		
		public Pairs(int value, int index){
			this.value = value;
			this.index = index;
		}
		@Override
		public int compareTo(Pairs o) {
			// TODO Auto-generated method stub
			return this.value - o.value;
		}
		
	}
	
	public static int[] twoSum(int[] numbers, int target){
		
		int[] res = new int[2];
		Pairs[] pairs = new Pairs[numbers.length];
		
		//get pairs and sort
		for(int i = 0; i < numbers.length; i ++){
			pairs[i] = new Pairs(numbers[i], i + 1);
		}
		
		Arrays.sort(pairs);
		
		int left = 0;
		int right = numbers.length - 1;
		int sum = 0;
		while(left < right){
			sum = numbers[left] + numbers[right];
			if(sum == target){
				res[0] = pairs[left].index;
				res[1] = pairs[right].index;
				
				if(res[0] > res[1]){
					int swap = res[0];
					res[0] = res[1];
					res[1] = swap; 
				}
				break;
			}else if(sum < target){
				++ left;
			}else if(sum > target){
				-- right;
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		int[] numbers = {1,2,4,6,8};
		int target = 5;
		
		int[] res = twoSum(numbers, target);
		
		for(int num : numbers){
			System.out.print(num + " ");
		}
		System.out.println();
		for(int r : res){
			System.out.print(r + " ");
		}
	}
}

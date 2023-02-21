package SWEA;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_1966_숫자를정렬하자 {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case<=T; test_case++) {
			
			int N = sc.nextInt();
			int[] nums = new int[N];
			for(int i=0; i<N; i++) nums[i] = sc.nextInt();
			
			Arrays.sort(nums);
			
			System.out.print("#" + test_case + " ");
			for(int num : nums) System.out.print(num + " ");
			System.out.println();
		}
		
	}
}

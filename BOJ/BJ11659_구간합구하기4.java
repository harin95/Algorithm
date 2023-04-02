package BOJ;

import java.io.*;

public class BJ11659_구간합구하기4 {
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int[] nums = new int[n+1];
		
		input = br.readLine().split(" ");
		
		for(int i=1; i<=n; i++) nums[i] = nums[i-1] + Integer.parseInt(input[i-1]);
		
		for(int i=0; i<m; i++) {
			input = br.readLine().split(" ");
			int from = Integer.parseInt(input[0]);
			int to = Integer.parseInt(input[1]);
			
			sb.append(nums[to] - nums[from-1]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}

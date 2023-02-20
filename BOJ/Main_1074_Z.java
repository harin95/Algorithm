package BOJ;

import java.util.*;
import java.io.*;

public class Main_1074_Z {

	static int n, r, c;
	static int i, j;
	static int[][] grid;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int len = (int) Math.pow(2, n);
		grid = new int[len][len];
		
		divide(len);
		
	}
	
	public static void divide(int len) {
		
		int mid = len/2;
		
		if(mid == 2) {
			
		}
		
		
		if(r < mid && c < mid) {	//1사분면
			
		}
		else if(r < mid && c >= mid) {	//2사분면
			
		}
		else if(r >= mid && c < mid) {	//4사분면
		
		}
		else {	//3사분면
			
		}
	}
	
	
	

}

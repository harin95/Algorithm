package BOJ;

import java.util.*;
import java.io.*;

public class Main_1074_Z {
	static int n, r, c;;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		divide(n, 0, 0, n*n, 0, n*n, 0);
	}
	
	public static void divide(int size, int base, int rstart, int rend, int cstart, int cend, int squareNum) {
		int rmid = (rstart+rend)/2;
		int cmid = (cstart+cend)/2;
		int square = squareNum;

		if(size == 0){
			System.out.println(base + (int)Math.pow(size-1, 2)*(square-1));
			return;
		}

		if(r < rmid && c < cmid){
			square = 1;
			int next_base = base + (int)Math.pow(size-1, 2)*(square-1);
			divide(size-1, next_base, rstart, rmid, cstart, cmid, square);
		}
		else if(r < rmid && c >= cmid){
			square = 2;
			int next_base = base + (int)Math.pow(size-1, 2)*(square-1);
			divide(size-1, next_base, rstart, rmid, cmid, cend, square);
		}
		else if(r >= rmid && c < cmid){
			square = 3;
			int next_base = base + (int)Math.pow(size-1, 2)*(square-1);
			divide(size-1, next_base, rmid, rend, cstart, cmid, square);
		}
		else if(r >= rmid && c >= cmid){
			square = 4;
			int next_base = base + (int)Math.pow(size-1, 2)*(square-1);
			divide(size-1, next_base, rmid, rend, cmid, cend, square);
		}

	}
	
	
	

}

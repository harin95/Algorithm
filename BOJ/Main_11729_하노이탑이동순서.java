package BOJ;

import java.util.Scanner;

public class Main_11729_하노이탑이동순서 {
	
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		System.out.println((int)Math.pow(2, n ) - 1);
		hanoi(n, 1, 2, 3);
		
		System.out.println(sb.toString());
		
		sc.close();
	}
	
	public static void hanoi(int n, int from, int mid, int to) {
		if(n == 1) {
			sb.append(from).append(" ").append(to).append("\n");
		}
		else {
			hanoi(n-1, from, to, mid);
			sb.append(from).append(" ").append(to).append("\n");
			hanoi(n-1, mid, from, to);
		}
	}
}

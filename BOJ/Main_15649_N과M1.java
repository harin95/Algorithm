package BOJ;

import java.util.Scanner;

public class Main_15649_N과M1 {

	static int N, M;
	static int[] numbers;
	static boolean[] isSelected;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();

		numbers = new int[M];
		isSelected = new boolean[N+1];
		
		permutation(0);
		
		sc.close();
	}
	
	
	
	public static void permutation(int cnt){
		
		if(cnt == M) {
			for(int i : numbers) System.out.print(i + " ");
			System.out.println();
			return;
		}
	
		for(int i=1; i<=N; i++) {
			if(isSelected[i]) continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}

}

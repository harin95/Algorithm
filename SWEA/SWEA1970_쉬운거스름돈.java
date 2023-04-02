package SWEA;

import java.util.Scanner;

public class SWEA1970_쉬운거스름돈 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test_case = 1; test_case<=T; test_case++) {
			
			int change = sc.nextInt();
			
			System.out.println("#" + test_case);
			System.out.print(change/50000 + " ");
			change %= 50000;
			System.out.print(change/10000 + " ");
			change %= 10000;
			System.out.print(change/5000 + " ");
			change %= 5000;
			System.out.print(change/1000 + " ");
			change %= 1000;
			System.out.print(change/500 + " ");
			change %= 500;
			System.out.print(change/100 + " ");
			change %= 100;
			System.out.print(change/50 + " ");
			change %= 50;
			System.out.print(change/10 + " ");
			System.out.println();
		}
	}

}

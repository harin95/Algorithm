package SWEA;

import java.util.*;

public class Solution_1946_간단한압축풀기 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			int N = sc.nextInt();
			sc.nextLine();
			
			String[][] print = new String[N][10]; 
			int row = 0, col = 0;
			
			for(int i=0; i<N; i++) {
				
				String[] input = sc.nextLine().split(" ");
				String letter = input[0];
				int num = Integer.parseInt(input[1]);
				
				for(int j=0; j<num; j++) {
					print[row][col++] = letter;
					if(col== 10) {
						row++;
						col = 0;
					}
				}
			}
			for(String[] strArr : print) {
				for(String str : strArr) {
					if(str != null) System.out.print(str);
				}
				System.out.println();
			}
			row = 0; col = 0;
		}
	}

}
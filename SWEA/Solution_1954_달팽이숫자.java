package SWEA;

import java.util.Scanner;

public class Solution_1954_달팽이숫자 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			int n = sc.nextInt();
			
			int[][] snail = new int[n][n];
			
			int dir = 1;
			int no = 1;
			
			int row = 0;
			int col = -1;
			
			int right = n-1, bottom = n-1;
			int left = 0; int top = 1;
			
			for(int i=0; i<n; i++) {
				
				if(dir == 1) {
					while(col < right) {
						snail[row][++col] = no++;
					}
					
					while(row < bottom) {
						snail[++row][col] = no++;
					}
					right--; bottom--;
					
					dir *= -1;
				}
				
				else {
					while(col > left) {
						snail[row][--col] = no++;
					}
					
					while(row > top) {
						snail[--row][col] = no++;
					}
					left++; top++;
					dir *= -1;
				}
			}
			
			System.out.println("#"+test_case);
			for(int[] arr : snail) {
				for(int num : arr) {
					System.out.print(num + " ");
				}
				System.out.println();
			}
		}
	}
}

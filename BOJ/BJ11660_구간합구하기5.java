package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11660_구간합구하기5 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);		//표의 크기
		int m = Integer.parseInt(input[1]);		//구해야하는 합 개수
		
		int[][] grid = new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			input = br.readLine().split(" ");
			for(int j=1; j<=n; j++) {
				grid[i][j] = grid[i][j-1] + Integer.parseInt(input[j-1]);
			}
		}
		
		for(int i=0; i<m; i++) {
			input = br.readLine().split(" ");
			
			int x1 = Integer.parseInt(input[0]);
			int y1 = Integer.parseInt(input[1]);
			int x2 = Integer.parseInt(input[2]);
			int y2 = Integer.parseInt(input[3]);
			
			int sum = 0;
			
			for(int x=x1; x<=x2; x++) {
				sum += grid[x][y2] - grid[x][y1-1];
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}
}

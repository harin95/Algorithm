package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ16935_배열돌리기3 {
	
	static int r;
	static int[][] numbers; 

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		r = Integer.parseInt(input[2]);
		
		numbers = new int[n][];

		int[] operators = new int[r];
		
		//배열 받기
		for(int i=0; i<n; i++) {
			input = br.readLine().split(" ");
			numbers[i] = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
		}
		//연산자들 배열로 저장
		operators = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		//입력 끝
		
		for(int i=0; i<r; i++) {
			switch(operators[i]) {
				case 1 :
					operate1();
					break;
					
				case 2 :
					operate2();
					break;
				
				case 3 :
					operate3();
					break;
					
				case 4 :
					operate4();
					break;
				
				case 5 :
					operate5();
					break;
					
				case 6 :
					operate6();
					break;
			}
		}
		sbPrint();
		
	}
	
	static void operate1() {	//상하반전
		
		int n = numbers.length;
		int m = numbers[0].length;
		
		int[][] copy = new int[n][m];
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				copy[i][j] = numbers[n-1-i][j];
			}
		}
		numbers = copy;
	}
	
	static void operate2() {	//좌우반전
		int n = numbers.length;
		int m = numbers[0].length;
		
		int[][] copy = new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				copy[i][j] = numbers[i][m-1-j];
			}
		}
		numbers = copy;
	}
	
	static void operate3() {	//오른쪽으로 90도 회전
		int n = numbers.length;
		int m = numbers[0].length;
		
		int[][] copy = new int[m][n];
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				copy[i][j] = numbers[n-1-j][i];
			}
		}
		numbers = copy;
	}
	
	
	static void operate4() {	//왼쪽으로 90도 회전
		int n = numbers.length;
		int m = numbers[0].length;
		
		int[][] copy = new int[m][n];
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				copy[i][j] = numbers[j][m-1-i];
			}
		}
		numbers = copy;
	}
	
	static void operate5() {
		int n = numbers.length;
		int m = numbers[0].length;
		
		int[][] copy = new int[n][m];
		
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<m/2; j++) {
				copy[i][j] = numbers[i+n/2][j];		// 4 -> 1
				copy[i][j+m/2] = numbers[i][j];	// 1 -> 2
				copy[i+n/2][j+m/2] = numbers[i][j+m/2];	// 2 -> 3
				copy[i+n/2][j] = numbers[i+n/2][j+m/2]; // 3 -> 4
			}

		}
		numbers = copy;
	}
	
	
	static void operate6() {
		int n = numbers.length;
		int m = numbers[0].length;
		
		int[][] copy = new int[n][m];
		
		for(int i=0; i<n/2; i++) {
			for(int j=0; j<m/2; j++) {
				copy[i][j] = numbers[i][j+m/2];	// 2 -> 1
				copy[i+n/2][j] = numbers[i][j];	// 1 -> 4
				copy[i+n/2][j+m/2] = numbers[i+n/2][j];	// 4 -> 3
				copy[i][j+m/2] = numbers[i+n/2][j+m/2];	// 3 -> 2
			}
		}
		numbers = copy;
	}
	
	static void sbPrint() {
		StringBuilder sb = new StringBuilder();
		for(int[] arr : numbers) {
			for(int i : arr) {
				sb.append(i + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

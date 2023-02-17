package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16926_배열돌리기1 {

	static int[][] numbers;
	static int cnt = 0;
	static int start_i = 0; static int start_j = 0;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int r = Integer.parseInt(input[2]);
		
		numbers = new int[n][];

		for(int i=0; i<n; i++) {
			input = br.readLine().split(" ");
			numbers[i] = Arrays.stream(input).mapToInt(Integer::parseInt).toArray();
		}//입력 끝
		
		
		//행, 열 원소 옮기는 횟수
		int n_limit = n-1;
		int m_limit = m-1;
		
		
		for(int sq=0; sq<Math.min(m, n)/2; sq++) {
			//네모 하나
			//시작점이 원래 자리로 돌아오는 것은 돌릴 필요 없음
			//나머지 연산으로 회전 횟수 줄이기
			int cycle = (n_limit+1)*2 + (m_limit-1)*2;
			int rotateNum = r%cycle;
			
			for(int i=0; i<rotateNum; i++) {
				rotate(n_limit, m_limit, start_i, start_j);
			}
			
			//안쪽 네모로 들어가기
			//시작점 이동
			start_i++; start_j++; 
			//네모 경계 줄이기 
			n_limit -=2; m_limit-=2;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int[] arr : numbers) {
			for(int num : arr) sb.append(num + " ");
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	
	public static void rotate(int n_limit, int m_limit, int start_row, int start_col) {
		
		int i = start_row; int j = start_col;
		int temp = numbers[i][j];
		int next = 0;
		
		//상 -> 하
		for(int t=0; t<n_limit; t++) {
			next = numbers[i+1][j];
			numbers[i+1][j] = temp;
			temp = next;
			i++;
		} 
		//좌 -> 우
		for(int t=0; t<m_limit; t++) {
			next = numbers[i][j+1];
			numbers[i][j+1] = temp;
			temp = next;
			j++;
		}
		//하 -> 상
		for(int t=0; t<n_limit; t++) {
			next = numbers[i-1][j];
			numbers[i-1][j] = temp;
			temp = next;
			i--;
		}
		//우 -> 좌
		for(int t=0; t<m_limit; t++) {
			next = numbers[i][j-1];
			numbers[i][j-1] = temp;
			temp = next;
			j--;
		}
	}
}

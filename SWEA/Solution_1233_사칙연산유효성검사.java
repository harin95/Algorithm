package SWEA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Solution_1233_사칙연산유효성검사 {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.setIn(new FileInputStream("./input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		for(int test_case=1; test_case<=10; test_case++) {
			
			int answer = 1;
			
			int n = sc.nextInt();
			int temp = 0;
			
			sc.nextLine();
			
			String[] input = sc.nextLine().split(" ");

			for(int i=0; i<n-1; i++) {
				
				input = sc.nextLine().split(" ");
				//단말노드 아님 -> 연산자만 가능
				if(input.length == 4 || input.length == 3) {
					if(input[1].equals("+") ||input[1].equals("-") || 
							input[1].equals("*") || input[1].equals("/")) {
						continue;
					} else {
						answer = 0;
					}
				}
				
				//단말노드 - 숫자만 가능
				if(input.length == 2) {
					int num = 0;
					try {
						num = Integer.parseInt(input[1]);
					} catch (NumberFormatException e) {
						answer = 0;
					}
				}
			}
			
			System.out.println("#" + test_case + " " + answer);
		}
	}
}

package SWEA;

import java.util.*;

public class Solution_1948_날짜계산기 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
			
			int answer = 0;
			
			int month1 = sc.nextInt();
			int day1 = sc.nextInt();
			int month2 = sc.nextInt();
			int day2 = sc.nextInt();
			
			if(month1 == month2) {
				answer = day2 - day1 + 1;
			}
			
			else {		
				if(month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11) {
					answer = 30 - day1 + 1;
				}
				else if(month1 == 2) {
					answer = 28 - day1 + 1;
				}
				else {
					answer = 31 - day1 + 1;
				}
				month1++;
				
				while(month1 < month2) {
					if(month1 == 4 || month1 == 6 || month1 == 9 || month1 == 11) {
						answer += 30;
					}
					else if(month1 == 2) {
						answer += 28;
					}
					else {
						answer += 31;
					}
					month1++;
				}
				answer += day2;
			}
			
			System.out.println("#" + test_case + " " + answer);
		}
	}

}

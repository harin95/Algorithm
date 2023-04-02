package SWEA;

import java.util.*;

public class SWEA4012_요리사 {
	
	static int n,input[];
	static int[] combArrFood1;
	static int[] combArrFood2;
	static boolean[] isSelected;
	static ArrayList<int[]> food1 = new ArrayList<>();
	static ArrayList<int[]> food2 = new ArrayList<>();

	static int[] cal = new int[2];
	static int sum = 0;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int test_case=1; test_case<=T; test_case++) {
		
			n = sc.nextInt();
			int[][] food = new int[n][n];
			input = new int[n];
			combArrFood1 = new int[n/2];
			combArrFood2 = new int[n/2];
			isSelected = new boolean[n];
			
			//다음 테케를 위해 초기화
			Arrays.fill(cal, 0);
			sum = 0;
			food1.clear();
			food2.clear();
			
			for(int i=0; i<n; i++) {
				input[i] = i;
			}
			
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					food[i][j] = sc.nextInt();
				}
			}
			
			//음식 재료를 A, B용 2묶음 으로 나눔
			combination(0, 0, n/2);
			
			//음식 맛 계산
			int[] makeFood1 = new int[] {};
			int[] makeFood2 = new int[] {};
			int a = 0, b = 0, cal = 0, min = Integer.MAX_VALUE;
			
			if(n==4) {
				
				for(int i=0; i<food1.size(); i++) {
					
					makeFood1 = food1.get(i); int f1x = makeFood1[0]; int f1y = makeFood1[1];
					makeFood2 = food2.get(i); int f2x = makeFood2[0]; int f2y = makeFood2[1];
					
					a = food[f1x][f1y] + food[f1y][f1x];
					b = food[f2x][f2y] + food[f2y][f2x];
					
					cal = Math.abs(a-b);
					
					//최소값 갱신
					if(min > cal) min = cal;
				}
			}
/*
 * n이 6 이상일 때
 * n/2중에서 2개를 뽑는 모든 경우의 수를 계산해서 합*/			
			else {
			
				for(int i=0; i<food1.size(); i++) {
					
					makeFood1 = food1.get(i);
					makeFood2 = food2.get(i);
					
					calculateTaste(food, makeFood1, 0, 0);
					a = sum; sum = 0;
					
					calculateTaste(food, makeFood2, 0, 0);
					b = sum; sum = 0;
					
					cal = Math.abs(a-b);
					if(min > cal) min = cal;
				}
			}
			System.out.println("#" + test_case + " " + min);
		}
	}
	
	private static void combination(int cnt, int start, int r) {
		if(cnt == r) {
			food1.add(Arrays.copyOf(combArrFood1, n/2));
			int idx = 0;
			for(int i=0; i<n; i++) {
				if(!isSelected[i] && idx <n/2) combArrFood2[idx++] = i;
			}
			food2.add(Arrays.copyOf(combArrFood2, n/2));
			return;
		}

		for(int i=start; i<n; i++) {
			combArrFood1[cnt] = i;
			isSelected[i] = true;
			combination(cnt+1, i+1, r);
			isSelected[i] = false;
		}
	}
	
	private static void calculateTaste(int[][] food, int[] arr, int cnt, int start) {
		if(cnt == 2) {
			sum += food[cal[0]][cal[1]] + food[cal[1]][cal[0]];
			return;
		}
		
		for(int i=start; i<arr.length; i++) {
			cal[cnt] = arr[i];
			calculateTaste(food, arr, cnt+1, i+1);
		}
	}
}

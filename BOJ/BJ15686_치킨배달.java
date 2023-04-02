package BOJ;

import java.io.*;
import java.util.*;

public class BJ15686_치킨배달 {
	
	static int n, m;
	static ArrayList<int[]> combArr;
	static ArrayList<ArrayList<int[]>> chiceknCombList;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		ArrayList<int[]> chicken = new ArrayList<>();
		ArrayList<int[]> house = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			String[] input = br.readLine().split(" ");
			for(int j=0; j<n; j++) {
				if(Integer.parseInt(input[j]) == 1) house.add(new int[] {i, j});
				else if(Integer.parseInt(input[j]) == 2) chicken.add(new int[] {i, j});
			}
		}
		
		combArr = new ArrayList<>();
		chiceknCombList = new ArrayList<>();
		combination(0, 0, chicken);		//남겨둘 치킨집들의 조합, 이 중에서 치킨거리가 가장 작은 것은 고르면 된다
		
		for(ArrayList<int[]> inner : chiceknCombList) {
			for(int[] arr : inner) {
				System.out.print(Arrays.toString(arr) + " ");
			}
			System.out.println();
		}
		
		//집마다 가까운 치킨집을 골라서 치킨거리에 더한다
		for(int i=0; i<house.size(); i++) {

		}
		
	}
	
	private static void combination(int cnt, int start, ArrayList<int[]> chicken) {
		
		if(cnt == m) {
			chiceknCombList.add(combArr);
			return;
		}
		
		for(int i=start; i<chicken.size(); i++) {
			combArr.add(chicken.get(i));
			combination(cnt+1, i+1, chicken);
		}
	}
	
	private static int calculateDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}
}
